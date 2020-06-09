package com.example.admin.findweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    public String shidu;
    public int pm25;
//    public int pm10;
    public String quality;
    public String wendu;
//    public String ganmao;
//    public static class yesterday{
//        public String date;
//        public String ymd;
//        public String week;
//        public String sunrise;
//        public String high;
//        public String low;
//        public String sunset;
//        public int aqi;
//        public String fx;
//        public String fl;
//        public String type;
//        public String notice;
//    }

//    public yesterday yesterday;

    @SerializedName("forecast")
    public List<Forecast> forecast;

}
