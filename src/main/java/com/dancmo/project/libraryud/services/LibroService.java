package com.dancmo.project.libraryud.services;

import com.dancmo.project.libraryud.persistence.entity.Libro;
import com.dancmo.project.libraryud.presentation.dto.LibroResponseDTO;

import java.util.List;

public interface LibroService {

    public List<LibroResponseDTO> getLibroByCategory(String category);
    public List<LibroResponseDTO> getLibroByAutor(String autor);
    public LibroResponseDTO fromLibroEntityToDTO(Libro book);
}
