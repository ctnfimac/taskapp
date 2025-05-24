# Aplicación de Tareas
Este es el proyecto final del Bootcamp [Microservicios con Java](https://codigofacilito.com/programas/java-microservicios)
realizado en [Codigo Facilito](https://codigofacilito.com/)
Se trata de un administrador de Tareas el cual se separó en tres microservicios: uno de usuarios, uno de tareas y por último un microservicio
de notificaciones.


## Tecnologías
- Java 17
- Spring Boot - Data
- Postgresql
- Junit
- Maven
- Docker
- Docker Compose
- Git
- RabbitMQ

## Arquitectura
- Microservicios 

![Image](https://github.com/user-attachments/assets/34cd8a1b-3a5e-4515-9008-bd1c8b3039d6)

## IDE Utilizado
IntelliJ IDEA pero también es compatible con otros IDEs como Eclipse o VS Code.

## Instalación del proyecto

### Requisitos Previos
Contar con las siguientes herramientas antes de la instalación:

- Java JDK 17+
- Maven 3.8+
- Docker
- Docker Compose
- Git


### Para el Entorno de Pruebas:
1. Clonar el repositorio
```
git https://github.com/ctnfimac/taskapp.git
```

2. Me muevo hacia la carpeta del proyecto
```
cd taskapp
```

3. Genero el .jar de los microservicios
```
mvn clean package -DskipTests
```

4. Levanto los contenedores de las base de datos, los microservicios, RabbitMq y el cliente de postgresql pgadmin(*):
```
docker-compose -f docker-compose.test.yml up --build -d
```

### Para el entorno de desarrollo:
1. Clonar el repositorio
```
git https://github.com/ctnfimac/taskapp.git
```

2. Me muevo hacia la carpeta del proyecto
```
cd taskapp
```

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

6. Levanto el microservicio de notificaciones, desde la carpeta microservice-notification ejecutar:
```
mvn spring-boot:run
```

### Documentacion, api de tareas:
```
http://localhost:8091/docs
```

### Documentacion, api de usuarios:
```
http://localhost:8092/docs
```
### Pantalla del sistema de mensajeria con rabbitmq
usuario y contraseña esta en el docker-compose
```
http://localhost:15672/
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
  - Enviar notificación por email
- Crear Tarea
- Obtener Tareas de un usuario y bloque finalizado
- Eliminar Tarea
- Cambio de estado de la Tarea
- Obtener bloques de tarea de un usuario


(*) El pgadmin es un cliente de base de datos, que no es indispensable para el funcionamiento del proyecto pero sirve 
para visualizar la información de la base de datos de una forma más gráfica