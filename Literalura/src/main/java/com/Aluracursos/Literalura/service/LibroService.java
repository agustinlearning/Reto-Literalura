package com.Aluracursos.Literalura.service;

import com.Aluracursos.Literalura.model.Autor;
import com.Aluracursos.Literalura.model.Libro;
import com.Aluracursos.Literalura.model.LibroData;
import com.Aluracursos.Literalura.model.Result;
import com.Aluracursos.Literalura.repository.AutorRepository;
import com.Aluracursos.Literalura.repository.LibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante para la consistencia

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private final ConsultaApi consultaApi;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Puedes instanciarlo aquí

    // Constructor para la inyección de dependencias
    public LibroService(ConsultaApi consultaApi, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.consultaApi = consultaApi;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void buscarYGuardarLibro(String titulo) {
        //  Verificar si el libro ya existe en la BD
        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(titulo);
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya existe en la base de datos.");
            return;
        }

        // Si no existe, buscar en la API
        String json = consultaApi.buscarLibroPorTitulo(titulo);
        try {
            LibroData libroData = objectMapper.readValue(json, LibroData.class);

            if (libroData != null && libroData.results() != null && !libroData.results().isEmpty()) {
                Result primerResultado = libroData.results().get(0);

                guardarLibroConAutores(primerResultado);

            } else {
                System.out.println("No se encontró ningún libro con ese título en la API.");
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error al procesar la respuesta JSON: " + e.getMessage());
        }
    }

    private void guardarLibroConAutores(Result result) {
        //  Mapear autores, buscándolos en la BD o creando nuevos
        List<Autor> autores = result.authors().stream()
                .map(autorData -> {
                    // Busca si el autor ya existe por su nombre
                    Optional<Autor> autorExistente = autorRepository.findByNombre(autorData.name());
                    // Si existe, lo usa; si no, crea y guarda uno nuevo.
                    Autor nuevoAutor = new Autor(autorData.name(), autorData.birth_year(), autorData.death_year());
                    return autorRepository.save(nuevoAutor);
                })
                .collect(Collectors.toList());

        //  Crear el objeto Libro
        // Corrección para evitar error si la lista de idiomas está vacía
        String idioma = result.languages().isEmpty() ? "Desconocido" : result.languages().get(0);

        Libro libro = new Libro(
                result.title(),
                idioma,
                result.download_count()
        );

        //  Asociar autores con el libro
        libro.setAutores(autores);

        // 8. Guardar el libro (gracias a la cascada, los autores se asociarán correctamente)
        libroRepository.save(libro);
        System.out.println("Libro guardado exitosamente: " + libro.getTitulo());
    }

    @Transactional(readOnly = true)
    public void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()){
            System.out.println("No hay libros registrados.");
            return;
        }
        libros.forEach(System.out::println); //Reciclando el toString() de la clase Libro.java
    }

    @Transactional(readOnly = true)
    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()){
            System.out.println("No hay autores registrados.");
            return;
        }
        autores.forEach(System.out::println); // Reciclando el toString() de la clase Autor.java
    }

    @Transactional(readOnly = true)
    public void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma '" + idioma + "'.");
        } else {
            System.out.println("Libros encontrados en '" + idioma + "':");
            libros.forEach(System.out::println); // Reciclando el toString() de la clase Libro.java
        }
    }

    @Transactional(readOnly = true)
    public void listarAutoresVivosEnAno(int ano) {
        List<Autor> autores = autorRepository.findAutoresVivosEnAno(ano);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + ano + ".");
        } else {
            System.out.println("Autores vivos en el año " + ano + ":");
            autores.forEach(System.out::println);
        }
    }
}
