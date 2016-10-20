package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Grade;
import com.andrewrnagel.objgrader.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Jimmy on 10/6/16.
 * Modified by Andrew on 10/7/16.
 */

public interface GradeRepo extends JpaRepository<Grade, Integer> {
    List<Grade> findByAcademicClassClassID(Integer classID);

    List<Grade> findByAssignmentAssignmentID(Integer assignmentID);

    @Query(value = "SELECT DISTINCT g.student FROM Grade g WHERE (g.academicClass.classID = ?1)")
    List<Student> getStudentRoster(Integer classID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Grade g WHERE (g.academicClass.classID = ?1 AND g.student.studentID = ?2)")
    void removeStudentFromClass(Integer classID, Integer studentID);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.assignment.assignmentName) LIKE upper(?2)) AND (?3 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?3)) AND (?4 IS NULL OR g.assignment.date = ?4) AND (?5 IS NULL OR g.possPoints = ?5) AND g.student.studentID IS NULL AND g.academicClass.teacher.teacherID IS ?6")
    List<Grade> searchForTeacherAssignments(Integer aPeriod, String aName, String aID, LocalDate aDate, Integer aPoints, Integer teacherID);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.assignment.assignmentName) LIKE upper(?2)) AND (?3 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?3)) AND (?4 IS NULL OR g.assignment.date = ?4) AND (?5 IS NULL OR g.possPoints = ?5) AND g.student.studentID IS NULL AND g.academicClass.teacher.teacherID IS ?6")
    Page<Grade> searchForTeacherAssignments(Integer aPeriod, String aName, String aID, LocalDate aDate, Integer aPoints, Integer teacherID, Pageable pageable);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.student.lastName) LIKE upper(?2)) AND (?3 = '' OR upper(g.student.firstName) LIKE upper(?3)) AND (?4 = '' OR upper(g.assignment.assignmentName) LIKE upper(?4)) AND (?5 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?5)) AND g.academicClass.teacher.teacherID IS ?6")
    List<Grade> searchForTeacherStudents(Integer sPeriod, String sLastName, String sFirstName, String sAName, String sAID, Integer teacherID);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.student.lastName) LIKE upper(?2)) AND (?3 = '' OR upper(g.student.firstName) LIKE upper(?3)) AND (?4 = '' OR upper(g.assignment.assignmentName) LIKE upper(?4)) AND (?5 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?5)) AND g.academicClass.teacher.teacherID IS ?6")
    Page<Grade> searchForTeacherStudents(Integer sPeriod, String sLastName, String sFirstName, String sAName, String sAID, Integer teacherID, Pageable pageable);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.student.lastName) LIKE upper(?2)) AND (?3 = '' OR upper(g.student.firstName) LIKE upper(?3)) AND (?4 = '') AND (?5 = '')")
    List<Grade> searchForTeacherStudents(Integer sPeriod, String sLastName, String sFirstName, String sID, Integer gradeLevel);

    @Query(value = "SELECT g FROM Grade g WHERE (g.academicClass.classID = ?1) AND g.student.studentID IS NULL")
    List<Grade> getClassAssignments(Integer academicClassID);

    @Query(value = "SELECT g FROM Grade g WHERE (g.student.studentID = ?1) AND g.assignment IS NOT NULL")
    List<Grade> findByStudentStudentID(Integer studentID);

    @Query(value = "SELECT DISTINCT g FROM Grade g WHERE (g.student.studentID = ?1) AND g.assignment IS NULL")
    List<Grade> findStudentClasses(Integer studentID);

    @Query(value = "SELECT COALESCE(SUM(g.earnedPoints), 0) FROM Grade AS g WHERE g.student.studentID = ?1 AND g.academicClass.classID = ?2 AND g.earnedPoints IS NOT NULL")
    Double findStudentEarnedPoints(Integer studentID, Integer classID);

    @Query(value = "SELECT COALESCE(SUM(g.possPoints), 0) FROM Grade AS g WHERE g.student.studentID = ?1 AND g.academicClass.classID = ?2")
    Double findStudentPossiblePoints(Integer studentID, Integer classID);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.assignment.assignmentName) LIKE upper(?2)) AND (?3 IS NULL OR g.possPoints = ?3) AND g.student.studentID IS ?4")
    List<Grade> searchForStudentAssignments(Integer period, String aName, Integer aPointsParsed, Integer studentID);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.assignment.assignmentName) LIKE upper(?2)) AND (?3 IS NULL OR g.possPoints = ?3) AND g.student.studentID IS ?4 ORDER BY g.academicClass.period, g.assignment.date ASC")
    Page<Grade> searchForStudentAssignments(Integer period, String aName, Integer aPointsParsed, Integer studentID, Pageable pageable);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.assignment.assignmentName) LIKE upper(?2)) AND (?3 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?3)) AND (?4 IS NULL OR g.assignment.date = ?4) AND (?5 IS NULL OR g.possPoints = ?5) AND g.student.studentID IS NULL AND g.academicClass.teacher.teacherID IS ?6")
    List<Grade> searchAssignments(Integer aPeriod, String aName, String aID, LocalDate aDate, Integer aPointsParsed, Integer teacherID);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.assignment.assignmentName) LIKE upper(?2)) AND (?3 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?3)) AND (?4 IS NULL OR g.assignment.date = ?4) AND (?5 IS NULL OR g.possPoints = ?5) AND g.student.studentID IS NULL AND g.academicClass.teacher.teacherID IS ?6 ORDER BY g.academicClass.period, g.assignment.date ASC")
    Page<Grade> searchAssignments(Integer aPeriod, String aName, String aID, LocalDate aDate, Integer aPointsParsed, Integer teacherID, Pageable pageable);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.student.lastName) LIKE upper(?2)) AND (?3 = '' OR upper(g.student.firstName) LIKE upper(?3)) AND (?4 = '' OR upper(g.assignment.assignmentName) LIKE upper(?4)) AND (?5 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?5)) AND g.academicClass.teacher.teacherID IS ?6")
    List<Grade> searchStudents(Integer sPeriod, String sLastName, String sFirstName, String sAName, String sAID, Integer teacherID);

    @Query(value = "SELECT g FROM Grade g WHERE (?1 IS NULL OR g.academicClass.period = ?1) AND (?2 = '' OR upper(g.student.lastName) LIKE upper(?2)) AND (?3 = '' OR upper(g.student.firstName) LIKE upper(?3)) AND (?4 = '' OR upper(g.assignment.assignmentName) LIKE upper(?4)) AND (?5 = '' OR upper(g.assignment.assignmentIDNumber) LIKE upper(?5)) AND g.academicClass.teacher.teacherID IS ?6 ORDER BY g.academicClass.period, g.student.lastName, g.assignment.date ASC")
    Page<Grade> searchStudents(Integer sPeriod, String sLastName, String sFirstName, String sAName, String sAID, Integer teacherID, Pageable pageable);

    @Query(value = "SELECT COUNT(g) FROM Grade g WHERE (?1 IS g.assignment.assignmentID) AND g.earnedPoints IS NOT NULL")
    int checkForDeletion(Integer assignmentID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Grade g WHERE (g.assignment.assignmentID = ?1)")
    void deleteAssignment(Integer assignmentID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Grade g WHERE (g.academicClass.classID = ?1)")
    void deleteClass(Integer classID);
}