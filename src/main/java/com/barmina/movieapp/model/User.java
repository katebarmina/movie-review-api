package com.barmina.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Email(message = "Email address must be valid.")
  @Column(unique = true)
  private String email;

  @NotBlank(message = "Password is mandatory.")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column
  private String password;

  @NotBlank(message = "Name is mandatory.")
  @Column(unique = true)
  private String name;

  @Column private Boolean enabled;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "role_name")
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Review> reviews;
}
