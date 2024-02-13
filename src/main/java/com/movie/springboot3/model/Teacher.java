package com.movie.springboot3.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length=255)
    private String name;

    @Column(name = "last_name", length=255)
    private String lastName;

    private LocalDate dob;

    private int salary;

    // Getters and setters (omitted for brevity)

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
