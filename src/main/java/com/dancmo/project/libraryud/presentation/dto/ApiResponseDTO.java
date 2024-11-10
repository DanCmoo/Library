package com.dancmo.project.libraryud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T>{
    private T body;
    private String message;
    private HttpStatus status;
    private LocalDate timestamp;
}
