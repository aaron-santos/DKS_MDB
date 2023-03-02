package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Movie.filterMoviesByNameAndAscending;
import static at.ac.fhcampuswien.fhmdb.models.Movie.initializeMovies;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    @Test
    void checks_if_list_of_initialized_movies_is_not_empty() {
        List<Movie> movies = initializeMovies();

        // Test that the list of movies is not empty
        assertFalse(movies.isEmpty());
    }

    @Test
    void lists_movies_that_contains_search_term_in_title() {
        List<Movie> movies = initializeMovies();

        // Test that the list of movies contains at least one movie with the title "The Dog"
        boolean hasTheDog = false;
        for (Movie movie : movies) {
            if (movie.getTitle().equals("The Dog")) {
                hasTheDog = true;
                break;
            }
        }
        assertTrue(hasTheDog);
    }
    @Test
    public void sort_movies_in_ascending_order_with_search_term() {
        // Arrange
        String searchTerm = "the";
        boolean sortAsc = true;
        List<Movie> movies = initializeMovies();
        int expectedSize = 4;
        String[] expectedTitles = {"The Dog", "The Incredibles", "The Title1", "The Title2"};

        // Act
        List<Movie> filteredMovies = filterMoviesByNameAndAscending(movies, searchTerm, sortAsc);

        // Assert
        for (int i = 0; i < expectedSize; i++) {
            assertEquals(expectedTitles[i], filteredMovies.get(i).getTitle());
        }
    }
    @Test
    public void sort_movies_in_ascending_order_without_search_term() {
        // Arrange
        String searchTerm = "";
        boolean sortAsc = true;
        List<Movie> movies = initializeMovies();
        int expectedSize = 6;
        String[] expectedTitles = {"CatsVenture", "Generic Movie Title", "The Dog", "The Incredibles", "The Title1", "The Title2"};

        // Act
        List<Movie> filteredMovies = filterMoviesByNameAndAscending(movies, searchTerm, sortAsc);

        // Assert
        for (int i = 0; i < expectedSize; i++) {
            assertEquals(expectedTitles[i], filteredMovies.get(i).getTitle());
        }
    }
    @Test
    public void number_of_movies_sorted_ascending_with_search_term() {
        // Arrange
        String searchTerm = "the";
        boolean sortAsc = true;
        List<Movie> movies = initializeMovies();
        int expectedSize = 4;
        String[] expectedTitles = {"The Dog", "The Incredibles", "The Title1", "The Title2"};

        // Act
        List<Movie> filteredMovies = filterMoviesByNameAndAscending(movies, searchTerm, sortAsc);

        // Assert
        assertEquals(expectedSize, filteredMovies.size());
    }
    @Test
    public void number_of_movies_sorted_ascending_without_search_term() {
        // Arrange
        String searchTerm = "";
        boolean sortAsc = true;
        List<Movie> movies = initializeMovies();
        int expectedSize = 6;

        // Act
        List<Movie> filteredMovies = filterMoviesByNameAndAscending(movies, searchTerm, sortAsc);

        // Assert
        assertEquals(expectedSize, filteredMovies.size());
    }

}