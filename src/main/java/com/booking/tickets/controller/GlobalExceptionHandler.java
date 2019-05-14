package com.booking.tickets.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String hanldeException(Model model, Exception ex) {
        LOG.error(ex, ex);
        model.addAttribute("errorMessage", ex);
        return "error";
    }
}
