package com.barmina.movieapp.controller;

import com.barmina.movieapp.model.Review;
import com.barmina.movieapp.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.CollectionModel.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@AllArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/movies/{movieId}/reviews")
    public CollectionModel<EntityModel<Review>> getAllById(@PathVariable Integer movieId) {
        List<Review> reviews = reviewService.getAllByMovieId(movieId);
        List<EntityModel<Review>> modelList = new ArrayList<>();
        for (Review review : reviews) {
            Link link = linkTo(ReviewController.class).slash(review.getId()).withSelfRel();
            modelList.add(EntityModel.of(review).add(link));
        }
        return of(modelList).add(linkTo(ReviewController.class).withSelfRel());
    }

    @GetMapping("/movies/{movieId}/reviews/{reviewId}")
    public EntityModel<Review> getById(
            @PathVariable Integer movieId, @PathVariable Integer reviewId) {
        Link link = linkTo(ReviewController.class).slash(movieId).slash(reviewId).withSelfRel();
        return EntityModel.of(reviewService.getByMovieIdAndReviewId(movieId, reviewId)).add(link);
    }

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<Review> create(@PathVariable Integer movieId, @RequestBody Review review) {
        Review savedReview = reviewService.create(movieId, review);
        URI location = linkTo(ReviewController.class).slash(movieId).slash(savedReview.getId()).toUri();
        return created(location).build();
    }

    @DeleteMapping("/movies/{movieId}/reviews/{reviewId}")
    public ResponseEntity<Review> delete(
            @PathVariable Integer movieId, @PathVariable Integer reviewId) {
        reviewService.deleteByReviewIdAndMovieId(movieId, reviewId);
        return noContent().build();
    }

}
