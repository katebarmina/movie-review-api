package com.barmina.movieapp.service;

import com.barmina.movieapp.model.Review;

import java.util.List;

public interface ReviewService {

  Review create(Integer id, Review review);

  List<Review> getAllByMovieId(Integer movieId);

  Review getByMovieIdAndReviewId(Integer movieId, Integer reviewId);

  void deleteByReviewIdAndMovieId(Integer movieId, Integer reviewId);
}
