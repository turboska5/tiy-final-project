package com.andrewrnagel.objgrader.repository;

import com.andrewrnagel.objgrader.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Andrew and Jimmy on 9/20/16.
 */

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getByUserId(Integer id);

//    @Query(value = "SELECT s FROM Student s WHERE (?1 = '' OR upper(s.lastName) LIKE upper(?1)) AND (?2 = '' OR upper(s.firstName) LIKE upper(?2)) AND (?3 = '' OR upper(s.user.email) LIKE upper(?3)) AND (?4 = '' OR upper(s.studentNumber) LIKE upper(?4) AND (?5 = '' OR upper(s.gradeLevel) LIKE upper(?5))")
//    List<Student> searchForStudents(String lastName, String firstName, String email, String studentID, String grade);
}