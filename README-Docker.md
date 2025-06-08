# EcoMovil API - Docker

Este proyecto incluye configuración Docker para ejecutar la API de EcoMovil conectándose a la base de datos MySQL de AWS RDS.

## Requisitos

- Docker
- Docker Compose
- Conexión a internet (para acceder a AWS RDS)

## Instrucciones de uso

### 1. Construir y ejecutar con Docker Compose

```bash
# Construir y ejecutar la aplicación
docker-compose up --build

# Ejecutar en segundo plano
docker-compose up -d --build
```

### 2. Comandos útiles

```bash
# Ver logs de la aplicación
docker-compose logs ecomovil-api

# Parar el servicio
docker-compose down

# Reconstruir la aplicación
docker-compose build ecomovil-api

# Ejecutar solo la aplicación (sin docker-compose)
docker build -t ecomovil-api .
docker run -p 8080:8080 ecomovil-api
```

### 3. Acceso

- **API**: http://localhost:8080
- **Base de datos**: AWS RDS MySQL (ecomovil.cqzckuuccbtd.us-east-1.rds.amazonaws.com)

### 4. Estructura de Docker

- **Dockerfile**: Construye la imagen de la aplicación Spring Boot
- **docker-compose.yml**: Ejecuta la aplicación con las variables de entorno para AWS
- **application.properties**: Configuración original que se mantiene

## Notas

- La aplicación se conecta directamente a la base de datos MySQL de AWS RDS
- No se incluye base de datos local - usa la configuración de producción de AWS
- Las variables de entorno en docker-compose.yml sobrescriben la configuración de application.properties
