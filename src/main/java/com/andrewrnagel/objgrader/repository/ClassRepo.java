package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:48 PM EST.
 */

public interface ClassRepo extends JpaRepository<AcademicClass, Integer> {
    @Query(value = "SELECT c FROM AcademicClass c WHERE (?1 IS NULL OR c.period = ?1)")
    List<AcademicClass> searchClasses(Integer period);

//    @Query(value = "SELECT w FROM Widget w WHERE (?1 = '' OR upper(w.name) LIKE upper(?1)) AND (?2 IS NULL OR w.type.id = ?2) AND (?3 IS NULL OR w.id = ?3)")
//    List<Widget> search(String name, Integer typeId, Integer id);
//
//    @Query(value = "SELECT w FROM Widget w WHERE (?1 = '' OR upper(w.name) LIKE upper(?1)) AND (?2 IS NULL OR w.type.id = ?2) AND (?3 IS NULL OR w.id = ?3)")
//    Page<Widget> search(String name, Integer typeId, Integer id, Pageable pageable);
//
//    List<AcademicClass> findByPeriodOrNameContainingOrIdentifierContainingOrDepartmentContainingOrTeacherLastNameContainingOrTeacherFirstNameContaining(Integer academicClassPeriod, String academicClassName, String academicClassIdentifier, String academicClassDepartment, String teacherLastName, String teacherFirstName);
//
//    List<AcademicClass> findByPeriodOrNameOrIdentifierOrDepartmentOrTeacherLastNameOrTeacherFirstNameOrTeacherTeacherIDContaining(Integer academicClassPeriod, String academicClassName, String academicClassIdentifier, String academicClassDepartment, String teacherLastName, String teacherFirstName, Integer teacherID);
}