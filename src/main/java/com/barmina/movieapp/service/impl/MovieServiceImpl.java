package com.barmina.movieapp.service.impl;

import com.barmina.movieapp.exceptions.MovieNotFoundException;
import com.barmina.movieapp.model.Movie;
import com.barmina.movieapp.repository.MovieRepository;
import com.barmina.movieapp.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Override
    public Movie create(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public List<Movie> getAll() {
        return repository.findAll();
    }

    @Override
    public Movie getById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " doesn't exist."));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Movie update(Movie newMovie, Integer id) {
        return repository
                .findById(id)
                .map(
                        movie -> {
                            movie.setName(newMovie.getName());
                            movie.setYear(newMovie.getYear());
                            movie.setGenre(newMovie.getGenre());
                            movie.setImage(newMovie.getImage());
                            movie.setLanguage(newMovie.getLanguage());
                            movie.setPlot(newMovie.getPlot());
                            movie.setRating(newMovie.getRating());
                            return repository.save(movie);
                        })
                .orElseGet(
                        () -> {
                            newMovie.setId(id);
                            return repository.save(newMovie);
                        });
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Transactional
    @Override
    public void updateRating( Movie movie, double rating) {
        var numOfReviews = movie.getNumOfReviews() + 1;
        double recalculatedRating = (movie.getRating() + rating) / numOfReviews;
        repository.updateRatingAndNumOfReviewsByMovieId(movie.getId(), recalculatedRating, numOfReviews);
    }

}
