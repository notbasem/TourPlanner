package com.example.tourplanner.business.Managers;

import com.example.tourplanner.DAL.model.Tour;
import com.example.tourplanner.DAL.model.TourLog;
import com.example.tourplanner.business.API.ApiConnection;
import com.example.tourplanner.view.controller.MenuController;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PdfManager {
    private static final Logger logger = LogManager.getLogger(MenuController.class.getSimpleName());


    private static PdfManager pdfManager;

    public static PdfManager getInstance() {
        if (pdfManager == null) {
            pdfManager = new PdfManager();
        }
        return pdfManager;
    }


    public void exportSelectedTour(Optional<Tour> selectedTour, List<TourLog> tourLogsList) {
        System.out.println("In PDFMANAGER");
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(selectedTour.get().getName() + ".pdf");

        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Paragraph titleHeader = null;
        try {
            titleHeader = new Paragraph(selectedTour.get().getName())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(20)
                    .setBold();
        } catch (IOException e) {
            e.printStackTrace();
        }


        document.add(titleHeader.setMarginLeft(55).setMarginBottom(10));
        ApiConnection apiConnection = new ApiConnection(selectedTour.get().getFrom(), selectedTour.get().getTo());
        String url = apiConnection.getMap().getMapString();

        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create(url.replace(" ", "%20"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image pdfImg = new Image(imageData);
        document.add(pdfImg.setMarginLeft(55));

        Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();


        Paragraph from = new Paragraph("From");
        from.add(new Tab());
        from.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        from.add(selectedTour.get().getFrom());

        Cell cell = new Cell().add(from);
        table.addCell(cell);

        Paragraph to = new Paragraph("To");
        to.add(new Tab());
        to.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        to.add(selectedTour.get().getTo());

        Cell cell2 = new Cell().add(to);
        table.addCell(cell2);

        Paragraph desc = new Paragraph("Tourdescription");
        desc.add(new Tab());
        desc.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        desc.add(selectedTour.get().getTourDescription());

        Cell cell3 = new Cell().add(desc);
        table.addCell(cell3);

        Paragraph distance = new Paragraph("Distance");
        distance.add(new Tab());
        distance.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        distance.add(selectedTour.get().getTourDistance().toString());

        Cell cell4 = new Cell().add(distance);
        table.addCell(cell4);

        Paragraph time = new Paragraph("Time");
        time.add(new Tab());
        time.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        time.add(selectedTour.get().getEstimatedTime());

        Cell cell5 = new Cell().add(time);
        table.addCell(cell5);

        document.add(table.setMarginLeft(55).setMarginRight(60).setMarginTop(10).setMarginBottom(10));

        Table newtable = createLogTable(tourLogsList).setMarginLeft(55).setMarginRight(60);
        setTable(tourLogsList, newtable);
        document.add(newtable);

        document.close();
        logger.info("PDF: "+selectedTour.get().getName()+".pdf created successfully");

        } catch (FileNotFoundException e) {
            logger.error("PDF could not be created");
            e.printStackTrace();
        }

    }


    private Table createLogTable(List<TourLog> tourLogsList) {
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth().setFixedLayout();

        table.addCell(getHeaderCell("Date and Time"));
        table.addCell(getHeaderCell("Duration"));
        table.addCell(getHeaderCell("Distance"));
        table.addCell(getHeaderCell("Comment"));
        table.addCell(getHeaderCell("Rating"));
        table.addCell(getHeaderCell("Difficulty "));


        return table;
    }

    private void setTable(List<TourLog> tourLogsList, Table table) {
        for (TourLog tourlog : tourLogsList) {
            table.setFontSize(10).setBackgroundColor(ColorConstants.WHITE);
            table.addCell(tourlog.getDate());
            table.addCell(tourlog.getDuration());
            table.addCell(String.valueOf(tourlog.getDistance()));
            table.addCell(tourlog.getComment());
            table.addCell(String.valueOf(tourlog.getRating()));
            table.addCell(String.valueOf(tourlog.getDifficulty()));

        }
    }

    public void exportAlltours(ObservableList<Tour> tourList, List<TourLog> tourLogsList) {
        PdfWriter writer = null;
        try {
            writer = new PdfWriter("allTours.pdf");

        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);


        for (Tour tour : tourList) {

            if (tour != tourList.get(0)) {
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }


            Paragraph titleHeader = null;
            try {
                titleHeader = new Paragraph(tour.getName())
                        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                        .setFontSize(20)
                        .setBold();
            } catch (IOException e) {
                e.printStackTrace();
            }

            document.add(titleHeader.setMarginLeft(55).setMarginBottom(10));
            ApiConnection apiConnection = new ApiConnection(tour.getFrom(), tour.getTo());
            String url = apiConnection.getMap().getMapString();

            ImageData imageData = null;
            try {
                imageData = ImageDataFactory.create(url.replace(" ", "%20"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
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

            List<TourLog> addTourlogs = new ArrayList<TourLog>();

            for (TourLog tourLog : tourLogsList
            ) {
                if (tourLog.getTourname().equals(tour.getName())) {
                    addTourlogs.add(tourLog);
                }

            }
            if (!addTourlogs.isEmpty()) {
                Table addTable = createLogTable(addTourlogs).setMarginLeft(55).setMarginRight(60);
                setTable(addTourlogs,addTable);
                document.add(addTable);
            }

        }

        document.close();
        logger.info("PDF: allTours.pdf created successfully");

        } catch (FileNotFoundException e) {
            logger.error("PDF could not be created");
            e.printStackTrace();
        }
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setFontSize(11).setBold().setBackgroundColor(ColorConstants.GRAY);
    }

}
