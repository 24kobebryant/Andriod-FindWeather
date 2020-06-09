package com.example.admin.findweather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.findweather.R;
import com.example.admin.findweather.db.Attention;
import com.example.admin.findweather.db.county;
import com.example.admin.findweather.db.province;

import java.util.List;

public class ChoseCountyAdapter extends ArrayAdapter<county> {
    public int resourceID;
    public ChoseCountyAdapter(Context context, int resource, List<county> objects){
        super(context,resource,objects);
        resourceID = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        county co = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.title = view.findViewById(R.id.CITYNAME);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.title.setText(co.getCounty_name());
        return view;
    }

    class ViewHolder{
        TextView title;
    }

}
