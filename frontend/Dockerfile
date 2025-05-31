# Etapa 1: build de Angular
FROM node:20 AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build -- --configuration production

# Etapa 2: servidor web con Nginx
FROM nginx:stable-alpine

COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Copio configuración de Nginx
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
