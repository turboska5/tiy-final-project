package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jimmy and Andrew on 10/5/16.
 */
@Controller
public class StudentController {
    @Autowired
    private MainService mainService;
    //local properties
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    String date = today.format(formatter);

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