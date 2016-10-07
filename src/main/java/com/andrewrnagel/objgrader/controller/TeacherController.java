package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.*;
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
import java.util.List;

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
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        model.addAttribute("classList", mainService.searchClasses(null, "", "", "", "", "", teacher.getTeacherID()));
        return "teacherHome";
    }
    @RequestMapping(value = "/teacherMyAttendance")
    public String teacherAttendancePagePersonalized(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        String teacherLastName = teacher.getLastName();
        String teacherFirstName = teacher.getFirstName();
        Integer teacherID = teacher.getTeacherID();
        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherLastName", teacherLastName);
        model.addAttribute("teacherFirstName", teacherFirstName);
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
                                        @RequestParam(defaultValue = "") Integer teacherID) {
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
//        model.addAttribute("assignmentList", "");
//        model.addAttribute("studentAssignmentList", "");
        return "teacherAttendance";
    }
    @RequestMapping(value = "/teacherMyGradeBook")
    public String teacherGradeBookPagePersonalized(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        String teacherLastName = teacher.getLastName();
        String teacherFirstName = teacher.getFirstName();
        Integer teacherID = teacher.getTeacherID();
        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherLastName", teacherLastName);
        model.addAttribute("teacherFirstName", teacherFirstName);
        model.addAttribute("teacherID", teacherID);
        return "redirect:/teacherGradeBook?teacherLastName=" + teacherLastName + "&teacherFirstName=" + teacherFirstName + "&teacherID=" + teacherID;
    }
    @RequestMapping(value = "/teacherGradeBook", method = RequestMethod.GET)
    public String teacherGradeBookPage(Model model, HttpSession session,
                                       @RequestParam(defaultValue = "") Integer period,
                                       @RequestParam(defaultValue = "") String name,
                                       @RequestParam(defaultValue = "") String identifier,
                                       @RequestParam(defaultValue = "") String department,
                                       @RequestParam(defaultValue = "") String teacherLastName,
                                       @RequestParam(defaultValue = "") String teacherFirstName,
                                       @RequestParam(defaultValue = "") Integer teacherID,
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
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        //banner
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        //class search
        model.addAttribute("period", period);
        model.addAttribute("name", name);
        model.addAttribute("identifier", identifier);
        model.addAttribute("department", department);
        model.addAttribute("teacherLastName", teacherLastName);
        model.addAttribute("teacherFirstName", teacherFirstName);
        model.addAttribute("teacherID", teacherID);
        model.addAttribute("classList", mainService.searchClasses(period, "%" + name + "%", "%" + identifier + "%", "%" + department + "%", "%" + teacherLastName + "%", "%" + teacherFirstName + "%", teacherID));
        //assignment search
        model.addAttribute("aPeriod", aPeriod);
        model.addAttribute("aName", aName);
        model.addAttribute("aID", aID);
        model.addAttribute("aDate", aDate);
        model.addAttribute("aPoints", aPoints);
//        model.addAttribute("assignmentList", mainService.getTeacherAssignments(aPeriod, "%" + aName + "%", "%" + aID + "%", "%" + aDate + "%", "%" + aPoints + "%"));
        //student search
        model.addAttribute("sPeriod", sPeriod);
        model.addAttribute("sLastName", sLastName);
        model.addAttribute("sFirstName", sFirstName);
        model.addAttribute("sAName", sAName);
        model.addAttribute("sAID", sAID);
//        model.addAttribute("gradeList", mainService.getTeacherStudents(sPeriod, "%" + sLastName + "%", "%" + sFirstName + "%", "%" + sAName + "%", "%" + sAID + "%"));
        return "teacherGradeBook";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.GET)
    public String teacherGradeBookAssignForm(Model model, HttpSession session,
                                             @RequestParam(defaultValue = "0") Integer assignmentID,
                                             @RequestParam(defaultValue = "0") Integer gradeID,
                                             @RequestParam(defaultValue = "0") Integer classID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
//        List<AcademicClass> teacherClasses = mainService.searchForTeacherClasses(teacher.getTeacherID());
        model.addAttribute("teacherClasses", mainService.searchForTeacherClasses(teacher.getTeacherID()));

        if(assignmentID > 0) {
            //TODO
//            Assignment assignment = mainService.getAssignment(assignmentID);
//            model.addAttribute("assignment", assignment);
            model.addAttribute("classID", classID);
            model.addAttribute("gradeID", gradeID);
        } else {
            Assignment assignment = new Assignment("", "");
            model.addAttribute("assignment", assignment);

            Grade grade = new Grade(0);
            model.addAttribute("grade", grade);
            model.addAttribute("classID", classID);
        }
        return "teacherManageAssign";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.POST)
    public String teacherGradeBookAssignFormPost(@Valid Assignment assignment, BindingResult bindingResult, Model model, HttpSession session,
                                                 @RequestParam(defaultValue = "0") Integer gradeID,
                                                 @RequestParam(defaultValue = "0") Integer classID) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", date);
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", session.getAttribute("teacher"));
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("assignment", assignment);
            model.addAttribute("teacher", session.getAttribute("teacher"));
            model.addAttribute("date", date);
            model.addAttribute("gradeID", gradeID);
            model.addAttribute("teacherClasses", mainService.searchForTeacherClasses(teacher.getTeacherID()));
            return "teacherManageAssign";
        }
        if(assignment.getAssignmentID() > 0) {

        }
        //TODO

//        mainService.saveAssignment(assignment, classID);

        return "redirect:/teacherGradeBook";
    }
}