package com.booking.tickets.repository.jpa;

import com.booking.tickets.domain.User;
import com.booking.tickets.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public long create(final User user) {
        em.merge(user);
        em.flush();
        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public User read(final long id) {
        return id > 0 ? em.find(User.class, id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public User read(final String login) {
        if (login != null) {
            return em.createNamedQuery("User.getByLogin", User.class).setParameter("login", login).getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readAll() {
        return em.createNamedQuery("User.getAll", User.class).getResultList();
    }

    @Override
    @Transactional
    public void update(final User user) {
        if (user != null) {
            em.merge(user);
        }
    }

    @Override
    @Transactional
    public void delete(final long id) {
        if (id > 0) {
            em.remove(em.find(User.class, id));
        }
    }
}
