package com.barmina.movieapp.controller;

import com.barmina.movieapp.model.Movie;
import com.barmina.movieapp.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

  private final MovieService movieService;

  @GetMapping
  public CollectionModel<EntityModel<Movie>> getAll() {
    List<Movie> movies = movieService.getAll();
    List<EntityModel<Movie>> modelList = new ArrayList<>();
    for (Movie movie : movies) {
      Link link = linkTo(MovieController.class).slash(movie.getId()).withSelfRel();
      modelList.add(EntityModel.of(movie).add(link));
    }

    return CollectionModel.of(modelList, linkTo(MovieController.class).withSelfRel());
  }

  @PostMapping
  public ResponseEntity<Movie> create(@Valid @RequestBody Movie movie) {
    Movie savedMovie = movieService.create(movie);
    URI location = linkTo(MovieController.class).slash(savedMovie.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{id}")
  public EntityModel<Movie> getById(@PathVariable Integer id) {
    Link link = linkTo(MovieController.class).slash(id).withSelfRel();
    return EntityModel.of(movieService.getById(id)).add(link);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Movie> deleteById(@PathVariable Integer id) {
    movieService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movie> update(@Valid @RequestBody Movie movie, @PathVariable Integer id) {
    movieService.update(movie, id);
    return ResponseEntity.ok().build();
  }
}
