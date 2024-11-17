package com.dancmo.project.libraryud.presentation.controller;

import com.dancmo.project.libraryud.presentation.dto.ApiResponseDTO;
import com.dancmo.project.libraryud.presentation.dto.ClienteDTO;
import com.dancmo.project.libraryud.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/registrar")
    public ApiResponseDTO<Void> registrar(@RequestBody @Valid ClienteDTO clienteDTO) {
        boolean registrado = clienteService.registrarCliente(clienteDTO);
        ApiResponseDTO<Void> responseDTO = new ApiResponseDTO<>();
        responseDTO.setTimestamp(LocalDate.now());
        if (registrado) {
            responseDTO.setMessage("Cliente registrado");
            responseDTO.setStatus(HttpStatus.CREATED);
        }else{
            responseDTO.setMessage("Cliente no registrado");
            responseDTO.setStatus(HttpStatus.CONFLICT);
        }
        return responseDTO;
    }
}
