package com.booking.tickets.service;

import com.booking.tickets.domain.User;
import com.booking.tickets.domain.UserRole;
import com.booking.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.booking.tickets.domain.UserRole.ROLE_USER;

@Service
public class UserService {

    private static final UserRole DEFAULT_ACCESS_ROLE = ROLE_USER;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void registerUser(final User newUser) {
        newUser.setAccessRole(DEFAULT_ACCESS_ROLE);
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
