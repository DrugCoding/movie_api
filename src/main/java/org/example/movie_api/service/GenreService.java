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

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private WebClient webClient = WebClient.create("https://api.themoviedb.org/3");

    public void saveGenre() {
        List<GenreDto> genres = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/genre/movie/list")
                        .queryParam("api_key", "990200730d414287e2bf9f9f147baca7")
                        .queryParam("language", "ko")
                        .build())
                .retrieve()
                .bodyToMono(GenreListResponse.class)
                .block()
                .getGenres();
//                .retrieve()
//                .bodyToFlux(GenreDto.class)
//                .collectList()
//                .block();

//        for (GenreDto genre : genres) {
//            Genre entity = new Genre(genre.getId(), genre.getName());
//            genreRepository.save(entity);
//        }

        for (GenreDto genre : genres) {
            Genre genre1 = new Genre();
            genre1.setId(genre.getId());
            genre1.setName(genre.getName());
            genreRepository.save(genre1);
        }
    }
}