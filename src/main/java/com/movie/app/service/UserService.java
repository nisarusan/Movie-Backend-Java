package com.movie.app.service;


import com.movie.app.dto.UserDto;
import com.movie.app.model.User;
import com.movie.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
//    private final UserRepository repos;
//    public UserService(UserRepository repos) {
//        this.repos = repos;
//    }
//    public UserDto createTeacher(UserDto userDto) {
//        User user = dtoToTeacher(userDto);
//        user.setUsername(userDto.name);
//        repos.save(teacher);
//        userDto.id = user.getId();
//        return userDto;
//    }
//    public List<UserDto> getAllTeachers() {
//        List<UserDto> userList = repos.findAll();
//        List<UserDto> userDtos = new ArrayList<>();
//        for (User user : userList) {
//            UserDto userDto = userDto(user);
//            UserDto.add(userDto);
//        }
//        return userDtos;
//    }
//    //TeacherToDo Mapper functies
//    public UserDto usersToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.id = user.getId();
//        return userDto;
//    }
//
//    //dtoToTeacher
//    public User dtoToUser(UserDto userDto) {
//        User user = new User();
//        return user;
//    }

//    public List<TeacherDto> getAllTeachersAfter(TeacherDto teacherDto) {
//        repos.findByDobAfter()
//    }
}
