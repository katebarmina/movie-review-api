package com.barmina.movieapp.service.impl;

import com.barmina.movieapp.exceptions.MovieNotFoundException;
import com.barmina.movieapp.model.Movie;
import com.barmina.movieapp.repository.MovieRepository;
import com.barmina.movieapp.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository repository;

    private MovieService service;

    @BeforeEach
    void setUp() {
        service = new MovieServiceImpl(repository);
    }

    @Test
    void willThrowExceptionWhenDoesntExist() {
        given(repository.findById(1)).willReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(1))
                .isInstanceOf(MovieNotFoundException.class)
                .hasMessageContaining("Movie with id " + 1 + " doesn't exist.");
    }

    @Test
    void canCreate() {
        Movie movie = new Movie();
        movie.setName("Matrix");
        movie.setPlot("Some plot");
        movie.setGenre("Action");
        movie.setImage("image");
        movie.setLanguage("English");
        movie.setRating(4.4);
        movie.setYear(1993);
        service.create(movie);
        ArgumentCaptor<Movie> movieArgumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(repository).save(movieArgumentCaptor.capture());
        Assertions.assertEquals(movie, movieArgumentCaptor.getValue());
    }

    @Test
    void canGetAll() {
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    void canGetById() {
        given(repository.findById(1)).willReturn(Optional.of(new Movie()));
        service.getById(1);
        verify(repository).findById(1);
    }

    @Test
    void canDeleteById() {
        service.deleteById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void canUpdate() {
        Movie movie = new Movie();
        movie.setName("Matrix");
        movie.setPlot("Some plot");
        movie.setGenre("Action");
        movie.setImage("image");
        movie.setLanguage("English");
        movie.setRating(4.4);
        movie.setYear(1993);
        service.update(movie, 2);
        ArgumentCaptor<Movie> argumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals(movie, argumentCaptor.getValue());
    }
}