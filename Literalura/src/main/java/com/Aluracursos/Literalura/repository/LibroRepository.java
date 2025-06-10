package com.Aluracursos.Literalura.repository;

import com.Aluracursos.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importante

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Busca un libro por título, ignorando mayúsculas/minúsculas
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
}