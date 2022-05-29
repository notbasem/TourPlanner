module com.example.tourplanner {
    requires java.sql;
    requires lombok;
    requires kernel;
    requires layout;
    requires java.net.http;
    requires json;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires io;


    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    exports com.example.tourplanner.view.factory;
    opens com.example.tourplanner.view.factory to javafx.fxml;
    exports com.example.tourplanner.view.controller;
    opens com.example.tourplanner.view.controller to javafx.fxml;
    opens  com.example.tourplanner.DAL.model to javafx.base;
}