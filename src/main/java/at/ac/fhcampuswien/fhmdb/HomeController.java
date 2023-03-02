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

import static at.ac.fhcampuswien.fhmdb.models.Genre.normalizeGenre;
import static at.ac.fhcampuswien.fhmdb.models.Movie.*;

public class HomeController implements Initializable {
    @FXML
    public Button searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public ListView movieListView;

    @FXML
    public ComboBox genreComboBox;

    @FXML
    public Button sortBtn;

    public static ArrayList<Label> titlesList = new ArrayList<Label>();
    public static ArrayList<Label> descriptionsList = new ArrayList<Label>();
    private Genre selectedGenre;
    boolean ascending = true;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createMovieCells(movies);

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            movieListView.getItems().clear();
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortBtn.setText("Sort (desc)");
            } else {
                sortBtn.setText("Sort (asc)");
            }
            ascending = !ascending;
            createMovieCells(filterMoviesByNameAndAscending(movies, searchField.getText(), ascending));
        });
        searchBtn.setOnAction(actionEvent -> {
            createMovieCells(filterMoviesByNameAndAscending(movies, searchField.getText(), ascending));
        });

        ObservableValue<Genre> genreObserver = genreComboBox.valueProperty();

        genreObserver.addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected value: " + normalizeGenre(String.valueOf(newValue)));
            createMovieCells(
                    filterMoviesByGenre(
                            filterMoviesByNameAndAscending(movies, searchField.getText(), ascending),
                            newValue));
        });
    }

    private void createMovieCells(List<Movie> moviesList) {
        movieListView.getItems().clear();
        for (Movie movie : moviesList) {
            VBox vbox = new VBox();
            Label title = new Label(movie.getTitle());
            Label description = new Label();

            vbox.getChildren().addAll(title, description);
            movieListView.getItems().add(vbox);

            title.getStyleClass().add("text-yellow");
            description.getStyleClass().add("text-white");
            vbox.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            description.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
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