package com.movie.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
