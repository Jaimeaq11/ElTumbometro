package com.jaime.eltumbometro.controllers;

import com.jaime.eltumbometro.models.Usuario;
import com.jaime.eltumbometro.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ControladorPrincipal {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String paginaPrincipal(HttpSession session, @AuthenticationPrincipal OAuth2User principal) {

        // Lógica para Google: Si hay un principal de OAuth2 y no hemos procesado la sesión
        if (principal != null && session.getAttribute("usuarioLogueado") == null) {
            String email = principal.getAttribute("email");
            String nombre = principal.getAttribute("name");
            String fotoURL = principal.getAttribute("picture");

            List<Usuario> encontrados = usuarioRepository.findByEmail(email);
            Usuario usuario;

            //tanto si existe como si no...
            if (encontrados.isEmpty()) {
                // REGISTRO AUTOMÁTICO
                usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setNombre(nombre); // Asumiendo que tu modelo tiene nombre
                usuario.setFotoURL(fotoURL);
                usuario.setContrasenia("OAUTH_USER"); // Password dummy
            } else {
                usuario = encontrados.getFirst();

                if (usuario.getFotoURL() == null) {
                    usuario.setFotoURL(fotoURL);
                }
            }
            usuarioRepository.save(usuario);
            session.setAttribute("usuarioLogueado", usuario);
        }

        if (session.getAttribute("usuarioLogueado") != null) {
            return "panel"; // Si hay sesión (venga de donde venga), al panel
        } else {
            return "landing"; // Si no hay nada, a la landing
        }
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String loginUsuario(Usuario usuario, HttpSession session, Model model) {

        List<Usuario> usuariosEncontrados = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuariosEncontrados.isEmpty()) {
            model.addAttribute("error", "El usuario no existe");
            return "login";
        }

        Usuario usuarioReal = usuariosEncontrados.getFirst();

        if (usuarioReal.getContrasenia().equals(usuario.getContrasenia())) {
            session.setAttribute("usuarioLogueado", usuarioReal);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Contraseña incorrecta");
            return "login";
        }
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registrar")
    public String registroUsuario(Usuario usuario) {

        if (usuario.getFotoURL() == null || usuario.getFotoURL().isEmpty() || usuario.getFotoURL().isBlank()) {
            usuario.setFotoURL("/imagenes/perfiles/sin_foto.jpg");
        }

        usuarioRepository.save(usuario);
        return "redirect:/login";
    }
}