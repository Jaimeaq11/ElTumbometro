package com.jaime.eltumbometro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Seguridad {

    @Value("${app.rememberme.key}")
    private String rememberMeKey;

    @Bean
    public UserDetailsService userDetailsService() {
        // Al usar OAuth2, el login real lo gestiona Google.
        // Este bean es necesario para que RememberMe no falle al arrancar.
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Permitimos que todoo el mundo vea la landing, el login, el registro y las fotos/css
                        .requestMatchers("/", "/login", "/registrar", "/usuario/**", "/css/**", "/imagenes/**", "/js/**").permitAll()
                        // 2. El resto de cosas (como el panel) requieren estar logueado
                        .anyRequest().authenticated()
                )

                // 4. Configuración de Remember Me (Recordarme)
                .rememberMe(remember -> remember
                        .key(rememberMeKey) // Una clave para firmar la cookie
                        .tokenValiditySeconds(604800) // 7 días de validez (en segundos)
                        .rememberMeParameter("remember-me") // Nombre del input en el formulario (si lo usaras)
                )

                // 3. Configuramos el login con Google
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Le decimos que nuestra página de login es la nuestra
                        .defaultSuccessUrl("/", true) // Al terminar con éxito, que vaya a "/" (donde tú haces la lógica)
                )
                // 4. Deshabilitamos CSRF por ahora para que tus formularios POST normales sigan funcionando
                .csrf(csrf -> csrf.disable())

                .logout(logout -> logout
                        .logoutUrl("/logout") // La URL que activará el logout
                        .logoutSuccessUrl("/") // A dónde redirigir tras éxito
                        .invalidateHttpSession(true) // Borrar la sesión
                        .clearAuthentication(true) // Limpiar datos de autenticación
                        .deleteCookies("JSESSIONID") // Borrar la cookie de sesión
                        .permitAll()
                );

        return http.build();
    }
}