package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private static HomeController homeController;
    @BeforeAll
    static void inti() { homeController = new HomeController(); }

    @Test
    void movies_and_observableMovies_are_equal(){
        //GIVEN
        homeController.initializeState();

        //WHEN & THEN
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    @Test
    void movies_are_sorted_correctly_with_current_sortState_none_then_ascending(){
        //GIVEN
        homeController.initializeState();
        homeController.sortState = SortState.NONE;

        //WHEN
        homeController.sortMovies();

        //THEN
        List<Movie> expected = Arrays.asList(
                new Movie(
                        "CatsVenture",
                        "A cute cat on an adventure.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE)
                ),
                new Movie(
                        "Generic Movie Title",
                        "Generic movie description.",
                        Arrays.asList(Genre.MYSTERY)
                ),
                new Movie(
                        "The Dog",
                        "Movie about a cute dog.",
                        Arrays.asList(Genre.ANIMATION, Genre.FAMILY)
                ),
                new Movie(
                        "The Title",
                        "The description.",
                        Arrays.asList(Genre.COMEDY)
                )
        );

        assertEquals(expected, homeController.observableMovies);
    }
}