package com.booking.tickets.repository.jpa;

import com.booking.tickets.domain.Ticket;
import com.booking.tickets.repository.TicketRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaTicketRepository implements TicketRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public long create(final Ticket ticket) {
        em.merge(ticket);
        em.flush();
        return ticket.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Ticket read(final long id) {
        return id > 0 ? em.find(Ticket.class, id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> readAll() {
        return em.createNamedQuery("Ticket.getAll", Ticket.class).getResultList();
    }

    @Override
    @Transactional
    public void update(final Ticket ticket) {
        if (ticket != null) {
            em.merge(ticket);
        }
    }

    @Override
    @Transactional
    public void delete(final long id) {
        if (id > 0) {
            em.remove(em.find(Ticket.class, id));
        }
    }
}
