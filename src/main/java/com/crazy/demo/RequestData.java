package com.crazy.demo;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestData {
    public static final String TESTING = "testing";
    public static final String PRODUCTION = "production";
    private String _id = null;
    private String api_name = null;
    private String project_name = null;
    private String env = null;
    private JSONObject bodys = null;
    private Map<String,Object> params = null;

    public RequestData(String api_name, String project_name) {
        this.api_name = api_name;
        this.project_name = project_name;
    }

    public RequestData(String _id, String api_name, String project_name, String env, JSONObject bodys, Map<String, Object> params) {
        this._id = _id;
        this.api_name = api_name;
        this.project_name = project_name;
        this.env = env;
        this.bodys = bodys;
        this.params = params;
    }

    public void addBody(String key, Object value) {
        if (bodys == null) bodys = new JSONObject();
        bodys.put(key, value);
    }

    public void addParam(String key, Object value) {
        if (params == null) params = new HashMap<String,Object>();
        params.put(key, value);
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "_id='" + _id + '\'' +
                ", api_name='" + api_name + '\'' +
                ", project_name='" + project_name + '\'' +
                ", env='" + env + '\'' +
                ", bodys=" + bodys +
                ", params=" + params +
                '}';
    }

    public String toJson(){
        JSONObject object = new JSONObject();
        object.put("_id",_id);
        object.put("api_name",api_name);
        object.put("project_name",project_name);
        object.put("env",env);
        object.put("bodys", bodys==null?new JSONObject():bodys);
        object.put("params", params==null?new HashMap<String,Object>():params);
        return object.toString();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public JSONObject getBodys() {
        return bodys;
    }

    public void setBodys(JSONObject bodys) {
        this.bodys = bodys;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
