package com.andrewrnagel.repository;

import com.andrewrnagel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by doug on 9/20/16.
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByName(String name);
}