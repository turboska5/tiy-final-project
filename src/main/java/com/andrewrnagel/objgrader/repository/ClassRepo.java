package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.AcademicClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:48 PM EST.
 */

public interface ClassRepo extends JpaRepository<AcademicClass, Integer> {
    @Query(value = "SELECT c FROM AcademicClass c WHERE (?1 IS NULL OR c.period = ?1) AND (?2 = '' OR upper(c.name) LIKE upper(?2)) AND (?3 = '' OR upper(c.identifier) LIKE upper(?3)) AND (?4 = '' OR upper(c.department) LIKE upper(?4)) AND (?5 = '' OR upper(c.teacher.lastName) LIKE upper(?5)) AND (?6 = '' OR upper(c.teacher.firstName) LIKE upper(?6)) AND (?7 IS NULL OR c.teacher.teacherID = ?7)")
    List<AcademicClass> searchClasses(Integer period, String name, String identifier, String department, String teacherLastName, String teacherFirstName, Integer teacherID);

    @Query(value = "SELECT c FROM AcademicClass c WHERE (?1 IS NULL OR c.period = ?1) AND (?2 = '' OR upper(c.name) LIKE upper(?2)) AND (?3 = '' OR upper(c.identifier) LIKE upper(?3)) AND (?4 = '' OR upper(c.department) LIKE upper(?4)) AND (?5 = '' OR upper(c.teacher.lastName) LIKE upper(?5)) AND (?6 = '' OR upper(c.teacher.firstName) LIKE upper(?6)) AND (?7 IS NULL OR c.teacher.teacherID = ?7)")
    Page<AcademicClass> searchClasses(Integer period, String name, String identifier, String department, String teacherLastName, String teacherFirstName, Integer teacherID, Pageable pageable);

    List<AcademicClass> getByTeacherTeacherID(Integer teacherID);

    @Query(value = "SELECT c FROM AcademicClass c WHERE (?1 IS NULL OR c.period = ?1) AND (?2 = '' OR upper(c.name) LIKE upper(?2)) AND (?3 = '' OR upper(c.identifier) LIKE upper(?3)) AND (?4 IS NULL OR c.teacher.teacherID = ?4)")
    List<AcademicClass> search(Integer period, String nameForSearch, String identifierForSearch, Integer teacherID);

    @Query(value = "SELECT c FROM AcademicClass c WHERE (?1 IS NULL OR c.period = ?1) AND (?2 = '' OR upper(c.name) LIKE upper(?2)) AND (?3 = '' OR upper(c.identifier) LIKE upper(?3)) AND (?4 IS NULL OR c.teacher.teacherID = ?4)")
    Page<AcademicClass> search(Integer period, String nameForSearch, String identifierForSearch, Integer teacherID, Pageable pageable);
}