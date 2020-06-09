package com.example.admin.findweather.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.findweather.Adapter.AttentionAdapter;
import com.example.admin.findweather.R;
import com.example.admin.findweather.db.Attention;

import org.litepal.LitePal;

import java.util.List;

public class AttentionActivity extends AppCompatActivity {

    private ListView listView;
    private List<Attention> attentionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        listView = (ListView)findViewById(R.id.listview);

        attentionList = LitePal.findAll(Attention.class);
        init();                     //初始化

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {                     //短摁查看
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Attention attention = attentionList.get(position);
                Intent intent = new Intent(AttentionActivity.this,WeatherActivity.class);
                intent.putExtra("name",attention.getAttentionname());
                intent.putExtra("code",attention.getCode());
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(AttentionActivity.this);
                adb.setTitle(attentionList.get(position).getAttentionname());
                final int ID = attentionList.get(position).getId();
                adb.setItems(new String[]{"删除", ""}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                LitePal.delete(Attention.class,ID);
                                attentionList.remove(position);
                                Toast.makeText(AttentionActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                                init();
                                break;
                                default:
                                    break;
                        }
                    }
                });
                adb.show();
                return true;
            }
        });
    }
    public void init(){
        AttentionAdapter attentionAdapter = new AttentionAdapter(AttentionActivity.this,R.layout.item_title,attentionList);
        listView.setAdapter(attentionAdapter);
    }
}
