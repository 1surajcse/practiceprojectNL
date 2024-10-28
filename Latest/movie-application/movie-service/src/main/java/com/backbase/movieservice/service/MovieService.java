package com.backbase.movieservice.service;/*
 * @author -Suraj Tiwari
 */

import com.backbase.movieservice.entity.Movie;
import com.backbase.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;


    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(String movieId) {
        return movieRepository.findById(movieId);
    }
}
