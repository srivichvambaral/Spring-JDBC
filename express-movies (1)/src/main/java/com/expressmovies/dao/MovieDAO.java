package com.expressmovies.dao;

import com.expressmovies.model.*;

import java.util.List;

public interface MovieDAO {
    void addMovie(Movie movie, Director director);
    List<Movie> getAllMovies();
    List<Movie> searchByTitle(String title);
    List<Movie> searchByDirectorName(String firstName, String lastName);
    void updateDirector(String firstName, String lastName, String address, int contactNumber);
    void deleteMovieByTitle(String title);
}
