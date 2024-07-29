### README

# Aplicación de Fabricación de Juguetes

Este proyecto es una simulación de un sistema de fabricación de juguetes. La aplicación está construida con Spring Boot para el backend y React para el frontend. Implementa un patrón Observer para actualizaciones de fabricación y utiliza programación reactiva para proporcionar actualizaciones en tiempo real al frontend.

## Características

- **Autenticación de Usuarios**: Panel de administrador para gestionar solicitudes de fabricación de juguetes.
- **Fabricación de Juguetes**: Simula la creación de juguetes, incluyendo el manejo de múltiples copias de juguetes.
- **Actualizaciones en Tiempo Real**: Utiliza programación reactiva para actualizar el frontend con el estado de la fabricación de juguetes.

## Tecnologías Utilizadas

- **Backend**: Spring Boot, Spring Data JPA, Spring WebFlux, Spring Scheduling
- **Frontend**: React, React Context API, Bootstrap
- **Base de Datos**: H2 (en memoria para desarrollo), soporte para otras bases de datos como MySQL
- **Otros**: Axios para solicitudes API, Lombok para reducir código boilerplate

## Prerrequisitos

- **Java 21**
- **Node.js** y **npm**
- **Maven**

## Configuración e Instalación

### Backend

1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/your-repo/toy-manufacturing.git
    cd toy-manufacturing
    ```

2. **Navegar al directorio del backend**:
    ```bash
    cd backend
    ```

3. **Construir y ejecutar la aplicación Spring Boot**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. El backend estará ejecutándose en `http://localhost:8080`.

### Frontend

1. **Navegar al directorio del frontend**:
    ```bash
    cd frontend
    ```

2. **Instalar dependencias**:
    ```bash
    npm install
    ```
3. **Instalar librerias necesarias**:
    ```bash
    npm install axios
    npm install @stomp/stompjs sockjs-client
    npl install react-router-dom
    ```

4. **Iniciar la aplicación React**:
    ```bash
    npm start
    ```

4. El frontend estará ejecutándose en `http://localhost:3000`.

## Estructura del Proyecto

### Backend

- `src/main/java/ec/edu/uce/interfaz/controller`: Contiene los controladores REST para manejar las solicitudes API.
- `src/main/java/ec/edu/uce/interfaz/service`: Contiene las clases de servicio con la lógica de negocio.
- `src/main/java/ec/edu/uce/interfaz/repository`: Contiene las interfaces de repositorio para las operaciones de base de datos.
- `src/main/java/ec/edu/uce/interfaz/observer`: Contiene las interfaces y clases del patrón Observer.
- `src/main/java/ec/edu/uce/interfaz/state`: Contiene las clases de estado para las entidades de juguetes.

### Frontend

- `src/components`: Contiene los componentes de React.
- `src/context`: Contiene los archivos de React Context API para la gestión del estado.
- `src/services`: Contiene los archivos de servicio para realizar solicitudes API.

## Endpoints de la API

### Manufacturing Controller

- `POST /fabric/fabricate/{toyId}/{copies}`: Inicia el proceso de fabricación para un juguete especificado y un número de copias.
- `GET /fabric/message`: Recupera el último mensaje de fabricación.

### Toy Controller

- `GET /toys/{id}`: Recupera un juguete por su ID.
- `GET /toys`: Recupera todos los juguetes.

### Message Controller

- `GET /message`: Recupera el último mensaje de fabricación.

## Ejemplo de Uso

1. **Inicia los servidores del backend y frontend.**
2. **Abre la aplicación en un navegador** (`http://localhost:3000`).
3. **Inicia sesión en el panel de administrador** y visualiza las solicitudes de fabricación.
4. **Acepta o rechaza solicitudes de fabricación** desde el panel de administrador.
5. **Monitorea actualizaciones en tiempo real** sobre el estado de la fabricación de juguetes.

## Contribuciones

Si deseas contribuir a este proyecto, por favor haz un fork del repositorio y envía un pull request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT.

## Contacto

Para cualquier pregunta o comentario, por favor contacta a [jjlunag@uce.edu.ec].

---

