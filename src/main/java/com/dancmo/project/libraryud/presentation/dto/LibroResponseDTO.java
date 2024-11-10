package com.dancmo.project.libraryud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*DTO de Respuesta de un libro*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroResponseDTO {
    private String titulo;
    private int anioPublicacion;
    private boolean disponibilidad;
    private String autor;
    private String categoria;
}
