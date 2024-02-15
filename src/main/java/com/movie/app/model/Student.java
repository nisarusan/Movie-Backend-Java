package com.movie.app.model;

import jakarta.persistence.*;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "numbers")
    int number;


    //default constructor
    public Student() {

    }
    public Student(String fullName, int number) {
        this.fullName = fullName;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
