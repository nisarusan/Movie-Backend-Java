package com.movie.springboot3.service;


import com.movie.springboot3.dto.TeacherDto;
import com.movie.springboot3.model.Teacher;
import com.movie.springboot3.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repos;
    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = dtoToTeacher(teacherDto);
        teacher.setName(teacherDto.name);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        teacher.setSalary(teacherDto.salary);
        repos.save(teacher);
        teacherDto.id = teacher.getId();
        return teacherDto;
    }
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachersList = repos.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for (Teacher teacher : teachersList) {
            TeacherDto teacherDto = teachersToDto(teacher);
            teacherDtos.add(teacherDto);
        }
        return teacherDtos;
    }
    //TeacherToDo Mapper functies
    public TeacherDto teachersToDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.id = teacher.getId();
        teacherDto.dob = teacher.getDob();
        teacherDto.name = teacher.getName();
        teacherDto.salary = teacher.getSalary();
        teacherDto.lastName = teacher.getLastName();
        return teacherDto;
    }

    //dtoToTeacher
    public Teacher dtoToTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setSalary(teacherDto.salary);
        teacher.setDob(teacherDto.dob);
        teacher.setLastName(teacherDto.lastName);
        teacher.setName(teacherDto.name);
        return teacher;
    }

//    public List<TeacherDto> getAllTeachersAfter(TeacherDto teacherDto) {
//        repos.findByDobAfter()
//    }
}
