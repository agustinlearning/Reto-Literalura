package com.Aluracursos.Literalura.model;

import java.util.List;

public record Result(
        String title,
        List<AutorData> authors,
        List<String> languages,
        Integer download_count
) {
}
