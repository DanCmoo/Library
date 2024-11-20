package com.dancmo.project.libraryud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoResponseDTO {
    private int id;
    private int idCliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
