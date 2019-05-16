package com.booking.tickets.controller;

import com.booking.tickets.domain.Event;
import com.booking.tickets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/all")
    public String redirectToPageWithAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @RequestMapping("/id/{id}")
    public String redirectToPageWithAllEventData(Model model, @PathVariable long id) {
        final Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        model.addAttribute("seats", eventService.getFreeSeatsForEvent(event));
        return "event";
    }

    @RequestMapping("/tickets")
    public String redirectToPageWithTicketsBookedForEvent(Model model, @RequestParam long eventId) {
        model.addAttribute("tickets", eventService.getEventById(eventId).getBookedTickets());
        return "tickets";
    }


    @RequestMapping(value = "/tickets/pdf", headers = "Accept=application/pdf")
    public String getPdfWithTicketsBookedForEvent(Model model, @RequestParam long eventId) {
        model.addAttribute("tickets", eventService.getEventById(eventId).getBookedTickets());
        return "ticketsPdfView";
    }

    @RequestMapping(value = "/create", method = POST)
    public String createEvent(Model model, @RequestParam String eventName, @RequestParam double price,
                              @RequestParam long auditoriumId, @RequestParam @DateTimeFormat(iso = DATE_TIME) LocalDateTime date) {
        final Event newEvent = new Event();
        newEvent.setName(eventName);
        newEvent.setSeatPrice(price);
        newEvent.setDate(date);
        eventService.registerEvent(newEvent, auditoriumId);
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @RequestMapping(value = "/remove", method = POST)
    public String removeEvent(Model model, @RequestParam Event event) {
        eventService.removeEvent(event);
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @RequestMapping(value = "/upload", method = POST)
    public void uploadEvents(@RequestParam("file") MultipartFile fileWithEvents) throws IOException {
        if (!fileWithEvents.isEmpty()) {
            byte[] events = fileWithEvents.getBytes();
            for (Event event : eventService.getListOfEventsFromString(new String(events))) {
                eventService.registerEvent(event);
            }
        }
    }
}
