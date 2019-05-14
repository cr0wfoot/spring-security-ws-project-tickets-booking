package com.booking.tickets.repository;

import com.booking.tickets.domain.Event;

import java.util.List;

public interface EventRepository {

    long create(Event event);

    Event read(long id);

    List<Event> readAll();

    void update(Event event);

    void delete(long id);
}
