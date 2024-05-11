package com.barmina.movieapp.service;

import com.barmina.movieapp.model.Movie;

import java.util.List;

public interface MovieService {

    Movie create(Movie movie);

    List<Movie> getAll();

    Movie getById(Integer id);

    void deleteById(Integer id);

    Movie update(Movie newMovie, Integer id);

    boolean existsById(Integer id);

    void updateRating(Movie movie, double rating);
}
