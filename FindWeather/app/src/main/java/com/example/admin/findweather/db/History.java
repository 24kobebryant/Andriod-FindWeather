package com.example.admin.findweather.db;

import com.example.admin.findweather.gson.Weather;

import org.litepal.crud.LitePalSupport;

public class History extends LitePalSupport {
    private String cityNAME;
    private Weather weather;

    public String getCityNAME() {
        return cityNAME;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setCityNAME(String cityNAME) {
        this.cityNAME = cityNAME;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
