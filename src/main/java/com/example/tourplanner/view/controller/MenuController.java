package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.viewmodel.MenuVM;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MenuController {
    private final MenuVM menuViewModel;
    @FXML MenuBar menuBar;
    @FXML MenuItem closeButton;
    @FXML MenuItem exportButton;
    @FXML TextField searchTextField;

    public MenuController (MenuVM menuViewModel)
    {
        this.menuViewModel = new MenuVM() ;
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional(menuViewModel.searchStringProperty() );
        searchTextField.textProperty().addListener((observable, oldValue, newValue) ->  menuViewModel.onSearch());
    }

    @FXML
    public void onExport(){
        System.out.println("File export button was clicked");
        ObservableList<Tour> tourlist = this.menuViewModel.exportallTours();
        PdfWriter writer = null;
        try {
            writer = new PdfWriter("tours.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph header = new Paragraph("Tours:");
        header.setFontSize(32);
        document.add(header);

        // Creating a table
        Table table = new Table(8);
        table.addHeaderCell("Name");
        table.addHeaderCell("Description");
        table.addHeaderCell("From");
        table.addHeaderCell("To");
        table.addHeaderCell("Transport type");
        table.addHeaderCell("Distance");
        table.addHeaderCell("Estimated time");
        table.addHeaderCell("Route information");

        for (Tour tour: tourlist) {
            table.addCell(tour.getName());
            table.addCell(tour.getTourDescription());
            table.addCell(tour.getFrom());
            table.addCell(tour.getTo());
            table.addCell(tour.getTransportType());
            table.addCell(String.valueOf(tour.getTourDistance()));
            table.addCell(String.valueOf(tour.getEstimatedTime()));
            table.addCell(tour.getRouteInformation());
        }
        document.add(table);
        document.close();
    }

    public void onClose() {
        ((Stage) menuBar.getScene().getWindow()).close();
    }
}
