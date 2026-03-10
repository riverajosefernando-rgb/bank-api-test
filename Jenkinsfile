pipeline {

    agent any

    tools {
        gradle 'Gradle'
        jdk 'JDK11'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/tu-repo/bank-api-test.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'gradle clean build -x test'
            }
        }

        stage('Start WireMock') {
            steps {
                sh 'nohup java -jar wiremock/wiremock.jar --port 8081 &'
                sh 'sleep 10'
            }
        }

        stage('Run Karate Tests') {
            steps {
                sh 'gradle test'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'build/test-results/test/*.xml'
            }
        }

    }

}