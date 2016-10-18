package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Teacher;
import com.sun.tools.javac.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Andrew and Jimmy on 9/20/16.
 */

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher getByUserId(Integer id);

    @Query(value = "SELECT t FROM Teacher t WHERE (?1 = '' OR upper(t.lastName) LIKE upper(?1)) AND (?2 = '' OR upper(t.firstName) LIKE upper(?2)) AND (?3 = '' OR upper(t.user.email) LIKE upper(?3)) AND (?4 = '' OR upper(t.department) LIKE upper(?4))")
    List<Teacher> findAllWithoutPages(String lastName, String firstName, String email, String department);

    @Query(value = "SELECT t FROM Teacher t WHERE (?1 = '' OR upper(t.lastName) LIKE upper(?1)) AND (?2 = '' OR upper(t.firstName) LIKE upper(?2)) AND (?3 = '' OR upper(t.user.email) LIKE upper(?3)) AND (?4 = '' OR upper(t.department) LIKE upper(?4)) ORDER BY t.lastName ASC")
    Page<Teacher> findAllWithPages(String lastName, String firstName, String email, String department, Pageable pageable);
}