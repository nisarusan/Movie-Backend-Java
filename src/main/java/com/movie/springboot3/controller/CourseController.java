package com.movie.springboot3.controller;
import com.movie.springboot3.dto.CourseDto;
import com.movie.springboot3.model.Course;
import com.movie.springboot3.model.Teacher;
import com.movie.springboot3.repository.CourseRepository;
import com.movie.springboot3.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    //LET OP GEBRUIK ALTIJD EEN SERVICE, IPV WAT WE NU DOEN REPOSITORIES)
    private final CourseRepository courseRepos;
    private final TeacherRepository teacherRepos;
    public CourseController(CourseRepository courseRepos, TeacherRepository teacherRepos) {
    this.courseRepos = courseRepos;
    this.teacherRepos = teacherRepos;
}

@PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        Course course = new Course();
        course.setTitle(courseDto.title);
        course.setSp(courseDto.sp);
        for(Long id :  courseDto.teacherIds) {
            Optional<Teacher> ot = teacherRepos.findById(id);
            if(ot.isPresent()) {
                Teacher teacher = ot.get();
                course.getTeachers().add(teacher);
            }
        }
        courseRepos.save(course);
        courseDto.id = course.getId();
        return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
}

}
