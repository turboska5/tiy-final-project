package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew Nagel on 10/13/16 at 10:41 AM EST.
 */

public interface PhotoRepo extends JpaRepository<Photo, Integer> {
}
