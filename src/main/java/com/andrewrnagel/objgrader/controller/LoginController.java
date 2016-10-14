package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

/**
 * Created by doug on 9/20/16. Modified by Andrew and Jimmy on 9/27/16.
 */

@Controller
public class LoginController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SchoolRepo schoolRepo;

    //Requires user to be logged in
    @RequestMapping(value = "/")
    public String home(HttpSession session, Model model){
        if(session.getAttribute("userId") == null){
        } else {
            // get the user
            Integer userId = (Integer) session.getAttribute("userId");
            User user = userRepository.getOne(userId);
            model.addAttribute("user", user);
            if(session.getAttribute("userRole").equals(1)) {
                return "redirect:/adminHome";
            } else if(session.getAttribute("userRole").equals(2)) {
                return "redirect:/teacherHome";
            } else if(session.getAttribute("userRole").equals(3)) {
                return "redirect:/studentHome";
            }
        }
        return "redirect:/login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() throws PasswordStorage.CannotPerformOperationException {
        //create sample users
//        Admin admin = new Admin("Admin", "Alpha", "2016-01-01", "Principal");
//        admin.getUser().setEmail("admin@fakeschools.org");
//        admin.getUser().setPassword(PasswordStorage.createHash("123"));
//        admin.getUser().setRole(1);
//        adminRepository.save(admin);
        //create school
//        School school = new School();
//        school.setSchoolName("Superior High School");
//        school.setDistrict("Fake County Public Schools");
//        school.setAddress("981 S. Emerson Street");
//        school.setCity("Cary");
//        school.setState("NC");
//        school.setZipCode("27519");
//        school.setEmail("superioroffice@fakeschools.org");
//        school.setPhone("(919)867-5309");
//        school.setPhoto(new Photo());
//        schoolRepo.save(school);
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String email, String password, HttpSession session, Model model) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User user = userRepository.getByEmail(email);
        if(user != null && (!user.getDisabled()) && PasswordStorage.verifyPassword(password, user.getPassword())){
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());
            //admin
            if(user.getRole() == 1) {
                Admin admin = adminRepository.getByUserId(user.getId());
                admin.getUser().setLastLogin();
                adminRepository.save(admin);
                session.setAttribute("admin", admin);
                session.setAttribute("userName", admin.getFirstName());
                return "redirect:/adminHome";
            }
            //teacher
            if(user.getRole() == 2) {
                Teacher teacher = teacherRepository.getByUserId(user.getId());
                teacher.getUser().setLastLogin();
                teacherRepository.save(teacher);
                session.setAttribute("teacher", teacher);
                session.setAttribute("userName", teacher.getFirstName());
                return "redirect:/teacherHome";
            }
            //student
            if(user.getRole() == 3) {
                Student student = studentRepository.getByUserId(user.getId());
                student.getUser().setLastLogin();
                studentRepository.save(student);
                session.setAttribute("student", student);
                session.setAttribute("userName", student.getFirstName());
                return "redirect:/studentHome";
            }
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }
    @RequestMapping(path = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}