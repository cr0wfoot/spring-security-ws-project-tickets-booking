package com.booking.tickets.service;

import com.booking.tickets.domain.Event;
import com.booking.tickets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import static java.time.LocalDate.parse;
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

    @Autowired
    private CsvConversionService csvConversionService;

    @Transactional
    public void registerEvent(final Event newEvent, final long auditoriumId) {
        newEvent.setAuditorium(auditoriumService.getAuditoirumById(auditoriumId));
        registerEvent(newEvent);
    }

    @Transactional
    public void registerEvent(final Event newEvent) {
        eventRepository.create(newEvent);
    }

    @Transactional
    public void removeEvent(final Event event) {
        eventRepository.delete(event.getId());
    }

    public Event getEventById(final long eventId) {
        return eventRepository.read(eventId);
    }

    public List<Event> getAllEvents() {
        return eventRepository.readAll();
    }

    public List<String> getFreeSeatsForEvent(final Event event) {
        final List<String> bookedSeats = event.getBookedTickets().stream().flatMap(t -> t.getListOfBookedSeats().stream()).collect(toList());
        final List<String> freeSeats = getAllSeatsForEvent(event);
        freeSeats.removeAll(bookedSeats);
        return freeSeats;
    }

    private List<String> getAllSeatsForEvent(final Event event) {
        final List<Integer> seats = range(1, event.getAuditorium().getSeatsQuantity() + 1).boxed().collect(toList());
        return seats.stream().map(Object::toString).collect(toList());
    }

    public List<Event> getListOfEventsFromString(final String eventsData) {
        List<Event> events = new ArrayList<>();
        for (Map<String, String> values : csvConversionService.convertStringToListOfValues(eventsData)) {
            Event event = new Event();
            event.setName(values.get("name"));
            event.setSeatPrice(parseDouble(values.get("price")));
            event.setDate(parse(values.get("date")).atStartOfDay());
            event.setAuditorium(auditoriumService.getAuditoirumById(parseLong(values.get("auditorium"))));
            events.add(event);
        }
        return events;
    }
}
