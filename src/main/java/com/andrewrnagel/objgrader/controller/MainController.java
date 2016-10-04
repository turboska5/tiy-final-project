package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.Admin;
import com.andrewrnagel.objgrader.entity.Student;
import com.andrewrnagel.objgrader.entity.Teacher;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:47 PM EST.
 */

@Controller
public class MainController {
    @Autowired
    private MainService mainService;
    //local properties
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    String date = today.format(formatter);

    //ADMIN ACCESS
    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String adminHomePage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "adminHome";
    }
    @RequestMapping(value = "/adminClasses", method = RequestMethod.GET)
    public String adminClassPage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "adminClasses";
    }
    @RequestMapping(value = "/adminManageClass", method = RequestMethod.GET)
    public String adminClassEditForm(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "adminManageClass";
    }
    @RequestMapping(value = "/adminUsers", method = RequestMethod.GET)
    public String adminUserPage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        model.addAttribute("adminList", mainService.getAllAdmins());
        model.addAttribute("teacherList", mainService.getAllTeachers());
        model.addAttribute("studentList", mainService.getAllStudents());
        return "adminUsers";
    }
    @RequestMapping(value = "/adminManageAdmin", method = RequestMethod.GET)
    public String adminUserAdminForm(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "adminManageAdmin";
    }
    @RequestMapping(value = "/adminManageAdmin", method = RequestMethod.POST)
    public String adminUserAdminFormSubmit(@Valid Admin admin, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("admin", admin);
            return "adminManageAdmin";
        } else {
            mainService.saveAdmin(admin);
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "redirect:/adminUsers";
    }
    @RequestMapping(value = "/adminManageTeacher", method = RequestMethod.GET)
    public String adminUserTeacherForm(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "adminManageTeacher";
    }
    @RequestMapping(value = "/adminManageTeacher", method = RequestMethod.POST)
    public String adminUserTeacherFormSubmit(@Valid Teacher teacher, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("teacher", teacher);
            return "adminManageTeacher";
        } else {
            mainService.saveTeacher(teacher);
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "redirect:/adminUsers";
    }
    @RequestMapping(value = "/adminManageStudent", method = RequestMethod.GET)
    public String adminUserStudentForm(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "adminManageStudent";
    }
    @RequestMapping(value = "/adminManageStudent", method = RequestMethod.POST)
    public String adminUserStudentFormSubmit(@Valid Student student, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("student", student);
            return "adminManageStudent";
        } else {
            mainService.saveStudent(student);
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "redirect:/adminUsers";
    }

    //TEACHER ACCESS
    @RequestMapping(value = "/teacherHome", method = RequestMethod.GET)
    public String teacherHomePage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "teacherHome";
    }
    @RequestMapping(value = "/teacherAttendance", method = RequestMethod.GET)
    public String teacherAttendancePage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "teacherAttendance";
    }
    @RequestMapping(value = "/teacherGradeBook", method = RequestMethod.GET)
    public String teacherGradeBookPage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "teacherGradeBook";
    }
    @RequestMapping(value = "/teacherManageAssign", method = RequestMethod.GET)
    public String teacherGradeBookAssignForm(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "teacherManageAssign";
    }

    //STUDENT ACCESS
    @RequestMapping(value = "/studentHome", method = RequestMethod.GET)
    public String studentHomePage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(3)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "studentHome";
    }
}