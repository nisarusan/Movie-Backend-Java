package com.movie.app.repository;

import com.movie.app.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByName(String user);
}
