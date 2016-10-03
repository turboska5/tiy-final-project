package com.andrewrnagel.objgrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:47 PM EST.
 */

@Controller
public class MainController {
    //ADMIN ACCESS
    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String adminHomePage(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 2) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "adminHome";
    }
    @RequestMapping(value = "/adminClasses", method = RequestMethod.GET)
    public String adminClassPage(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 2) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "adminClasses";
    }
    @RequestMapping(value = "/adminManageClass", method = RequestMethod.GET)
    public String adminClassEditForm(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 2) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "adminManageClass";
    }
    @RequestMapping(value = "/adminUsers", method = RequestMethod.GET)
    public String adminUserPage(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 2) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "adminUsers";
    }
    @RequestMapping(value = "/adminManageAdmin", method = RequestMethod.GET)
    public String adminUserAdminForm(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 2) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "adminManageAdmin";
    }
    @RequestMapping(value = "/adminManageTeacher", method = RequestMethod.GET)
    public String adminUserTeacherForm(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 2) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "adminManageTeacher";
    }
    @RequestMapping(value = "/adminManageStudent", method = RequestMethod.GET)
    public String adminUserStudentForm(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 2) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "adminManageStudent";
    }

    //TEACHER ACCESS
    @RequestMapping(value = "/teacherHome", method = RequestMethod.GET)
    public String teacherHomePage(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 1) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "teacherHome";
    }
    @RequestMapping(value = "/teacherAttendance", method = RequestMethod.GET)
    public String teacherAttendancePage(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 1) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "teacherAttendance";
    }
    @RequestMapping(value = "/teacherGradeBook", method = RequestMethod.GET)
    public String teacherGradeBookPage(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null || ((Integer) session.getAttribute("userRole")) == 1) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
        return "teacherGradeBook";
    }

    //STUDENT ACCESS
    @RequestMapping(value = "/studentHome", method = RequestMethod.GET)
    public String studentHomePage(Model model, HttpSession session) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("date", java.time.LocalDate.now());
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