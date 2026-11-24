pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar-token-2024sl70042')
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
                  mvn sonar:sonar \
                    -Dsonar.projectKey=leave-service-<BITSID> \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.token=$SONAR_TOKEN
                '''
            }
        }
    }
}
