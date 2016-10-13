package com.andrewrnagel.objgrader.service;

import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:50 PM EST.
 */

@Service
public class MainService {
    //local properties
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    String date = today.format(formatter);

    //spring properties
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
    @Autowired
    private SchoolRepo schoolRepo;

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

    public void addStudentToClass(Integer academicClassID, Integer studentID) {
        //create record on grade table
        Grade grade = new Grade();
        AcademicClass academicClass = this.classRepo.findOne(academicClassID);
        grade.setAcademicClass(academicClass);
        Student student = this.studentRepository.findOne(studentID);
        grade.setStudent(student);
        grade.setPossPoints(0); //to allow for validation of assignment (not null constraint)
        this.gradeRepo.save(grade);
        //create assignments for student based on class added to
        List <Grade> classAssignments = this.gradeRepo.getClassAssignments(academicClassID);
        for(Grade classAssignment : classAssignments) {
            Grade currentGrade = new Grade();
            currentGrade.setStudent(student);
            currentGrade.setAssignment(classAssignment.getAssignment());
            currentGrade.setAcademicClass(classAssignment.getAcademicClass());
            currentGrade.setPossPoints(classAssignment.getPossPoints());
            currentGrade.setDateCreated(classAssignment.getDateCreated());
            this.gradeRepo.save(currentGrade);
        }
    }

    public void dropStudentFromClass(Integer classID, Integer studentID) {
        this.gradeRepo.removeStudentFromClass(classID, studentID);
    }

    public void saveAssignment(Grade grade) {
        if(grade.getGradeID().equals(0)) {
            //new assignment: save base assignment as grade
            Assignment assignment = this.assignmentRepo.save(grade.getAssignment());
            grade.setAssignment(assignment);
            //iterate through roster, adding a line to grade table for each student
            List<Student> roster = getStudentRoster(grade.getAcademicClass().getClassID());
            for (Student student : roster) {
                Grade currentGrade = new Grade();
                currentGrade.setAssignment(assignment);
                currentGrade.setAcademicClass(grade.getAcademicClass());
                currentGrade.setStudent(student);
                currentGrade.setPossPoints(grade.getPossPoints());
                currentGrade.setDateCreated(grade.getDateCreated());
                this.gradeRepo.save(currentGrade);
            }
            this.gradeRepo.save(grade);
        } else {
            //update base assignment as grade
            this.assignmentRepo.save(grade.getAssignment());
            //iterate through all grade references on table
            List<Grade> gradesList = this.gradeRepo.findByAssignmentAssignmentID(grade.getAssignment().getAssignmentID());
            for (Grade grades : gradesList) {
                grades.setPossPoints(grade.getPossPoints());
                this.gradeRepo.save(grade);
            }
        }
    }

    public Grade getBaseAssignment(Integer gradeID) {
        return this.gradeRepo.getOne(gradeID);
    }

    //search criteria and organize by period
    public List<AcademicClass> searchClasses(Integer period, String name , String identifer, String department, String teacherLastName, String teacherFirstName, Integer teacherID) {
        List<AcademicClass> results = this.classRepo.searchClasses(period, name, identifer, department, teacherLastName, teacherFirstName, teacherID);
        Collections.sort(results, (AcademicClass a1, AcademicClass a2) -> a1.getPeriod() - a2.getPeriod());
        return results;
    }

    //all of one teacher's assignments (search)
    public List<Grade> getTeacherAssignments(Integer aPeriod, String aName, String aID, String aDate, String aPoints, Integer teacherID) {
        LocalDate aDateConverted = null;
        Integer aPointsParsed = null;
        if(!aDate.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            aDateConverted = LocalDate.parse(aDate, formatter);
        }
        if(!aPoints.equals("")) {
            aPointsParsed = Integer.parseInt(aPoints);
        }
        List<Grade> results = this.gradeRepo.searchForTeacherAssignments(aPeriod, aName, aID, aDateConverted, aPointsParsed, teacherID);
        return results;
    }

    public List<Grade> getTeacherStudents(Integer sPeriod, String sLastName, String sFirstName, String sAName, String sAID, Integer teacherID) {
        return this.gradeRepo.searchForTeacherStudents(sPeriod, sLastName, sFirstName, sAName, sAID, teacherID);
    }

    public List<Grade> getTeacherStudents(Integer sPeriod, String sLastName, String sFirstName, String sID, Integer gradeLevel) {
        return this.gradeRepo.searchForTeacherStudents(sPeriod, sLastName, sFirstName, sID, gradeLevel);
    }

    //search criteria and organize by period
    public List<AcademicClass> searchForTeacherClasses(Integer teacherID) {
        List<AcademicClass> results = this.classRepo.getByTeacherTeacherID(teacherID);
        Collections.sort(results, (AcademicClass a1, AcademicClass a2) -> a1.getPeriod() - a2.getPeriod());
        return results;
    }

    public void gradePost(Grade grade){
        Grade grader = this.gradeRepo.findOne(grade.getGradeID());
        grader.setEarnedPoints(grade.getEarnedPoints());
        this.gradeRepo.save(grader);
    }

    public String getDate() {
        return this.date;
    }

    public School getSchool() {
        return this.schoolRepo.getOne(0);
    }

    public List<Grade> getStudentGrades(Integer studentID) {
        List<Grade> results = this.gradeRepo.findByStudentStudentID(studentID);
        Collections.sort(results, (Grade a1, Grade a2) -> a1.getAcademicClass().getPeriod() - a2.getAcademicClass().getPeriod());
        return results;
    }

    public void updateSchool(School school) {
        this.schoolRepo.save(school);
    }
}