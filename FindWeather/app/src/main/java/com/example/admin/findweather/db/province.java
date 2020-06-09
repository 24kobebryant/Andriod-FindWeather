package com.example.admin.findweather.db;

import org.litepal.crud.LitePalSupport;

public class province extends LitePalSupport {
    private int location;
    private int id;
    private int pid;
    private String province_code;
    private String privince_name;


    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public void setPrivince_name(String privince_name) {
        this.privince_name = privince_name;
    }


    public int getId() {
        return id;
    }

    public int getPid() {
        return pid;
    }

    public String getProvince_code() {
        return province_code;
    }

    public String getPrivince_name() {
        return privince_name;
    }

}
