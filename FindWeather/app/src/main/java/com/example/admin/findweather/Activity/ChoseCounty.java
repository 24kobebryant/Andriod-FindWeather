package com.example.admin.findweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.findweather.Adapter.ChoseCityAdapter;
import com.example.admin.findweather.Adapter.ChoseCountyAdapter;
import com.example.admin.findweather.R;
import com.example.admin.findweather.db.county;
import com.example.admin.findweather.gson.Weather;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChoseCounty extends AppCompatActivity {

    private ListView listView;
    private List<county> countyList;
    private List<county> counties = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_county);

        listView = (ListView)findViewById(R.id.listview_county);
        countyList = LitePal.findAll(county.class);

        System.out.println("county————————————"+countyList.size());

        Intent intent = getIntent();
        int pid = intent.getIntExtra("city",0);
        System.out.println("pid ——————————————————"+pid);
        for (county co : countyList){
            if (co.getPid() == pid){
                counties.add(co);
            }
        }
        System.out.println("counties————————————"+counties.size());

        init();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                county co2 = counties.get(position);
                Intent intent1 = new Intent(ChoseCounty.this,WeatherActivity.class);
                intent1.putExtra("code",co2.getCounty_code());
                intent1.putExtra("name",co2.getCounty_name());
                startActivity(intent1);
            }
        });

    }
    public void init(){
        ChoseCountyAdapter choseCountyAdapter = new ChoseCountyAdapter(ChoseCounty.this,R.layout.item_title,counties);
        listView.setAdapter(choseCountyAdapter);
    }

}
