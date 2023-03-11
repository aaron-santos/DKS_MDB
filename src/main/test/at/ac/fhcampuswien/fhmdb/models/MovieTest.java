package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;

import java.util.*;

import static at.ac.fhcampuswien.fhmdb.models.Movie.*;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void checks_if_initialized_movies_is_not_empty(){
        //given
        movies.clear();
        initializeMovies();
        //when
        boolean actual = movies.isEmpty();
        //then
        assertFalse(actual);

    }
    @Test
    void checks_if_all_4_dummy_movies_are_initialized(){
        movies.clear();
        initializeMovies();
        assertEquals(4, movies.size());
    }
    @Test
    void checks_if_size_of_movies_list_is_not_greater_than_4(){
        movies.clear();
        initializeMovies();
        assertNotEquals(5,movies.size());
    }
    @Test
    void first_added_movie_title_is_correct(){
        movies.clear();
        movies.add(new Movie("The Incredibles", "This is a dummy description",
                Arrays.asList(Genre.COMEDY, Genre.ADVENTURE, Genre.ANIMATION, Genre.ACTION)));
        initializeMovies();

        String actualTitle = movies.get(0).getTitle();

        String expectedTitle = "The Incredibles";
        assertEquals(expectedTitle, actualTitle);
    }
    @Test
    void first_added_movie_description_is_correct(){
        movies.clear();
        movies.add(new Movie("Minions", "Minions dummy description",
                Arrays.asList(Genre.ANIMATION, Genre.COMEDY,Genre.ACTION, Genre.ADVENTURE)));
        initializeMovies();

        String actualDescription = movies.get(0).getDescription();

        String expectedDescription = "Minions dummy description";
        assertEquals(expectedDescription, actualDescription);
    }
    @Test
    void first_added_movie_genres_are_correct(){
        movies.clear();
        movies.add(new Movie("The Lion King", "The Lion King dummy description",
                Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ADVENTURE)));
        initializeMovies();

        List<Genre> actualGenres = movies.get(0).getGenres();

        List<Genre> expectedGenres = Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ADVENTURE);
        assertEquals(expectedGenres, actualGenres);
    }
    @Test
    void added_movie_title_is_correct(){
        movies.clear();
        initializeMovies();
        movies.add(new Movie("The Incredibles", "This is a dummy description",
                Arrays.asList(Genre.COMEDY, Genre.ADVENTURE, Genre.ANIMATION, Genre.ACTION)));

        String actualTitle = movies.get(4).getTitle();

        String expectedTitle = "The Incredibles";
        assertEquals(expectedTitle, actualTitle);
    }
    @Test
    void added_movie_description_is_correct(){
        movies.clear();
        initializeMovies();
        movies.add(new Movie("Minions", "Minions dummy description",
                Arrays.asList(Genre.ANIMATION, Genre.COMEDY,Genre.ACTION, Genre.ADVENTURE)));

        String actualDescription = movies.get(4).getDescription();

        String expectedDescription = "Minions dummy description";
        assertEquals(expectedDescription, actualDescription);
    }
    @Test
    void added_movie_genres_are_correct(){
        movies.clear();
        initializeMovies();
        movies.add(new Movie("The Lion King", "The Lion King dummy description",
                Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ADVENTURE)));

        List<Genre> actualGenres = movies.get(4).getGenres();

        List<Genre> expectedGenres = Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ADVENTURE);
        assertEquals(expectedGenres, actualGenres);
    }

    @Test
    void ascending_with_no_search_term_and_no_genre(){
        //given
        List<Movie> testList = new ArrayList<>();
        testList.add(new Movie("Matrix", "Matrix dummy description", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        testList.add(new Movie("Spiderman", "Spiderman dummy description", Arrays.asList(Genre.FANTASY, Genre.SCIENCE_FICTION, Genre.FANTASY)));
        testList.add(new Movie("Conjuring", "Conjuring dummy description", Arrays.asList(Genre.HORROR, Genre.THRILLER)));

        //when
        List<Movie> actual = filterMoviesAscending(testList, true);

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Conjuring", "Conjuring dummy description", Arrays.asList(Genre.HORROR, Genre.THRILLER)));
        expected.add(new Movie("Matrix", "Matrix dummy description", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        expected.add(new Movie("Spiderman", "Spiderman dummy description", Arrays.asList(Genre.FANTASY, Genre.SCIENCE_FICTION, Genre.FANTASY)));
        assertEquals(expected,actual);

    }



}