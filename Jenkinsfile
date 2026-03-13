pipeline {

    agent any

    parameters {
        string(
            name: 'TAGS',
            defaultValue: '@wiremock',
            description: 'Karate tags to execute'
        )
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/riverajosefernando-rgb/bank-api-test.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat """
                set JAVA_HOME=C:\\Users\\Usuario\\.jdks\\ms-17.0.18
                set PATH=%JAVA_HOME%\\bin;%PATH%
                java -version
                gradlew.bat clean test "-Dkarate.tags=${params.TAGS}"
                """
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