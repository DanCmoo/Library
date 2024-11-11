package com.dancmo.project.libraryud.service;

import com.dancmo.project.libraryud.persistence.entity.Libro;
import com.dancmo.project.libraryud.persistence.entity.Prestamo;
import com.dancmo.project.libraryud.presentation.dto.LibroResponseDTO;
import com.dancmo.project.libraryud.presentation.dto.PrestamoRequestDTO;
import com.dancmo.project.libraryud.presentation.dto.PrestamoResponseDTO;

import java.util.List;

public interface PrestamoService {
    PrestamoResponseDTO createPrestamoById(PrestamoRequestDTO prestamoRequestDTO);
    List<LibroResponseDTO> getLibrosByClienteId(int id);

    public LibroResponseDTO fromLibroEntityToDTO(Libro book);
}
