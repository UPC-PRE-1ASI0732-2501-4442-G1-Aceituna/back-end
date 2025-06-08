# Guía de Despliegue en AWS App Runner

## Pasos para desplegar EcoMovil API en AWS App Runner

### 1. Preparación del Código

✅ **Ya completado**: 
- Dockerfile configurado
- Variables de entorno preparadas
- Configuración de base de datos AWS RDS

### 2. Subir el código a un repositorio

Asegúrate de que tu código esté en un repositorio Git (GitHub, GitLab, etc.):

```bash
# Si aún no tienes un repositorio remoto
git add .
git commit -m "Configuración Docker para App Runner"
git push origin main
```

### 3. Crear el servicio en AWS App Runner

#### Opción A: Desde la consola AWS

1. **Ir a AWS App Runner** en la consola de AWS
2. **Crear servicio** → **Crear y configurar**
3. **Fuente del código**:
   - Repository type: `Source code repository`
   - Provider: `GitHub` (o tu proveedor)
   - Repository: Selecciona tu repositorio
   - Branch: `main`
   
4. **Configuración de despliegue**:
   - Deployment trigger: `Automatic`
   - Configuration file: `Use configuration file` (apprunner.yaml)

5. **Configuración del servicio**:
   - Service name: `ecomovil-api`
   - Virtual CPU: `1 vCPU`
   - Memory: `2 GB`
   - Environment variables (si no usas apprunner.yaml):
     ```
     SPRING_DATASOURCE_URL=jdbc:mysql://ecomovil.cqzckuuccbtd.us-east-1.rds.amazonaws.com:3306/ecomovilaceituna?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
     SPRING_DATASOURCE_USERNAME=ecomovilaceituna
     SPRING_DATASOURCE_PASSWORD=123Fer53
     ```

6. **Networking**:
   - Incoming traffic: `Public endpoint`
   - Outgoing traffic: `Allow all`

#### Opción B: Con AWS CLI

```bash
# Crear servicio App Runner
aws apprunner create-service \
  --service-name ecomovil-api \
  --source-configuration '{
    "ImageRepository": {
      "ImageIdentifier": "tu-repo/ecomovil-api:latest",
      "ImageConfiguration": {
        "Port": "8080",
        "RuntimeEnvironmentVariables": {
          "SPRING_DATASOURCE_URL": "jdbc:mysql://ecomovil.cqzckuuccbtd.us-east-1.rds.amazonaws.com:3306/ecomovilaceituna?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
          "SPRING_DATASOURCE_USERNAME": "ecomovilaceituna",
          "SPRING_DATASOURCE_PASSWORD": "123Fer53"
        }
      }
    },
    "AutoDeploymentsEnabled": true
  }' \
  --instance-configuration '{
    "Cpu": "1 vCPU",
    "Memory": "2 GB"
  }'
```

### 4. Configurar permisos de red

**Importante**: Asegúrate de que App Runner pueda acceder a tu RDS:

1. **Security Group de RDS**: Añadir regla de entrada
   - Type: `MySQL/Aurora`
   - Port: `3306`
   - Source: `0.0.0.0/0` (o específico de App Runner)

2. **Subnet Groups**: Verificar que RDS esté en subnets públicas o configurar VPC adecuadamente

### 5. Verificar el despliegue

Una vez creado el servicio:

1. **URL del servicio**: App Runner te proporcionará una URL pública
2. **Logs**: Revisar los logs en la consola de App Runner
3. **Health check**: App Runner verificará que el puerto 8080 responda

### 6. Pruebas

```bash
# Probar la API una vez desplegada
curl https://tu-url-de-app-runner.region.awsapprunner.com/

# Ejemplo de endpoint
curl https://tu-url-de-app-runner.region.awsapprunner.com/api/health
```

## Archivos importantes creados

- `apprunner.yaml`: Configuración específica para App Runner
- `Dockerfile`: Optimizado para producción
- Variables de entorno configuradas para AWS RDS

## Notas importantes

- **Costos**: App Runner cobra por vCPU y memoria usada
- **Escalado**: Se escala automáticamente según la demanda
- **SSL**: App Runner proporciona HTTPS automáticamente
- **Dominio personalizado**: Se puede configurar después del despliegue

## Troubleshooting

Si tienes problemas:

1. **Revisar logs** en la consola de App Runner
2. **Verificar conectividad** a RDS desde App Runner
3. **Comprobar variables de entorno**
4. **Validar que el puerto 8080** esté correcto
