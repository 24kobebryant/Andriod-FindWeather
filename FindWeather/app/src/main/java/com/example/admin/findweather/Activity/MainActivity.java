package com.example.admin.findweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.findweather.R;
import com.example.admin.findweather.db.History;
import com.example.admin.findweather.db.province;
import com.example.admin.findweather.db.county;
import com.example.admin.findweather.db.city;
import com.example.admin.findweather.gson.Weather;
import com.example.admin.findweather.gson.jsobject;
import com.example.admin.findweather.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn_search;
    private Button btn_check_attention;
    private Button btn_choose_city;
    private Button btn_init;
    private Button btn_make;
    private Button btn_history1;
    private Button btn_history2;
    private Button btn_history3;

    private EditText editText;
    private TextView textView;

    private String name;
    private List<jsobject> jsonList;                //存取 city.json里的数据

    private List<History> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);

        btn_search = (Button)findViewById(R.id.main_search);
        btn_check_attention = (Button)findViewById(R.id.main_check_attention);
        btn_choose_city = (Button)findViewById(R.id.main_choose_city);
        btn_init = (Button)findViewById(R.id.main_refresh);
        btn_make = (Button)findViewById(R.id.main_init);
        btn_history1 = (Button)findViewById(R.id.main_history_1);
        btn_history2 = (Button)findViewById(R.id.main_history_2);
        btn_history3 = (Button)findViewById(R.id.main_history_3);

        editText = (EditText)findViewById(R.id.main_name);
        textView = (TextView)findViewById(R.id.main_history);


        init();                 //添加历史记录

        btn_search.setOnClickListener(new View.OnClickListener() {              //查询城市
            @Override
            public void onClick(View v) {

                name = editText.getText().toString();
                for (jsobject js : jsonList){
                    if (js.getCity_name().equals(name)){
                        Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
                        intent.putExtra("id",js.get_id());
                        intent.putExtra("name",js.getCity_name());
                        intent.putExtra("code",js.getCity_code());
                        startActivity(intent);
                    }
                }
            }
        });


        btn_check_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AttentionActivity.class);
                startActivity(intent);
            }
        });

        btn_init.setOnClickListener(new View.OnClickListener() {        //初始化 访问 json，并存取到数组里
            @Override
            public void onClick(View v) {
                parse();
            }
        });

        btn_choose_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChoseProvince.class);
                startActivity(intent);
            }
        });
        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make();
            }
        });

        btn_history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = new String();
                for (jsobject js : jsonList){
                    if (js.getCity_name().equals(btn_history1.getText())){
                        code = js.getCity_code();
                        break;
                    }
                }
                if (!code.equals(null)) {
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    intent.putExtra("code", code);
                    intent.putExtra("name", btn_history1.getText());
                    startActivity(intent);
                }
            }
        });
        btn_history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = new String();
                for (jsobject js : jsonList){
                    if (js.getCity_name().equals(btn_history2.getText())){
                        code = js.getCity_code();
                        break;
                    }
                }
                if(!code.equals(null)) {
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    intent.putExtra("code", code);
                    intent.putExtra("name", btn_history2.getText());
                    startActivity(intent);
                }
            }
        });
        btn_history3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = new String();
                for (jsobject js : jsonList){
                    if (js.getCity_name().equals(btn_history3.getText())){
                        code = js.getCity_code();
                        break;
                    }
                }
                if (!code.equals(null)) {
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    intent.putExtra("code", code);
                    intent.putExtra("name", btn_history3.getText());
                    startActivity(intent);
                }
            }
        });


    }

    public void init(){                                             //初始化刷新历史记录，并且把数据库清空

        String content = new String();
        content = "History";
        historyList = LitePal.findAll(History.class);
        if (historyList.size() > 0){
            if (historyList.size() >= 3){
                for (int i = historyList.size()-1;i >= historyList.size()-3;i--){
                    History history = historyList.get(i);
                    if (i == historyList.size()-1){
                        btn_history1.setText(history.getCityNAME());
                    }
                    if (i == historyList.size()-2){
                        btn_history2.setText(history.getCityNAME());
                    }
                    if (i == historyList.size()-3){
                        btn_history3.setText(history.getCityNAME());
                    }
                }
            }else {
                if (historyList.size() == 1){
                    History history = historyList.get(0);
                    btn_history1.setText(history.getCityNAME());
                }
                if (historyList.size() == 2){
                    History history = historyList.get(0);
                    History history1 = historyList.get(1);
                    btn_history1.setText(history.getCityNAME());
                    btn_history2.setText(history1.getCityNAME());
                }
            }
        }
        textView.setText(content);
    }

    public void make(){
        LitePal.deleteAll(province.class);
        LitePal.deleteAll(city.class);
        LitePal.deleteAll(county.class);

        for (jsobject js : jsonList){                   //遍历 分成 省、市、县
            int pid = js.getPid();
            int _id = js.getId();
            String cityname = js.getCity_name();
            String ccode = js.getCity_code();
            if (pid == 0){           //省
                if (!ccode.equals("")){                 //直辖市
                    city c = new city();
                    c.setCity_code(ccode);
                    c.setCity_name(cityname);
                    c.setPid(_id);
                    c.setLocation(_id);
                    c.save();
                }
                province p = new province();
                p.setLocation(_id);
                p.setPid(pid);
                p.setPrivince_name(cityname);
                p.setProvince_code(ccode);
                p.save();

            } else {
                boolean is_city = false;
                List<province> provinceList = LitePal.findAll(province.class);  //遍历所有省份找是不是市  这里 北京啥的都不算省
                for (province p : provinceList){
                    if (p.getLocation() == pid && p.getProvince_code().equals("")){
                        is_city = true;
                        break;
                    }
                }
                if (is_city){                               //是 市
                    city c = new city();
                    c.setLocation(_id);
                    c.setPid(pid);
                    c.setCity_name(cityname);
                    c.setCity_code(ccode);
                    c.save();
                }else {
                    List<city> cityList = LitePal.findAll(city.class);
                    for (city c : cityList){
                        if (c.getLocation() == pid){
                            county co = new county();
                            co.setPid(pid);
                            co.setLocation(_id);
                            co.setCounty_name(cityname);
                            co.setCounty_code(ccode);
                            co.save();
                            break;
                        }
                    }
                }
            }
        }
        Toast.makeText(MainActivity.this,"省、市、县读取成功",Toast.LENGTH_SHORT).show();
    }
    public void parse(){//解析 city.json

        HttpUtil.sendOkHttpRequest("http://cdn.sojson.com/_city.json",new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                jsonList = gson.fromJson(responseData,new TypeToken<List<jsobject>>(){}.getType());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"城市数据读取成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
