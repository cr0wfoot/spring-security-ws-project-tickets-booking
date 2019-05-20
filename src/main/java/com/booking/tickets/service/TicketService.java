package com.booking.tickets.service;

import com.booking.tickets.domain.Event;
import com.booking.tickets.domain.Ticket;
import com.booking.tickets.domain.User;
import com.booking.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private static final int VIP_SEAT_PRICE_FACTOR = 2;

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public void bookTicket(final User user, final Event event, final List<String> seats) {
        final Ticket newTicket = new Ticket();
        newTicket.setUser(user);
        newTicket.setEvent(event);
        newTicket.setBookedSeats(seats);
        newTicket.setTotalPrice(calculateTotalPrice(event, seats));
        ticketRepository.create(newTicket);
    }

    private double calculateTotalPrice(final Event event, final List<String> seatsToBook) {
        final List<String> vipSeats = event.getAuditorium().getListOfVipSeats();
        double totalPrice = 0;
        for (String seat : seatsToBook) {
            totalPrice += vipSeats.contains(seat) ? event.getSeatPrice() * VIP_SEAT_PRICE_FACTOR : event.getSeatPrice();
        }
        return totalPrice;
    }
}
