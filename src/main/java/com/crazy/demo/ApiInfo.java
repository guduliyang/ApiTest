package com.crazy.demo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiInfo {
    public static final String POST = "post";
    public static final String GET = "get";
    private String _id = null;
    private String name = null;
    private String method = null;
    private String url = null;
    private String project_name = null;
    private List<Param> params = null;
    private List<Param> bodys = null;
    private Map<String,String> headers = null;

    public ApiInfo(String name, String project_name) {
        this.name = name;
        this.project_name = project_name;
    }

    public ApiInfo(String _id, String name, String project_name, String url, List<Param> params, List<Param> bodys, String method, Map<String,String> headers) {
        this._id = _id;
        this.name = name;
        this.project_name = project_name;
        this.url = url;
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
        object.put("url", url);
        object.put("method",method);
        object.put("params",params==null?new JSONArray():new JSONArray(params));
        object.put("bodys",bodys==null?new JSONArray():new JSONArray(bodys));
        object.put("headers",headers==null?new JSONObject():new JSONObject(headers));
        return object.toString();
    }

    public void addParams(String name,boolean necessary){
        if(params==null)params = new ArrayList<Param>();
        params.add(new Param(name,necessary));
    }

    public void addParams(String name,boolean necessary,int type){
        if(params==null)params = new ArrayList<Param>();
        params.add(new Param(name,necessary,type));
    }

    public void addBody(String name,boolean necessary){
        if(bodys==null)bodys = new ArrayList<Param>();
        bodys.add(new Param(name,necessary));
    }

    public void addBody(String name,boolean necessary,int type){
        if(bodys==null)bodys = new ArrayList<Param>();
        bodys.add(new Param(name,necessary,type));
    }

    public void addHeader(String key,String value){
        if(headers == null)headers = new HashMap<String,String>();
        headers.put(key,value);
    }

    @Override
    public String toString() {
        return "ApiInfo{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", project_name='" + project_name + '\'' +
                ", params=" + params +
                ", bodys=" + bodys +
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public List<Param> getBodys() {
        return bodys;
    }

    public void setBodys(List<Param> bodys) {
        this.bodys = bodys;
    }
}
