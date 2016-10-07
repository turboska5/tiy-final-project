package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rush on 10/6/16.
 */

public interface GradeRepo extends JpaRepository<Grade, Integer> {
    List<Grade> countDistinctStudentIDByClassID(Integer classID);
}
