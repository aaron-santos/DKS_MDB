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
                "Avengers: End Game",
                "Nebula and Tony Stark are stranded in space following their defeat by Thanos on Titan, " +
                        "but are returned to Earth by Carol Danvers and reunited with Natasha Romanoff, Bruce Banner, " +
                        "Steve Rogers, Rocket, Thor, and James Rhodes.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)
        ));
        movies.add(new Movie(
                "Free Guy",
                "It tells the story of a bank teller who discovers that he is a non-player character in a " +
                        "massively multiplayer online game who then partners with a player to find evidence that a " +
                        "gaming company's CEO stole the player's game's source code.",
                Arrays.asList(Genre.COMEDY, Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE)
        ));
        movies.add(new Movie(
                "Pulp Fiction",
                "Pulp fiction refers to a genre of racy, action-based stories published in cheaply printed " +
                        "magazines from around 1900 to the 1950s, mostly in the United States. Pulp fiction gets its " +
                        "name from the paper it was printed on.",
                Arrays.asList(Genre.COMEDY, Genre.THRILLER, Genre.CRIME, Genre.DRAMA)
        ));
        movies.add(new Movie(
                "The Hunger Games",
                "Synopsis. The nation of Panem is divided into 12 districts, ruled from the Capitol. As " +
                        "punishment for a failed revolt, each district is forced to select two tributes, one boy and " +
                        "one girl between the ages of 12 and 18, to fight to the death in the annual Hunger Games until " +
                        "there is only one survivor.",
                Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ADVENTURE)
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

    public static List<Movie> filterMoviesByDescription(List<Movie> movies, String searchTerm) {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
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
            return filterMoviesByGenres(filterMoviesAscending(filterMoviesByName(movies, searchTerm), sortAsc), genre);
        }
    }

    public static List<Movie> filterMovies(List<Movie> listOfMovies, String searchTerm, String genre, boolean sortAsc)
    {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie: listOfMovies)
        {
            if (movie.getTitle().toLowerCase().contains(searchTerm) && !movie.isMovieInList(filteredMovies, movie))
                filteredMovies.add(movie);
            if (movie.getDescription().toLowerCase().contains(searchTerm) && !movie.isMovieInList(filteredMovies, movie))
                filteredMovies.add(movie);
        }

        sortMovies(filteredMovies, sortAsc);

        if (Objects.equals(genre, "null")) {
            return filteredMovies;
        } else {
            return filterMoviesByGenres(filteredMovies, genre);
        }
    }

    private boolean isMovieInList(List<Movie> listOfMovies, Movie movie)
    {
        if (listOfMovies.contains(movie))
            return true;

        return false;
    }

    private static void sortMovies(List<Movie> movies, boolean sortAsc) {
        if (sortAsc) {
            movies.sort(Comparator.comparing(Movie::getTitle));
        } else {
            movies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
    }

    private static List<Movie> filterMoviesByGenres(List<Movie> movies, String genre) {
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (String.valueOf(movie.getGenres()).toLowerCase().contains(genre.toLowerCase())) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
}
