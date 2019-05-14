package com.booking.tickets.service;

import com.booking.tickets.domain.Event;
import com.booking.tickets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private TicketService ticketService;

    @Transactional
    public void registerEvent(final Event newEvent, final long auditoriumId) {
        newEvent.setAuditorium(auditoriumService.getAuditoirumById(auditoriumId));
        eventRepository.create(newEvent);
    }

    public Event getEventById(final long eventId) {
        return eventRepository.read(eventId);
    }

    public List<Event> getAllEvents() {
        return eventRepository.readAll();
    }

    public List<String> getFreeSeatsForEvent(final Event event) {
        final List<String> bookedSeats = ticketService.getTicktesBookedForEvent(event).stream().flatMap(t -> t.getBookedSeats().stream()).collect(toList());
        final List<String> freeSeats = getAllSeatsForEvent(event);
        freeSeats.removeAll(bookedSeats);
        return freeSeats;
    }

    private List<String> getAllSeatsForEvent(final Event event) {
        final List<Integer> seats = range(1, event.getAuditorium().getSeatsQuantity() + 1).boxed().collect(toList());
        return seats.stream().map(Object::toString).collect(toList());
    }
}
