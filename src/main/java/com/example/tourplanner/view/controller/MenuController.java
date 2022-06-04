package com.example.tourplanner.view.controller;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.viewmodel.MenuVM;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.UnitValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MenuController {
    private final MenuVM menuViewModel;
    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem closeButton;
    @FXML
    MenuItem exportButton;
    @FXML
    TextField searchTextField;

    public MenuController(MenuVM menuViewModel) {
        this.menuViewModel = new MenuVM();
    }

    @FXML
    void initialize() {
        searchTextField.textProperty().bindBidirectional(menuViewModel.searchStringProperty());
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> menuViewModel.onSearch());
    }

    @FXML
    public void onExport() throws IOException, InterruptedException {
        System.out.println("File export button was clicked");
        ObservableList<Tour> tourList = this.menuViewModel.exportallTours();
        PdfWriter writer = null;
        try {
            writer = new PdfWriter("tours.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);


        for (Tour tour : tourList) {

            if (tour != tourList.get(0)) {
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            Paragraph titleHeader = new Paragraph(tour.getName())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(20)
                    .setBold();

            document.add(titleHeader.setMarginLeft(55).setMarginBottom(10));
            ApiConnection apiConnection = new ApiConnection(tour.getFrom(), tour.getTo());
            String url = apiConnection.getMap().getMapString();

            ImageData imageData = ImageDataFactory.create(url.replace(" ", "%20"));
            Image pdfImg = new Image(imageData);
            document.add(pdfImg.setMarginLeft(55));

            Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();


            Paragraph from = new Paragraph("From");
            from.add(new Tab());
            from.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            from.add(tour.getFrom());

            Cell cell = new Cell().add(from);
            table.addCell(cell);

            Paragraph to = new Paragraph("To");
            to.add(new Tab());
            to.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            to.add(tour.getTo());

            Cell cell2 = new Cell().add(to);
            table.addCell(cell2);

            Paragraph desc = new Paragraph("Tourdescription");
            desc.add(new Tab());
            desc.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            desc.add(tour.getTourDescription());

            Cell cell3 = new Cell().add(desc);
            table.addCell(cell3);

            Paragraph distance = new Paragraph("Distance");
            distance.add(new Tab());
            distance.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            distance.add(tour.getTourDistance().toString());

            Cell cell4 = new Cell().add(distance);
            table.addCell(cell4);

            Paragraph time = new Paragraph("Time");
            time.add(new Tab());
            time.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            time.add(tour.getEstimatedTime());

            Cell cell5 = new Cell().add(time);
            table.addCell(cell5);

            document.add(table.setMarginLeft(55).setMarginRight(60).setMarginTop(10).setMarginBottom(10));

            document.add(createLogTable(tour.getName()).setMarginLeft(55).setMarginRight(60));
        }

        document.close();
    }

    public void onClose() {
        ((Stage) menuBar.getScene().getWindow()).close();
    }

    public Table createLogTable(String tourname) {
        ObservableList<TourLog> tourLogsList = this.menuViewModel.exportallTourLogs(tourname);

        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth().setFixedLayout();

        table.addCell(getHeaderCell("Date"));
        table.addCell(getHeaderCell("Duration"));
        table.addCell(getHeaderCell("Distance"));
        table.addCell(getHeaderCell("Comment"));
        table.addCell(getHeaderCell("Rating"));


        for (TourLog tourlog : tourLogsList) {
            table.setFontSize(10).setBackgroundColor(ColorConstants.WHITE);
            table.addCell(tourlog.getDate());
            table.addCell(tourlog.getDuration());
            table.addCell(String.valueOf(tourlog.getDistance()));
            table.addCell(tourlog.getComment());
            table.addCell(String.valueOf(tourlog.getRating()));

        }

        return table;
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setFontSize(11).setBold().setBackgroundColor(ColorConstants.GRAY);
    }
}
