package com.booking.tickets.controller;

import com.booking.tickets.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auditorium")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping("/all")
    public String redirectToPageWithAllAuditoriums(Model model) {
        model.addAttribute("auditoriums", auditoriumService.getAllAuditoriums());
        return "auditoriums";
    }

    @RequestMapping("/{id}")
    public String redirectToPageWithAuditoriumData(Model model, @PathVariable long id) {
        model.addAttribute("auditorium", auditoriumService.getAuditoriumById(id));
        return "auditorium";
    }
}
