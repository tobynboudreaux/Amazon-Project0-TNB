  
pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('deploy') {
      when {
        branch 'main'
      }

      steps {
        sh 'rm -rf /usr/share/tomcat/webapps/Amazon || true'
        sh 'cp -r build /usr/share/tomcat/webapps/Amazon'
      }
    }
  }
}
