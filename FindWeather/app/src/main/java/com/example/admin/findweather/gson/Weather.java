package com.example.admin.findweather.gson;

import android.provider.DocumentsContract;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    public String time;

    @SerializedName("cityInfo")
    public Cityinfo cityinfo;

    public String date;
    public String message;
    public int status;

    public Data data;

}
