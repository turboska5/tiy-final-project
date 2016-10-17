package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Andrew and Jimmy on 9/20/16.
 */

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin getByUserId(Integer id);

//    @Query(value = "SELECT a FROM Admin a WHERE (?1 = '' OR upper(a.lastName) LIKE upper(?1)) AND (?2 = '' OR upper(a.firstName) LIKE upper(?2)) AND (?3 = '' OR upper(a.user.email) LIKE upper(?3)) AND (?4 = '' OR upper(a.title) LIKE upper(?4))")
//    List<Admin> searchForAdmins(String lastName, String firstName, String email, String title);
}