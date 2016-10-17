package com.andrewrnagel.objgrader.controller;

import com.andrewrnagel.objgrader.bean.SearchTeacherAssign;
import com.andrewrnagel.objgrader.bean.SearchTeacherClasses;
import com.andrewrnagel.objgrader.bean.SearchTeacherStudents;
import com.andrewrnagel.objgrader.entity.AcademicClass;
import com.andrewrnagel.objgrader.entity.Grade;
import com.andrewrnagel.objgrader.entity.School;
import com.andrewrnagel.objgrader.entity.Teacher;
import com.andrewrnagel.objgrader.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Andrew Nagel on 10/14/16 at 8:24 AM EST.
 */

@RestController
public class MainController {
    @Autowired
    private MainService mainService;

    @GetMapping("/school/image")
    @ResponseBody
    public ResponseEntity serveFile() throws URISyntaxException {
        School school = mainService.getSchool();
        //TODO: photo caching (param?)
        if(school.getPhoto().getContentType() != null && school.getPhoto().getData() != null){
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
    @RequestMapping(value = "/teacherGradePost", method = RequestMethod.POST)
    public void teacherGradeBookGradeFormPost(@RequestParam(defaultValue = "") String gradeID,
                                              @RequestParam(defaultValue = "") String earnedPoints,
                                              HttpSession session) throws SQLException {
        if (!(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2))) {
            mainService.gradePost(gradeID, earnedPoints);
        }
    }
    @RequestMapping(value = "/teacherClassUpdate")
    public Page<AcademicClass> teacherGradeBookPageClassUpdate(SearchTeacherClasses searchTeacherClasses,
                                                               @PageableDefault(size = 2, sort = "period") Pageable pageable,
                                                               HttpSession session) throws SQLException {
        if (!(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2))) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            return mainService.listClasses(searchTeacherClasses, teacher.getTeacherID(), pageable);
        }
        return null;
    }
    @RequestMapping(value = "/teacherAssignUpdate")
    public Page<Grade> teacherGradeBookPageAssignUpdate(SearchTeacherAssign searchTeacherAssign,
                                                        @PageableDefault(size = 2, sort = "aName") Pageable pageable,
                                                        HttpSession session) throws SQLException {
        if (!(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2))) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            return mainService.listAssignments(searchTeacherAssign, teacher.getTeacherID(), pageable);
        }
        return null;
    }
    @RequestMapping(value = "/teacherStudentUpdate")
    public Page<Grade> teacherGradeBookPageStudentUpdate(SearchTeacherStudents searchTeacherStudents,
                                                         @PageableDefault(size = 2, sort = "sLastName") Pageable pageable,
                                                         HttpSession session) throws SQLException {
        if (!(session.getAttribute("userId") == null || !(session.getAttribute("userRole")).equals(2))) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            return mainService.listStudents(searchTeacherStudents, teacher.getTeacherID(), pageable);
        }
        return null;
    }
}