package com.movie.app.repository;

import com.movie.app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public List<Teacher> findByDobAfter(LocalDate date);
}
