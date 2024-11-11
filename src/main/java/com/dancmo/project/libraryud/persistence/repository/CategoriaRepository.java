package com.dancmo.project.libraryud.persistence.repository;

import com.dancmo.project.libraryud.persistence.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByNombreCategoria(String categoria);

}
