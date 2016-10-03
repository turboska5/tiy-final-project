package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Teacher;
import com.andrewrnagel.objgrader.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by doug on 9/20/16.
 */

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
//    Teacher getByEmail(String email);
}