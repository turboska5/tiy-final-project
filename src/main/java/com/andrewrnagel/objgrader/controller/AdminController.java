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

/**
 * Created by Jimmy and Andrew on 10/5/16.
 */
@Controller
public class AdminController {
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
        model.addAttribute("classList", mainService.getAllClasses());
        model.addAttribute("studentList", mainService.getAllStudents());
        return "adminClasses";
    }
    @RequestMapping(value = "/adminManageClass", method = RequestMethod.GET)
    public String adminClassEditForm(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        model.addAttribute("teacherList", mainService.getAllTeachers());
//        model.addAttribute("studentRoster", mainService.getAllStudentsInClass());
        return "adminManageClass";
    }
    @RequestMapping(value = "/adminManageClass", method = RequestMethod.POST)
    public String adminClassEditFormSubmit(@Valid AcademicClass academicClass, BindingResult bindingResult, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        model.addAttribute("teacherList", mainService.getAllTeachers());
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("class", academicClass);
            return "adminManageClass";
        } else {
            mainService.saveClass(academicClass);
        }
        return "redirect:/adminClasses";
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
    public String adminUserAdminForm(Model model, HttpSession session,  @RequestParam(defaultValue = "0") Integer adminID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        if(adminID > 0) {
            Admin admin = mainService.getAdmin(adminID);
            model.addAttribute("admin", admin);
        } else {
            Admin admin = new Admin("", "", "", "");
            model.addAttribute(admin);
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
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("date", date);
            return "adminManageAdmin";
        } else {
            mainService.saveAdmin(admin);
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        return "redirect:/adminUsers";
    }
    @RequestMapping(value = "/adminManageTeacher", method = RequestMethod.GET)
    public String adminUserTeacherForm(Model model, HttpSession session, @RequestParam(defaultValue = "0") Integer teacherID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }

        if(teacherID > 0) {
            Teacher teacher = mainService.getTeacher(teacherID);
            model.addAttribute("teacher", teacher);
        } else {
            Teacher teacher = new Teacher("", "", "");
            model.addAttribute(teacher);
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

        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);

        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("teacher", teacher);
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("date", date);
            return "adminManageTeacher";
        }

        if(teacher.getTeacherID() > 0) {
            //bring over correct userID
            User user = mainService.getTeacher(teacher.getTeacherID()).getUser();
            //update email if changed
            if(!(teacher.getUser().getEmail().equals(user.getEmail()))){
                user.setEmail(teacher.getUser().getEmail());
            }
            //update password if changed
            if(!(teacher.getUser().getPassword().equals(user.getPassword()))){
                user.setPassword(teacher.getUser().getPassword());
            }
            teacher.setUser(user);
        }

        teacher.getUser().setPassword(PasswordStorage.createHash(teacher.getUser().getPassword()));
        teacher.getUser().setRole(2);
        mainService.saveTeacher(teacher);

        return "redirect:/adminUsers";
    }
    @RequestMapping(value = "/adminManageStudent", method = RequestMethod.GET)
    public String adminUserStudentForm(Model model, HttpSession session,
                                       @RequestParam(defaultValue = "0") Integer studentID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);
        if(studentID > 0) {
            Student student = mainService.getStudent(studentID);
            model.addAttribute("student", student);
        } else {
            Student student = new Student("", "", "", 0);
            model.addAttribute("student", student);
        }
        return "adminManageStudent";
    }
    @RequestMapping(value = "/adminManageStudent", method = RequestMethod.POST)
    public String adminUserStudentFormSubmit(@Valid Student student, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("date", date);

        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("student", student);
            return "adminManageStudent";
        }
        if(student.getStudentID() > 0) {
            //bring over correct userID
            User user = mainService.getStudent(student.getStudentID()).getUser();
            //update email if changed
            if(!(student.getUser().getEmail().equals(user.getEmail()))){
                user.setEmail(student.getUser().getEmail());
            }
            //update password if changed
            if(!(student.getUser().getPassword().equals(user.getPassword()))){
                user.setPassword(student.getUser().getPassword());
            }
            student.setUser(user);
        }
        student.getUser().setPassword(PasswordStorage.createHash(student.getUser().getPassword()));
        student.getUser().setRole(3);
        mainService.saveStudent(student);
        return "redirect:/adminUsers";
    }

}