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

                stage('Set Java') {
                    steps {
                        bat '''
                        set JAVA_HOME=C:\\Users\\Usuario\\.jdks\\ms-17.0.18
                        set PATH=%JAVA_HOME%\\bin;%PATH%
                         '''
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