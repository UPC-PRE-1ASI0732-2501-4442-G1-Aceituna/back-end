#!/bin/bash

# Build script for App Runner deployment
# This script builds and pushes the Docker image configured for App Runner

echo "🏗️  Building EcoMovil API for App Runner..."

# Build with App Runner profile
docker buildx build --platform linux/amd64 \
  --build-arg SPRING_PROFILES_ACTIVE=apprunner \
  -t ecomovil-api:apprunner .

echo "✅ Build completed for App Runner!"
echo "📝 To deploy to App Runner, use this image: ecomovil-api:apprunner"
echo "🔧 The image is configured with CORS settings optimized for App Runner"

# Tag for ECR if needed
if [ "$1" = "push" ]; then
    echo "🚀 Pushing to ECR..."
    
    # Login to ECR
    aws ecr get-login-password --region us-east-1 --profile rumi | docker login --username AWS --password-stdin 184642059039.dkr.ecr.us-east-1.amazonaws.com
    
    # Tag and push
    docker tag ecomovil-api:apprunner 184642059039.dkr.ecr.us-east-1.amazonaws.com/david/emo:apprunner
    docker push 184642059039.dkr.ecr.us-east-1.amazonaws.com/david/emo:apprunner
    
    echo "✅ Image pushed to ECR!"
    echo "📝 Use this image in App Runner: 184642059039.dkr.ecr.us-east-1.amazonaws.com/david/emo:apprunner"
fi
