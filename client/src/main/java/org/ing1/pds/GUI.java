package org.ing1.pds;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Shop Management");

        BorderPane mainContainer = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(mainContainer);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnchorPane middleContainer = FXMLLoader.load(getClass().getResource("gui.fxml"));
        mainContainer.setCenter(middleContainer);
    }

    static void launchApp(String[] args) {
        launch(args);
    }
}
