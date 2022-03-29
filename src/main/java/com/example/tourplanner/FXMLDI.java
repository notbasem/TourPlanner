package com.example.tourplanner;

import com.example.tourplanner.view.factory.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;

public class FXMLDI {
    public static Parent load(String fxml, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(fxml, locale);
        return loader.load();
    }

    private static FXMLLoader getLoader(String fxml, Locale locale) {
        return new FXMLLoader(
                FXMLDI.class.getResource("/com/example/tourplanner/" + fxml),
                null,
                new JavaFXBuilderFactory(),
                clazz -> ControllerFactory.getInstance().create(clazz)
        );
    }
}