package com.barmina.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  Integer id;

  @Column
  @Size(min = 10, max = 400, message = "Content must be more than 10 and less than 400 symbols.")
  @NotBlank(message = "Content is mandatory.")
  String content;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "review_date")
  private LocalDateTime time;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  @JsonIgnore
  private Movie movie;
}
