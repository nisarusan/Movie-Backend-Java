package com.movie.app.controller;


import com.movie.app.repository.CourseRepository;
import com.movie.app.repository.LessonRepository;
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
