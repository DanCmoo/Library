package com.dancmo.project.libraryud.service.implementation;

import com.dancmo.project.libraryud.persistence.entity.Cliente;
import com.dancmo.project.libraryud.persistence.entity.Libro;
import com.dancmo.project.libraryud.persistence.entity.Prestamo;
import com.dancmo.project.libraryud.persistence.repository.ClienteRepository;
import com.dancmo.project.libraryud.persistence.repository.LibroRepository;
import com.dancmo.project.libraryud.persistence.repository.PrestamoRepository;
import com.dancmo.project.libraryud.presentation.dto.LibroResponseDTO;
import com.dancmo.project.libraryud.presentation.dto.PrestamoRequestDTO;
import com.dancmo.project.libraryud.presentation.dto.PrestamoResponseDTO;
import com.dancmo.project.libraryud.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final ClienteRepository clienteRepository;
    private final LibroRepository libroRepository;
    @Autowired
    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,LibroRepository libroRepository,ClienteRepository clienteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public PrestamoResponseDTO createPrestamoById(PrestamoRequestDTO prestamoRequestDTO) {
        Cliente cliente = clienteRepository.findById(prestamoRequestDTO.getIdCliente()).orElse(null);
        if(cliente==null){
            return null;
        }
        List<Integer> idLibros = prestamoRequestDTO.getIdLibros();
        List<Libro> libros = new ArrayList<>();
        for (Integer libroId : idLibros) {
            Libro libro = libroRepository.findById(libroId).orElse(null);
            if (libro != null) {
                if (libro.isDisponibilidad()) {
                    libros.add(libro);
                }
            }
        }
        if (libros.isEmpty()){
            return null;
        }
        Prestamo prestamo = new Prestamo();
        prestamo.setCliente(cliente);
        prestamo.setFechaInicioPrestamo(LocalDate.now());
        prestamo.setFechaFinPrestamo(LocalDate.of(prestamoRequestDTO.getAnio(),prestamoRequestDTO.getMes(),prestamoRequestDTO.getDia()));
        prestamo.setLibros(libros);
        prestamoRepository.save(prestamo);
        return new PrestamoResponseDTO(prestamo.getCliente().getIdCliente(), prestamo.getIdPrestamo(),prestamo.getFechaInicioPrestamo(),prestamo.getFechaFinPrestamo());
    }

    @Override
    public List<LibroResponseDTO> getLibrosByClienteId(int id) {
        List<Libro> libros = prestamoRepository.findLibrosByClienteId(id);
        List<LibroResponseDTO> librosResponse = new ArrayList<>();
        for(Libro libro : libros){
            librosResponse.add(this.fromLibroEntityToDTO(libro));
        }
        return librosResponse;
    }

    @Override
    public LibroResponseDTO fromLibroEntityToDTO(Libro book) {
        LibroResponseDTO dto = new LibroResponseDTO();
        dto.setTitulo(book.getTitulo());
        dto.setAnioPublicacion(book.getAnioPublicacion());
        dto.setDisponibilidad(book.isDisponibilidad());
        dto.setAutor(book.getAutor().getNombre());
        dto.setCategoria(book.getCategoria().getNombreCategoria());
        return dto;
    }

}
