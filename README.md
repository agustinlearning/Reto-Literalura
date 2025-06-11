# LiterAlura 📚

**LiterAlura** es una aplicación de consola desarrollada en Java y Spring Boot que funciona como un catálogo de libros interactivo. Permite a los usuarios buscar libros utilizando la API pública de [Gutendex](https://gutendex.com/), guardar la información en una base de datos PostgreSQL y realizar diversas consultas sobre los libros y autores almacenados.

Este proyecto fue desarrollado como parte del Challenge de Programación de Alura Latam, enfocado en poner en práctica conceptos de desarrollo backend, consumo de APIs, y persistencia de datos con JPA.

## ✨ Funcionalidades Principales

La aplicación ofrece un menú interactivo en la consola con las siguientes opciones:

1.  **Buscar libro por título**: Busca un libro en la API de Gutendex. Si se encuentra, guarda la información del libro y su autor en la base de datos local.
2.  **Listar libros registrados**: Muestra una lista de todos los libros que han sido guardados en la base de datos.
3.  **Listar autores registrados**: Muestra una lista de todos los autores almacenados, junto con sus años de nacimiento, fallecimiento y los títulos de sus libros.
4.  **Listar libros por idioma**: Permite al usuario filtrar y ver todos los libros registrados en un idioma específico (ej. español, inglés, francés).
5.  **Listar autores vivos en un año determinado**: Pide al usuario un año y muestra una lista de los autores que estaban vivos en ese período.

## 🛠️ Tecnologías Utilizadas

  - **Lenguaje**: Java 17
  - **Framework**: Spring Boot 3.5.0
  - **Persistencia de Datos**: Spring Data JPA
  - **Base de Datos**: PostgreSQL
  - **Cliente HTTP**: `HttpClient` nativo de Java
  - **Manejo de JSON**: Jackson Databind (`ObjectMapper`)
  - **Gestión de Dependencias**: Apache Maven

## 📋 Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

  - [**JDK (Java Development Kit)**](https://www.oracle.com/java/technologies/downloads/) - Versión 17 o superior.
  - [**Apache Maven**](https://maven.apache.org/download.cgi) - Versión 3.8 o superior.
  - [**PostgreSQL**](https://www.postgresql.org/download/) - Una instancia de base de datos activa.

## 🚀 Cómo Ejecutar el Proyecto

Sigue estos pasos para poner en funcionamiento la aplicación en tu entorno local.

**1. Clonar el Repositorio**

```bash
git clone https://github.com/tu-usuario/literalura.git
cd literalura
```

*(Recuerda reemplazar `tu-usuario` con tu nombre de usuario de GitHub)*

**2. Configurar la Base de Datos**

Abre el archivo de configuración de Spring, ubicado en `src/main/resources/application.properties`, y ajusta los siguientes valores para que coincidan con tu configuración de PostgreSQL:

```properties
# URL de conexión a tu base de datos. Asegúrate de que la base de datos "literalura_db" exista.
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db

# Usuario y contraseña de tu base de datos
spring.datasource.username=tu_usuario_postgres
spring.datasource.password=tu_contraseña_postgres

# Configuración de JPA (Hibernate)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Nota:** Asegúrate de haber creado una base de datos en PostgreSQL con el nombre que especifiques en la URL (en el ejemplo, `literalura_db`).

**3. Compilar y Ejecutar la Aplicación**

Puedes ejecutar la aplicación directamente usando el plugin de Maven para Spring Boot. Abre una terminal en la raíz del proyecto y ejecuta el siguiente comando:

```bash
./mvnw spring-boot:run
```

O si tienes Maven instalado globalmente:

```bash
mvn spring-boot:run
```

La aplicación se iniciará y verás el menú interactivo en la consola, ¡listo para usar\!

## 🌐 API Externa

Este proyecto consume datos de **Gutendex**, una API web para libros de dominio público del Proyecto Gutenberg. Toda la información de los libros y autores se obtiene de este servicio.

## 📁 Estructura del Proyecto

El proyecto sigue una estructura estándar de Maven y Spring Boot, organizada por capas para una mejor mantenibilidad:

```
src/main/java/com/aluracursos/literalura/
├── dto/              # Data Transfer Objects (DTOs) para resultados de consultas.
├── model/            # Entidades JPA (Libro, Autor) y records para mapeo de API.
├── repository/       # Interfaces de Spring Data JPA para el acceso a datos.
├── service/          # Lógica de negocio (Consumo de API, interacción con repositorios).
└── LiteraluraApplication.java  # Clase principal que inicia la aplicación.
```

-----

*Hecho con esfuerzo y persistencia por Agustin Álvarez Batista*
