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
                start "" /B java -jar src/test/resources/wiremock/wiremock-standalone-3.13.2.jar --port 8081 --root-dir src/test/resources/wiremock
                ping 127.0.0.1 -n 10 > nul
                '''
            }
        }

        stage('Check WireMock') {
            steps {
                bat 'curl http://localhost:8081/__admin'
            }
        }

        stage('Check Mappings') {
            steps {
                bat 'curl http://localhost:8081/__admin/mappings'
            }
        }

        stage('Build & Test') {
            steps {
                bat '''
                set JAVA_HOME=C:\\Users\\Usuario\\.jdks\\ms-17.0.18
                set PATH=%JAVA_HOME%\\bin;%PATH%
                java -version
                gradlew.bat clean test -Dkarate.options="--tags %TAGS%"
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
                   allowMissing: true,
                   alwaysLinkToLastBuild: true,
                   keepAll: true,
                   reportDir: 'build/karate-reports',
                   reportFiles: 'karate-summary.html,karate-timeline.html,karate-tags.html',
                   reportName: 'Karate API Test Report'
               ])
           }
       }

    }

}