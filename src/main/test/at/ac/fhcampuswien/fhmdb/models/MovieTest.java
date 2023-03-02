package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    @Test

    void tests_if_two_identical_movies_are_equal() {
        Movie movie1 = new Movie("The Title", "The description.", Arrays.asList(Genre.COMEDY));
        Movie movie2 = new Movie("The Title", "The description.", Arrays.asList(Genre.COMEDY));
        Movie movie3 = new Movie("Different Title", "Different description.", Arrays.asList(Genre.ACTION));

        // Test that two identical movies are equal
        assertEquals(movie1, movie2);
    }

    @Test
    void test_if_two_identical_movies_are_not_equal() {
        Movie movie1 = new Movie("The Title", "The description.", Arrays.asList(Genre.COMEDY));
        Movie movie2 = new Movie("The Title", "The description.", Arrays.asList(Genre.COMEDY));
        Movie movie3 = new Movie("Different Title", "Different description.", Arrays.asList(Genre.ACTION));

        // Test that two identical movies are equal
        assertNotEquals(movie1, movie2);
    }

    @Test
    void test_initialize_movies_if_list_of_movies_is_not_empty() {
        List<Movie> movies = Movie.initializeMovies();

        // Test that the list of movies is not empty
        assertFalse(movies.isEmpty());
    }

    @Test
    void test_list_of_movies_contains_contains_at_least_one_movie_with_title_the_dog() {
        List<Movie> movies = Movie.initializeMovies();

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

}