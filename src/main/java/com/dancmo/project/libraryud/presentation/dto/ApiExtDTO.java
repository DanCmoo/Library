package com.dancmo.project.libraryud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiExtDTO {
    private int numFound;
    private int start;
    private boolean numFoundExact;
    private List<DocumentDTO> docs;

}
