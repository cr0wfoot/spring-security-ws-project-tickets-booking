package com.booking.tickets.controller;

import com.booking.tickets.domain.Event;
import com.booking.tickets.domain.User;
import com.booking.tickets.service.EventService;
import com.booking.tickets.service.TicketService;
import com.booking.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/book", method = POST)
    public String bookTicket(Model model, Principal principal, @RequestParam(value = "seat") List<String> seats,
                             @RequestParam long eventId) {
        final User user = userService.getUserByLogin(principal.getName());
        final Event event = eventService.getEventById(eventId);
        ticketService.bookTicket(user, event, seats);
        model.addAttribute(user);
        return "redirect:/user/tickets?userId=" + user.getId();
    }
}
