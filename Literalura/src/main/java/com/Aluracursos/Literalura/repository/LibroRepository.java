package com.Aluracursos.Literalura.repository;

import com.Aluracursos.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Métodos personalizados (opcionales)
    boolean existsByTitulo(String titulo);
}