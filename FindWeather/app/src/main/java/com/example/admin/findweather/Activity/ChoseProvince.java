package com.example.admin.findweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.findweather.Adapter.AttentionAdapter;
import com.example.admin.findweather.Adapter.ChoseProvinceAdapter;
import com.example.admin.findweather.R;
import com.example.admin.findweather.db.province;

import org.litepal.LitePal;

import java.util.List;

public class ChoseProvince extends AppCompatActivity {

    private List<province> provinceList;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_province);

        listView = (ListView)findViewById(R.id.listview_province);
        provinceList = LitePal.findAll(province.class);

        System.out.println("province ————————————"+provinceList.size());

        init();                     //初始化

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                province p = provinceList.get(position);
                Intent intent = new Intent(ChoseProvince.this,ChoseCity.class);
                intent.putExtra("province",p.getLocation());
                startActivity(intent);
            }
        });
    }
    public void init(){
        ChoseProvinceAdapter choseProvinceAdapter = new ChoseProvinceAdapter(ChoseProvince.this,R.layout.item_title,provinceList);
        listView.setAdapter(choseProvinceAdapter);
    }
}
