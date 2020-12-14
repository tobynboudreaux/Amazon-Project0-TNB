pipeline {
    agent any

    stages {
        stage('package') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('deploy') {
            steps {
                sh 'cp /usr/share/tomcat/.jenkins/workspace/Amazon_main/target/Amazon.war /usr/share/tomcat/webapps'
            }
        }
    }
  }

