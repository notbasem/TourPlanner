module com.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires kernel;
    requires layout;


    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    exports com.example.tourplanner.view.factory;
    opens com.example.tourplanner.view.factory to javafx.fxml;
    exports com.example.tourplanner.view.controller;
    opens com.example.tourplanner.view.controller to javafx.fxml;
}