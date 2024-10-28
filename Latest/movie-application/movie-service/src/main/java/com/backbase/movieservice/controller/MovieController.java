package com.backbase.movieservice.controller;/*
 * @author -Suraj Tiwari
 */

import com.backbase.movieservice.entity.Movie;
import com.backbase.movieservice.entity.MovieInformation;
import com.backbase.movieservice.entity.MovieSummary;
import com.backbase.movieservice.models.MovieRequestDto;
import com.backbase.movieservice.service.MovieService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {
    @Autowired
    WebClient.Builder webClientbuilder;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private MovieService movieService;
    @Value("${api.key}")
    private String apiKey;
    @Value("${api.baseUrl}")
    private String baseUrl;

    @GetMapping("/movie-info")
    @CircuitBreaker(name = "myInstance",fallbackMethod = "getFallbackLogic")
    public Movie getMovieInfo(@RequestParam(required=false,name="i") String movieId, @RequestParam(required=false,name="t")String title) throws  Exception {

        System.out.println(" Suraj............"+movieId+":"+title+":"+apiKey);

        MovieInformation movieInformation = webClientbuilder.baseUrl(baseUrl).build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("t", "{title}")
                        .queryParam("i", "{movieId}")
                        .build(title,movieId))
                .retrieve()
                .bodyToMono(MovieInformation.class).
                block();

        String url=baseUrl+"/"+"?apikey="+apiKey+"&i="+movieId+"&t="+title;
        System.out.println(url);

      String userservice = restTemplate.getForObject("http://localhost:8081/hello", String.class);
       // MovieInformation movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  "db0b6d7434659cd6d5985a29b76f4fb7", MovieInformation.class);

        boolean awardCheck=movieInformation.getAwards()!=null&& movieInformation.getAwards().toLowerCase().contains("won")?true:false;

        return new Movie(movieId, movieInformation.getTitle(), movieInformation.getBoxOffice(), movieInformation.getType(),awardCheck);

    }

    public String getFallbackLogic(Throwable ex){
    return "Waiting..for service to be up.";
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieRequestDto movieRequestDto) {

        Movie movie = Movie.builder().
               Title(movieRequestDto.movieTitle())
                .Type(movieRequestDto.type())
                .Awards(movieRequestDto.awards())
                .BoxOffice(movieRequestDto.boxOffice())

                .build();
        Movie movieResponse = movieService.save(movie);
        return ResponseEntity.ok(movieResponse);
    }

    @GetMapping("/")
    public List<Movie> getMovieList() {
        return movieService.findAll();
    }

    @GetMapping("/{movie-id}")
    public Optional<Movie> getMovieById(@PathVariable("movie-id") String movieId) {
        return movieService.findById(movieId);
    }
}
