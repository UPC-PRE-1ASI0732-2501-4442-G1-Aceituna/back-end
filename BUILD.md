# 🚀 EcoMovil API - Guía de Build y Deploy

## 📦 Build Local (Desarrollo)

Para desarrollar localmente en cualquier arquitectura:

```bash
# Opción 1: Con Docker Compose (recomendado para desarrollo)
docker-compose up --build

# Opción 2: Con Docker directo
docker build -t ecomovil-api .
docker run -p 8080:8080 ecomovil-api
```

## ☁️ Build para AWS (Producción)

Para deployment en AWS ECS Fargate, necesitas arquitectura `linux/amd64`:

```bash
# Opción 1: Script automático (recomendado)
./build.sh

# Opción 2: Comando manual
docker buildx build --platform linux/amd64 -t ecomovil-api:amd64 .
```

## 🔧 Configuración de Arquitecturas

### **Si tienes Mac con Apple Silicon (M1/M2/M3):**
- **Desarrollo local**: Usa `docker-compose up` (usa ARM64 nativo)
- **Para AWS**: Usa `./build.sh` o el comando `docker buildx --platform linux/amd64`

### **Si tienes PC Windows/Linux Intel:**
- **Desarrollo local**: Usa `docker-compose up` (usa AMD64 nativo)
- **Para AWS**: Funciona igual, pero puedes usar `docker build` normal

## 🚨 Importante para el Equipo

1. **Nunca subas imágenes ARM64 a ECR para AWS** - ECS Fargate solo acepta AMD64
2. **Usa docker-compose para desarrollo local** - Es más fácil y funciona en cualquier arquitectura
3. **Usa el script `build.sh` para builds de producción** - Asegura compatibilidad con AWS

## 🐛 Troubleshooting

**Error: "manifest does not contain descriptor matching platform"**
- Solución: Usa `docker buildx build --platform linux/amd64`

**Docker buildx no funciona:**
```bash
# Habilitar buildx
docker buildx create --use
```

## 📝 Variables de Entorno

El proyecto usa estas variables (ya configuradas en docker-compose.yml):
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
