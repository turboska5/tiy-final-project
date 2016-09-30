package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by doug on 9/20/16.
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);
}