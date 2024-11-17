package com.dancmo.project.libraryud.presentation.controller;

import com.dancmo.project.libraryud.presentation.dto.ApiResponseDTO;
import com.dancmo.project.libraryud.presentation.dto.LibroResponseDTO;
import com.dancmo.project.libraryud.presentation.dto.PrestamoRequestDTO;
import com.dancmo.project.libraryud.presentation.dto.PrestamoResponseDTO;
import com.dancmo.project.libraryud.service.LibroService;
import com.dancmo.project.libraryud.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    private final LibroService libroService;
    private final PrestamoService prestamoService;

    @Autowired
    public LibroController(LibroService libroService, PrestamoService prestamoService) {
        this.libroService = libroService;
        this.prestamoService = prestamoService;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/autor/{name}")
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
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/categoria/{category}")
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
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value="/titulo/{title}", produces = "application/json")
    public ApiResponseDTO<List<LibroResponseDTO>> getLibroByTitulo(@PathVariable String title) {
        ApiResponseDTO<List<LibroResponseDTO>> responseDTO = new ApiResponseDTO<>();
        List<LibroResponseDTO> librosResponseDTO = libroService.getLibroByTitle(title);
        responseDTO.setTimestamp(LocalDate.now());
        if (librosResponseDTO == null ) {
            responseDTO.setStatus(HttpStatus.NOT_FOUND);
            responseDTO.setMessage("El libro solicitado no existe.");
            responseDTO.setBody(null);
            return responseDTO;
        }
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Libro(s) encontrados.");
        responseDTO.setBody(librosResponseDTO);
        return responseDTO;
    }

    @PostMapping("/guardar")
    public ApiResponseDTO<String> saveLibro(LibroResponseDTO libro) {
        libroService.saveLibro(libro);
        ApiResponseDTO<String> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        responseDTO.setStatus(HttpStatus.CREATED);
        responseDTO.setMessage("Libro guardado con exito.");
        responseDTO.setBody("OK");
        return responseDTO;
    }

    @PostMapping("/borrar")
    public ApiResponseDTO<String> deleteLibro(LibroResponseDTO libro) {
        libroService.deleteLibro(libro);
        ApiResponseDTO<String> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Libro borrado con exito.");
        responseDTO.setBody("OK");
        return responseDTO;
    }

    @PostMapping("/actualizar")
    public ApiResponseDTO<String> updateLibro(int id, LibroResponseDTO libro) {
        libroService.updateLibro(id,libro);
        ApiResponseDTO<String> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Libro actualizado con exito.");
        responseDTO.setBody("OK");
        return responseDTO;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/prestamo/crear")
    public ApiResponseDTO<PrestamoResponseDTO> createPrestamoById(PrestamoRequestDTO prestamoRequestDTO) {
        PrestamoResponseDTO prestamoResponseDTO = prestamoService.createPrestamoById(prestamoRequestDTO);
        ApiResponseDTO<PrestamoResponseDTO> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        if (prestamoResponseDTO == null) {
            responseDTO.setStatus(HttpStatus.BAD_REQUEST);
        } else {
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setMessage("Prestamo creado con exito");
            responseDTO.setBody(prestamoResponseDTO);
        }
        return responseDTO;
    }
    @PreAuthorize("hasRole('USER') or #id == authentication.principal.id ")
    @GetMapping("/prestamo/{id}")
    public ApiResponseDTO<List<LibroResponseDTO>> getPrestamoById(@PathVariable int id) {
        List<LibroResponseDTO> libroResponseDTOS = prestamoService.getLibrosByClienteId(id);
        ApiResponseDTO<List<LibroResponseDTO>> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        if (libroResponseDTOS == null ) {
            responseDTO.setStatus(HttpStatus.NOT_FOUND);
        }else{
            responseDTO.setStatus(HttpStatus.OK);
            responseDTO.setMessage("Libros encontrados");
            responseDTO.setBody(libroResponseDTOS);
        }
        return responseDTO;
    }
}
