package org.example.movie_api.genre.repository;


import org.example.movie_api.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<Genre, Long> {
}
