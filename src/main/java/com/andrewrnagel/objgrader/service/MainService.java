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
    public void saveClass(AcademicClass academicClass) {
        this.classRepo.save(academicClass);
    }

    public void saveAdmin(Admin admin) throws SQLException, PasswordStorage.CannotPerformOperationException {
        admin.getUser().setPassword(PasswordStorage.createHash(admin.getPassword()));
        admin.getUser().setEmail(admin.getEmailAddress());
        admin.getUser().setRole(1);
        this.adminRepository.save(admin);
    }

    public void saveTeacher(Teacher teacher) throws SQLException, PasswordStorage.CannotPerformOperationException {
        teacher.getUser().setPassword(PasswordStorage.createHash(teacher.getPassword()));
        teacher.getUser().setEmail(teacher.getEmailAddress());
        teacher.getUser().setRole(2);
        this.teacherRepository.save(teacher);
    }

    public void saveStudent(Student student) throws SQLException, PasswordStorage.CannotPerformOperationException {
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

    public User getUserByID(Integer id) {
        return userRepository.getById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public Student getStudent(Integer studentID) {
        return this.studentRepository.findOne(studentID);
    }
}