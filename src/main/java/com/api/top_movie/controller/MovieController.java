package com.api.top_movie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.top_movie.dto.ExternalMovieDTO;
import com.api.top_movie.dto.MovieRequestDTO;
import com.api.top_movie.dto.MovieResponseDTO;
import com.api.top_movie.service.MovieService;
import com.api.top_movie.service.TmdbMovieService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;
    private TmdbMovieService tmdbMovieService;

    public MovieController(MovieService movieService, TmdbMovieService tmdbMovieService) {
        this.movieService = movieService;
        this.tmdbMovieService = tmdbMovieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@RequestBody MovieRequestDTO dto) {
        MovieResponseDTO saved = movieService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAll() {

        List<MovieResponseDTO> movies = movieService.listAll();

        if (movies.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @GetMapping("/external")
    public ResponseEntity<List<ExternalMovieDTO>> getExternalMovie(@RequestParam String title) {
        List<ExternalMovieDTO> movies = tmdbMovieService.searchMovies(title);
        if (movies.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getById(@PathVariable UUID id) {

        Optional<MovieResponseDTO> movie = movieService.findById(id);

        if (!movie.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(movie.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable UUID id, @RequestBody MovieRequestDTO dto) {

        Optional<MovieResponseDTO> updated = movieService.update(id, dto);

        if (!updated.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> delete(@PathVariable UUID id) {

        boolean deleted = movieService.delete(id);

        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

}
