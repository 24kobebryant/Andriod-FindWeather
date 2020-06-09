package com.example.admin.findweather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.findweather.R;
import com.example.admin.findweather.db.Attention;
import com.example.admin.findweather.db.province;

import java.util.List;

public class ChoseProvinceAdapter extends ArrayAdapter<province> {
    public int resourceID;
    public ChoseProvinceAdapter(Context context, int resource, List<province> objects){
        super(context,resource,objects);
        resourceID = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        province p = getItem(position);
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
        viewHolder.title.setText(p.getPrivince_name());
        return view;
    }

    class ViewHolder{
        TextView title;
    }

}
