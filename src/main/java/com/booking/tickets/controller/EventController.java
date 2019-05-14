package com.booking.tickets.controller;

import com.booking.tickets.domain.Event;
import com.booking.tickets.service.AuditoriumService;
import com.booking.tickets.service.EventService;
import com.booking.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/all")
    public String getAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @RequestMapping("/id/{id}")
    public String getEventById(Model model, @PathVariable long id) {
        final Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        model.addAttribute("seats", eventService.getFreeSeatsForEvent(event));
        return "event";
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
}
