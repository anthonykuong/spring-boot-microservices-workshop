package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Movie Catalog Resource Rest Controller
 * http://localhost:8081/catalogResource/anthony
 * This will return [{"name":"Onward","desc":"Pixar movie that cameout 2020. Anthony Ku Ong gives it a rating of 4","rating":4}]
 */
@RestController
@RequestMapping("/catalogResource")
public class MovieCatalogResource {

    // get all rated movie Ids
    // for each movie ID, call movie info service and get details
    // put them all together
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        RestTemplate restTemplate = new RestTemplate();
        //the first URL, any call, what it gest back is a string and helps you unmarshall it into an object
        // create an instance of the class .

        //http://localhost:8081/movies/foo
        //http://localhost:8081/catalogResource/anthony
        // {"movieId":"foo","name":"Onward","description":"Movie Overview"}
        // Can copy Movie in models from movie-info-service in microservices not in monolithic

       // Movie movieTest = restTemplate.getForObject("http://localhost:8082/moviesMock/foo", Movie.class );


        // get all rated movie Ids
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                 new Rating("5468",3)
        );


        //RestTemplate allows me to make a call
        //for every rated movie, make that movieId call the movie-info-service
        return ratings.stream().map(rating -> {
                Movie movie = restTemplate.getForObject("http://localhost:8082/moviesMock/" + rating.getMovieId(), Movie.class );
                return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
        })
                .collect(Collectors.toList());

        //return Collections.singletonList(
         //       new CatalogItem("Onward", "Pixar movie that cameout 2020. Anthony Ku Ong gives it a rating of 4", 4)
        //);
    }
}
