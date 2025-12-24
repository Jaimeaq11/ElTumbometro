package com.jaime.eltumbometro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal   {

    @GetMapping("/")
    public String paginaPrincipal(Model model) {

        model.addAttribute("titulo", "Bienvenido al Tumbómetro");
        return "Inicio";
    }
}
