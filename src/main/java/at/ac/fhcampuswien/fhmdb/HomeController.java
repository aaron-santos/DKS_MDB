package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static at.ac.fhcampuswien.fhmdb.models.Genre.normalize;
import static at.ac.fhcampuswien.fhmdb.models.Movie.*;

public class HomeController implements Initializable {
    @FXML
    public Button searchBtn;

    @FXML
    public Button resetFilter;

    @FXML
    public TextField searchField;

    @FXML
    public ListView movieListView;

    @FXML
    public ComboBox genreComboBox;

    @FXML
    public Button sortBtn;

    boolean isSorted = false;

    public static ArrayList<Label> titlesList = new ArrayList<Label>();
    public static ArrayList<Label> descriptionsList = new ArrayList<Label>();
    private Genre selectedGenre;
    boolean isAscending = true;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createMovieCells(movies);

        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());


        sortBtn.setOnAction(actionEvent -> {
            movieListView.getItems().clear();
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortBtn.setText("Sort (desc)");
            } else {
                sortBtn.setText("Sort (asc)");
            }
            isAscending = !isAscending;
            updateList();
        });

        searchBtn.setOnAction(actionEvent -> {
            updateList();
        });

        ObservableValue<Genre> genreObserver = genreComboBox.valueProperty();

        genreObserver.addListener((observable, oldValue, newValue) -> {
            selectedGenre = newValue;
        });

        resetFilter.setOnAction(actionEvent -> {
            genreComboBox.getSelectionModel().clearSelection();
            searchField.setText("");
            updateList();
        });
    }

    private void updateList() {
        List<Movie> filteredList = filterMoviesByEverything(movies, searchField.getText(), isAscending, String.valueOf(selectedGenre));
        createMovieCells(filteredList);
        isSorted = filteredList.size() != movies.size();
    }

    private void createMovieCells(List<Movie> moviesList) {
        movieListView.getItems().clear();
        for (Movie movie : moviesList) {
            VBox vbox = new VBox();
            Label title = new Label(movie.getTitle());
            Label description = new Label();
            Label genre = new Label();

            vbox.getChildren().addAll(title, description, genre);
            movieListView.getItems().add(vbox);

            title.getStyleClass().add("text-yellow");
            description.getStyleClass().add("text-white");
            genre.getStyleClass().add("text-white");
            vbox.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            description.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"

            );

            genre.setText(
                    movie.getGenres() != null
                            ? movie.getGenres().toString().toUpperCase().replace("[", "").replace("]", "")
                            : "No genres available"

            );

            title.fontProperty().set(title.getFont().font(20));
            description.setMaxWidth(890 - 40);
            description.setWrapText(true);
            vbox.setPadding(new Insets(10));
            vbox.spacingProperty().set(10);
            vbox.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);

            titlesList.add(title);
            descriptionsList.add(description);
        }
    }
}