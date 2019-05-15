package com.booking.tickets.service;

import com.booking.tickets.domain.User;
import com.booking.tickets.domain.UserRole;
import com.booking.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.booking.tickets.domain.UserRole.ROLE_USER;
import static java.time.LocalDate.parse;

@Service
public class UserService {

    private static final UserRole DEFAULT_ACCESS_ROLE = ROLE_USER;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CsvConversionService csvConversionService;

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

    public List<User> getListOfUsersFromString(final String usersData) {
        List<User> users = new ArrayList<>();
        for (Map<String, String> values : csvConversionService.convertStringToListOfValues(usersData)) {
            User user = new User();
            user.setEmail(values.get("email"));
            user.setFullName(values.get("name"));
            user.setLogin(values.get("login"));
            user.setPassword(values.get("password"));
            user.setAccessRole(UserRole.valueOf(values.get("role")));
            users.add(user);
        }
        return users;
    }
}
