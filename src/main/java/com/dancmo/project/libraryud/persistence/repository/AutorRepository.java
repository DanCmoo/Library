package com.dancmo.project.libraryud.persistence.repository;

import com.dancmo.project.libraryud.persistence.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAllByNombre(String autor);

}
