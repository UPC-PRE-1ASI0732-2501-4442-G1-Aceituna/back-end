#!/bin/bash

# Script de build para EcoMovil API
# Asegura que la imagen sea compatible con AWS ECS Fargate

echo "🚀 Building EcoMovil API for AWS deployment..."

# Build para desarrollo local (arquitectura nativa)
echo "📦 Building for local development..."
docker build -t ecomovil-api:local .

# Build para producción (AWS ECS Fargate - linux/amd64)
echo "☁️ Building for AWS ECS Fargate (linux/amd64)..."
docker buildx build --platform linux/amd64 -t ecomovil-api:amd64 .

echo "✅ Build completed!"
echo ""
echo "Images created:"
echo "  - ecomovil-api:local   (for local development)"
echo "  - ecomovil-api:amd64   (for AWS deployment)"
echo ""
echo "To run locally: docker run -p 8080:8080 ecomovil-api:local"
echo "To deploy to AWS: Push ecomovil-api:amd64 to ECR"
