package com.Aluracursos.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
    public record LibroData(
            List<Result> results
    ) {}
