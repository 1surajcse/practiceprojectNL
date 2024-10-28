package com.example.RestApiSample.book;/*
 * @author -Suraj Tiwari
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book,Integer> {
}
