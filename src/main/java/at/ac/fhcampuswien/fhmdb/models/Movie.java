package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;

import static at.ac.fhcampuswien.fhmdb.models.Genre.normalize;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    public static List<Movie> movies = new ArrayList<>();

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

    public static List<Movie> filterMoviesByName(List<Movie> movies, String searchTerm) {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public static List<Movie> filterMoviesAscending(List<Movie> movies, boolean sortAsc) {
        if (sortAsc) {
            movies.sort(Comparator.comparing(Movie::getTitle));
        } else {
            movies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
        return movies;
    }

    public static List<Movie> filterMoviesByGenre(List<Movie> movies, String genre) {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (String.valueOf(movie.getGenres()).toLowerCase().contains(normalize(genre).toLowerCase())) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public static List<Movie> filterMoviesByNameAndAscending(List<Movie> movies, String searchTerm, boolean sortAsc) {
        return filterMoviesAscending(filterMoviesByName(movies, searchTerm), sortAsc);
    }

    public static List<Movie> filterMoviesByEverything(List<Movie> movies, String searchTerm, boolean sortAsc, String genre) {
        if (Objects.equals(genre, "null")) {
            return filterMoviesByNameAndAscending(movies, searchTerm, sortAsc);
        } else {
            return filterMoviesByGenre(filterMoviesAscending(filterMoviesByName(movies, searchTerm), sortAsc), genre);
        }
    }


}
