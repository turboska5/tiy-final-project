package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:48 PM EST.
 */

public interface ClassRepo extends JpaRepository<AcademicClass, Integer> {
    List<AcademicClass> findByPeriodOrNameContainingOrIdentifierContainingOrDepartmentContainingOrTeacherLastNameContainingOrTeacherFirstNameContaining(Integer academicClassPeriod, String academicClassName, String academicClassIdentifier, String academicClassDepartment, String teacherLastName, String teacherFirstName);

    List<AcademicClass> findByPeriodOrNameOrIdentifierOrDepartmentOrTeacherLastNameOrTeacherFirstNameOrTeacherTeacherIDContaining(Integer academicClassPeriod, String academicClassName, String academicClassIdentifier, String academicClassDepartment, String teacherLastName, String teacherFirstName, Integer teacherID);
}