pipeline {
  agent {
    docker {
      image 'maven:3.6.3-openjdk-8'
      args '-v /root/.m2:/root/.m2'
    }
  }
  stages {
    stage('Build and test') {
      steps {
        // Deze command is nodig omdat UMLet een verouderde API voor het aanmaken van folders gebruikt.
        // Het is teveel werk om te controlleren waar deze API aangeroepen wordt,
        // door middel van deze manier kan dit probleem omgezeild worden.
        sh 'mkdir -p /root/.config/UMLet'
        sh 'mvn clean install'
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