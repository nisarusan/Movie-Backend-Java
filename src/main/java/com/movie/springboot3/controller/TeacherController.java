package com.movie.springboot3.controller;

import com.movie.springboot3.model.Teacher;
import com.movie.springboot3.repository.TeacherRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    //Auto wired methode is er, maar er is ook d.m.v. een object zelf te maken
    @Autowired
    private TeacherRepository teacherRepository;
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
         return ResponseEntity.ok(teacherRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
       return ResponseEntity.ok(teacherRepository.save(teacher));
    }

}
