package com.dancmo.project.libraryud.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPrestamo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private LocalDate fechaInicioPrestamo;
    private LocalDate fechaFinPrestamo;

    @ManyToMany(mappedBy = "prestamos")
    private List<Libro> libros = new ArrayList<>();
}
