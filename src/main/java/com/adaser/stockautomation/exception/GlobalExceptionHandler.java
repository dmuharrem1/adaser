package com.adaser.stockautomation.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception exception, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred");
        return "error";
    }
}