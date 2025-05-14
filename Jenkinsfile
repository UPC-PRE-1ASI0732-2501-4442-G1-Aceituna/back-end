pipeline {
    agent any  // Define que se ejecute en cualquier agente disponible de Jenkins
    
    environment {
        // Variables de entorno
        GIT_REPO_URL = 'https://github.com/UPC-PRE-1ASI0732-2501-4442-G1-Aceituna/back-end'
        GIT_BRANCH = 'main'  // O 'master' dependiendo de la rama que quieras usar
        DOCKER_IMAGE = 'ecovimil/app'  // Ejemplo de nombre de la imagen Docker
        STAGING_SERVER = 'staging-server' // Servidor de staging
        PROD_SERVER = 'production-server' // Servidor de producción
    }
    
    tools {
        // Define herramientas necesarias, como Maven, Node, etc.
        maven 'Maven 3.6.3' // Aquí puedes agregar cualquier otra herramienta que utilices
    }
    
    stages {
        stage('Clonar Repositorio') {
            steps {
                script {
                    // Clonación del repositorio con credenciales si es necesario
                    git credentialsId: 'github-credentials', url: 'https://github.com/UPC-PRE-1ASI0732-2501-4442-G1-Aceituna/back-end'
                }
            }
        }
        
        stage('Compilación y Construcción') {
            steps {
                script {
                    // Aquí va el comando para compilar y construir el proyecto
                    // Ejemplo si es un proyecto de Java con Maven
                    sh 'mvn clean install' // Cambia este comando según tu stack
                }
            }
        }
        
        stage('Pruebas Unitarias') {
            steps {
                script {
                    // Ejecutar pruebas unitarias
                    sh 'mvn test' // Cambia esto según tus necesidades (por ejemplo, si usas Jest, PyTest, etc.)
                }
            }
        }

        stage('Pruebas de Integración') {
            steps {
                script {
                    // Ejecutar pruebas de integración
                    sh './run_integration_tests.sh'  // Cambia esto a tu propio script o comando de pruebas de integración
                }
            }
        }

        stage('Despliegue a Staging') {
            steps {
                script {
                    // Construir y desplegar en un entorno de staging (por ejemplo, usando Docker)
                    sh 'docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .'
                    sh 'docker run -d --name eco-movil-staging ${DOCKER_IMAGE}:${BUILD_NUMBER}'
                    // Comandos adicionales para desplegar en un servidor de staging
                    sh "ssh user@${STAGING_SERVER} 'docker pull ${DOCKER_IMAGE}:${BUILD_NUMBER} && docker run -d ${DOCKER_IMAGE}:${BUILD_NUMBER}'"
                }
            }
        }

        stage('Pruebas en Staging') {
            steps {
                script {
                    // Aquí puedes ejecutar pruebas adicionales en el entorno de staging
                    sh './run_staging_tests.sh'
                }
            }
        }

        stage('Despliegue a Producción') {
            steps {
                script {
                    // Desplegar la aplicación en el entorno de producción
                    sh 'docker build -t ${DOCKER_IMAGE}:latest .'
                    sh 'docker run -d --name eco-movil-prod ${DOCKER_IMAGE}:latest'
                    // Comandos adicionales para desplegar en producción
                    sh "ssh user@${PROD_SERVER} 'docker pull ${DOCKER_IMAGE}:latest && docker run -d ${DOCKER_IMAGE}:latest'"
                }
            }
        }

        stage('Monitoreo Post-Despliegue') {
            steps {
                script {
                    // Ejecutar monitoreo o comprobaciones de la aplicación después del despliegue
                    sh './monitor_production.sh'  // Cambia esto a tu propio script de monitoreo
                }
            }
        }
    }

    post {
        success {
            // Enviar notificación en caso de éxito
            echo "Pipeline ejecutado con éxito"
            slackSend(channel: '#deployments', message: "Despliegue exitoso!")
        }
        failure {
            // Enviar notificación en caso de error
            echo "Pipeline fallido"
            slackSend(channel: '#deployments', message: "Fallo en el despliegue!")
        }
        always {
            // Limpiar el entorno después de la ejecución del pipeline
            cleanWs()  // Limpia el workspace
        }
    }
}
