package org.example.movie_api.controller;


import org.example.movie_api.entity.Movie;
import org.example.movie_api.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/popular")
    public String popularMovies() {
        movieService.savePopularMovies();
        return "popular movies...저장성공!";
    }

    @GetMapping("/playing")
    public String nowPlayingMovie() {
        movieService.saveNowPlayingMovies();
        return "now play movie...저장성공!";
    }

    @GetMapping("/search")
    public List<Movie> searchMovies() {
        return movieService.listMovies();
    }
}
