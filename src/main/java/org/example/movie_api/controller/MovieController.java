package org.example.movie_api.controller;


import org.example.movie_api.entity.Movie;
import org.example.movie_api.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/save_popular")
    public String popularMovies() {

        movieService.savePopularMovies();

        return "popular movies...저장성공!";
    }

    @GetMapping("/save_playing")
    public String nowPlayingMovie() {

        movieService.saveNowPlayingMovies();

        return "now play movie...저장성공!";

    }

    // 영화제목 검색하면 리스트로 반환
    @GetMapping("/search_list")
    public List<Movie> searchMoviesList(@RequestParam String title) {

        return movieService.listMovies(title);

    }

    // 영화아이디로 검색시 한개의 영화 반환
    @GetMapping("/search_one/{id}")
    public Movie searchMovieOne(@PathVariable Long id) {

        return movieService.oneMovie(id);

    }
}
