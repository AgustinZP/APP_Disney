package com.AccesoDatos.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Errores {
	
	@ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        return "error";
    }

}
