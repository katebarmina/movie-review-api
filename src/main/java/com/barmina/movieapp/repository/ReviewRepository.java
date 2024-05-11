package com.barmina.movieapp.repository;

import com.barmina.movieapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.id =:reviewId AND r.movie.id =:movieId")
    void deleteByReviewIdAndMovieId(
            @Param("reviewId") Integer reviewId, @Param("movieId") Integer movieId);

}
