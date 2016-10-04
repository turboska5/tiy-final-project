package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.Admin;
import com.andrewrnagel.objgrader.entity.Student;
import com.andrewrnagel.objgrader.entity.Teacher;
import com.andrewrnagel.objgrader.entity.User;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.repository.AdminRepository;
import com.andrewrnagel.objgrader.repository.StudentRepository;
import com.andrewrnagel.objgrader.repository.TeacherRepository;
import com.andrewrnagel.objgrader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

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

    // requires user to be logged in
    @RequestMapping(value = "/")
    public String home(HttpSession session, Model model){
        if(session.getAttribute("userId") == null){
            return "redirect:/login";
        } else {
            // get the user
            Integer userId = (Integer) session.getAttribute("userId");
            User user = userRepository.getOne(userId);
            model.addAttribute("user", user);
            return "redirect:/adminHome";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() throws PasswordStorage.CannotPerformOperationException {
        //create sample users
//        Admin admin = new Admin("Andrew", "Nagel", "arnagel@gmail.com", "Principal");
//        admin.getUser().setEmail(admin.getEmailAddress());
//        admin.getUser().setPassword(PasswordStorage.createHash("12345"));
//        adminRepository.save(admin);
//        Teacher teacher = new Teacher("Jimmy", "Bush", "jbone@gmail.com", "Math");
//        teacher.getUser().setEmail(teacher.getEmailAddress());
//        teacher.getUser().setPassword(PasswordStorage.createHash("12345"));
//        teacherRepository.save(teacher);
//        Student student = new Student("Bob", "Jones", "bj@gmail.com", "BJ324123", 11);
//        student.getUser().setEmail(student.getEmailAddress());
//        student.getUser().setPassword(PasswordStorage.createHash("12345"));
//        studentRepository.save(student);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String email, String password, HttpSession session, Model model) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User user = userRepository.getByEmail(email);
        if(user != null && PasswordStorage.verifyPassword(password, user.getPassword())){
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());
            //admin
            if(user.getRole() == 1) {
                Admin admin = adminRepository.getByEmailAddress(user.getEmail());
                admin.getUser().setLastLogin();
                adminRepository.save(admin);
                session.setAttribute("userName", admin.getFirstName());
                return "redirect:/adminHome";
            }
            //teacher
            if(user.getRole() == 2) {
                Teacher teacher = teacherRepository.getByEmailAddress(user.getEmail());
                teacher.getUser().setLastLogin();
                teacherRepository.save(teacher);
                session.setAttribute("userName", teacher.getFirstName());
                return "redirect:/teacherHome";
            }
            //student
            if(user.getRole() == 3) {
//                Student student = studentRepository.getByEmailAddress(user.getEmail());
//                student.getUser().setLastLogin();
//                studentRepository.save(student);
//                session.setAttribute("userName", student.getFirstName());
//                return "redirect:/studentHome";
            }
        }
//        session.setAttribute("userName", studentRepository.getByEmailAddress(user.getEmail()).getFirstName());
        model.addAttribute("loginFailed", true);
        return "login";
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}