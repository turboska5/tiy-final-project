package com.andrewrnagel.objgrader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.andrewrnagel.objgrader.entity.School;

/**
 * Created by Andrew Nagel on 10/12/16 at 4:19 PM EST.
 */

public interface SchoolRepo extends JpaRepository<School, Integer> {

}