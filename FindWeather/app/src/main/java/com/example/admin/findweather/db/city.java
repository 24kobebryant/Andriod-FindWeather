package com.example.admin.findweather.db;

import org.litepal.crud.LitePalSupport;

public class city extends LitePalSupport {
    private int location;
    private int id;
    private int pid;
    private String city_code;
    private String city_name;

    public int getId() {
        return id;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public int getPid() {
        return pid;
    }

    public String getCity_code() {
        return city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

}
