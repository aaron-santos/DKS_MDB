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
        //when
        initializeMovies();
        //then
        assertFalse(movies.isEmpty());

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

    @Test
    void descending_with_no_search_term_and_no_genre(){
        //given
        List<Movie> testList = new ArrayList<>();
        testList.add(new Movie("Matrix", "Matrix dummy description", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        testList.add(new Movie("Spiderman", "Spiderman dummy description", Arrays.asList(Genre.FANTASY, Genre.SCIENCE_FICTION, Genre.FANTASY)));
        testList.add(new Movie("Conjuring", "Conjuring dummy description", Arrays.asList(Genre.HORROR, Genre.THRILLER)));

        //when
        List<Movie> actual = filterMoviesAscending(testList, false);

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Spiderman", "Spiderman dummy description", Arrays.asList(Genre.FANTASY, Genre.SCIENCE_FICTION, Genre.FANTASY)));
        expected.add(new Movie("Matrix", "Matrix dummy description", Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        expected.add(new Movie("Conjuring", "Conjuring dummy description", Arrays.asList(Genre.HORROR, Genre.THRILLER)));

        assertEquals(expected,actual);

    }

    @Test
    void only_display_movies_with_selected_genre(){
        List<Movie> testList = new ArrayList<>();

        testList.add(new Movie(
                "Avengers: End Game",
                "Nebula and Tony Stark are stranded in space following their defeat by Thanos on Titan, " +
                        "but are returned to Earth by Carol Danvers and reunited with Natasha Romanoff, Bruce Banner, " +
                        "Steve Rogers, Rocket, Thor, and James Rhodes.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)
        ));
        testList.add(new Movie(
                "Free Guy",
                "It tells the story of a bank teller who discovers that he is a non-player character in a " +
                        "massively multiplayer online game who then partners with a player to find evidence that a " +
                        "gaming company's CEO stole the player's game's source code.",
                Arrays.asList(Genre.COMEDY, Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE)
        ));
        testList.add(new Movie(
                "Pulp Fiction",
                "Pulp fiction refers to a genre of racy, action-based stories published in cheaply printed " +
                        "magazines from around 1900 to the 1950s, mostly in the United States. Pulp fiction gets its " +
                        "name from the paper it was printed on.",
                Arrays.asList(Genre.COMEDY, Genre.THRILLER, Genre.CRIME, Genre.DRAMA)
        ));
        testList.add(new Movie(
                "The Hunger Games",
                "Synopsis. The nation of Panem is divided into 12 districts, ruled from the Capitol. As " +
                        "punishment for a failed revolt, each district is forced to select two tributes, one boy and " +
                        "one girl between the ages of 12 and 18, to fight to the death in the annual Hunger Games until " +
                        "there is only one survivor.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ADVENTURE)
        ));

        List<Movie> testListFilteredByGenres = filterMoviesByGenres(testList, "ACTION");

        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie(
                "Avengers: End Game",
                "Nebula and Tony Stark are stranded in space following their defeat by Thanos on Titan, " +
                        "but are returned to Earth by Carol Danvers and reunited with Natasha Romanoff, Bruce Banner, " +
                        "Steve Rogers, Rocket, Thor, and James Rhodes.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)
        ));
        expected.add(new Movie(
                "Free Guy",
                "It tells the story of a bank teller who discovers that he is a non-player character in a " +
                        "massively multiplayer online game who then partners with a player to find evidence that a " +
                        "gaming company's CEO stole the player's game's source code.",
                Arrays.asList(Genre.COMEDY, Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE)
        ));

        assertEquals(expected, testListFilteredByGenres);
    }

    @Test
    void only_display_movies_containing_searchQuery_in_title(){
        List<Movie> testList = new ArrayList<>();

        testList.add(new Movie(
                "Avengers: End Game",
                "Nebula and Tony Stark are stranded in space following their defeat by Thanos on Titan, " +
                        "but are returned to Earth by Carol Danvers and reunited with Natasha Romanoff, Bruce Banner, " +
                        "Steve Rogers, Rocket, Thor, and James Rhodes.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)
        ));
        testList.add(new Movie(
                "Free Guy",
                "It tells the story of a bank teller who discovers that he is a non-player character in a " +
                        "massively multiplayer online game who then partners with a player to find evidence that a " +
                        "gaming company's CEO stole the player's game's source code.",
                Arrays.asList(Genre.COMEDY, Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE)
        ));
        testList.add(new Movie(
                "Pulp Fiction",
                "Pulp fiction refers to a genre of racy, action-based stories published in cheaply printed " +
                        "magazines from around 1900 to the 1950s, mostly in the United States. Pulp fiction gets its " +
                        "name from the paper it was printed on.",
                Arrays.asList(Genre.COMEDY, Genre.THRILLER, Genre.CRIME, Genre.DRAMA)
        ));
        testList.add(new Movie(
                "The Hunger Games",
                "Synopsis. The nation of Panem is divided into 12 districts, ruled from the Capitol. As " +
                        "punishment for a failed revolt, each district is forced to select two tributes, one boy and " +
                        "one girl between the ages of 12 and 18, to fight to the death in the annual Hunger Games until " +
                        "there is only one survivor.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ADVENTURE)
        ));

        List<Movie> testListFilteredByTitle = filterMoviesByEverything(testList, "Avengers", true, "Action");

        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie(
                "Avengers: End Game",
                "Nebula and Tony Stark are stranded in space following their defeat by Thanos on Titan, " +
                        "but are returned to Earth by Carol Danvers and reunited with Natasha Romanoff, Bruce Banner, " +
                        "Steve Rogers, Rocket, Thor, and James Rhodes.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)
        ));

        assertEquals(expected, testListFilteredByTitle);
    }

}