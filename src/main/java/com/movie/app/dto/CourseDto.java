package com.movie.app.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CourseDto {

    public Long id;
    @NotBlank
    public String title;
    public int sp;

    public List<Long> teacherIds;
}
