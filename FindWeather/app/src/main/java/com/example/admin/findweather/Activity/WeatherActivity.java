package com.example.admin.findweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.findweather.R;
import com.example.admin.findweather.db.Attention;
import com.example.admin.findweather.db.History;
import com.example.admin.findweather.gson.Data;
import com.example.admin.findweather.gson.Forecast;
import com.example.admin.findweather.gson.Weather;
import com.example.admin.findweather.util.GsonUtil;
import com.example.admin.findweather.util.HttpUtil;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private ScrollView weatherlayout;
    private LinearLayout forecastlayout;

    private Button btn_attention;
    private Button btn_refresh;

    private TextView wea_title;
    private TextView wea_title_updatetime;
    private TextView wea_tem;
    private TextView wea_type;
    private TextView wea_shidu;
    private TextView wea_pm25;

    private String city_code;
    private String city_name;
    private int id;

    private List<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherlayout = (ScrollView)findViewById(R.id.wea_scroll_layout);
        forecastlayout = (LinearLayout)findViewById(R.id.forecast_layout);

        btn_attention = (Button)findViewById(R.id.wea_add_attention);
        btn_refresh = (Button)findViewById(R.id.wea_refresh);

        wea_title = (TextView)findViewById(R.id.wea_title);
        wea_title_updatetime = (TextView)findViewById(R.id.wea_title_updatetime);
        wea_tem = (TextView)findViewById(R.id.wea_tem);
        wea_type = (TextView)findViewById(R.id.wea_type);
        wea_shidu = (TextView)findViewById(R.id.wea_shidu);
        wea_pm25 = (TextView) findViewById(R.id.wea_pm25);

        Intent intent = getIntent();
        city_code = intent.getStringExtra("code");
        city_name = intent.getStringExtra("name");

        boolean exist = false;                                          //判断是否可以从缓存中读取
        histories = LitePal.findAll(History.class);
        History historyExist = new History();
        if (!histories.equals(null)){
            for (History history : histories){
                if (history.getCityNAME() == city_name){
                    exist = true;
                    historyExist = history;
                    break;
                }
            }
        }

        if (!exist){                        //缓存中不存在
            RequestServer();
        }else {
            Weather weather = historyExist.getWeather();
            showWeatherInfo(weather);
        }


        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestServer();
                Toast.makeText(WeatherActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();
            }
        });

        btn_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attention attention = new Attention();
                attention.setAttentionname(city_name);
                Weather weather = new Weather();                    //weather加到Attention数据库
                for (int i = histories.size()-1;i>=0;i--){
                    History h = histories.get(i);
                    if (h.getCityNAME().equals(city_name)){
                        weather = h.getWeather();
                    }
                }
                attention.setWeather(weather);
                attention.setCode(city_code);

                boolean repeat = false;
                List<Attention> attentions = LitePal.findAll(Attention.class);
                for (Attention a : attentions){
                    if (a.getAttentionname().equals(attention.getAttentionname())){
                        repeat = true;
                    }
                }
                if (!repeat)
                    attention.save();

                Toast.makeText(WeatherActivity.this,"关注成功",Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void RequestServer(){
        Weather requestWeather = new Weather();
        HttpUtil.sendOkHttpRequest("http://t.weather.sojson.com/api/weather/city/"+city_code,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseData = response.body().string();
                final Weather weather = GsonUtil.handleWeatherResponse(responseData);

                History history = new History();                //历史记录
                history.setCityNAME(city_name);
                history.setWeather(weather);
                history.save();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showWeatherInfo(weather);
                    }
                });

            }
        });

    }
    public void showWeatherInfo(Weather weather){
        String updatetime = weather.time;
        String tem = weather.data.wendu;
        String pm25 = String.valueOf(weather.data.pm25);
        String shidu = weather.data.shidu;
        String type = weather.data
                .forecast
                .get(0)
                .type;

        wea_title_updatetime.setText(updatetime);
        wea_title.setText(city_name);
        wea_tem.setText(tem);
        wea_type.setText(type);
        wea_pm25.setText(pm25);
        wea_shidu.setText(shidu);

        forecastlayout.removeAllViews();

        for (int i = 1;i < weather.data.forecast.size();i++){
            Forecast forecast1 = weather.data.forecast.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.forest_item,forecastlayout,false);

            TextView wea_date = (TextView)view.findViewById(R.id.wea_date);
            TextView wea_info_for = (TextView)view.findViewById(R.id.wea_info_for);
            TextView wea_high = (TextView)view.findViewById(R.id.wea_high);
            TextView wea_low = (TextView)view.findViewById(R.id.wea_low);

            wea_date.setText(forecast1.ymd);
            wea_info_for.setText(forecast1.type);
            wea_high.setText(forecast1.high);
            wea_low.setText(forecast1.low);

            forecastlayout.addView(view);
        }

    }


}
