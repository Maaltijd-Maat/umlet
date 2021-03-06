#!groovy​
pipeline {
    agent {
        docker {
            image 'maven:3.6.3-openjdk-8'
            args '--net="software-validation_sonarnet" -v /root/.m2:/root/.m2'
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
        stage('Build') {
            steps {
                sh 'mvn -B clean install -Dmaven.test.skip=true'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv(installationName: 'Default') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
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