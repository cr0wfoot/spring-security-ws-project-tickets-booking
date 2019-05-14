package com.booking.tickets.repository;

import com.booking.tickets.domain.User;

import java.util.List;

public interface UserRepository {

    long create(User event);

    User read(long id);

    User read(String login);

    List<User> readAll();

    void update(User event);

    void delete(long id);
}
