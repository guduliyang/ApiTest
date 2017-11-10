package com.crazy.demo;

import org.json.JSONObject;

public class Project {
    public String _id = null;
    public String name = null;
    public String address_www = null;
    public String address_uat = null;

    public Project(String name) {
        this.name = name;
    }

    public Project(String _id, String name, String address_www, String address_uat) {
        this._id = _id;
        this.name = name;
        this.address_www = address_www;
        this.address_uat = address_uat;
    }

    public String toJson() {
        JSONObject object = new JSONObject();
        object.put("_id", _id);
        object.put("name", name);
        object.put("address_www", address_www);
        object.put("address_uat", address_uat);
        return object.toString();
    }

    @Override
    public String toString() {
        return "Project{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", address_www='" + address_www + '\'' +
                ", address_uat='" + address_uat + '\'' +
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

    public String getAddress_www() {
        return address_www;
    }

    public void setAddress_www(String address_www) {
        this.address_www = address_www;
    }

    public String getAddress_uat() {
        return address_uat;
    }

    public void setAddress_uat(String address_uat) {
        this.address_uat = address_uat;
    }
}
