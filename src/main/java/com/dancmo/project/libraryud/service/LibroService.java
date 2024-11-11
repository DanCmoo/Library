package com.dancmo.project.libraryud.service;

import com.dancmo.project.libraryud.persistence.entity.Libro;
import com.dancmo.project.libraryud.presentation.dto.LibroResponseDTO;

import java.util.List;

public interface LibroService {

    public List<LibroResponseDTO> getLibroByCategory(String category);
    public List<LibroResponseDTO> getLibroByAutor(String autor);
    public List<LibroResponseDTO> getLibroByTitle(String title);
    public void saveLibro(LibroResponseDTO libro);
    public void deleteLibro(LibroResponseDTO libro);
    public void updateLibro(int id, LibroResponseDTO libro);
    public LibroResponseDTO fromLibroEntityToDTO(Libro book);
}
