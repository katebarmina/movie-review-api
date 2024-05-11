package com.barmina.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  @NotBlank(message = "Name is mandatory.")
  private String name;

  @NotNull(message = "Year is mandatory.")
  @Column(name = "release_year")
  private Integer year;

  @NotBlank(message = "Genre is mandatory.")
  @Column
  private String genre;

  @Column private String image;

  @Column(name = "movie_language")
  @NotBlank(message = "Language is mandatory.")
  private String language;

  @Column
  @NotBlank(message = "Language is mandatory")
  private String plot;

  @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
  @JsonIgnore
  private List<Review> reviews;

  @Column
  private Double rating;

  @Column(name = "num_of_reviews")
  private int numOfReviews;

}
