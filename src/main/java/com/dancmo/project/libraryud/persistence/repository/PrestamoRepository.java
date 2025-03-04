package com.dancmo.project.libraryud.persistence.repository;

import com.dancmo.project.libraryud.persistence.entity.Libro;
import com.dancmo.project.libraryud.persistence.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    @Query("SELECT l FROM Prestamo p JOIN p.libros l WHERE p.cliente.idCliente = :id")
    List<Libro> findLibrosByClienteId(@Param("id") int clienteId);
}
