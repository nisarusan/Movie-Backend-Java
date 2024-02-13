package com.movie.springboot3.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherDto {

    public Long id;
    @NotBlank(message = "Lege berichten zijn niet toegestaan")
    @Size(min=3, max=128, message = "Minimaal 3 en maximaal 128 karakters toegestaan")
    public String lastName;
    @Size(min=3, max=128)
    public String name;

    // met message kan je het overschrijven;
    @Past(message = "Mag niet in het verleden zijn")
    public LocalDate dob;
    @Max(10000)
    public int salary;


}

