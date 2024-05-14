package org.example.movie_api.service;


import org.example.movie_api.dto.GenreDto;
import org.example.movie_api.dto.GenreListResponse;
import org.example.movie_api.entity.Genre;
import org.example.movie_api.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GenreService {

    private final WebClient webClient;
    private final GenreRepository genreRepository;

    public GenreService(WebClient.Builder webClientBuilder, GenreRepository genreRepository) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OTAyMDA3MzBkNDE0Mjg3ZTJiZjlmOWYxNDdiYWNhNyIsInN1YiI6IjYyZGEyODI1YjM5ZTM1MDA2NzY3NTA3NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.E9Xk9fdU9pIRV2hkhURuDxEzZok2vEcjtVY5FWScrIQ")
                .build();
        this.genreRepository = genreRepository;
    }

//    private WebClient webClient = WebClient.create("https://api.themoviedb.org/3");

    public String saveGenre() {
        List<GenreDto> genres = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/genre/movie/list")
                        .queryParam("language", "ko")
                        .build())
                .retrieve()
                .bodyToMono(GenreListResponse.class)
                .block()
                .getGenres();

        for (GenreDto genre : genres) {
            Genre genre1 = new Genre();
            genre1.setId(genre.getId());
            genre1.setName(genre.getName());
            genreRepository.save(genre1);
        }

        return "genre 데이터 저장완료";

    }
}