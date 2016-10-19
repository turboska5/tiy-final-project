package com.andrewrnagel.objgrader.service;

import com.andrewrnagel.objgrader.bean.*;
import com.andrewrnagel.objgrader.entity.*;
import com.andrewrnagel.objgrader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:50 PM EST.
 */

@Service
public class MainService {
    //local properties
    LocalDate today = LocalDate.now();
    LocalTime theTime = LocalTime.now();
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

    //Banner methods
    public String getDate() {
        return this.date;
    }

    public String getTimeOfDay() {
        if(theTime.getHour() >= 6 && theTime.getHour() < 12) {
            return "morning";
        } else if(theTime.getHour() >= 12 && theTime.getHour() < 18) {
            return "afternoon";
        } else if((theTime.getHour() >= 18 && theTime.getHour() < 24) ||
                (theTime.getHour() >= 0 && theTime.getHour() < 6)) {
            return "evening";
        }
        return "day";
    }

    //Admin methods
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

    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.getValidTeachers();
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.getValidStudents();
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
        grade.setPossPoints(0.0); //to allow for validation of assignment (not null constraint)
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

    //Teacher Methods
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

    //search criteria and organize by period
    public List<AcademicClass> searchForTeacherClasses(Integer teacherID) {
        List<AcademicClass> results = this.classRepo.getByTeacherTeacherID(teacherID);
        Collections.sort(results, (AcademicClass a1, AcademicClass a2) -> a1.getPeriod() - a2.getPeriod());
        return results;
    }

    public void gradePost(String gradeID, String earnedPoints){
        Integer gradeIDParsed = null;
        Double earnedPointsParsed = null;
        if(!gradeID.equals("")) {
            gradeIDParsed = Integer.parseInt(gradeID);
        }
        if(!earnedPoints.equals("")) {
            earnedPointsParsed = Double.parseDouble(earnedPoints);
        }
        Grade grader = this.gradeRepo.findOne(gradeIDParsed);
        grader.setEarnedPoints(earnedPointsParsed);
        this.gradeRepo.save(grader);
    }

    public School getSchool() {
        return this.schoolRepo.findOne(0);
    }

    public void updateSchool(School school) {
        this.schoolRepo.save(school);
    }

    //Student Stuff
    public List<Grade> getStudentClasses(Integer studentID) {
        List<Grade> results = this.gradeRepo.findStudentClasses(studentID);
        Collections.sort(results, (Grade a1, Grade a2) -> a1.getAcademicClass().getPeriod() - a2.getAcademicClass().getPeriod());
        return results;
    }

    public String getStudentGradeAverage(Integer studentID, Integer classID) {
        Double earnedPoints = this.gradeRepo.findStudentEarnedPoints(studentID, classID);
        Double possPoints = this.gradeRepo.findStudentPossiblePoints(studentID, classID);
        if (!possPoints.equals(0.0)){
            Double results = ((earnedPoints / possPoints)*100.0);
            if(results == 0.0) {
                return "0.00";
            } else {
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                return numberFormat.format(results);
            }
        }else {
            return "0.00";
        }
    }

    public Page<Grade> getStudentAssignments(Integer period, String aName, String aPoints, Integer studentID, Pageable pageable) {
        Integer aPointsParsed = null;
        if(!aPoints.equals("")) {
            aPointsParsed = Integer.parseInt(aPoints);
        }
        Page<Grade> results = this.gradeRepo.searchForStudentAssignments(period, aName, aPointsParsed, studentID, pageable);
        return results;
    }

    //Teacher page ajax content
    public Integer listAllClasses(SearchTeacherClasses search, Integer teacherID) {
        return classRepo.search(search.getPeriod(), search.getNameForSearch(), search.getIdentifierForSearch(), teacherID).size();
    }

    public Page<AcademicClass> listClasses(SearchTeacherClasses search, Integer teacherID, Pageable pageable) {
        return classRepo.search(search.getPeriod(), search.getNameForSearch(), search.getIdentifierForSearch(), teacherID, pageable);
    }

    public Integer listAllAssignments(SearchTeacherAssign search, Integer teacherID) {
        return gradeRepo.searchAssignments(search.getaPeriod(), search.getaNameForSearch(), search.getaIDForSearch(), search.getaDateForSearch(), search.getaPoints(), teacherID).size();
    }

    public Page<Grade> listAssignments(SearchTeacherAssign search, Integer teacherID, Pageable pageable) {
        return gradeRepo.searchAssignments(search.getaPeriod(), search.getaNameForSearch(), search.getaIDForSearch(), search.getaDateForSearch(), search.getaPoints(), teacherID, pageable);
    }

    public Integer listAllStudents(SearchTeacherStudents search, Integer teacherID) {
        return gradeRepo.searchStudents(search.getsPeriod(), search.getsLastNameForSearch(), search.getsFirstNameForSearch(), search.getsANameForSearch(), search.getsAIDForSearch(), teacherID).size();
    }

    public Page<Grade> listStudents(SearchTeacherStudents search, Integer teacherID, Pageable pageable) {
        return gradeRepo.searchStudents(search.getsPeriod(), search.getsLastNameForSearch(), search.getsFirstNameForSearch(), search.getsANameForSearch(), search.getsAIDForSearch(), teacherID, pageable);
    }

    //Admin page ajax content
    public Integer listAllAdmins(SearchUsersAdmin searchUsersAdmin) {
        return this.adminRepository.findAllWithoutPages(searchUsersAdmin.getLastNameForSearch(), searchUsersAdmin.getFirstNameForSearch(), searchUsersAdmin.getEmailForSearch(), searchUsersAdmin.getTitleForSearch());
    }

    public Page<Admin> listAdmins(SearchUsersAdmin searchUsersAdmin, Pageable pageable) {
        return this.adminRepository.findAllWithPages(searchUsersAdmin.getLastNameForSearch(), searchUsersAdmin.getFirstNameForSearch(), searchUsersAdmin.getEmailForSearch(), searchUsersAdmin.getTitleForSearch(), pageable);
    }

    public Integer listAllTeachers(SearchUsersTeacher searchUsersTeacher) {
        return this.teacherRepository.findAllWithoutPages(searchUsersTeacher.gettLastNameForSearch(), searchUsersTeacher.gettFirstNameForSearch(), searchUsersTeacher.gettEmailForSearch(), searchUsersTeacher.getDepartmentForSearch());
    }

    public Page<Teacher> listTeachers(SearchUsersTeacher searchUsersTeacher, Pageable pageable) {
        return this.teacherRepository.findAllWithPages(searchUsersTeacher.gettLastNameForSearch(), searchUsersTeacher.gettFirstNameForSearch(), searchUsersTeacher.gettEmailForSearch(), searchUsersTeacher.getDepartmentForSearch(), pageable);
    }

    public Integer listAllStudents(SearchUsersStudent searchUsersStudent) {
        return this.studentRepository.findAllWithoutPages(searchUsersStudent.getsLastNameForSearch(), searchUsersStudent.getsFirstNameForSearch(), searchUsersStudent.getsEmailForSearch(), searchUsersStudent.getsIDForSearch(), searchUsersStudent.getGrade());
    }

    public Page<Student> listStudents(SearchUsersStudent searchUsersStudent, Pageable pageable) {
        return this.studentRepository.findAllWithPages(searchUsersStudent.getsLastNameForSearch(), searchUsersStudent.getsFirstNameForSearch(), searchUsersStudent.getsEmailForSearch(), searchUsersStudent.getsIDForSearch(), searchUsersStudent.getGrade(), pageable);
    }

    public void deleteAssignment(Integer assignmentID) {
        //if grade table has no records with earned points
        if(this.gradeRepo.checkForDeletion(assignmentID) != 0) {
            //else do nothing
        } else {
            //delete grade record then delete assignment record
            this.gradeRepo.deleteAssignment(assignmentID);
            this.assignmentRepo.delete(assignmentID);
        }
    }

    public void deleteClass(Integer classID) {
        //if there are students in class do not delete
        if(getStudentRoster(classID).size() != 0) {
            //else do nothing
        } else {
            //delete grade record then delete class record
            this.gradeRepo.deleteClass(classID);
            this.classRepo.delete(classID);
        }
    }
}