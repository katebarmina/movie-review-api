package com.barmina.movieapp.repository;

import com.barmina.movieapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Modifying
    @Query("UPDATE Movie m SET m.rating = :rating, m.numOfReviews = :numOfReviews WHERE m.id = :movieId")
    void updateRatingAndNumOfReviewsByMovieId(@Param("movieId") int movieId, @Param("rating") double rating,
                                              @Param("numOfReviews") int numOfReviews);

}
