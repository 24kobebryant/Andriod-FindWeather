package com.example.admin.findweather.db;

import com.example.admin.findweather.gson.Weather;

import org.litepal.crud.LitePalSupport;

public class Attention extends LitePalSupport {
    private String Attentionname;
    private Weather weather;
    private String code;
    private int id;

    public String getAttentionname() {
        return Attentionname;
    }

    public void setAttentionname(String attentionname) {
        Attentionname = attentionname;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;

    }
}
