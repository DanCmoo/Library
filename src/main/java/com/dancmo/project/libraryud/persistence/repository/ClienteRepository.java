package com.dancmo.project.libraryud.persistence.repository;

import com.dancmo.project.libraryud.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByCorreo(String correo);
}
