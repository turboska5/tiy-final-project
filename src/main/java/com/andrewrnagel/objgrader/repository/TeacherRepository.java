package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew and Jimmy on 9/20/16.
 */

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher getByEmailAddress(String email);
}