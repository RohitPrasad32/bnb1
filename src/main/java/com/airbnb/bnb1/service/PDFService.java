package com.airbnb.bnb1.service;

import com.airbnb.bnb1.entity.Booking;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.stream.Stream;


@Service
public class PDFService {
    private EmailService emailService;

    public PDFService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void generatePdf(Booking booking) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C://bnb_bookings//"+booking.getId()+"_booking_confirmation.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addRows(table, booking);
            //addCustomRows(table);

            document.add(table);
            document.close();
            emailService.sendEmailWithAttachment(booking.getEmail(),"Booking Conformation. Your booking id is "+booking.getId(),
                    "test","C://bnb_bookings//"+booking.getId()+"_booking_confirmation.pdf");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Guest Name", "Hotel Name", "City")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Booking booking) {
        table.addCell(booking.getGuestName());
        table.addCell(booking.getProperty().getName());
        table.addCell(booking.getProperty().getCity().getName());
    }
//    private void addCustomRows(PdfPTable table)
//            throws URISyntaxException, BadElementException, IOException {
//        Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
//        Image img = Image.getInstance(path.toAbsolutePath().toString());
//        img.scalePercent(10);
//
//        PdfPCell imageCell = new PdfPCell(img);
//        table.addCell(imageCell);
//
//        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(horizontalAlignCell);
//
//        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//        table.addCell(verticalAlignCell);
//    }

}
