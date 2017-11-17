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
public class ApiList {
    private static final Logger logger = LogManager.getLogger(ApiList.class);

    @RequestMapping(value = "find")
    public String findAll(HttpServletRequest request){
        List<ApiInfo> apis = ApiDB.findAll();
        String project_name = request.getParameter("project_name");
        if(project_name!=null){
            apis = ApiDB.findByProject(project_name);
        }else {
            apis = ApiDB.findAll();
        }
        JSONObject result = new JSONObject();
        if(apis!=null){
            result.put("code",0);
            result.put("msg","获取数据成功");
            result.put("count",apis.size());
            JSONArray array = new JSONArray();
            for (ApiInfo api: apis){
                JSONObject object = new JSONObject(api.toJson());
                array.put(object);
            }
            result.put("data",array);
        }
        return result.toString();
    }

    @RequestMapping(value = "edit")
    public String edit(HttpServletRequest request){
        String _id = request.getParameter("_id");
        ApiInfo apiInfo = ApiDB.findByID(_id);
        apiInfo.setName(request.getParameter("name"));
        apiInfo.setProject_name(request.getParameter("project_name"));
        apiInfo.setUrl(request.getParameter("url"));
        apiInfo.setMethod(request.getParameter("method"));
        for (String s:request.getParameter("params").split(",")){
            apiInfo.addParams(s);
        }
        for (String s:request.getParameter("bodys").split(",")){
            apiInfo.addBody(s);
        }
        for (String s : request.getParameter("bodys").split(",")){
            apiInfo.addBody(s);
        }
        return "";
    }
}
