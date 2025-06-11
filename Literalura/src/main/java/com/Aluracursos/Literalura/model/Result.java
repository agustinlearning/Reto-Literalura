package com.Aluracursos.Literalura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Result(
        String title,
        List<AutorData> authors,
        List<String> languages,
        Integer download_count
) {
}
