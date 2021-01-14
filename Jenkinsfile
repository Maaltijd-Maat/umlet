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
                docker.image('mysql:5').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw" -p 3306:3306') { c ->
                    /* Wait until mysql service is up */
                    sh 'while ! mysqladmin ping -h0.0.0.0 --silent; do sleep 1; done'
                    /* Run some tests which require MySQL */
                    sh 'make check'
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