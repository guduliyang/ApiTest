package com.crazy.view;

import com.crazy.db.ApiDB;
import com.crazy.demo.ApiInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "apis")
public class Apis {
    private static final Logger logger = LogManager.getLogger(Apis.class);

    @RequestMapping(value = "findByName")
    public String findByNames(HttpServletRequest request){
        String name = request.getParameter("name");
        String project_name = request.getParameter("project_name");
        ApiInfo apiInfo = ApiDB.findByNames(name,project_name);
        if(apiInfo!=null){
            return apiInfo.toJson();
        }else {
            return "{\"success\":"+false+"}";
        }
    }

    @RequestMapping(value = "findAll")
    public String findAll(HttpServletRequest request) {
        List<ApiInfo> apis = ApiDB.findAll();
        String project_name = request.getParameter("project_name");
        if (project_name != null) {
            apis = ApiDB.findByProject(project_name);
        } else {
            apis = ApiDB.findAll();
        }
        JSONObject result = new JSONObject();
        if (apis != null) {
            result.put("code", 0);
            result.put("msg", "获取数据成功");
            result.put("count", apis.size());
            JSONArray array = new JSONArray();
            for (ApiInfo api : apis) {
                JSONObject object = new JSONObject(api.toJson());
                object.put("params", object.getJSONArray("params").toString());
                object.put("bodys", object.getJSONArray("bodys").toString());
                object.put("headers", object.getJSONObject("headers").toString());
                array.put(object);
            }
            result.put("data", array);
        }
        return result.toString();
    }

    @RequestMapping(value = "del")
    public String drop(HttpServletRequest request) {
        String _id = request.getParameter("_id");
        ApiDB.dropByID(_id);
        return "{\"success\":true}";
    }

    @RequestMapping(value = "save")
    public String save(HttpServletRequest request){
        String name = request.getParameter("name");
        String method = request.getParameter("method");
        String url = request.getParameter("url");
        String project_name = request.getParameter("project_name");
        String params = request.getParameter("params");
        String bodys = request.getParameter("bodys");
        String headers = request.getParameter("headers");
        ApiInfo apiInfo = ApiDB.findByNames(name,project_name);
        if(apiInfo!=null){
            apiInfo.setHeaders(null);
            apiInfo.setBodys(null);
            apiInfo.setParams(null);
            apiInfo.setMethod(method);
            apiInfo.setUrl(url);
            for (Object object : new JSONArray(request.getParameter("params"))) {
                JSONObject obj = new JSONObject(object.toString());
                apiInfo.addParams(obj.getString("name"), obj.getBoolean("necessary"), obj.getInt("type"));
            }
            for (Object object : new JSONArray(request.getParameter("bodys"))) {
                JSONObject obj = new JSONObject(object.toString());
                apiInfo.addBody(obj.getString("name"), obj.getBoolean("necessary"), obj.getInt("type"));
            }
            JSONObject temp = new JSONObject(request.getParameter("headers"));
            for (String key : temp.keySet()) {
                apiInfo.addHeader(key, temp.getString(key));
            }
            ApiDB.updateByID(apiInfo.get_id(), apiInfo);
        }else {
            apiInfo = new ApiInfo(name, project_name);
            apiInfo.setMethod(method);
            apiInfo.setUrl(url);
            JSONObject tempObj;
            for (String key : (tempObj = new JSONObject(headers)).keySet()) {
                apiInfo.addHeader(key, tempObj.getString(key));
            }
            JSONArray tempArr;
            for (Object object : tempArr = new JSONArray(params)) {
                JSONObject pa = new JSONObject(object.toString());
                apiInfo.addParams(pa.getString("name"), pa.getBoolean("necessary"), pa.getInt("type"));
            }
            for (Object object : tempArr = new JSONArray(bodys)) {
                JSONObject bd = new JSONObject(object.toString());
                apiInfo.addBody(bd.getString("name"), bd.getBoolean("necessary"), bd.getInt("type"));
            }
            ApiDB.add(apiInfo);
        }
        return "{\"success\":true}";
    }
}
