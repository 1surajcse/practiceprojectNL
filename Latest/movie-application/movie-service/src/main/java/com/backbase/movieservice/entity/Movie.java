package com.backbase.movieservice.entity;/*
 * @author -Suraj Tiwari
 */

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
@Entity
@Table(name = "movie_tbl")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "movie_title")
    private String Title;
    @Column(name = "box_office")
    private String BoxOffice;
    @Column(name = "type")
    private String Type;
    @Column(name = "award_won")
    private boolean Awards;

}
