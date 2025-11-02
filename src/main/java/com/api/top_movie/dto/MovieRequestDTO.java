package com.api.top_movie.dto;

public class MovieRequestDTO {
    
    private String title;
    private Integer releaseDate;
    private String Genre;
    
    public MovieRequestDTO() {
    }

    public MovieRequestDTO(String title, Integer releaseDate, String genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        Genre = genre;
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
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

}
