pipeline {

    agent any

    parameters {

        string(
            name: 'TAGS',
            defaultValue: '@wiremock',
            description: 'Karate tags to execute'
        )

        booleanParam(
            name: 'USE_DOCKER',
            defaultValue: false,
            description: 'Run tests inside Docker'
        )
    }

    environment {

        JAVA_HOME = "C:\\Users\\Usuario\\.jdks\\ms-17.0.18"
        GRADLE_USER_HOME = "${WORKSPACE}\\.gradle"

    }

    stages {

        stage('Checkout Code') {

            steps {
                git branch: 'main',
                url: 'https://github.com/riverajosefernando-rgb/bank-api-test.git'
            }

        }

        stage('Environment Info') {

            when {
                expression { return !params.USE_DOCKER }
            }

            steps {

                bat """
                set PATH=%JAVA_HOME%\\bin;%PATH%
                java -version
                gradlew --version
                """

            }
        }

        stage('Build & Test') {

            when {
                expression { return !params.USE_DOCKER }
            }

            steps {

                echo "Running Karate tests with tags: ${params.TAGS}"

                bat """
                set PATH=%JAVA_HOME%\\bin;%PATH%
                gradlew.bat clean test "-Dkarate.tags=${params.TAGS}" --no-daemon
                """

            }
        }

        stage('Run Tests in Docker') {

            when {
                expression { return params.USE_DOCKER }
            }

            steps {

                echo "Running tests inside Docker"

                bat """
                docker build -t karate-tests .
                docker run -e KARATE_TAGS=${params.TAGS} karate-tests
                """

            }
        }

        stage('Publish JUnit Results') {

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
                        reportFiles: 'karate-summary.html',
                        reportName: 'Karate API Test Report'
                ])

            }

        }

        stage('Publish Allure Report') {

            steps {

                allure(
                        includeProperties: false,
                        jdk: '',
                        results: [[path: 'build/allure-results']]
                )

            }

        }

    }

    post {

        always {

            archiveArtifacts artifacts: 'build/karate-reports/**/*.*', fingerprint: true

        }

        success {

            echo "Karate tests completed successfully 🚀"

        }

        failure {

            echo "Karate tests failed ❌"

        }

    }

}