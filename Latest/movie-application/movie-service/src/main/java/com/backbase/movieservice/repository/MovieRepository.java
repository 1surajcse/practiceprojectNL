package com.backbase.movieservice.repository;/*
 * @author -Suraj Tiwari
 */

import com.backbase.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
