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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = GET)
    public String redirectToRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/register", method = POST)
    public String registerNewUser(@RequestParam String email, @RequestParam String name,
                                  @RequestParam String login, @RequestParam String password) {
        final User userData = new User();
        userData.setLogin(login);
        userData.setFullName(name);
        userData.setEmail(email);
        userService.registerUser(userData, password);
        return "redirect:/user/profile";
    }

    @RequestMapping("/profile")
    public String redirectToUserProfilePage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByLogin(principal.getName()));
        return "user";
    }

    @RequestMapping("/tickets")
    public String redirectToPageWithTicketsBookedByUser(Model model, @RequestParam long userId) {
        User user = userService.getById(userId);
        model.addAttribute("tickets", user.getBookedTickets());
        model.addAttribute("user", user);
        return "tickets";
    }

    @RequestMapping(value = "/tickets/pdf", headers="Accept=application/pdf")
    public String getPdfWithTicketsBookedByUser(Model model, @RequestParam long userId) {
        User user = userService.getById(userId);
        model.addAttribute("tickets", user.getBookedTickets());
        return "ticketsPdfView";
    }

    @RequestMapping("/id/{id}")
    public String redirectToPageWithUserPersonalData(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @RequestMapping("/all")
    public String redirectToAdminPageWithAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-users";
    }

    @RequestMapping(value = "/remove", method = POST)
    public String removeUser(@RequestParam long id) {
        userService.removeUser(id);
        return "redirect:/user/all";
    }

    @RequestMapping(value = "/upload", method = POST)
    public String uploadUsers(Model model, @RequestParam("file") MultipartFile fileWithUsers) throws IOException {
        if (!fileWithUsers.isEmpty()) {
            byte[] users = fileWithUsers.getBytes();
            for (User user : userService.getListOfUsersFromString(new String(users))) {
                userService.registerUser(user);
            }
            return "redirect:/user/all";
        } else {
            model.addAttribute("errorMessage", "Error while uploading users.");
            return "error";
        }
    }
}
