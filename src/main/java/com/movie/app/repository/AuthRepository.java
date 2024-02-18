package com.movie.app.repository;

import com.movie.app.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String user);
}
