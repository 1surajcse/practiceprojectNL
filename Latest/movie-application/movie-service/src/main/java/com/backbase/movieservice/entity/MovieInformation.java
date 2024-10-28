package com.backbase.movieservice.entity;/*
 * @author -Suraj Tiwari
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MovieInformation {
    @JsonProperty("year")
    private String Year;
    @JsonProperty(
            value="Title",
            required=true,
            defaultValue="No name",
            access= JsonProperty.Access.READ_WRITE)
    private String title;
    @JsonProperty(
            value="Genre",
            required=true,
            defaultValue="No name",
            access= JsonProperty.Access.READ_WRITE)
    private String genre;
    @JsonProperty(
            value="Awards",
            required=true,
            defaultValue="No name",
            access= JsonProperty.Access.READ_WRITE)
    private String awards;
    @JsonProperty(
            value="Type",
            required=true,
            defaultValue="No name",
            access= JsonProperty.Access.READ_WRITE)
    private String type;
    @JsonProperty(
            value="BoxOffice",
            required=true,
            defaultValue="No name",
            access= JsonProperty.Access.READ_WRITE)
    private String boxOffice;

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }


    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    private String imdbRating;
    @JsonProperty(
            value="Plot",
            required=true,
            defaultValue="No name",
            access= JsonProperty.Access.READ_WRITE)
    private String plot;
}
