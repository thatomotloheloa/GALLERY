package com.example.motlohelwa;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private static final int THUMBNAIL_SIZE = 100; // Adjust the size here

    private List<Image> images;
    private int currentIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        // Set the background color to blue
        root.setBackground(new Background(new BackgroundFill(Color.rgb(50, 100, 200), CornerRadii.EMPTY, Insets.EMPTY)));

        images = loadImages();

        GridPane gridPane = createThumbnailGrid();
        root.setCenter(gridPane);

        Button previousButton = new Button("Previous");
        previousButton.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white;");
        previousButton.setOnAction(event -> showPreviousImage());

        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white;");
        nextButton.setOnAction(event -> showNextImage());

        HBox navigationBox = new HBox(10);
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.getChildren().addAll(previousButton, nextButton);

        root.setBottom(navigationBox);
        BorderPane.setMargin(navigationBox, new Insets(20, 0, 20, 0));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Gallery");
        primaryStage.show();
    }

    private GridPane createThumbnailGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        int col = 0;
        int row = 0;
        for (int i = 0; i < images.size(); i++) {
            Image image = images.get(i);
            ImageView thumbnail = createThumbnail(image);

            int finalI = i;
            thumbnail.setOnMouseClicked(event -> {
                currentIndex = finalI;
                showFullSizeImage(image);
            });

            gridPane.add(thumbnail, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        return gridPane;
    }

    private ImageView createThumbnail(Image image) {
        ImageView thumbnail = new ImageView(image);
        thumbnail.setFitWidth(THUMBNAIL_SIZE);
        thumbnail.setFitHeight(THUMBNAIL_SIZE);

        // Make the thumbnail circular
        Circle clip = new Circle(THUMBNAIL_SIZE / 2, THUMBNAIL_SIZE / 2, THUMBNAIL_SIZE / 2);
        thumbnail.setClip(clip);

        return thumbnail;
    }

    private List<Image> loadImages() {
        // Load your images here
        List<Image> images = new ArrayList<>();
        images.add(new Image("img1.jpg"));
        images.add(new Image("img2.jpg"));
        images.add(new Image("img3.jpg"));
        images.add(new Image("img4.jpg"));
        images.add(new Image("img5.jpg"));
        images.add(new Image("img6.jpg"));
        images.add(new Image("img7.jpg"));
        images.add(new Image("img8.jpg"));
        images.add(new Image("img9.jpg"));
        images.add(new Image("img10.jpg"));

        return images;
    }

    private void showFullSizeImage(Image image) {
        Stage stage = new Stage();
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        imageView.setFitHeight(500);
        StackPane pane = new StackPane(imageView);
        pane.setStyle("-fx-background-color: #555555;");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Full Size Image");
        stage.show();
    }

    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            showFullSizeImage(images.get(currentIndex));
        }
    }

    private void showNextImage() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
            showFullSizeImage(images.get(currentIndex));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

