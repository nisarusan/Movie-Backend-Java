package com.movie.springboot3.controller;


import com.movie.springboot3.repository.CourseRepository;
import com.movie.springboot3.repository.LessonRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonController {

    private LessonRepository lessonRepos;
    private CourseRepository courseRepos;
    public LessonController (LessonRepository lessonRepos, CourseRepository courseRepos) {
        this.lessonRepos = lessonRepos;
        this.courseRepos = courseRepos;
    }


}
