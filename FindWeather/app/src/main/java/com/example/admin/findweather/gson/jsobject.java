package com.example.admin.findweather.gson;

public class jsobject {
    private int _id;
    private int id;             //匹配
    private int pid;            //父亲
    private String city_code;
    private String city_name;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
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
