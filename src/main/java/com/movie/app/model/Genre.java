package com.movie.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

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
