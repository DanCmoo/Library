package com.dancmo.project.libraryud.service.implementation;

import com.dancmo.project.libraryud.persistence.entity.Cliente;
import com.dancmo.project.libraryud.persistence.repository.ClienteRepository;
import com.dancmo.project.libraryud.presentation.dto.ClienteDTO;
import com.dancmo.project.libraryud.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registrarCliente(ClienteDTO clienteDTO) {

        if (clienteRepository.findByCorreo(clienteDTO.getCorreo()) != null) {
            return false;
        }

        Cliente cliente = new Cliente();
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setPassword(passwordEncoder.encode(clienteDTO.getPassword()));
        cliente.setRole("USER");

        return true;
    }
}
