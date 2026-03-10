pipeline {

    agent any

    tools {
        gradle 'Gradle'
        jdk 'JDK17'
    }

        stages {

            stage('Checkout Code') {
                steps {
                    git branch: 'main', url: 'https://github.com/riverajosefernando-rgb/bank-api-test.git'
                }
            }

            stage('Build Project') {
                steps {
                    bat 'gradlew.bat clean build'
                }
            }

            stage('Run Karate Tests') {
                steps {
                    bat 'gradlew.bat test'
                }
            }


            stage('Publish Test Results') {
                steps {
                    junit 'build/test-results/test/*.xml'
                }
            }

    }

}