package com.crazy.view;

import com.crazy.db.ApiDB;
import com.crazy.db.RequestDataDB;
import com.crazy.demo.ApiInfo;
import com.crazy.demo.RequestData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "requestData")
public class RequestDatas {

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public String findByApi(HttpServletRequest request){
        String name = request.getParameter("name");
        String project_name = request.getParameter("project_name");
        ApiInfo apiInfo = ApiDB.findByNames(name,project_name);
        List<RequestData> requestDatas = RequestDataDB.findByApi(apiInfo);
        if(requestDatas!=null){
            JSONArray array = new JSONArray();
            for (RequestData requestData: requestDatas){
                array.put(new JSONObject(requestData.toJson()));
            }
            return array.toString();
        }else {
            return "{\"success\":false}";
        }
    }
}
