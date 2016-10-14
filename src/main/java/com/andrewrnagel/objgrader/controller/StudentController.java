package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.Grade;
import com.andrewrnagel.objgrader.entity.Student;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy and Andrew on 10/5/16.
 */

@Controller
public class StudentController {
    @Autowired
    private MainService mainService;

    //STUDENT ACCESS
    @RequestMapping(value = "/studentHome", method = RequestMethod.GET)
    public String studentHomePage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(3)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("school", mainService.getSchool());
        Student student = (Student)session.getAttribute("student");
        List<Grade> classes = mainService.getStudentClasses(student.getStudentID());
        for (Grade grade : classes){
            grade.setStudentClassAverage(mainService.getStudentGradeAverage(student.getStudentID(), grade.getAcademicClass().getClassID()));
        }
        model.addAttribute("classList", classes);
        return "studentHome";
    }

    @RequestMapping(value = "/studentGradeView", method = RequestMethod.GET)
    public String studentGradeView(Model model, HttpSession session,
                                   @RequestParam(defaultValue = "") Integer period,
                                   @RequestParam(defaultValue = "") String aName,
                                   @RequestParam(defaultValue = "") String aPoints) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(3)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        Student student = (Student)session.getAttribute("student");
        model.addAttribute("studentList", mainService.getStudentAssignments(period, '%' + aName + '%', aPoints, student.getStudentID()));
        return "studentGradeView";
    }
}
