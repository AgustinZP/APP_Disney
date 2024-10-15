# Aplicación Web de Búsqueda de Personajes de Disney

## Descripción

Esta aplicación web, desarrollada en **Spring Boot**, permite a los usuarios explorar y buscar personajes de Disney a través de la [API de Disney](https://disneyapi.dev/). Los usuarios pueden buscar personajes por su nombre en inglés, añadirlos a una lista de favoritos, compartirlos con otros usuarios registrados y calificarlos según su preferencia.

## Funcionalidades

- **Búsqueda de Personajes**: Permite a los usuarios buscar personajes de Disney utilizando su nombre en inglés.
- **Compartir Personajes**: Los usuarios pueden compartir personajes guardados con otros usuarios registrados de la aplicación.
- **Puntuación**: Los usuarios pueden dar una puntuación a los personajes que han guardado.
- **Personajes Guardados**: En esta sección, los usuarios pueden ver todos los personajes que han guardado como favoritos.
- **Personajes Compartidos**: Los usuarios pueden acceder a los personajes que otros usuarios han compartido con ellos.

## Requisitos

- **Java JDK 11 o superior**
- **Spring Boot**: Se recomienda utilizar la última versión estable.
- **Dependencias**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-devtools`
  - `spring-boot-starter-thymeleaf`
  - `spring-boot-starter-validation`

## Instalación

1. **Clonar el repositorio**:
2. **Configuración del proyecto:**
   - Asegúrate de tener configurado un archivo application.properties para conectar a la base de datos y a la API de Disney.
   - Configura las credenciales y propiedades necesarias para el correcto funcionamiento de la aplicación.
3. **Ejecutar la aplicación**

## Uso
1. Registro de Usuario: Los nuevos usuarios deben registrarse para poder acceder a todas las funcionalidades de la aplicación.
2. Búsqueda de Personajes: Utiliza la barra de búsqueda para encontrar personajes de Disney.
3. Agregar a Mis Personajes: Haz clic en el botón "Guardar" para guardar un personaje.
4. Compartir Personajes: Puedes compartir personajes seleccionados con tus amigos registrados haciendo clic en el botón "Enviar".
5. Puntuación: Asigna una puntuación a los personajes guardados o que te han compartido según tu preferencia.
   
## Contribuciones
Las contribuciones son bienvenidas.
