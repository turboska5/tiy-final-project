package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Student;
import com.andrewrnagel.objgrader.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew and Jimmy on 9/20/16.
 */

public interface StudentRepository extends JpaRepository<Student, Integer> {
//    Teacher getByEmail(String email);
}