package com.dancmo.project.libraryud.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCliente;

    private String nombre;

    @Column(unique = true,nullable = false)
    private String correo;

    @Column(nullable = false)
    private String password;

    private String role;

    private int telefono;
    private boolean estadoCuenta;
}
