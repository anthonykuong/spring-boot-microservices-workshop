package io.javabrains.movieinfoservice.resources;

import io.javabrains.movieinfoservice.models.Movie;
import io.javabrains.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 *    Movie Info Resource Rest Controller
 *    http://localhost:8082/movies/anthony
 *   http://localhost:8082/moviesMock/foo
 *
 *  * Movie Catalog Resource Rest Controller
 *  * http://localhost:8081/catalogResource/anthony
 *  * This will return [{"name":"Onward","desc":"Pixar movie that cameout 2020. Anthony Ku Ong gives it a rating of 4","rating":4}]
 */
@RestController
@RequestMapping("/moviesMock")
public class MovieMockResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
         return new Movie("foo", "Onward", "Movie Overview");

    }

}
