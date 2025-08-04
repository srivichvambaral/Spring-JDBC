package com.expressmovies.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movie {
    private int movieId;
    private String title;
    private LocalDate dateReleased;
    private LocalDateTime runningTime;

    // Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    public LocalDateTime getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(LocalDateTime runningTime) {
        this.runningTime = runningTime;
    }
}
