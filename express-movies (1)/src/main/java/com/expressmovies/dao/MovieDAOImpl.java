package com.expressmovies.dao;

import com.expressmovies.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;

public class MovieDAOImpl implements MovieDAO {

    private JdbcTemplate jdbcTemplate;

    public MovieDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMovie(Movie movie, Director director) {
        jdbcTemplate.update("INSERT INTO Movie VALUES (?, ?, ?, ?)",
            movie.getMovieId(), movie.getTitle(), movie.getDateReleased(), movie.getRunningTime());

        jdbcTemplate.update("INSERT INTO Director VALUES (?, ?, ?, ?, ?, ?)",
            director.getDirectorId(), director.getFirstName(), director.getLastName(),
            director.getAddress(), director.getContactNumber(), director.getEmail());

        jdbcTemplate.update("INSERT INTO Movie_Director VALUES (?, ?)",
            director.getDirectorId(), movie.getMovieId());
    }

    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query("SELECT * FROM Movie", (rs, rowNum) -> {
            Movie m = new Movie();
            m.setMovieId(rs.getInt("Movie_Id"));
            m.setTitle(rs.getString("Movie_Title"));
            m.setDateReleased(rs.getDate("Date_Released").toLocalDate());
            m.setRunningTime(rs.getTimestamp("Movie_Running_Time").toLocalDateTime());
            return m;
        });
    }

    @Override
    public List<Movie> searchByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM Movie WHERE Movie_Title = ?", new Object[]{title}, (rs, rowNum) -> {
            Movie m = new Movie();
            m.setMovieId(rs.getInt("Movie_Id"));
            m.setTitle(rs.getString("Movie_Title"));
            m.setDateReleased(rs.getDate("Date_Released").toLocalDate());
            m.setRunningTime(rs.getTimestamp("Movie_Running_Time").toLocalDateTime());
            return m;
        });
    }

    @Override
    public List<Movie> searchByDirectorName(String firstName, String lastName) {
        return jdbcTemplate.query(
            "SELECT m.* FROM Movie m JOIN Movie_Director md ON m.Movie_Id = md.Movie_Id " +
            "JOIN Director d ON d.Director_Id = md.Director_Id " +
            "WHERE d.First_Name = ? AND d.Last_Name = ?",
            new Object[]{firstName, lastName},
            (rs, rowNum) -> {
                Movie m = new Movie();
                m.setMovieId(rs.getInt("Movie_Id"));
                m.setTitle(rs.getString("Movie_Title"));
                m.setDateReleased(rs.getDate("Date_Released").toLocalDate());
                m.setRunningTime(rs.getTimestamp("Movie_Running_Time").toLocalDateTime());
                return m;
            });
    }

    @Override
    public void updateDirector(String firstName, String lastName, String address, int contactNumber) {
        int rows = jdbcTemplate.update(
            "UPDATE Director SET Address = ?, Contact_Number = ? WHERE First_Name = ? AND Last_Name = ?",
            address, contactNumber, firstName, lastName);
        if (rows == 0) {
            throw new RuntimeException("Invalid Director name");
        }
    }

    @Override
    public void deleteMovieByTitle(String title) {
        int rows = jdbcTemplate.update("DELETE FROM Movie WHERE Movie_Title = ?", title);
        if (rows == 0) {
            throw new RuntimeException("Movie with the given title is not present");
        }
    }
}
