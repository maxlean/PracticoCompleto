# Proyecto Completo con Spring Boot y MongoDB

Este proyecto es una aplicación desarrollada con **Spring Boot**, **MongoDB** y **Docker Compose**, diseñada para gestionar datos de manera eficiente y escalable.

## Tecnologías utilizadas

- **Java**
- **Spring Boot**
  - Spring Data MongoDB
- **MongoDB** (base de datos NoSQL)
- **Docker Compose** (para orquestación de contenedores)
- **Maven**

## Funcionalidades principales

1. **Gestión de datos**:
   - Conexión a una base de datos MongoDB alojada en la nube.
   - Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos.

2. **Despliegue con Docker**:
   - Configuración de contenedores para la aplicación y la base de datos MongoDB.

3. **Configuración de entorno**:
   - Variables de entorno para credenciales y URI de la base de datos.

## Estructura del proyecto

src/main/java 
├── com.example.proyecto 
│ ├── controller # Controladores REST 
│ ├── service # Lógica de negocio 
│ ├── repository # Repositorios MongoDB 
│ ├── model # Entidades y DTOs 
│ └── ProyectoApplication # Clase principal

## Configuración

### Variables de entorno

En el archivo `docker-compose.yml` se configuran las siguientes variables de entorno:

- `SPRING_DATA_DB_USER_NAME`: Usuario de la base de datos.
- `SPRING_DATA_DB_PASSWORD`: Contraseña de la base de datos.
- `SPRING_DATA_MONGODB_URI`: URI de conexión a MongoDB.

### Base de datos

El proyecto utiliza una base de datos MongoDB configurada en un contenedor Docker. Los datos se almacenan en un volumen persistente (`mongo-data`).

## Ejecución del proyecto

1. **Clonar el repositorio**:
   ```bash
   git clone <url-del-repositorio>
   cd <nombre-del-proyecto>
   
