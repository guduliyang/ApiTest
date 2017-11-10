package com.crazy.view;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "edit")
public class Edit {
    private static final Logger logger = LogManager.getLogger(Edit.class);

    @RequestMapping(value = {"","/"})
    public ModelAndView index(HttpServletRequest request){
        return new ModelAndView("edit");
    }
}
