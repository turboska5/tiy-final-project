package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Admin;
import com.sun.tools.javac.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Andrew and Jimmy on 9/20/16.
 */

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin getByUserId(Integer id);

    @Query
    List<Admin> findAllWithoutPages(SearchUsersAdmin searchUsersAdmin);

    @Query
    Page<Admin> findAllWithPages(SearchUsersAdmin searchUsersAdmin, Pageable pageable);
}