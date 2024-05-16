package org.example.movie_api.repository;

import org.example.movie_api.dto.MovieSearchDto;
import org.example.movie_api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContaining(String title);
}
