package com.jaime.eltumbometro.controllers;

import com.jaime.eltumbometro.models.Usuario;
import com.jaime.eltumbometro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorPrincipal   {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String paginaPrincipal(Model model) {

        return "landing";
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String loginUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/";
    }
}