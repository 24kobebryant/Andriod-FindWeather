package com.example.admin.findweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.findweather.Adapter.ChoseCityAdapter;
import com.example.admin.findweather.Adapter.ChoseProvinceAdapter;
import com.example.admin.findweather.R;
import com.example.admin.findweather.db.city;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChoseCity extends AppCompatActivity {

    private ListView listView;
    private List<city> cityList;
    private List<city> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_city);

        listView = (ListView)findViewById(R.id.listview_city);
        cityList = LitePal.findAll(city.class);                         //在全部的市里找出所有属于此省的

        System.out.println("city——————————————"+cityList.size());

        Intent intent = getIntent();
        int pid = intent.getIntExtra("province",0);
        for (city c : cityList){
            if (c.getPid() == pid){
                cities.add(c);
            }
        }
        System.out.println("cities——————————————————"+cities.size());

        init();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city c = cities.get(position);
                Intent intent1 = new Intent(ChoseCity.this,ChoseCounty.class);
                intent1.putExtra("city",c.getLocation());
                startActivity(intent1);
            }
        });

    }

    public void init(){
        ChoseCityAdapter choseCityAdapter = new ChoseCityAdapter(ChoseCity.this,R.layout.item_title,cities);
        listView.setAdapter(choseCityAdapter);
    }
}
