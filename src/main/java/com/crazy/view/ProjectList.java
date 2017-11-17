package com.crazy.view;

import com.crazy.db.ApiDB;
import com.crazy.db.ProjectDB;
import com.crazy.demo.Project;
import com.mongodb.client.result.UpdateResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "projects")
public class ProjectList {

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(HttpServletRequest request){
        String name = request.getParameter("name");
        String host_wuxia = request.getParameter("host_wuxia");
        String host_wuxib = request.getParameter("host_wuxib");
        String host_uat = request.getParameter("host_uat");
        Project project = new Project(name);
        project.setHost_wuxia(host_wuxia);
        project.setHost_wuxib(host_wuxib);
        project.setHost_uat(host_uat);
        ProjectDB.addProject(project);
        return ProjectDB.find().toString();
    }

    @RequestMapping(value = "updata",method = RequestMethod.GET)
    public boolean updata(HttpServletRequest request){
        String _id = request.getParameter("_id");
        String name = request.getParameter("name");
        String host_wuxia = request.getParameter("host_wuxia");
        String host_wuxib = request.getParameter("host_wuxib");
        String host_uat = request.getParameter("host_uat");
        Project project = ProjectDB.findOneByID(_id);
        String oldName = project.getName();
        if(!name.equals(oldName)) {
            ApiDB.updateByProject(oldName,name);
        }
        project.setName(name);
        project.setHost_wuxia(host_wuxia);
        project.setHost_wuxib(host_wuxib);
        project.setHost_uat(host_uat);
        project.set_id(null);
        UpdateResult result = ProjectDB.updateByID(_id,project);
        return true;
    }

    @RequestMapping(value = "drop",method = RequestMethod.GET)
    public String drop(HttpServletRequest request){
        String _id = request.getParameter("_id");
        ProjectDB.dropByID(_id);
        return "drop success";
    }

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public String findAll(HttpServletRequest request){
        JSONObject result = new JSONObject();
        List<Project> projects = ProjectDB.find();
        if(projects!=null){
            result.put("code",0);
            result.put("msg","获取数据成功");
            result.put("count",projects.size());
            JSONArray array = new JSONArray();
            for (Project project: projects){
                array.put(project.toJson());
            }
            result.put("data",array);
        }
        return result.toString();
    }
}
