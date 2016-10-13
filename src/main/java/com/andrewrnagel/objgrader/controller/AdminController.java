package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jimmy and Andrew on 10/5/16.
 */

@Controller
public class AdminController {
    @Autowired
    private MainService mainService;

    //ADMIN ACCESS
    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String adminHomePage(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("thisSchool", mainService.getSchool());
        return "adminHome";
    }
    @RequestMapping(value = "/adminManageInfo", method = RequestMethod.GET)
    public String adminEditInfoForm(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("school", mainService.getSchool());
        return "adminManageInfo";
    }
    @RequestMapping(value = "/adminManageInfo", method = RequestMethod.POST)
    public String adminEditInfoFormSubmit(@Valid School school, BindingResult bindingResult,
                                          @RequestParam(name = "photoFile") MultipartFile photoFile,
                                          Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        School thisSchool = mainService.getSchool();
        //TODO: error if exceeds 1MB
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("school", school);
            return "adminManageInfo";
        }
        if(!photoFile.isEmpty()) {
            try {
                Photo tempPhoto;
                if(!thisSchool.getPhoto().getPhotoID().equals(0)) {
                    tempPhoto = thisSchool.getPhoto();
                } else {
                    tempPhoto = new Photo();
                }
                tempPhoto.setContentType(photoFile.getContentType());
                tempPhoto.setData(photoFile.getBytes());
                school.setPhoto(tempPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            school.setPhoto(thisSchool.getPhoto());
        }
        mainService.updateSchool(school);
        return "redirect:/adminHome";
    }
    @RequestMapping(value = "/adminClasses", method = RequestMethod.GET)
    public String adminClassPage(Model model, HttpSession session,
                                 @RequestParam(defaultValue = "") String name,
                                 @RequestParam(defaultValue = "") String identifier,
                                 @RequestParam(defaultValue = "") String department,
                                 @RequestParam(defaultValue = "") String teacherLastName,
                                 @RequestParam(defaultValue = "") String teacherFirstName,
                                 @RequestParam(defaultValue = "") Integer teacherID,
                                 @RequestParam(defaultValue = "") Integer period) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("name", name);
        model.addAttribute("identifier", identifier);
        model.addAttribute("department", department);
        model.addAttribute("teacherLastName", teacherLastName);
        model.addAttribute("teacherFirstName", teacherFirstName);
        model.addAttribute("teacherID", teacherID);
        model.addAttribute("period", period);
        model.addAttribute("classList", mainService.searchClasses(period, "%" + name + "%", "%" + identifier + "%", "%" + department + "%", "%" + teacherLastName + "%", "%" + teacherFirstName + "%", teacherID));
        return "adminClasses";
    }
    @RequestMapping(value = "/adminManageClass", method = RequestMethod.GET)
    public String adminClassEditForm(Model model, HttpSession session,
                                     @RequestParam(defaultValue = "0") Integer classID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacherList", mainService.getAllTeachers());
        if(classID > 0) {
            AcademicClass academicClass = mainService.getAcademicClass(classID);
            model.addAttribute("academicClass", academicClass);
            List<Student> roster = mainService.getStudentRoster(academicClass.getClassID());
            List<Student> pool = mainService.getAllStudents();
            model.addAttribute("studentRoster", roster);
            if(roster.size() > 0) {
                pool.removeAll(roster);
            }
            model.addAttribute("studentList", pool);
        } else {
            AcademicClass academicClass = new AcademicClass("", "");
            model.addAttribute("academicClass", academicClass);
        }
        return "adminManageClass";
    }
    @RequestMapping(value = "/adminManageClass", method = RequestMethod.POST)
    public String adminClassEditFormSubmit(@Valid AcademicClass academicClass, BindingResult bindingResult, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("teacherList", mainService.getAllTeachers());
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("academicClass", academicClass);
            model.addAttribute("studentList", mainService.getAllStudents());
            return "adminManageClass";
        }
        mainService.saveClass(academicClass);
        return "redirect:/adminClasses";
    }
    @RequestMapping(value = "/adminAddStudentToClass", method = RequestMethod.GET)
    public String adminAddStudentToClass(Model model, HttpSession session,
                                         @RequestParam(defaultValue = "0") Integer classID,
                                         @RequestParam(defaultValue = "0") Integer studentID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        mainService.addStudentToClass(classID, studentID);
        return "redirect:/adminManageClass?classID=" + classID;
    }
    @RequestMapping(value = "/adminDropStudentFromClass", method = RequestMethod.GET)
    public String adminDropStudentFromClass(Model model, HttpSession session,
                                         @RequestParam(defaultValue = "0") Integer classID,
                                         @RequestParam(defaultValue = "0") Integer studentID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        mainService.dropStudentFromClass(classID, studentID);
        return "redirect:/adminManageClass?classID=" + classID;
    }
    @RequestMapping(value = "/adminUsers", method = RequestMethod.GET)
    public String adminUserPage(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
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
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("thisAdmin", session.getAttribute("admin"));
        model.addAttribute("checkboxDisabled", false);
        if(adminID > 0) {
            Admin admin = mainService.getAdmin(adminID);
            Admin thisAdmin = (Admin)session.getAttribute("admin");
            model.addAttribute("admin", admin);
            if(admin.getUser().getId().equals(thisAdmin.getUser().getId())) {
                model.addAttribute("checkboxDisabled", true);
            }
        } else {
            Admin admin = new Admin("", "", "");
            model.addAttribute("admin", admin);
        }
        return "adminManageAdmin";
    }
    @RequestMapping(value = "/adminManageAdmin", method = RequestMethod.POST)
    public String adminUserAdminFormSubmit(@Valid Admin admin, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("admin", admin);
            return "adminManageAdmin";
        }
        if(admin.getAdminID() > 0) {
            //bring over correct userID
            User user = mainService.getAdmin(admin.getAdminID()).getUser();
            //update attributes on user object if changed (email/pass/disabled)
            if(!(admin.getUser().getEmail().equals(user.getEmail()))){
                user.setEmail(admin.getUser().getEmail());
            }
            if(!(admin.getUser().getPassword().equals(user.getPassword()))){
                user.setPassword(admin.getUser().getPassword());
            }
            if((admin.getUser().getDisabled() != user.getDisabled())) {
                user.setDisabled(admin.getUser().getDisabled());
            }
            admin.setUser(user);
        }
        admin.getUser().setPassword(PasswordStorage.createHash(admin.getUser().getPassword()));
        admin.getUser().setRole(1);
        mainService.saveAdmin(admin);
        return "redirect:/adminUsers";
    }
    @RequestMapping(value = "/adminManageTeacher", method = RequestMethod.GET)
    public String adminUserTeacherForm(Model model, HttpSession session, @RequestParam(defaultValue = "0") Integer teacherID) {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        if(teacherID > 0) {
            Teacher teacher = mainService.getTeacher(teacherID);
            model.addAttribute("teacher", teacher);
        } else {
            Teacher teacher = new Teacher("", "", "");
            model.addAttribute("teacher", teacher);
        }
        return "adminManageTeacher";
    }
    @RequestMapping(value = "/adminManageTeacher", method = RequestMethod.POST)
    public String adminUserTeacherFormSubmit(@Valid Teacher teacher, BindingResult bindingResult, Model model, HttpSession session) throws SQLException, PasswordStorage.CannotPerformOperationException {
        if(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(1)) {
            return "redirect:/logout";
        }
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("teacher", teacher);
            return "adminManageTeacher";
        }
        if(teacher.getTeacherID() > 0) {
            //bring over correct userID
            User user = mainService.getTeacher(teacher.getTeacherID()).getUser();
            //update attributes on user object if changed (email/pass/disabled)
            teacher.setTeacherClasses(mainService.getTeacher(teacher.getTeacherID()).getTeacherClasses());
            if(!(teacher.getUser().getEmail().equals(user.getEmail()))){
                user.setEmail(teacher.getUser().getEmail());
            }
            if(!(teacher.getUser().getPassword().equals(user.getPassword()))){
                user.setPassword(teacher.getUser().getPassword());
            }
            if((teacher.getUser().getDisabled() != user.getDisabled())) {
                user.setDisabled(teacher.getUser().getDisabled());
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
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
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
        model.addAttribute("date", mainService.getDate());
        model.addAttribute("day", mainService.getTimeOfDay());
        model.addAttribute("userName", session.getAttribute("userName"));
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("student", student);
            return "adminManageStudent";
        }
        if(student.getStudentID() > 0) {
            //bring over correct userID
            User user = mainService.getStudent(student.getStudentID()).getUser();
            //update attributes on user object if changed (email/pass/disabled)
            if(!(student.getUser().getEmail().equals(user.getEmail()))){
                user.setEmail(student.getUser().getEmail());
            }
            if(!(student.getUser().getPassword().equals(user.getPassword()))){
                user.setPassword(student.getUser().getPassword());
            }
            if((student.getUser().getDisabled() != user.getDisabled())) {
                user.setDisabled(student.getUser().getDisabled());
            }
            student.setUser(user);
        }
        student.getUser().setPassword(PasswordStorage.createHash(student.getUser().getPassword()));
        student.getUser().setRole(3);
        mainService.saveStudent(student);
        return "redirect:/adminUsers";
    }
    @GetMapping("/school/image")
    @ResponseBody
    public ResponseEntity serveFile() throws URISyntaxException {
        System.out.println("*****************");
        School school = mainService.getSchool();
        //TODO: photo caching (param?)
        if(school.getPhoto().getContentType() != null){
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_TYPE, school.getPhoto().getContentType())
                    .body(school.getPhoto().getData());
        } else {
            return ResponseEntity
                    .status(301)
                    .location(new URI("/images/schoolext.jpg"))
                    .build();
        }
    }
}