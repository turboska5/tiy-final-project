package com.andrewrnagel.objgrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:47 PM EST.
 */

@Controller
public class MainController {
    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String adminHome() {
        return "adminHome";
    }
    @RequestMapping(value = "/teacherHome", method = RequestMethod.GET)
    public String teacherHome() {
        return "teacherHome";
    }
    @RequestMapping(value = "/studentHome", method = RequestMethod.GET)
    public String studentHome() {
        return "studentHome";
    }

//    @RequestMapping(value = "/ManageClass", method = RequestMethod.POST)
//    public String classForm() {
//        return "";
//    }
//    @RequestMapping(value = "/ManageTeacher", method = RequestMethod.POST)
//    public String teacherForm() {
//        return "";
//    }
//    @RequestMapping(value = "/ManageStudent", method = RequestMethod.POST)
//    public String studentForm() {
//        return "";
//    }
}