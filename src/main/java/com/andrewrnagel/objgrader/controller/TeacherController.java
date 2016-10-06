package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.Assignment;
import com.andrewrnagel.objgrader.entity.Teacher;
import com.andrewrnagel.objgrader.entity.User;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jimmy and Andrew on 10/5/16.
 */
@Controller
public class TeacherController {
    @Autowired
    private MainService mainService;
    //local properties
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    String date = today.format(formatter);

    //TEACHER ACCESS
    @RequestMapping(value = "/teacherHome", method = RequestMethod.GET)
    public String teacherHomePage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        return "teacherHome";
    }
    @RequestMapping(value = "/teacherAttendance", method = RequestMethod.GET)
    public String teacherAttendancePage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        return "teacherAttendance";
    }
    @RequestMapping(value = "/teacherGradeBook", method = RequestMethod.GET)
    public String teacherGradeBookPage(Model model, HttpSession session,
                                       @RequestParam(defaultValue = "") Integer period,
                                       @RequestParam(defaultValue = "") String name,
                                       @RequestParam(defaultValue = "") String identifier,
                                       @RequestParam(defaultValue = "") String department,
                                       @RequestParam(defaultValue = "") String teacherLastName,
                                       @RequestParam(defaultValue = "") String teacherFirstName,
                                       @RequestParam(defaultValue = "") Integer teacherID) throws SQLException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        model.addAttribute("period", period);
        model.addAttribute("name", name);
        model.addAttribute("identifier", identifier);
        model.addAttribute("department", department);
        model.addAttribute("teacherLastName", teacherLastName);
        model.addAttribute("teacherFirstName", teacherFirstName);
        model.addAttribute("teacherID", teacherID);
        model.addAttribute("classList", mainService.searchClasses(period, "%" + name + "%", "%" + identifier + "%", "%" + department + "%", "%" + teacherLastName + "%", "%" + teacherFirstName + "%", teacherID));
        return "teacherGradeBook";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.GET)
    public String teacherGradeBookAssignForm(Model model, HttpSession session,
                                             @RequestParam(defaultValue = "0") Integer assignmentID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        if(assignmentID > 0) {
            //TODO
//            Assignment assignment = mainService.getAssignment(assignmentID);
//            model.addAttribute("assignment", assignment);
        } else {
            Assignment assignment = new Assignment("", "", "2016-09-01", 0, 0);
            model.addAttribute("assignment", assignment);
        }
        return "teacherManageAssign";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.POST)
    public String teacherGradeBookAssignFormPost(@Valid Assignment assignment, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("assignment", assignment);
            model.addAttribute("teacher", session.getAttribute("teacher"));
            model.addAttribute("date", date);
            return "teacherManageAssign";
        }
        if(assignment.getAssignmentID() > 0) {

        }
        mainService.saveAssignment(assignment);
        return "redirect:/adminUsers";
    }
}