setup:
	mvn clean package -DskipTests && \
	cd frontend && npm install && ng build --configuration production && \
	cd .. && docker-compose -f docker-compose.full.yml up --build -d