package com.booking.tickets.service;

import com.booking.tickets.domain.Auditorium;
import com.booking.tickets.repository.AuditoriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @PreAuthorize("hasPermission(#auditoriumId, 'GET')")//Role need to be ROLE_LONG_GET
    public Auditorium getAuditoriumById(final long auditoriumId) {
        return auditoriumRepository.read(auditoriumId);
    }

    public List<Auditorium> getAllAuditoriums() {
        return auditoriumRepository.readAll();
    }
}
