#!groovy​
pipeline {
    agent {
        docker {
            image 'maven:3.6.3-openjdk-8'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Configure') {
            steps {
                // This command is needed because UMLet uses an outdated API for creating folders.
                // It's too much work to check where this API is called, so with this way the problem can be avoided.
                sh 'mkdir -p /root/.config/UMLet'
            }
        }
        stage('SonarTests') {
            steps {
                withSonarQubeEnv('Default') {
                    sh '''mvn sonar:sonar \\
                        -Dsonar.projectKey=umlet \\
                        -Dsonar.host.url=http://127.0.0.1:9000 \\
                        -Dsonar.login=aee96827e217c752a8ff4844cdc449e4c632624c'''
                }
            }
        }
    }
    post {
        always {
            cucumber failedFeaturesNumber: -1,
                    failedScenariosNumber: -1,
                    failedStepsNumber: -1,
                    fileIncludePattern: '**/*.json',
                    jsonReportDirectory: '/var/jenkins_home/workspace/umlet_master/umlet-standalone',
                    pendingStepsNumber: -1,
                    skippedStepsNumber: -1,
                    sortingMethod: 'ALPHABETICAL',
                    undefinedStepsNumber: -1
        }
    }
}