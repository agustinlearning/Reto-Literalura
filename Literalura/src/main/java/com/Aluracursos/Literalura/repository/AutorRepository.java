package com.Aluracursos.Literalura.repository;

import com.Aluracursos.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importante

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Busca un autor por nombre para evitar duplicados
    Optional<Autor> findByNombre(String nombre);
}