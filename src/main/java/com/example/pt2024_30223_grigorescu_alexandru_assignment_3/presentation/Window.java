package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.exceptions.ViewNotFoundException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.exceptions.WindowUninitializedException;

import java.io.IOException;
import java.net.URL;

public class Window {
    private final Stage stage;
    private static Window instance;

    public Window(Stage stage) {
        stage.setTitle("Dispecerat Emag");
        stage.setWidth(1512);
        stage.setHeight(982);
        stage.getIcons().add(new Image("file:/images/logo.png"));
        stage.show();
        this.stage = stage;
        instance = this;
    }

    public static Window getInstance() throws WindowUninitializedException {
        if (instance == null) throw new WindowUninitializedException();
        return instance;
    }

    public static void setView(String view) throws ViewNotFoundException {
        Window window = null;
        try {
            window = getInstance();
        } catch (WindowUninitializedException e) {
            throw new RuntimeException(e);
        }

        URL viewPath = window.getClass().getResource(view + ".fxml");

        if (viewPath == null) throw new ViewNotFoundException();

        try {
            window.stage.setScene(new Scene(FXMLLoader.load(viewPath), 800, 600));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
