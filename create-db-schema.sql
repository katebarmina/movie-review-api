CREATE DATABASE IF NOT EXISTS movie_review_app;

USE movie_review_app;

CREATE TABLE IF NOT EXISTS users
(
    id       INTEGER AUTO_INCREMENT,
    name     VARCHAR(255) UNIQUE NOT NULL,
    email    VARCHAR(50) UNIQUE  NOT NULL,
    password VARCHAR(300)        NOT NULL,
    enabled  TINYINT(1) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS authorities
(
    email     VARCHAR(50) UNIQUE,
    authority VARCHAR(50),
    FOREIGN KEY (email) REFERENCES users (email)
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id   INTEGER      NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS movies
(
    id             INTEGER AUTO_INCREMENT,
    name           VARCHAR(255) NOT NULL,
    release_year   INTEGER      NOT NULL,
    genre          VARCHAR(255) NOT NULL,
    rating         FLOAT NOT NULL DEFAULT 0,
    num_of_reviews INT NOT NULL DEFAULT 0,
    image          VARCHAR(300),
    plot           VARCHAR(500) NOT NULL,
    movie_language VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS reviews
(
    id          INTEGER AUTO_INCREMENT,
    user_id     INTEGER      NOT NULL,
    movie_id    INTEGER      NOT NULL,
    content     VARCHAR(400) NOT NULL,
    rating      FLOAT        NOT NULL,
    review_date DATE DEFAULT (CURDATE()),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (movie_id) REFERENCES movies (id)
);

INSERT INTO movies (name, release_year, genre, movie_language, image, plot)
VALUES (
           'The Matrix',
           1999,
           'Science Fiction, Action',
           'English',
           'https://image.tmdb.org/t/p/w440_and_h660_face/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg',
           'The series features a cyberpunk story of the technological fall of humanity, in which the creation of artificial intelligence led the way to a race of powerful and self-aware machines that imprisoned humans in a neural interactive simulation — the Matrix — to be farmed as a power source.'
       );

INSERT INTO movies (name, release_year, genre, image, movie_language, plot)
VALUES (
           'Star Wars: Episode III - Revenge of the Sith',
           2005,
           'Science Fiction, Action, Adventure',
           'https://m.media-amazon.com/images/I/61jCCwfO6HL._AC_SL1000_.jpg',
           'English',
           'It has been three years since the Clone Wars began. Jedi Master Obi-Wan Kenobi (Ewan McGregor) and Jedi Knight Anakin Skywalker (Hayden Christensen) rescue Chancellor Palpatine (Ian McDiarmid) from General Grievous, the commander of the droid armies, but Grievous escapes. Suspicions are raised within the Jedi Council concerning Chancellor Palpatine, with whom Anakin has formed a bond. Asked to spy on the chancellor, and full of bitterness toward the Jedi Council, Anakin embraces the Dark Side.'
       );

INSERT INTO movies (name, release_year, genre, image, movie_language, plot)
VALUES (
           'Pretty Woman',
           1990,
           'Romance/Comedy',
           'https://upload.wikimedia.org/wikipedia/ru/b/bf/PrettyWoman.jpg',
           'English',
           'In this modern update on Cinderella, a prostitute and a wealthy businessman fall hard for one another, forming an unlikely pair. While on a business trip in L.A., Edward (Richard Gere), who makes a living buying and breaking up companies, picks up a hooker, Vivian (Julia Roberts), on a lark. After Edward hires Vivian to stay with him for the weekend, the two get closer, only to discover there are significant hurdles to overcome as they try to bridge the gap between their very different worlds.'
       );

INSERT INTO movies (name, release_year, genre, image, movie_language, plot)
VALUES (
           'Star Wars: Episode II - Attack of the Clones',
           2002,
           'Action/Sci-fi',
           'https://m.media-amazon.com/images/I/61nFfWio-sL._AC_SL1024_.jpg',
           'English',
           'Set ten years after the events of "The Phantom Menace," the Republic continues to be mired in strife and chaos. A separatist movement encompassing hundreds of planets and powerful corporate alliances poses new threats to the galaxy that even the Jedi cannot stem. These moves, long planned by an as yet unrevealed and powerful force, lead to the beginning of the Clone Wars -- and the beginning of the end of the Republic.'
       );

INSERT INTO movies (name, release_year, genre, image, movie_language, plot)
VALUES (
           'Dune: Part One',
           2021,
           'Sci-fi/Adventure',
           'https://upload.wikimedia.org/wikipedia/en/8/8e/Dune_%282021_film%29.jpg',
           'English',
           'A mythic and emotionally charged hero''s journey, "Dune" tells the story of Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, who must travel to the most dangerous planet in the universe to ensure the future of his family and his people.'
       );