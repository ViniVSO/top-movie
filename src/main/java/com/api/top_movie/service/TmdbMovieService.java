package com.api.top_movie.service;

import com.api.top_movie.dto.ExternalMovieDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Service
public class TmdbMovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private Map<Integer, String> genreMap = new HashMap<>();

    @PostConstruct
    public void loadGenres() {
        String url = String.format("%s/genre/movie/list?api_key=%s&language=pt-BR", apiUrl, apiKey);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null && response.get("genres") != null) {
            List<Map<String, Object>> genres = (List<Map<String, Object>>) response.get("genres");
            for (Map<String, Object> g : genres) {
                Integer id = ((Number) g.get("id")).intValue();
                String name = (String) g.get("name");
                genreMap.put(id, name);
            }
        }
    }

    public List<ExternalMovieDTO> searchMovies(String title) {
        String encodedTitle = java.net.URLEncoder.encode(title, java.nio.charset.StandardCharsets.UTF_8);
        String url = String.format("%s/search/movie?api_key=%s&query=%s", apiUrl, apiKey, encodedTitle);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<ExternalMovieDTO> movies = new ArrayList<>();

        if (response != null && response.get("results") != null) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

            for (Map<String, Object> item : results) {
                List<?> genreIdsRaw = (List<?>) item.get("genre_ids");
                String firstGenre = "Desconhecido";
                if (genreIdsRaw != null && !genreIdsRaw.isEmpty()) {
                    Integer genreId = ((Number) genreIdsRaw.get(0)).intValue();
                    firstGenre = genreMap.getOrDefault(genreId, "Desconhecido");
                }

                int year = 0;
                if (item.get("release_date") != null && !((String) item.get("release_date")).isEmpty()) {
                    year = Integer.parseInt(((String) item.get("release_date")).split("-")[0]);
                }

                ExternalMovieDTO dto = new ExternalMovieDTO(
                        (String) item.get("title"),
                        year,
                        firstGenre
                );

                movies.add(dto);
            }
        }

        return movies;
    }
}
