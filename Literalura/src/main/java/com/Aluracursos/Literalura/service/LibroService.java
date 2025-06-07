package com.Aluracursos.Literalura.service;

import com.Aluracursos.Literalura.DTO.AutorDTO;
import com.Aluracursos.Literalura.model.Autor;
import com.Aluracursos.Literalura.model.Libro;
import com.Aluracursos.Literalura.model.LibroData;
import com.Aluracursos.Literalura.repository.AutorRepository;
import com.Aluracursos.Literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final ConsultaApi consultaApi;
    private final ObjectMapper objectMapper; // Jackson para parsear JSON
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;



    public LibroService(ConsultaApi consultaApi) {
        this.consultaApi = consultaApi;
        this.objectMapper = new ObjectMapper();
    }

    public void buscarYGuardarLibro(String titulo) {
        try {
            String json = consultaApi.buscarLibroPorTitulo(titulo);
            LibroData libroData = objectMapper.readValue(json, LibroData.class);

            libroData.results().forEach(result -> {
                // Verificar si el libro ya existe
                if (!libroRepository.existsByTitulo(result.title())) {
                    // Crear y guardar el libro
                    Libro libro = new Libro(
                            result.title(),
                            result.languages().get(0),
                            result.download_count()
                    );

                    // Guardar autores (primero necesitas crearlos)
                    List<Autor> autores = result.authors().stream()
                            .map(a -> new Autor(a.name()))
                            .toList();
                    libro.setAutores(autores);

                    libroRepository.save(libro);
                    System.out.println("Libro guardado: " + libro.getTitulo());
                } else {
                    System.out.println("El libro ya existe en la base de datos.");
                }
            });

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(libro -> System.out.println(
                "Título: " + libro.getTitulo() +
                        ", Idioma: " + libro.getIdioma() +
                        ", Descargas: " + libro.getDescargas()
        ));
    }

    public void listarAutoresRegistrados() {
        List<AutorDTO> autores = autorRepository.findAllAutorDTO();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }

        System.out.println("\n=== Autores Registrados ===");
        autores.forEach(autor -> System.out.println(
                String.format("ID: %d - Nombre: %-20s - Libros: %d",
                        autor.id(),
                        autor.nombre(),
                        autor.cantidadLibros())
        ));
    }
}

