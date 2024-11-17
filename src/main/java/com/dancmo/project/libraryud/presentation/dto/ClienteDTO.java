package com.dancmo.project.libraryud.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotBlank
    private String nombre;

    @Email
    private String correo;

    @NotBlank
    private String password;
}
