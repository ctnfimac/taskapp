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

En caso de usar el Frontend
- node v20.18.0
- npm 10.8.2


### Para el Entorno de Pruebas:
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

4. Inicio los contenedores de las base de datos, los microservicios, RabbitMq y el cliente de postgresql pgadmin(*):
```
docker-compose -f docker-compose.test.yml up --build -d
```

### Para el entorno de desarrollo:
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

4. Inicio el microservicio de usuarios, *desde la carpeta microservice-users* ejecutar:
```
mvn spring-boot:run
```

5. Inicio el microservicio de tareas, *desde la carpeta microservice-tasks* ejecutar:
```
mvn spring-boot:run
```

6. Inicio el microservicio de notificaciones, *desde la carpeta microservice-notification* ejecutar:
```
mvn spring-boot:run
```

### Para el FrontEnd
Tener [Angular cli](https://angular.dev/installation) previamente instalado
```
npm install -g @angular/cli
```
1. Me muevo a la carpeta frontend
```
cd frontend
```

2. Instalo las dependencias
```
npm install
```

3. Inicio el proyecto Angular
```
ng serve
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

## Diagrama de las base de datos de los microservicios
![Image](https://github.com/user-attachments/assets/6d71e9c2-a9ca-491f-b05a-ab7b215a8f3c)

## Diagrama de flujo del uso de la aplicación
El proyecto está pensado para trabajar sobre el backend, pero el diagrama lo realicé para
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