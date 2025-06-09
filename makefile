# Instalador para linux. ejecutar desde la terminal: make

.PHONY: all build-backend install-frontend build-frontend up

all: build-backend install-frontend build-frontend up

build-backend:
	@echo "Generando JARs de los microservicios..."
	mvn clean package -DskipTests

install-frontend:
	@echo "Instalando dependencias del frontend..."
	cd frontend && npm install

build-frontend:
	@echo "Compilando frontend Angular..."
	cd frontend && ng build --configuration production

up:
	@echo "Levantando contenedores Docker..."
	docker-compose -f docker-compose.full.yml up --build -d