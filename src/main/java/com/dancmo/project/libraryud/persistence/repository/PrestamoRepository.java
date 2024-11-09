package com.dancmo.project.libraryud.persistence.repository;

import com.dancmo.project.libraryud.persistence.entity.Libro;
import com.dancmo.project.libraryud.persistence.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    @Query("SELECT l FROM Prestamo p JOIN p.libros l WHERE p.cliente.idCliente = :id")
    List<Libro> findLibrosByClienteId(@Param("id") int clienteId);
}
