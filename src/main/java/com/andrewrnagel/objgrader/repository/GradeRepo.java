package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Grade;
import com.andrewrnagel.objgrader.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Created by Jimmy on 10/6/16.
 */

public interface GradeRepo extends JpaRepository<Grade, Integer> {
    List<Grade> findByAcademicClassClassID(Integer classID);

    @Query(value = "SELECT DISTINCT g.student FROM Grade g WHERE (g.academicClass.classID = ?1)")
    List<Student> getStudentRoster(Integer classID);
}
