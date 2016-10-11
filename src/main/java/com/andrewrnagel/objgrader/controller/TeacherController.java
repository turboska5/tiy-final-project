package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        model.addAttribute("studentList", mainService.getAllStudents());
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
        model.addAttribute("assignmentList", mainService.getTeacherAssignments(aPeriod, "%" + aName + "%", "%" + aID + "%", aDate, aPoints, teacherID));
        //student search
        model.addAttribute("sPeriod", sPeriod);
        model.addAttribute("sLastName", sLastName);
        model.addAttribute("sFirstName", sFirstName);
        model.addAttribute("sAName", sAName);
        model.addAttribute("sAID", sAID);
        model.addAttribute("studentList", mainService.getTeacherStudents(sPeriod, "%" + sLastName + "%", "%" + sFirstName + "%", "%" + sAName + "%", "%" + sAID + "%", teacherID));
        return "teacherGradeBook";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.GET)
    public String teacherGradeBookAssignForm(Model model, HttpSession session,
                                             @RequestParam(defaultValue = "0") Integer gradeID,
                                             @RequestParam(defaultValue = "0") Integer assignmentID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        //banner
        model.addAttribute("date", date);
        model.addAttribute("userName", session.getAttribute("userName"));
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("teacher", teacher);
        //fill the form
        model.addAttribute("teacherClasses", mainService.searchForTeacherClasses(teacher.getTeacherID()));

        if(gradeID > 0) {
            Grade grade = mainService.getBaseAssignment(gradeID);
            Assignment assignment = grade.getAssignment();
            model.addAttribute("gradeID", gradeID);
            model.addAttribute("grade", grade);
            model.addAttribute("assignment", assignment);
            model.addAttribute("assignmentID", assignmentID);
        } else {
            Grade grade = new Grade(0);
            Assignment assignment = new Assignment("", "");
            model.addAttribute("grade", grade);
            model.addAttribute("assignment", assignment);
            model.addAttribute("assignmentID", assignmentID);
        }
        return "teacherManageAssign";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.POST)
    public String teacherGradeBookAssignFormPost(@Valid Grade grade, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        //banner
        model.addAttribute("date", date);
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", teacher);
        //errors that validation won't catch
        // TODO: 10/11/16
//        if (grade.getAcademicClass().equals("") || grade.getAcademicClass() == null){
//            FieldError fieldError = new FieldError("grade", "academicClass", grade.getAcademicClass(), false, new String[]{"Declined.grade.academicClass"}, (String[])null, "Did not Take Class");
//            bindingResult.addError(fieldError);
//        }
        if(grade.getAssignment().getAssignmentName().equals("")){
            FieldError fieldError = new FieldError("grade", "assignment.assignmentName", grade.getAssignment().getAssignmentName(), false, new String[]{"Declined.student.user.password"}, (String[])null, "Did not Take Password");
            bindingResult.addError(fieldError);
        }
        if(grade.getAssignment().getAssignmentIDNumber().equals("")){
            FieldError fieldError = new FieldError("grade", "assignment.assignmentIDNumber", grade.getAssignment().getAssignmentIDNumber(), false, new String[]{"Declined.student.user.password"}, (String[])null, "Did not Take Password");
            bindingResult.addError(fieldError);
        }
        // TODO: 10/11/16
//        if (grade.getAssignment().getDate().equals("")){
//            FieldError fieldError = new FieldError("grade", "assignment.date", grade.getAssignment().getDate(), false, new String[]{"Declined.student.user.password"}, (String[])null, "Did not Take Password");
//            bindingResult.addError(fieldError);
//        }
        if (grade.getPossPoints() == null || grade.getPossPoints().equals("")){
            FieldError fieldError = new FieldError("grade", "possPoints", grade.getPossPoints(), false, new String[]{"Declined.student.user.password"}, (String[])null, "Did not Take Password");
            bindingResult.addError(fieldError);
        }

        //error checking
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("assignment", grade.getAssignment());
            model.addAttribute("teacher", teacher);
            model.addAttribute("date", date);
            model.addAttribute("gradeID", grade.getGradeID());
            model.addAttribute("teacherClasses", mainService.searchForTeacherClasses(teacher.getTeacherID()));
            return "teacherManageAssign";
        }
//        if(grade.getGradeID() > 0) {
//            //TODO: edit assignment (gradeID null when it should be zero)
//        }
        mainService.saveAssignment(grade);
        return "redirect:/teacherMyGradeBook";
    }
}