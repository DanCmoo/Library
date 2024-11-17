package com.dancmo.project.libraryud.service;

import com.dancmo.project.libraryud.persistence.entity.Cliente;
import com.dancmo.project.libraryud.presentation.dto.ClienteDTO;

public interface ClienteService {

    boolean registrarCliente(ClienteDTO clienteDTO);
}
