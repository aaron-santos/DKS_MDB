package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Movie.filterMoviesByName;
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
    @Test
    public void test_filter_movies_by_name_with_search_term() {
        // Arrange
        String searchTerm = "the";
        boolean sortAsc = true;
        int expectedSize = 3;
        String[] expectedTitles = {"The Incredibles", "The Avengers", "The Lion King"};

        // Act
        List<Movie> filteredMovies = filterMoviesByName(searchTerm, sortAsc);

        // Assert
        assertEquals(expectedSize, filteredMovies.size());
        for (int i = 0; i < expectedSize; i++) {
            assertEquals(expectedTitles[i], filteredMovies.get(i).getTitle());
        }
    }

    @Test
    public void test_filter_movies_by_name_without_search_term() {
        // Arrange
        String searchTerm = "";
        boolean sortAsc = true;
        int expectedSize = 7;

        // Act
        List<Movie> filteredMovies = filterMoviesByName(searchTerm, sortAsc);

        // Assert
        assertEquals(expectedSize, filteredMovies.size());
    }

    @Test
    public void test_filter_movies_by_name_with_sort_asc() {
        // Arrange
        String searchTerm = "the";
        boolean sortAsc = true;
        int expectedSize = 3;
        String[] expectedTitles = {"The Incredibles", "The Avengers", "The Lion King"};

        // Act
        List<Movie> filteredMovies = filterMoviesByName(searchTerm, sortAsc);

        // Assert
        assertEquals(expectedSize, filteredMovies.size());
        for (int i = 0; i < expectedSize; i++) {
            assertEquals(expectedTitles[i], filteredMovies.get(i).getTitle());
        }
    }
    @Test
    public void test_filter_movies_by_name_with_sort_desc() {
        // Arrange
        String searchTerm = "the";
        boolean sortAsc = false;
        int expectedSize = 3;
        String[] expectedTitles = {"The Incredibles", "The Avengers", "The Lion King"};

        // Act
        List<Movie> filteredMovies = filterMoviesByName(searchTerm, sortAsc);

        // Assert
        assertEquals(expectedSize, filteredMovies.size());
        for (int i = 0; i < expectedSize; i++) {
            assertEquals(expectedTitles[i], filteredMovies.get(i).getTitle());
        }
    }
}