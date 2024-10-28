package com.example.RestApiSample.book;/*
 * @author -Suraj Tiwari
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book_tbl")
public class Book {
    @Id
    private int id;
    private String name;
}
