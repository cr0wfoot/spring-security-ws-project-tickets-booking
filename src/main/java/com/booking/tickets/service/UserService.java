package com.booking.tickets.service;

import com.booking.tickets.domain.User;
import com.booking.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void registerUser(final User newUser) {
        userRepository.create(newUser);
    }

    public User getById(final long userId) {
        return userRepository.read(userId);
    }

    public User getUserByLogin(final String userLogin) {
        return userRepository.read(userLogin);
    }

    public List<User> getAllUsers() {
        return userRepository.readAll();
    }
}
