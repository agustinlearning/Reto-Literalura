package com.Aluracursos.Literalura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private List<Libro> libros;

    // Constructor vacío
    public Autor() {}

    // Constructor desde datos de la API
    public Autor(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }
}

//@Override
//public String toString() {
//    String titulosLibros = libros.stream()
//            .map(Libro::getTitulo)
//            .collect(Collectors.joining(", "));
//    return String.format(
//            "Autor: %s\n" +
//                    "Libros: [%s]\n",
//            nombre, titulosLibros
//    );
//}