# Movie-review-api

A simple RESTful movie review api. It was built using Java, Spring boot, Spring Framework, MySQL database and some additional libraries, such as Lombok and JUnit.

## Features
This API provides HTTP endpoint's and tools for the following:


*  Create new user: ```POST/users``` 
*  Update user: ```PUT/users/{id}```
*  Delete user: ```DELETE/users/{id}```
*  Get user: ```GET/users/{id}```
*  Get all users: ```GET/users```
*  Create new movie: ```POST/movies``` 
*  Update movie: ```PUT/movies/{id}```
*  Delete movie: ```DELETE/movies/{id}```
*  Get movie: ```GET/movies/{id}```
*  Get all movies: ```GET/movies```
*  Create new review: ```POST/movies/{id}/reviews``` 
*  Delete review: ```DELETE/movies/{id}/reviews/{id}```
*  Get review: ```GET/movies/{id}/reviews/{id}```
*  Get all reviews: ```GET/movies/{id}/reviews```

## Example of a request

```GET/movies/{id}```
This end-point is called to get the movie by id.

## Returns:

## Body
```
{
    "id": 1,
    "name": "Matrix",
    "year": 1993,
    "genre": "Action, Drama, Fantasy",
    "rating": 7.7,
    "image": "https://m.media-amazon.com/images/M/MV5BYzUzOTA5ZTMtMTdlZS00MmQ5LWFmNjEtMjE5MTczN2RjNjE3XkEyXkFqcGdeQXVyNTc2ODIyMzY@._V1_SX300.jpg",
    "language": "English",
    "plot": "Neo (Keanu Reeves) believes that Morpheus (Laurence Fishburne), an elusive figure considered to be the most dangerous man alive, can answer his question -- What is the Matrix? Neo is contacted by Trinity (Carrie-Anne Moss), a beautiful stranger who leads him into an underworld where he meets Morpheus. They fight a brutal battle for their lives against a cadre of viciously intelligent secret agents. It is a truth that could cost Neo something more precious than his life.",
    "_links": {
        "self": {
            "href": "http://localhost:8080/movies/1"
        }
    }
}
```
 ## Where:
 
-  ```id``` - movie id
-  ```name``` - name of the movie
-   ```year``` - year, when the movie was released
-    ```genre``` - genre of the movie
-    ```rating``` - rating of the movie
-  ```image``` - link to the poster of the movie
-  ```language``` - language of the movie
-  ```plot``` - plot of the movie
-  ```links``` - self-linking URL for the movie
 
## Status

- 200 - OK. Everything worked as expected.
- 401 - Unauthorized. The user isn't authorized.
- 404 - Not Found. The requested resource doesn't exist.

## Technologies

This project was developed with:
- Java 17
- Spring Boot 2.7.5
- Spring Web
- Spring Security 
- Spring HATEOAS
- Spring Data JPA
- Hibernate 5.6.12.Final
- Maven
- JUnit 5
- MySQL 8.0.31
- Lombok 1.18.24
## Launch guide
To run this project you'll need:
- Java 17 or higher
- MySQL
- Apache Maven
- IDE

 1. Clone the repository.
 2. Run in MySQL Command Line Client the following command to set up the database:
  <br/>```source src/main/resources/db.sql```
 3. Change ```spring.datasource.password``` and ```spring.datasource.username``` to your username and password.
 4. To start the application run:
 <br/> ```java -jar target/movie-app-0.0.1-SNAPSHOT.jar```



