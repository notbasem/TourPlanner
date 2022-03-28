module com.example.tourplanner {

    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;


    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    exports com.example.tourplanner.view.factory;
    opens com.example.tourplanner.view.factory to javafx.fxml;
    exports com.example.tourplanner.view.controller;
    opens com.example.tourplanner.view.controller to javafx.fxml;
}