package com.api.top_movie.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.top_movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    
}
