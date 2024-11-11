package com.dancmo.project.libraryud.presentation.controller;

import com.dancmo.project.libraryud.presentation.dto.ApiResponseDTO;
import com.dancmo.project.libraryud.presentation.dto.LibroResponseDTO;
import com.dancmo.project.libraryud.services.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @GetMapping(value="/autor/{name}", produces = "application/json")
    public ApiResponseDTO<List<LibroResponseDTO>> getLibroByAutor(@PathVariable String name) {
        ApiResponseDTO<List<LibroResponseDTO>> responseDTO = new ApiResponseDTO<>();
        List<LibroResponseDTO> librosResponseDTO = libroService.getLibroByAutor(name);
        responseDTO.setTimestamp(LocalDate.now());
        if (librosResponseDTO == null ) {
            responseDTO.setStatus(HttpStatus.NOT_FOUND);
            responseDTO.setMessage("Autor no encontrado");
            responseDTO.setBody(null);
            return responseDTO;
        }
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Autor encontrado");
        responseDTO.setBody(librosResponseDTO);
        return responseDTO;
    }

    @GetMapping(value="/categoria/{category}", produces ="application/json")
    public ApiResponseDTO<List<LibroResponseDTO>> getLibroByCategoria(@PathVariable String category) {
        ApiResponseDTO<List<LibroResponseDTO>> responseDTO = new ApiResponseDTO<>();
        List<LibroResponseDTO> librosResponseDTO = libroService.getLibroByCategory(category);
        responseDTO.setTimestamp(LocalDate.now());
        if (librosResponseDTO == null ) {
            responseDTO.setStatus(HttpStatus.NOT_FOUND);
            responseDTO.setMessage("Categoria no encontrada");
            responseDTO.setBody(null);
            return responseDTO;
        }
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Categoria encontrada");
        responseDTO.setBody(librosResponseDTO);
        return responseDTO;
    }
}
