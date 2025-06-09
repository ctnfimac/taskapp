@echo off
echo Clonando repositorio...
git clone https://github.com/ctnfimac/taskapp.git
cd taskapp

echo Generando JARs de los microservicios...
mvn clean package -DskipTests

echo Instalando dependencias del Frontend...
cd frontend
npm install

echo Compilando frontend...
ng build --configuration production

cd ..

echo Levantando contenedores...
docker-compose -f docker-compose.full.yml up --build -d

echo Instalacion finalizada! Accede a http://localhost/login