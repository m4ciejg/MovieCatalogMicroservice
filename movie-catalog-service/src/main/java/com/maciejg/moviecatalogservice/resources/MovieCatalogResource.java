package com.maciejg.moviecatalogservice.resources;

import com.maciejg.moviecatalogservice.models.CatalogItem;
import com.maciejg.moviecatalogservice.models.Movie;
import com.maciejg.moviecatalogservice.models.Rating;
import com.maciejg.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired  // Look for bean of type RestTemplate and inject it here when n eeded
    private RestTemplate restTemplate; //standard way of coding

    @Autowired
    private WebClient.Builder webClientBuilder; //reactive Client

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        //get all rated movie Ids
        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserReting().stream().map(rating -> {
           //For each movie Id, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            // Put them all together
            return new CatalogItem(movie.getName(), "Desc", rating.getRating());
        })
        .collect(Collectors.toList());
    }

            /*reactive way
            Movie movie = webClientBuilder.build() //build pattern give me a client
                    .get() // get me what you have
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class) //whatever body you get convert it to Movie class
                    .block(); //convert from async to sync
            */

}
