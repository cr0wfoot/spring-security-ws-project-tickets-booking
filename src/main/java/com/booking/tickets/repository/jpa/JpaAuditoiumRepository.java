package com.booking.tickets.repository.jpa;

import com.booking.tickets.domain.Auditorium;
import com.booking.tickets.repository.AuditoriumRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaAuditoiumRepository implements AuditoriumRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public long create(final Auditorium auditorium) {
        em.merge(auditorium);
        em.flush();
        return auditorium.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Auditorium read(final long id) {
        return id > 0 ? em.find(Auditorium.class, id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auditorium> readAll() {
        return em.createNamedQuery("Auditorium.getAll", Auditorium.class).getResultList();
    }

    @Override
    @Transactional
    public void update(final Auditorium auditorium) {
        if (auditorium != null) {
            em.merge(auditorium);
        }
    }

    @Override
    @Transactional
    public void delete(final long id) {
        if (id > 0) {
            em.remove(em.find(Auditorium.class, id));
        }
    }
}
