package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.entity.Teacher;
import com.andrewrnagel.objgrader.entity.User;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.repository.TeacherRepository;
import com.andrewrnagel.objgrader.repository.UserRepository;
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
    TeacherRepository teacherRepository;
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
        //create a sample user
        Teacher teacher = new Teacher("Andrew", "Nagel", "arnagel@gmail.com");
        teacher.getUser().setEmail(teacher.getEmailAddress());
        teacher.getUser().setPassword(PasswordStorage.createHash("12345"));
        teacherRepository.save(teacher);
//        userRepository.save(user);
//        User user = new User("arnagel@gmail.com", "12345");
//        user.setPassword(PasswordStorage.createHash(user.getPassword()));
//        userRepository.save(user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String email, String password, HttpSession session, Model model) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User user = userRepository.getByEmail(email);
        if(user != null && PasswordStorage.verifyPassword(password, user.getPassword())){
            session.setAttribute("userId", user.getId());
            //admin
            if(user.getRole() == 1) {
                return "redirect:/adminHome";
            }
            //teacher
            if(user.getRole() == 2) {
                return "redirect:/teacherHome";
            }
            //student
            return "redirect:/studentHome";
        } else {
            model.addAttribute("loginFailed", true);
            return "login";
        }
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}