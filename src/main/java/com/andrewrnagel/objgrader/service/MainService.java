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
    private AssignmentRepo assignmentRepo;
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

    public void saveAdmin(Admin admin) throws SQLException {
        this.adminRepository.save(admin);
    }

    public void saveTeacher(Teacher teacher) throws SQLException {
        this.teacherRepository.save(teacher);
    }

    public void saveStudent(Student student) throws SQLException{
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

    public AcademicClass getAcademicClass(Integer classID) {
        return this.classRepo.findOne(classID);
    }

    public Student getStudent(Integer studentID) {
        return this.studentRepository.findOne(studentID);
    }

    public Teacher getTeacher(Integer teacherID) {
        return this.teacherRepository.findOne(teacherID);
    }

    public Admin getAdmin(Integer adminID) {
        return this.adminRepository.findOne(adminID);
    }

    public void addStudentToClass(Integer classID, Integer studentID) {
        AcademicClass academicClass = getAcademicClass(classID);
        academicClass.getStudents().add(getStudent(studentID));
        this.classRepo.save(academicClass);
    }

    public void dropStudentFromClass(Integer classID, Integer studentID) {
        AcademicClass academicClass = getAcademicClass(classID);
        academicClass.getStudents().remove(getStudent(studentID));
        this.classRepo.save(academicClass);
    }

    public void saveAssignment(Assignment assignment){
        this.assignmentRepo.save(assignment);
    }

    public List<AcademicClass> searchClasses(Integer period, String name , String identifer, String department, String teacherLastName, String teacherFirstName, Integer teacherID) {
        return this.classRepo.searchClasses(period, name, identifer, department, teacherLastName, teacherFirstName, teacherID);
    }
}