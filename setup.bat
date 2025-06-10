@REM instalacion en windows
@echo off
setlocal

echo ===============================
echo 1. Generando JARs...
echo ===============================
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo Fallo mvn
    pause
    exit /b
)

echo ===============================
echo 2. Instalando dependencias Angular...
echo ===============================
cd frontend
call npm install
if %ERRORLEVEL% NEQ 0 (
    echo Fallo npm install
    pause
    exit /b
)

echo ===============================
echo 3. Compilando frontend...
echo ===============================
call ng build --configuration production
if %ERRORLEVEL% NEQ 0 (
    echo Fallo ng build
    pause
    exit /b
)
cd ..

echo ===============================
echo 4. Levantando contenedores Docker...
echo ===============================
call docker-compose -f docker-compose.full.yml up --build -d

echo ===============================
echo Instalacion completada.
echo Accede a http://localhost/login