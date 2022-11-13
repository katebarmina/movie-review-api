package com.barmina.movieapp.service.impl;

import com.barmina.movieapp.exceptions.ReviewNotFoundException;
import com.barmina.movieapp.model.Movie;
import com.barmina.movieapp.model.Review;
import com.barmina.movieapp.model.Role;
import com.barmina.movieapp.model.User;
import com.barmina.movieapp.repository.ReviewRepository;
import com.barmina.movieapp.security.authentication.AuthenticationWrapper;
import com.barmina.movieapp.service.MovieService;
import com.barmina.movieapp.service.ReviewService;
import com.barmina.movieapp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ConditionalOnEnabledResourceChain
@ContextConfiguration
@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private MovieService movieService;

    @Mock
    private AuthenticationWrapper authenticationWrapper;

    @Mock
    private UserService userService;
    private ReviewService reviewService;


    @BeforeEach
    void setUp() {
        reviewService = new ReviewServiceImpl(reviewRepository, movieService, userService, authenticationWrapper);
    }

    @Test
    void create() {
        Review review = new Review();
        review.setContent("Content");
        final String email = "jane@gmail.com";
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, "password", List.of(Role.ROLE_USER));
        given(authenticationWrapper.getAuthentication()).willReturn(authentication);
        given(userService.findByEmail(email)).willReturn(new User());
        reviewService.create(1, review);
        ArgumentCaptor<Review> argumentCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewRepository).save(argumentCaptor.capture());
        Assertions.assertEquals(review, argumentCaptor.getValue());

    }

    @Test
    void getAllByMovieId() {
        given(movieService.getById(1)).willReturn(new Movie());
        reviewService.getAllByMovieId(1);
        verify(movieService).getById(1);
    }

    @Test
    void canGetByMovieAndReviewId() {
        Movie movie = new Movie();
        Review review = new Review();
        review.setId(2);
        movie.setReviews(List.of(review));
        given(movieService.getById(1)).willReturn(movie);
        Assertions.assertEquals(reviewService.getByMovieIdAndReviewId(1, 2), review);

    }

    @Test
    void willThrowExceptionIfReviewListIsNull() {
        given(movieService.getById(1)).willReturn(new Movie());
        assertThatThrownBy(() -> reviewService.getByMovieIdAndReviewId(1, 1))
                .isInstanceOf(ReviewNotFoundException.class)
                .hasMessageContaining("Review with id " + 1 + " wasn't found");
    }

    @Test
    void willThrowExceptionIfNotFound() {
        Movie movie = new Movie();
        Review review = new Review();
        review.setId(2);
        movie.setReviews(List.of(review));
        given(movieService.getById(1)).willReturn(movie);
        assertThatThrownBy(() -> reviewService.getByMovieIdAndReviewId(1, 1))
                .isInstanceOf(ReviewNotFoundException.class)
                .hasMessageContaining("Review with id " + 1 + " wasn't found");
    }


    @Test
    void deleteById() {
      reviewService.deleteByReviewIdAndMovieId(anyInt(),anyInt());
     verify(reviewRepository).deleteByReviewIdAndMovieId(anyInt(),anyInt());
    }
}