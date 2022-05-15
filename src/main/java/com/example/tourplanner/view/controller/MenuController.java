package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.business.TourManager;
import com.example.tourplanner.viewmodel.MenuVM;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.EventListener;

public class MenuController {
    private final MenuVM menuViewModel;
    @FXML Button FileButton;
    @FXML TextField searchTextField;
    @FXML Button searchButton;

    public MenuController (MenuVM menuViewModel)
    {
        this.menuViewModel = new MenuVM() ;
    }

    public void onFileButtonClick(){
        System.out.println("File export button was clicked");
        ObservableList<Tour> tourlist = this.menuViewModel.exportallTours();
        PdfWriter writer = null;
        try {
            writer = new PdfWriter("target.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        for (Tour tour: tourlist
             ) {
            Table table = new Table(UnitValue.createPercentArray(8));
            table.addHeaderCell("Name");
            table.addHeaderCell("Description");
            table.addHeaderCell("From");
            table.addHeaderCell("To");
            table.addHeaderCell("Transport Type");
            table.addHeaderCell("Distance");
            table.addHeaderCell("estimated Time");
            table.addHeaderCell("Route Information");

            table.addCell(tour.getName());
            table.addCell(tour.getTourDescription());
            table.addCell(tour.getFrom());
            table.addCell(tour.getTo());
            table.addCell(tour.getTransportType());
            table.addCell(String.valueOf(tour.getTourDistance()));
            table.addCell(String.valueOf(tour.getEstimatedTime()));
            table.addCell(tour.getRouteInformation());

            document.add(table);

        }

        document.close();
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional(menuViewModel.searchStringProperty() );
        searchTextField.textProperty().addListener((observable, oldValue, newValue) ->  menuViewModel.onSearch());
    }
}
