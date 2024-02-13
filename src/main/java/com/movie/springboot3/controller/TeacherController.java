package com.movie.springboot3.controller;

import com.movie.springboot3.dto.TeacherDto;
import com.movie.springboot3.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List <TeacherDto> teacherDto = service.getAllTeachers();
         return ResponseEntity.ok().body(teacherDto);
    }

//    @GetMapping("/after")
//    public ResponseEntity<List<TeacherDto>> getAllTeachersAfter(@RequestParam LocalDate date) {
//        List<TeacherDto> teacherDto = service.getAllTeachersAfter(LocalDate date);
//        return ResponseEntity.ok(service.findByDobAfter(date));
//    }

    //Object van gemaakt, omdat situatie is waarin twee verschillende types er zijn;
    @PostMapping
    public ResponseEntity<Object> addTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) {
        if(br.hasFieldErrors()) {
           StringBuilder sb = new StringBuilder();
            for(FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : " );
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
           }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
        teacherDto = service.createTeacher(teacherDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + teacherDto.id).toUriString());
       return ResponseEntity.created(uri).body(teacherDto);
       }
    }
}
