package com.crazy.demo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiInfo {
    public static final String POST = "post";
    public static final String GET = "get";
    public String _id = null;
    public String name = null;
    public String project_name = null;
    public String requstUrl = null;
    public List<String> params = null;
    public List<String> bodys = null;
    public String method = null;
    public Map headers = null;

    public ApiInfo(String name, String project_name) {
        this.name = name;
        this.project_name = project_name;
    }

    public ApiInfo(String _id, String name, String project_name, String requstUrl, List<String> params, List<String> bodys, String method, Map headers) {
        this._id = _id;
        this.name = name;
        this.project_name = project_name;
        this.requstUrl = requstUrl;
        this.params = params;
        this.bodys = bodys;
        this.method = method;
        this.headers = headers;
    }

    public String toJson(){
        JSONObject object = new JSONObject();
        object.put("_id",_id);
        object.put("name",name);
        object.put("project_name",project_name);
        object.put("requstUrl",requstUrl);
        object.put("params",params==null?new ArrayList<String>():params);
        object.put("bodys",bodys==null?new ArrayList<String>():bodys);
        object.put("method",method);
        object.put("headers",headers==null?new HashMap<String,String>():headers);
        return object.toString();
    }

    public void addParams(String key){
        if(params==null)params = new ArrayList<String>();
        params.add(key);
    }

    public void addBody(String key){
        if(bodys==null)bodys = new ArrayList<String>();
        bodys.add(key);
    }

    public void addHeader(String key,String value){
        if(headers == null)headers = new HashMap();
        headers.put(key,value);
    }

    @Override
    public String toString() {
        return "ApiInfo{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", project_name='" + project_name + '\'' +
                ", requstUrl='" + requstUrl + '\'' +
                ", params=" + params +
                ", bodys=" + bodys +
                ", method='" + method + '\'' +
                ", headers=" + headers +
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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getRequstUrl() {
        return requstUrl;
    }

    public void setRequstUrl(String requstUrl) {
        this.requstUrl = requstUrl;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public List<String> getBodys() {
        return bodys;
    }

    public void setBodys(List<String> bodys) {
        this.bodys = bodys;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map getHeaders() {
        return headers;
    }

    public void setHeaders(Map headers) {
        this.headers = headers;
    }
}
