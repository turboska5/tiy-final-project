package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

/**
 * Created by Jimmy and Andrew on 10/5/16.
 */

@Controller
public class TeacherController {
    @Autowired
    private MainService mainService;

    //TEACHER ACCESS
    @RequestMapping(value = "/teacherHome", method = RequestMethod.GET)
    public String teacherHomePage(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("thisSchool", mainService.getSchool());
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        model.addAttribute("classList", mainService.searchClasses(null, "", "", "", "", "", teacher.getTeacherID()));
        return "teacherHome";
    }
    @RequestMapping(value = "/teacherMyAttendance")
    public String teacherAttendancePagePersonalized(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        String teacherLastName = teacher.getLastName();
        model.addAttribute("teacherLastName", teacherLastName);
        String teacherFirstName = teacher.getFirstName();
        model.addAttribute("teacherFirstName", teacherFirstName);
        Integer teacherID = teacher.getTeacherID();
        model.addAttribute("teacherID", teacherID);
        return "redirect:/teacherAttendance?teacherLastName=" + teacherLastName + "&teacherFirstName=" + teacherFirstName + "&teacherID=" + teacherID;
    }
    @RequestMapping(value = "/teacherAttendance", method = RequestMethod.GET)
    public String teacherAttendancePage(Model model, HttpSession session,
                                        @RequestParam(defaultValue = "") Integer period,
                                        @RequestParam(defaultValue = "") String name,
                                        @RequestParam(defaultValue = "") String identifier,
                                        @RequestParam(defaultValue = "") String department,
                                        @RequestParam(defaultValue = "") String teacherLastName,
                                        @RequestParam(defaultValue = "") String teacherFirstName,
                                        @RequestParam(defaultValue = "") Integer teacherID,
                                        @RequestParam(defaultValue = "") Integer sPeriod,
                                        @RequestParam(defaultValue = "") String sLastName,
                                        @RequestParam(defaultValue = "") String sFirstName,
                                        @RequestParam(defaultValue = "") String sID,
                                        @RequestParam(defaultValue = "") Integer gradeLevel) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        model.addAttribute("teacherLastName", teacherLastName);
        model.addAttribute("teacherFirstName", teacherFirstName);
        model.addAttribute("teacherID", teacherID);
        model.addAttribute("period", period);
        model.addAttribute("name", name);
        model.addAttribute("identifier", identifier);
        model.addAttribute("department", department);
        model.addAttribute("classList", mainService.searchClasses(period, "%" + name + "%", "%" + identifier + "%", "%" + department + "%", "%" + teacherLastName + "%", "%" + teacherFirstName + "%", teacherID));
//        model.addAttribute("studentList", mainService.getTeacherStudents(sPeriod, "%" + sLastName + "%", "%" + sFirstName + "%", "%" + sID + "%", gradeLevel));
        model.addAttribute("studentList", mainService.getAllStudents());
        return "teacherAttendance";
    }
    @RequestMapping(value = "/teacherGradeBook", method = RequestMethod.GET)
    public String teacherGradeBookPage(Model model, HttpSession session, @PageableDefault(size = 10) Pageable pageable,
//                                       @SortDefault("name") Pageable pageable,
                                       @RequestParam(defaultValue = "") Integer period,
                                       @RequestParam(defaultValue = "") String name,
                                       @RequestParam(defaultValue = "") String identifier,
                                       @RequestParam(defaultValue = "") Integer aPeriod,
                                       @RequestParam(defaultValue = "") String aName,
                                       @RequestParam(defaultValue = "") String aID,
                                       @RequestParam(defaultValue = "") String aDate,
                                       @RequestParam(defaultValue = "") String aPoints,
                                       @RequestParam(defaultValue = "") Integer sPeriod,
                                       @RequestParam(defaultValue = "") String sLastName,
                                       @RequestParam(defaultValue = "") String sFirstName,
                                       @RequestParam(defaultValue = "") String sAName,
                                       @RequestParam(defaultValue = "") String sAID) throws SQLException {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        //banner
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", teacher);
        model.addAttribute("pageable", pageable);
        //class search
        model.addAttribute("period", period);
        model.addAttribute("name", name);
        model.addAttribute("identifier", identifier);
        model.addAttribute("classList", mainService.searchClasses(period, "%" + name + "%", "%" + identifier + "%", "%" + teacher.getDepartment() + "%", "%" + teacher.getLastName() + "%", "%" + teacher.getFirstName() + "%", teacher.getTeacherID(), pageable));
        //assignment search
        model.addAttribute("aPeriod", aPeriod);
        if (!(period == null)){
            model.addAttribute("aPeriod", period);
            aPeriod = period;
        }
        model.addAttribute("aName", aName);
        model.addAttribute("aID", aID);
        model.addAttribute("aDate", aDate);
        model.addAttribute("aPoints", aPoints);
        model.addAttribute("assignmentList", mainService.getTeacherAssignments(aPeriod, "%" + aName + "%", "%" + aID + "%", aDate, aPoints, teacher.getTeacherID()));
        //student search
        model.addAttribute("sPeriod", sPeriod);
        if (!(period == null)){
            model.addAttribute("sPeriod", period);
            sPeriod = period;
        }
        model.addAttribute("sLastName", sLastName);
        model.addAttribute("sFirstName", sFirstName);
        model.addAttribute("sAName", sAName);
        model.addAttribute("sAID", sAID);
        model.addAttribute("studentList", mainService.getTeacherStudents(sPeriod, "%" + sLastName + "%", "%" + sFirstName + "%", "%" + sAName + "%", "%" + sAID + "%", teacher.getTeacherID()));
        return "teacherGradeBook";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.GET)
    public String teacherGradeBookAssignForm(Model model, HttpSession session,
                                             @RequestParam(defaultValue = "0") Integer gradeID) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        //banner
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        //fill the form
        model.addAttribute("teacherClasses", mainService.searchForTeacherClasses(teacher.getTeacherID()));
        if (gradeID > 0) {
            Grade grade = mainService.getBaseAssignment(gradeID);
            Assignment assignment = grade.getAssignment();
            model.addAttribute("gradeID", gradeID);
            model.addAttribute("grade", grade);
            model.addAttribute("assignment", assignment);
        } else {
            Grade grade = new Grade(0);
            Assignment assignment = new Assignment("", "");
            model.addAttribute("grade", grade);
            model.addAttribute("assignment", assignment);
        }
        return "teacherManageAssign";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.POST)
    public String teacherGradeBookAssignFormPost(@Valid Grade grade, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("assignment", grade.getAssignment());
            model.addAttribute("gradeID", grade.getGradeID());
            model.addAttribute("teacherClasses", mainService.searchForTeacherClasses(teacher.getTeacherID()));
            return "teacherManageAssign";
        }
        mainService.saveAssignment(grade);
        return "redirect:/teacherGradeBook";
    }
}