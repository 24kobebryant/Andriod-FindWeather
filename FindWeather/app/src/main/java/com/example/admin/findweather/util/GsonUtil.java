package com.example.admin.findweather.util;

import com.example.admin.findweather.gson.Cityinfo;
import com.example.admin.findweather.gson.Data;
import com.example.admin.findweather.gson.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class GsonUtil {
    public static Weather handleWeatherResponse(String responseData){
        Gson gson = new Gson();
        Weather weather = gson.fromJson(responseData,Weather.class);

//        System.out.println("time————————————————————————————————————————————————————"+weather.time);
//        System.out.println("city_info————————————————————————————————————————————————————"+weather.cityinfo.city+" "+
//        weather.cityinfo.cityId+" "+
//        weather.cityinfo.parent+" "+
//        weather.cityinfo.updateTime);
//        System.out.println("date————————————————————————————————————————————————————"+weather.date);
//        System.out.println("message————————————————————————————————————————————————————"+weather.message);
//        System.out.println("status————————————————————————————————————————————————————"+weather.status);
//        System.out.println("data————————————————————————————————————————————————————"+weather.data.shidu+" "+
//        weather.data.pm25+" "+
//        weather.data.pm10+" "+
//                "forecast "+
//        weather.data.forecast.get(0).date+" "+
//                weather.data.forecast.get(0).ymd+" "+
//                weather.data.forecast.get(0).week+" "+
//                weather.data.forecast.get(0).sunrise+" ");
        return weather;
    }
}
