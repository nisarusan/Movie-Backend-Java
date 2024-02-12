package com.movie.springboot3.controller;

import com.movie.springboot3.model.Teacher;
import com.movie.springboot3.repository.TeacherRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
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

    @GetMapping("/after")
    public ResponseEntity<List<Teacher>> getTeachersAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(teacherRepository.findByDobAfter(date));
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + teacher.getId()).toUriString());
       return ResponseEntity.created(uri).body(teacher);
    }

}
