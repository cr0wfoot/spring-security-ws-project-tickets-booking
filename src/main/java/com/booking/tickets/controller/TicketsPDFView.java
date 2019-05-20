package com.booking.tickets.controller;

import com.booking.tickets.domain.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;


@Component("ticketsPdfView")
public class TicketsPDFView extends AbstractPdfView {

    private static final String VALUES_DELIMITER = " | ";
    private static final String HEADER = "Event | Date | Seats | Price | Auditorium";

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Set<Ticket> tickets = (Set<Ticket>) map.get("tickets");
        document.add(new Paragraph(HEADER));
        for (Ticket t : tickets) {
            document.add(new Paragraph(t.getEvent().getName() + VALUES_DELIMITER + t.getEvent().getDate() + VALUES_DELIMITER +
                    t.getListOfBookedSeats() + VALUES_DELIMITER + t.getTotalPrice() + VALUES_DELIMITER + t.getEvent().getAuditorium().getName()));
        }
    }
}
