package com.andrewrnagel.objgrader.service;

import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Array;
import java.sql.SQLException;
import java.util.*;

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
    @Autowired
    private GradeRepo gradeRepo;

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

    public List<Grade> getClassGrades(Integer classID) {
        return this.gradeRepo.findByAcademicClassClassID(classID);
    }

    public List<Student> getStudentRoster(Integer classID) {
        return this.gradeRepo.getStudentRoster(classID);
    }

//    public Integer getStudentCount(Integer classID) {
//        return this.gradeRepo.countDistinctStudentByAcademicClassClassID(classID);
//        //student --> grade; location --> Student(studentID)
//        //map: studentID(DB) --> List of Grade Objects
//        HashMap<Integer, List<Grade>> hashMap = new HashMap<Integer, List<Grade>>();
//        if (!hashMap.containsKey(studentID)) {
//            List<Grade> list = new ArrayList<Grade>();
//            list.add(grade);
//            hashMap.put(studentID, list);
//        } else {
//            hashMap.get(studentID).add(grade);
//        }
//        return classRoster;
//    }

    public void addStudentToClass(Integer academicClassID, Integer studentID) {
        Grade grade = new Grade();
        AcademicClass academicClass = this.classRepo.findOne(academicClassID);
        grade.setAcademicClass(academicClass);
        Student student = this.studentRepository.findOne(studentID);
        grade.setStudent(student);
        this.gradeRepo.save(grade);
    }

    public void dropStudentFromClass(Integer classID, Integer studentID) {
        this.gradeRepo.removeStudentFromClass(classID, studentID);
    }

    public void saveAssignment(Assignment assignment, Integer classID){
        this.assignmentRepo.save(assignment);
        AcademicClass academicClass = this.classRepo.findOne(classID);
        Grade grade = new Grade(academicClass, assignment);
        this.gradeRepo.save(grade);
    }

    //search criteria and organize by period
    public List<AcademicClass> searchClasses(Integer period, String name , String identifer, String department, String teacherLastName, String teacherFirstName, Integer teacherID) {
        List<AcademicClass> results = this.classRepo.searchClasses(period, name, identifer, department, teacherLastName, teacherFirstName, teacherID);
        Collections.sort(results, (AcademicClass a1, AcademicClass a2) -> a1.getPeriod() - a2.getPeriod());
        return results;
    }

    //TODO
    public void getTeacherAssignments(Integer aPeriod, String s, String s1, String s2, String s3) {}

    //TODO
    public void getTeacherStudents(Integer sPeriod, String s, String s1, String s2, String s3) {}

    //search criteria and organize by period
    public List<AcademicClass> searchForTeacherClasses(Integer teacherID) {
        List<AcademicClass> results = this.classRepo.getByTeacherTeacherID(teacherID);
        Collections.sort(results, (AcademicClass a1, AcademicClass a2) -> a1.getPeriod() - a2.getPeriod());
        return results;
    }
}