pipeline {
  agent {
    docker {
      image 'maven:3.6.3-openjdk-8'
      args '-v /root/.m2:/root/.m2'
    }
  }
  stages {
    stage('Build and test'){
    steps {
        sh 'mkdir -p /root/.config/UMLet'
        sh 'mvn clean install'
     }
    }
    stage('Generate report'){
        steps {
            cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', jsonReportDirectory: '/var/jenkins_home/workspace/umlet_master/umlet-standalone', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
         }
        }
  }
}