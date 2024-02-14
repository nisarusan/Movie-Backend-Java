package com.movie.springboot3.repository;

import com.movie.springboot3.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
