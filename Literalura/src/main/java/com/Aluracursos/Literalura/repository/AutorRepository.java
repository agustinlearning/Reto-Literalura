package com.Aluracursos.Literalura.repository;

import com.Aluracursos.Literalura.DTO.AutorDTO;
import com.Aluracursos.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByNombre(String nombre);
    @Query("SELECT NEW com.Aluracursos.Literalura.DTO.AutorDTO(a.id, a.nombre, SIZE(a.libros)) FROM Autor a")
    List<AutorDTO> findAllAutorDTO();
}

