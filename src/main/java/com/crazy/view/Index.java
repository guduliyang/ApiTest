package com.crazy.view;

import com.crazy.db.ApiDB;
import com.crazy.db.ProjectDB;
import com.crazy.demo.ApiInfo;
import com.crazy.demo.Project;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

    @RequestMapping(value = "loadnav",method = RequestMethod.GET)
    public String loadNavData(){
        JSONArray nav = new JSONArray();
        for (Project project : ProjectDB.find()){
            JSONObject object = new JSONObject();
            object.put("project_name",project.getName());
            JSONArray array = new JSONArray();
            for (ApiInfo apiInfo: ApiDB.findByProject(project.getName())){
                array.put(apiInfo.getName());
            }
            object.put("apiList",array);
            nav.put(object);
        }
        return nav.toString();
    }
}
