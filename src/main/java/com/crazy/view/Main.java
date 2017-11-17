package com.crazy.view;

import com.crazy.db.ApiDB;
import com.crazy.db.ProjectDB;
import com.crazy.demo.Project;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "main")
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    @RequestMapping(value = {"","/"})
    public ModelAndView index(HttpServletRequest request){
        List<Project> projects = ProjectDB.find();
        request.setAttribute("projects",projects);
        for (Project project : projects){
            String name = project.getName();
            request.setAttribute(name, ApiDB.findByProject(name));
        }
        return new ModelAndView("main");
    }

    private JSONObject getProject(){
//        List<DBObject> projects = ProjectDB.findAllDB();
//        for ()
        return null;
    }
}
