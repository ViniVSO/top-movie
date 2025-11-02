package com.api.top_movie.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.top_movie.dto.MovieRequestDTO;
import com.api.top_movie.dto.MovieResponseDTO;
import com.api.top_movie.model.Movie;
import com.api.top_movie.repository.MovieRepository;
import com.api.top_movie.utils.Utils;

@Service
public class MovieService {
    
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDTO> listAll() {
        return movieRepository.findAll()
                .stream()
                .map(m -> new MovieResponseDTO(m.getId(), m.getTitle(), m.getReleaseDate(), m.getGenre()))
                .collect(Collectors.toList());
    }

    public Optional<MovieResponseDTO> findById(UUID id) {
        return movieRepository.findById(id)
                .map(m -> new MovieResponseDTO(m.getId(), m.getTitle(), m.getReleaseDate(), m.getGenre()));
    }

    public MovieResponseDTO save(MovieRequestDTO dto) {
        Movie movie = new Movie(dto.getTitle(), dto.getReleaseDate(), dto.getGenre());
        Movie saved = movieRepository.save(movie);

        return new MovieResponseDTO(saved.getId(), saved.getTitle(), saved.getReleaseDate(), saved.getGenre());
    }

    public Optional<MovieResponseDTO> update(UUID id, MovieRequestDTO dto) {
        return movieRepository.findById(id)
            .map(existing -> {

                Utils.copyNonNullProperties(dto, existing);

                Movie updated = movieRepository.save(existing);
                return new MovieResponseDTO(
                        updated.getId(),
                        updated.getTitle(),
                        updated.getReleaseDate(),
                        updated.getGenre()
                );
            });
    }

    public boolean delete(UUID id) {
        if (!movieRepository.existsById(id)) return false;

        movieRepository.deleteById(id);
        
        return true;
    }

}
