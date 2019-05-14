package com.booking.tickets.repository;

import com.booking.tickets.domain.Auditorium;

import java.util.List;

public interface AuditoriumRepository {

    long create(Auditorium auditorium);

    Auditorium read(long id);

    List<Auditorium> readAll();

    void update(Auditorium auditorium);

    void delete(long id);
}
