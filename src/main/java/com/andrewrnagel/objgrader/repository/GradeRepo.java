package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rush on 10/6/16.
 */

public interface GradeRepo extends JpaRepository<Grade, Integer> {
}
