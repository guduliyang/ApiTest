package com.crazy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Test {
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public @ResponseBody String test(HttpServletRequest request){
        String p1 = request.getParameter("p1");
        String p2 =  request.getParameter("p2");
        String p3 =  request.getParameter("p3");
        String p4 = request.getParameter("p4");
        JSONObject object = new JSONObject();
        object.put("p1",p1);
        object.put("p2",p2);
        object.put("p3",p3);
        object.put("p4",p4);
        return object.toString();
    }

    @RequestMapping(value = "testPost",method = RequestMethod.GET )
    public String testPost(HttpServletRequest request) throws UnirestException {
        HttpResponse<String> response = null;
        response = Unirest.get("http://www.baidu.com").asString();
        return response.getBody();
    }
}
