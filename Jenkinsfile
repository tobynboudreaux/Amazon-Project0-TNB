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
<<<<<<< HEAD:jenkinsfile
}
=======
  }
}
>>>>>>> d5df90684b6bf2238405e45030825a1d1167221c:Jenkinsfile
