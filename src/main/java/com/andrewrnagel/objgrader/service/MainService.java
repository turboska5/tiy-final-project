package com.andrewrnagel.objgrader.service;

import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.misc.PasswordStorage;
import com.andrewrnagel.objgrader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:50 PM EST.
 */

@Service
public class MainService {
    //object properties
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepo classRepo;

    //methods
    //save admin user to admin table
    public void saveAdmin(Admin admin) throws SQLException, PasswordStorage.CannotPerformOperationException {
        admin.getUser().setPassword(PasswordStorage.createHash(admin.getPassword()));
        admin.getUser().setEmail(admin.getEmailAddress());
        admin.getUser().setRole(1);
        this.adminRepository.save(admin);
    }

    //save admin user to admin table
    public void saveTeacher(Teacher teacher) throws SQLException, PasswordStorage.CannotPerformOperationException {
        teacher.getUser().setPassword(PasswordStorage.createHash(teacher.getPassword()));
        teacher.getUser().setEmail(teacher.getEmailAddress());
        teacher.getUser().setRole(2);
        this.teacherRepository.save(teacher);
    }

    //save admin user to admin table
    public void saveNewStudent(Student student) throws SQLException, PasswordStorage.CannotPerformOperationException {
        student.getUser().setPassword(PasswordStorage.createHash(student.getPassword()));
        student.getUser().setEmail(student.getEmailAddress());
        student.getUser().setRole(3);
        this.studentRepository.save(student);
    }
    //save admin user to admin table
    public void updateStudent(Student student) throws SQLException, PasswordStorage.CannotPerformOperationException {
        User user = student.getUser();
        user.setEmail(student.getEmailAddress());
        if(!student.getPassword().isEmpty() || !(student.getPassword() == "")){
            user.setPassword(PasswordStorage.createHash(student.getPassword()));
        }
        this.studentRepository.save(student);
    }

    public List<AcademicClass> getAllClasses() {
        return this.classRepo.findAll();
    }

    public List<Admin> getAllAdmins() {
        return this.adminRepository.findAll();
    }

    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student getStudent(Integer studentID) {
        return this.studentRepository.findOne(studentID);
    }
}