package com.crazy.demo;

import org.json.JSONObject;

public class Project {
    private String _id = null;
    private String name = null;
    private String host_wuxia = null;
    private String host_uat = null;
    private String host_wuxib = null;

    public Project(String name) {
        this.name = name;
    }

    public Project(String _id, String name, String host_wuxia, String host_wuxib, String host_uat) {
        this._id = _id;
        this.name = name;
        this.host_wuxia = host_wuxia;
        this.host_wuxib = host_wuxib;
        this.host_uat = host_uat;
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("_id", _id);
        object.put("name", name);
        object.put("host_wuxia", host_wuxia);
        object.put("host_wuxib", host_wuxib);
        object.put("host_uat", host_uat);
        return object;
    }

    @Override
    public String toString() {
        return "Project{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", host_wuxia='" + host_wuxia + '\'' +
                ", host_wuxib='" + host_wuxib + '\'' +
                ", host_uat='" + host_uat + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost_wuxia() {
        return host_wuxia;
    }

    public void setHost_wuxia(String host_wuxia) {
        this.host_wuxia = host_wuxia;
    }

    public String getHost_uat() {
        return host_uat;
    }

    public void setHost_uat(String host_uat) {
        this.host_uat = host_uat;
    }

    public String getHost_wuxib() {
        return host_wuxib;
    }

    public void setHost_wuxib(String host_wuxib) {
        this.host_wuxib = host_wuxib;
    }
}
