package com.barmina.movieapp.service.impl;

import com.barmina.movieapp.exceptions.MovieNotFoundException;
import com.barmina.movieapp.exceptions.ReviewNotFoundException;
import com.barmina.movieapp.model.Movie;
import com.barmina.movieapp.model.Review;
import com.barmina.movieapp.repository.ReviewRepository;
import com.barmina.movieapp.security.authentication.AuthenticationWrapper;
import com.barmina.movieapp.service.MovieService;
import com.barmina.movieapp.service.ReviewService;
import com.barmina.movieapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final MovieService movieService;
    private final UserService userService;
    private final AuthenticationWrapper authenticationWrapper;

    @Override
    public Review create(Integer id, Review review) {
        if (movieService.existsById(id)) {
            var movie = movieService.getById(id);
            review.setMovie(movie);
            review.setTime(LocalDateTime.now());
            Authentication authentication = authenticationWrapper.getAuthentication();
            String email = authentication.getName();
            review.setUser(userService.findByEmail(email));
            var newReview = repository.save(review);
            movieService.updateRating(movie, review.getRating());
            return newReview;
        }
        throw new MovieNotFoundException("Movie with id " + id + " doesn't exists.");
    }

    @Override
    public List<Review> getAllByMovieId(Integer movieId) {
        Movie movie = movieService.getById(movieId);
        return movie.getReviews();
    }

    @Override
    public Review getByMovieIdAndReviewId(Integer movieId, Integer reviewId) {
        List<Review> reviewList =
                Optional.ofNullable(movieService.getById(movieId).getReviews())
                        .orElseThrow(
                                () -> new ReviewNotFoundException("Review with id " + reviewId + " wasn't found"));
        return reviewList.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(
                        () -> new ReviewNotFoundException("Review with id " + reviewId + " wasn't found"));
    }

    @Override
    public void deleteByReviewIdAndMovieId(Integer movieId, Integer reviewId) {
        repository.deleteByReviewIdAndMovieId(reviewId, movieId);
    }

}
