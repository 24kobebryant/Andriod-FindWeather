package com.example.admin.findweather.db;

import org.litepal.crud.LitePalSupport;

public class county extends LitePalSupport {
    private int location;
    private int id;
    private int pid;
    private String county_code;
    private String county_name;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public int getPid() {
        return pid;
    }

    public String getCounty_code() {
        return county_code;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setCounty_code(String county_code) {
        this.county_code = county_code;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }
}
