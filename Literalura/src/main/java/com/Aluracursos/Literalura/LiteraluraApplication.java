package com.Aluracursos.Literalura;

import com.Aluracursos.Literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private void listarLibrosPorIdioma(Scanner scanner) {
		System.out.println("Ingrese el código del idioma para buscar libros:");
		System.out.println("es - Español");
		System.out.println("en - Inglés");
		System.out.println("fr - Francés");
		System.out.println("pt - Portugués");
		System.out.print("> ");
		String idioma = scanner.nextLine();
		libroService.listarLibrosPorIdioma(idioma);
	}

	private void listarAutoresVivos(Scanner scanner) {
		System.out.print("Ingrese el año para buscar autores vivos: ");
		try {
			int ano = scanner.nextInt();
			scanner.nextLine();
			libroService.listarAutoresVivosEnAno(ano);
		} catch (java.util.InputMismatchException e) { // si el usuario no introduce un numero
			System.out.println("Entrada inválida. Por favor, ingrese un año válido (número).");
			scanner.nextLine();
		}
	}

	@Autowired
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
			System.out.println("4. Listar libros por idioma");
			System.out.println("5. Listar autores vivos en un año determinado");
			System.out.println("0. Salir");
			System.out.print("Elija una opción: ");


			// Validar que la entrada sea un número
			if (!scanner.hasNextInt()) {
				System.out.println("Por favor, ingrese un número válido.");
				scanner.next(); // Limpiar la entrada no válida
				continue;
			}
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
					listarLibrosPorIdioma(scanner);
					break;
				case 5:
					listarAutoresVivos(scanner);
				case 0:
					System.out.println("¡Hasta luego!");
					System.exit(0);
				default:
					System.out.println("Opción inválida.");
			}
		}
	}
}

