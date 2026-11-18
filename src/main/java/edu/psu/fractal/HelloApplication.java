package edu.psu.fractal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    // method for drawing square
    private void drawSquare(Pane pane, double x, double y, double size){
        Rectangle square = new Rectangle(x, y, size, size);
        square.setFill(Color.BLACK);
        pane.getChildren().add(square);
    }

    private void drawVicsek(Pane pane, double x, double y, double size, int iteration){
        double smallSize = size / 3;

        if (iteration == 1){
            // top-left
            drawSquare(pane, x, y, smallSize);
            // top-right
            drawSquare(pane, x + 2 * smallSize, y, smallSize);
            // center
            drawSquare(pane, x + smallSize, y + smallSize, smallSize);
            // bottom-left
            drawSquare(pane, x, y + 2 * smallSize, smallSize);
            // bottom-right
            drawSquare(pane, x + 2 * smallSize, y + 2 * smallSize, smallSize);

        } else{
            drawVicsek(pane, x, y, smallSize, iteration - 1);
            drawVicsek(pane, x + 2 * smallSize, y, smallSize, iteration - 1);
            drawVicsek(pane, x + smallSize, y + smallSize, smallSize, iteration -1);
            drawVicsek(pane, x, y + 2 * smallSize, smallSize, iteration - 1);
            drawVicsek(pane, x + 2 * smallSize, y + 2 * smallSize, smallSize, iteration - 1);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();

        Pane drawingPane = new Pane();
        root.setCenter(drawingPane);

        TextField iterationField = new TextField("1");
        Label iterationLabel = new Label("Iteration: ");
        iterationField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int iteration = Integer.parseInt(newValue);
                if (iteration < 1 || iteration > 6){
                    return;
                }
                drawingPane.getChildren().clear();
                drawVicsek(drawingPane, 200, 150, 300, iteration);
            } catch (NumberFormatException e) {

            }
        });

        BorderPane inputPane = new BorderPane();
        inputPane.setLeft(iterationLabel);
        inputPane.setCenter(iterationField);

        root.setTop(inputPane);


        Scene scene = new Scene(root, 700, 700);
        stage.setScene(scene);
        stage.setTitle("Vicsek Fractal");
        stage.show();

        drawVicsek(drawingPane, 200, 150, 300, 1);
    }
}
