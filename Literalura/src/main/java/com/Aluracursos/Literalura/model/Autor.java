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

    private Integer anoNacimiento;
    private Integer anoFallecimiento;

    // IMPORTANTE Constructor vacio (obligatorio para JPA)
    public Autor() {}

    // Constructor desde datos de la API
    public Autor(String nombre, Integer anoNacimiento, Integer anoFallecimiento) {
        this.nombre = nombre;
        this.anoNacimiento = anoNacimiento;
        this.anoFallecimiento = anoFallecimiento;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    @Override
    public String toString() {
        String titulosLibros = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));
        return String.format(
                "Autor: %s (Nacimiento: %d - Fallecimiento: %d)\n" +
                        "Libros: [%s]\n",
                nombre, anoNacimiento, anoFallecimiento, titulosLibros
        );
    }
}

