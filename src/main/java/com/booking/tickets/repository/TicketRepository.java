package com.booking.tickets.repository;

import com.booking.tickets.domain.Ticket;

import java.util.List;

public interface TicketRepository {

    long create(Ticket event);

    Ticket read(long id);

    List<Ticket> readAll();

    void update(Ticket event);

    void delete(long id);
}
