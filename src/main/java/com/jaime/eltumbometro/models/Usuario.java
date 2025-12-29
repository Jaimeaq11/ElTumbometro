package com.jaime.eltumbometro.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 1. Dice: "Esto es una tabla de base de datos"
@Table(name = "usuarios") // 2. Dice: "La tabla en Supabase se llamará 'usuarios'"
@Data // 3. Lombok: Crea Getters, Setters, toString, equals automáticamente
@NoArgsConstructor // 4. Crea constructor vacío (obligatorio para JPA)
@AllArgsConstructor // 5. Crea constructor con todoo

public class Usuario {

    @Id // Esto es la Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esto es Auto-Increment (Serial)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String email;

    private String contrasenia;
}