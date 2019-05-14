package com.booking.tickets.repository.jpa;

import com.booking.tickets.domain.Event;
import com.booking.tickets.repository.EventRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaEventRepository implements EventRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public long create(final Event event) {
        em.merge(event);
        em.flush();
        return event.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Event read(final long id) {
        return id > 0 ? em.find(Event.class, id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> readAll() {
        return em.createNamedQuery("Event.getAll", Event.class).getResultList();
    }

    @Override
    @Transactional
    public void update(final Event event) {
        if (event != null) {
            em.merge(event);
        }
    }

    @Override
    @Transactional
    public void delete(final long id) {
        if (id > 0) {
            em.remove(em.find(Event.class, id));
        }
    }
}
