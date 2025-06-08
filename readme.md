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

## Entorno de desarrollo:
*Para este entorno se levantan 2 contenedores de base de datos, el de rabbitMq y un cliente de base de datos. El frontend se instala aparte*
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

3. Genero el .jar de los microservicios
```
mvn clean package -DskipTests
```

4. Me posiciono en la carpeta del frontend
```
cd frontend
```

5. Instalo las dependencias del proyecto angular
```
npm install
```

6. Compilo el proyecto angular
```
ng build --configuration production
```

7. Regreso a la raiz del proyecto
```
cd ..
```

8. Inicio los contenedores:
```
docker-compose -f docker-compose.full.yml up --build -d
```

9. Ingreso a la aplicación
```
http://localhost/login
```

### Documentacion, api de tareas:
```
http://localhost:8091/docs
```

### Documentacion, api de usuarios:
```
http://localhost:8092/docs
```

### Ejecutar pruebas unitarias
```
mvn test
```

## Front-End
*Pasos para instalar el proyecto angular en caso de necesitarlo para el ambiente de desarrollo o de pruebas. Tener [Angular cli](https://angular.dev/installation) previamente instalado*
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

4. Ingreso a la aplicación
```
http://localhost:4200
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