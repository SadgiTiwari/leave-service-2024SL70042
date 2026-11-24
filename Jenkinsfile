pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('Sonar-token-2024SL70042')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Code Inspection') {
            steps {
                sh '''
                  mvn org.sonarsource.scanner.maven:sonar-maven-plugin:5.0.0.4389:sonar \
                    -Dsonar.projectKey=leave-service-2024SL70042 \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.token=$SONAR_TOKEN
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t leave-service:${BUILD_NUMBER} .'
            }
        }

        stage('Deploy to Staging') {
            steps {
                sh '''
                  docker rm -f leave-staging || true
                  docker run -d --name leave-staging -p 8085:8080 \
                    -e APP_ENV=staging leave-service:${BUILD_NUMBER}
                  sleep 5
                  curl -s http://localhost:8085
                '''
            }
        }
        stage('Approval') {
            steps {
                input message: 'Staging verified. Deploy to production?', ok: 'Approve'
            }
        }

        stage('Deploy to Production') {
            steps {
                sh '''
                  docker rm -f leave-production || true
                  docker run -d --name leave-production -p 8086:8080 \
                    -e APP_ENV=production leave-service:${BUILD_NUMBER}
                  sleep 5
                  curl -s http://localhost:8086
                '''
            }
        }  

  }
}
