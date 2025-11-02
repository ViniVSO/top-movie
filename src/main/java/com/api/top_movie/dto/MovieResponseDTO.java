package com.api.top_movie.dto;

import java.util.UUID;

public class MovieResponseDTO {
    
    private UUID id;
    private String title;
    private Integer releaseDate;
    private String genre;
    
    public MovieResponseDTO(UUID id, String title, Integer releaseDate, String genre) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    
    
}
