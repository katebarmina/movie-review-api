CREATE
DATABASE movie_review_app;

USE
movie_review_app;

CREATE TABLE users
(
    id       INTEGER AUTO_INCREMENT,
    name     VARCHAR(255) UNIQUE NOT NULL,
    email    VARCHAR(50) UNIQUE  NOT NULL,
    password VARCHAR(300)        NOT NULL,
    enabled  TINYINT(1) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE authorities
(
    email     VARCHAR(50) UNIQUE,
    authority VARCHAR(50),
    FOREIGN KEY (email) REFERENCES users (email)
);

CREATE TABLE user_role
(
    user_id   INTEGER      NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE movies
(
    id             INTEGER AUTO_INCREMENT,
    name           VARCHAR(255) NOT NULL,
    release_year   INTEGER      NOT NULL,
    genre          VARCHAR(255) NOT NULL,
    rating         DOUBLE       NOT NULL,
    image          VARCHAR(300),
    plot           VARCHAR(500) NOT NULL,
    movie_language VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reviews
(
    id          INTEGER AUTO_INCREMENT,
    user_id     INTEGER      NOT NULL,
    movie_id    INTEGER      NOT NULL,
    content     VARCHAR(400) NOT NULL,
    review_date VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (movie_id) REFERENCES movies (id)
);