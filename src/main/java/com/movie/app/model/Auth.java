package com.movie.app.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Auth implements Serializable {


    //Creates authorities role
    @Id
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
