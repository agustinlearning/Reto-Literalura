package com.Aluracursos.Literalura;

import com.Aluracursos.Literalura.service.ConsultaApi;
import com.Aluracursos.Literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n=== LiterAlura ===");
			System.out.println("1. Buscar libro por título");
			System.out.println("2. Mostrar libros registrados");
			System.out.println("3. Mostrar autores registrados");
			System.out.println("4. Salir");
			System.out.print("Elija una opción: ");

			int opcion = scanner.nextInt();
			scanner.nextLine(); // Limpiar buffer

			switch (opcion) {
				case 1:
					System.out.print("Ingrese el título: ");
					String titulo = scanner.nextLine();
					libroService.buscarYGuardarLibro(titulo);
					break;
				case 2:
					libroService.listarLibrosRegistrados();
					break;
				case 3:
					libroService.listarAutoresRegistrados();
					break;
				case 4:
					System.out.println("¡Hasta luego!");
					System.exit(0);
				default:
					System.out.println("Opción inválida.");
			}
		}
	}
}
