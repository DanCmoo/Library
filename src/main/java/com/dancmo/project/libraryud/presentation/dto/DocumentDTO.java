package com.dancmo.project.libraryud.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private List<String> author_name;
    private int first_publish_year;
    private String title;
    private List<String> subject;
}
