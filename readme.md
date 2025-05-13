# Aplicación de Tareas
Este es el proyecto Final del Bootcamp [Microservicios con Java](https://codigofacilito.com/programas/java-microservicios)
realizado en [Codigo Facilito](https://codigofacilito.com/)
Se trata de un administrador de Tareas el cual se separó en dos microservicios, uno de usuarios y otro de tareas


## Tecnologías
- Java 17
- Spring Boot 3.4.4
- Spring Data
- Postgresql
- Resilience
- Junit
- Maven
- Docker
- Docker Compose
- Git

## Arquitectura
- Microservicios 

![Image](https://github.com/user-attachments/assets/a8c7bb55-90ff-4b89-b450-c5ecb72b4049)


## IDE Utilizado
IntelliJ IDEA pero también es compatible con otros IDEs como Eclipse o VS Code.

## Instalación del proyecto

### Requisitos Previos
Contar con las siguientes herramientas antes de instalar el proyecto:

- Java JDK 17+
- Maven 3.8+
- Docker
- Docker Compose
- Git


1. Clonar el repositorio
```
git https://github.com/ctnfimac/taskapp.git

```

2. Me muevo hacia la carpeta del proyecto
```
cd taskapp
```

### Para el entorno de Pruebas:
3. Levanto los contenedores de las base de datos, los microservicios y el cliente de postgresql pgadmin(*):
```
docker-compose -f docker-compose.test.yml up --build -d
```


### Para el entorno de desarrollo:
3.  Levanto los contenedores de las base de datos y cliente pgadmin(*):
```
docker-compose -f docker-compose.dev.yml up --build -d
```

4. Levanto el microservicio de usuarios, desde la carpeta microservice-users ejecutar:
```
mvn spring-boot:run
```

5. Levanto el microservicio de tareas, desde la carpeta microservice-tasks ejecutar:
```
mvn spring-boot:run
```

Documentacion, api de tareas:
```
http://localhost:8091/docs
```

Documentacion, api de usuarios:
```
http://localhost:8092/docs
```


## Diagrama de flujo del uso de la aplicación
El proyecto por el momento está pensado para el desarrollo del backend, pero el diagrama lo realicé para
poder analizar que endpoints desarrollar

![Image](https://github.com/user-attachments/assets/5f40b768-4b39-4a72-a515-65b270e82099)



## Funcionalidades Actuales
- Registrarse con email y contraseña.
- Iniciar Sesión con usuario y contraseña.
- Crear bloque de tareas
- Finalizar bloque de tareas
- Crear Tarea
- Obtener Tareas de un usuario y bloque finalizado
- Eliminar Tarea
- Cambio de estado de la Tarea
- Obtener bloques de tarea de un usuario


(*) El pgadmin es un cliente de base de datos, que no es indispensable par el funcionamiento del proyecto pero sirve 
para ver la información de la base de datos de una forma más gráfica