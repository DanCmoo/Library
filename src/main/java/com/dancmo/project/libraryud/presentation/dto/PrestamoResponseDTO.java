package com.dancmo.project.libraryud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoResponseDTO {
    private int id;
    private int idCliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
