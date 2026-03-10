pipeline {

    agent any

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/riverajosefernando-rgb/bank-api-test.git'
            }
        }

        stage('Start WireMock') {
            steps {
                bat '''
                start "" /B java -jar src/test/resources/wiremock/wiremock-standalone-3.13.2.jar --port 8081 --root-dir resources/wiremock/mappings
                ping 127.0.0.1 -n 10 > nul
                '''
            }
        }

        stage('Build & Test') {
            steps {
                bat '''
                set JAVA_HOME=C:\\Users\\Usuario\\.jdks\\ms-17.0.18
                set PATH=%JAVA_HOME%\\bin;%PATH%
                java -version
                gradlew.bat clean test
                '''
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'build/test-results/test/*.xml'
            }
        }

        stage('Publish Karate Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'build/karate-reports',
                    reportFiles: 'karate-summary.html',
                    reportName: 'Karate API Test Report'
                ])
            }
        }

    }

}