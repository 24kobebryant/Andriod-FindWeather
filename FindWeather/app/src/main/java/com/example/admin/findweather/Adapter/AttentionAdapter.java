package com.example.admin.findweather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.findweather.R;
import com.example.admin.findweather.db.Attention;

import java.util.List;

public class AttentionAdapter extends ArrayAdapter<Attention> {
    public int resourceID;
    public AttentionAdapter(Context context, int resource, List<Attention> objects){
        super(context,resource,objects);
        resourceID = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Attention attention = getItem(position);
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
        viewHolder.title.setText(attention.getAttentionname());
        return view;
    }

    class ViewHolder{
        TextView title;
    }

}
