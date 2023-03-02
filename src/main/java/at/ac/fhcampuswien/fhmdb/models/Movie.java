package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    public static List<Movie> movies = new ArrayList<Movie>();

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

    public static void initializeMovies(){
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
                "The Title",
                "The description.",
                Arrays.asList(Genre.COMEDY)
        ));
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

    public static List<Movie> filterMoviesByGenre(List<Movie> movies, Genre genre) {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getGenres().contains(genre)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

}
