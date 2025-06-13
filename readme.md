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
- Docker - Docker Compose
- Git
- RabbitMQ
- Angular 18

## Arquitectura
- Microservicios 

![Image](https://github.com/user-attachments/assets/70ffb8d3-9fda-43d3-9d58-6d1f620f5f4d)

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

En caso de usar el Frontend
- node v20.18.0
- npm 10.8.2


## Entorno de desarrollo:
*Para este entorno se levantan 2 contenedores de base de datos, el de rabbitMq y un cliente de base de datos. El frontend se instala aparte.*
1. Clonar el repositorio
```
git clone https://github.com/ctnfimac/taskapp.git
```

2. Me muevo hacia la carpeta del proyecto
```
cd taskapp
```

3. Inicio los contenedores de las base de datos y cliente pgadmin(*):
```
docker-compose -f docker-compose.dev.yml up --build -d
```

4. Para levantar los microservicios ingreso a cada una de las carpetas(microservice-users, microservice-tasks, microservice-notification) y ejecuto:
```
mvn spring-boot:run
```


## Entorno de Pruebas:
*Para este entorno se levantan 2 contenedores de base de datos, 3 de aplicación y el de rabbitMq. El frontend se instala aparte*
1. Clonar el repositorio
```
git clone https://github.com/ctnfimac/taskapp.git
```

2. Me muevo hacia la carpeta del proyecto
```
cd taskapp
```

3. Genero el .jar de los microservicios
```
mvn clean package -DskipTests
```

4. Inicio los contenedores:
```
docker-compose -f docker-compose.test.yml up --build -d
```


## Entorno de Pruebas Full:
*Para este entorno se levantan 2 contenedores de base de datos, 3 de aplicación, el de rabbitMq y el frontend*
1. Clonar el repositorio
```
git clone https://github.com/ctnfimac/taskapp.git
```

2. Me muevo hacia la carpeta del proyecto
```
cd taskapp
```

3. (Windows 10) Ejecuto el setup.bat desde la terminal, preferentemente como administrador
```
setup.bat
```

3. (Linux) Ejecuto el makefile desde la terminal
```
make all
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


### Ejecutar pruebas unitarias
```
mvn test
```

## Front-End
[Pasos para la instalación](https://github.com/ctnfimac/taskapp/tree/main/frontend)


## Diagrama de las base de datos de los microservicios
![Image](https://github.com/user-attachments/assets/6d71e9c2-a9ca-491f-b05a-ab7b215a8f3c)

## Diagrama de flujo del uso de la aplicación
El proyecto está pensado para trabajar sobre el backend, pero el diagrama lo realicé para
poder analizar que endpoints desarrollar

![Image](https://github.com/user-attachments/assets/1b7be29f-5aab-47c1-8243-db38a2b187cf)



## Funcionalidades Actuales
- Registrarse con email y contraseña.
- Iniciar Sesión con usuario y contraseña.
- Crear bloque de tareas
- Finalizar bloque de tareas
- Enviar notificación por email
- Crear Tarea
- Obtener Tareas de un usuario y bloque finalizado
- Obtener Tareas de un usuario y bloque espeficico sin importar el estado
- Eliminar Tarea
- Cambio de estado de la Tarea
- Obtener bloques de tarea de un usuario
- Eliminar bloque de tarea activo junto con todas sus tareas relacionadas



(*) El pgadmin es un cliente de base de datos, que no es indispensable para el funcionamiento del proyecto pero sirve 
para visualizar la información de la base de datos de una forma más gráfica