package com.dancmo.project.libraryud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoRequestDTO {
    private int idCliente;
    private List<Integer> idLibros;
    private int anio;
    private int mes;
    private int dia;
}
