package com.andrewrnagel.repository;

import com.andrewrnagel.entity.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew Nagel on 9/28/16 at 2:48 PM EST.
 */

public interface ClassRepo extends JpaRepository<AcademicClass, Integer> {
}