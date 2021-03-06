package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.bean.SearchTeacherAssign;
import com.andrewrnagel.objgrader.bean.SearchTeacherClasses;
import com.andrewrnagel.objgrader.bean.SearchTeacherStudents;
import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    //TODO: Attendance overhaul
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
    public String teacherGradeBookPage(SearchTeacherClasses searchTeacherClasses, SearchTeacherAssign searchTeacherAssign,
                                       SearchTeacherStudents searchTeacherStudents, Model model, HttpSession session) throws SQLException {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        //banner
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacher", teacher);
        //assignment search
        if (!(searchTeacherClasses == null)){
            searchTeacherAssign.setaPeriod(searchTeacherClasses.getPeriod());
        }
        //student search
        if (!(searchTeacherClasses == null)){
            searchTeacherStudents.setsPeriod(searchTeacherClasses.getPeriod());
        }
        return "teacherGradeBook";
    }
    @RequestMapping(value = "/classTable")
    public String populateClassData(SearchTeacherClasses searchTeacherClasses, @PageableDefault(size = 5, sort = "period") Pageable pageable,
                                    @RequestParam(defaultValue = "0") Integer page,
                                    Model model, HttpSession session) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("classListSize", mainService.listAllClasses(searchTeacherClasses, teacher.getTeacherID()));
        model.addAttribute("classList", mainService.listClasses(searchTeacherClasses, teacher.getTeacherID(), pageable));
        return "teacherGradeBook/classesTable";
    }
    @RequestMapping(value = "/assignTable")
    public String populateAssignData(SearchTeacherAssign searchTeacherAssign, @PageableDefault(size = 5, sort = "academicClass.period") Pageable pageable,
                                     @RequestParam(defaultValue = "0") Integer aPage,
                                     Model model, HttpSession session) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("assignListSize", mainService.listAllAssignments(searchTeacherAssign, teacher.getTeacherID()));
        model.addAttribute("assignmentList", mainService.listAssignments(searchTeacherAssign, teacher.getTeacherID(), pageable));
        return "teacherGradeBook/assignmentTable";
    }
    @RequestMapping(value = "/studentTable")
    public String populateStudentData(SearchTeacherStudents searchTeacherStudents, @PageableDefault(size = 5, sort = "academicClass.period") Pageable pageable,
                                      @RequestParam(defaultValue = "0") Integer sPage,
                                      Model model, HttpSession session) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        model.addAttribute("studentListSize", mainService.listAllStudents(searchTeacherStudents, teacher.getTeacherID()));
        model.addAttribute("studentList", mainService.listStudents(searchTeacherStudents, teacher.getTeacherID(), pageable));
        return "teacherGradeBook/studentTable";
    }
    @RequestMapping(value = "/teacherDeleteAssign", method = RequestMethod.GET)
    public String teacherGradeBookDeleteAssign(HttpSession session, @RequestParam(defaultValue = "0") Integer assignmentID) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        mainService.deleteAssignment(assignmentID);
        return "redirect:/teacherGradeBook";
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
            Grade grade = new Grade(0.0);
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