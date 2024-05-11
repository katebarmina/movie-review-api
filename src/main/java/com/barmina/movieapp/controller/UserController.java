package com.barmina.movieapp.controller;

import com.barmina.movieapp.model.User;
import com.barmina.movieapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.CollectionModel.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public CollectionModel<EntityModel<User>> getAll() {

        List<User> users = userService.getAll();
        List<EntityModel<User>> entityModelList = new ArrayList<>();
        for (User user : users) {
            Link link = linkTo(UserController.class).slash(user.getId()).withSelfRel();
            entityModelList.add(EntityModel.of(user).add(link));
        }
        return of(entityModelList, linkTo(UserController.class).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user)
            throws InstanceAlreadyExistsException {
        User savedUser = userService.create(user);
        URI location = linkTo(UserController.class).slash(savedUser.getId()).toUri();
        return created(location).build();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getById(@PathVariable Integer id) {

        User user = userService.getById(id);
        EntityModel<User> model = EntityModel.of(user);
        Link link = linkTo(UserController.class).slash(user.getId()).withSelfRel();
        return model.add(link);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable Integer id) {
        userService.update(user, id);
        return ok().build();
    }

}
