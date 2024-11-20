package com.dancmo.project.libraryud.persistence.repository;

import com.dancmo.project.libraryud.persistence.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    List<Libro> findAllByCategoria_NombreCategoria(String categoria);
    List<Libro> findAllByAutor_Nombre(String autor);
    List<Libro> findAllByTitulo(String titulo);
}
