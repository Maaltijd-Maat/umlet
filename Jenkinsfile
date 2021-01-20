#!groovy​
pipeline {
    agent {
        docker {
            image 'maven:3.6.3-openjdk-8'
            args '--net="sonarnet" -v /root/.m2:/root/.m2'
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
                sh '''mvn sonar:sonar \\
  -Dsonar.projectKey=umlet \\
  -Dsonar.host.url=http://172.22.0.2:9000 \\
  -Dsonar.login=c22385269461a84317fe3dd72e6dd766835c7b03'''
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