package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        if(object == this){
            return true;
        }
        if(!(object instanceof Movie other)){
            return false;
        }
        return this.title.equals(other.title) && this.description.equals(other.description) && this.genres.equals(other.genres);
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() { return genres; }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        movies.add(new Movie(
                "The Dog",
                "Movie about a cute dog.Movie about a cute dog.Movie about a cute dog.Movie about a cute dog.Movie about a cute dog.Movie about a cute dog.Movie about a cute dog.Movie about a cute dog.Movie about a cute dog.",
                Arrays.asList(Genre.ANIMATION, Genre.FAMILY)
        ));
        movies.add(new Movie(
                "CatsVenture",
                "A cute cat on an adventure.",
                Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE)
        ));
        movies.add(new Movie(
                "Generic Movie Title",
                "Generic movie description.",
                Arrays.asList(Genre.MYSTERY)
        ));
        movies.add(new Movie(
                "The Incredibles",
                "The description0.",
                Arrays.asList(Genre.COMEDY)
        ));
        movies.add(new Movie(
                "The Title1",
                "The description.",
                Arrays.asList(Genre.COMEDY)
        ));
        movies.add(new Movie(
                "The Title2",
                "The description.",
                Arrays.asList(Genre.COMEDY)
        ));

        return movies;
    }
    public static List<Movie> filterMoviesByNameAndAscending(List<Movie> movies, String searchTerm, boolean sortAsc) {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredMovies.add(movie);
            }
        }

        if (sortAsc) {
            filteredMovies.sort(Comparator.comparing(Movie::getTitle));
        } else {
            filteredMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
        return filteredMovies;
    }
}
