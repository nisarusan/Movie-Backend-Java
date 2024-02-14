package com.movie.springboot3.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private int sp;


    //Een Course kan meerdere teachers hebben;
    @ManyToMany
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    public Long getId() {
        return id;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }
}
