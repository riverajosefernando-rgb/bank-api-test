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

                stage('Verify Java Version') {
                    steps {
                        bat 'java -version'
                    }
                }

                stage('Build & Test') {
                    steps {
                        bat 'gradlew.bat clean test'
                    }
                }

                stage('Publish Test Results') {
                    steps {
                        junit 'build/test-results/test/*.xml'
                    }
                }

            }

}