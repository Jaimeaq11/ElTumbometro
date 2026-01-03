package com.jaime.eltumbometro.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import com.jaime.eltumbometro.models.Usuario;
import com.jaime.eltumbometro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class ControladorUsuario {

    @GetMapping("/editar")
    public String mostrarFormularioEditarPerfil(Model model, @AuthenticationPrincipal Usuario usuarioLogueado) {
        model.addAttribute("usuario", usuarioLogueado);
        return "editar_perfil";
    }
}
