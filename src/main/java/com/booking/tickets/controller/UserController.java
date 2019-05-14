package com.booking.tickets.controller;

import com.booking.tickets.domain.User;
import com.booking.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = GET)
    public String redirectToUserRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/register", method = POST)
    public String registerNewUser(Model model, @RequestParam String email, @RequestParam String name,
                                  @RequestParam String login, @RequestParam String password) {
        final User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(name);
        newUser.setEmail(email);
        userService.registerUser(newUser);
        return "redirect:/user/profile";
    }

    @RequestMapping("/profile")
    public String getUserByLogin(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByLogin(principal.getName()));
        return "user";
    }

    @RequestMapping("/id/{id}")
    public String getUserById(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @RequestMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-users";
    }
}
