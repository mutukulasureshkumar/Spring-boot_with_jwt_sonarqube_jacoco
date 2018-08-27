pipeline {

    agent any
    tools {
        maven 'Maven_3.5.2' 
    }
    stages {
        /**For the first time execute command :: mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false*/
        stage('Slack start Notification'){
               steps {
                   slackSend baseUrl: 'https://hooks.slack.com/services/', 
                       channel: '#jenkins-build', 
                       color: 'good', 
                       message: 'Spring-boot_with_jwt_sonarqube_jacoco deployment build started!!', 
                       teamDomain: 'javahomecloud', 
                       tokenCredentialId: 'slack-demo'
            }
       }
        stage('Compile stage') {
            steps {
                sh "mvn clean compile" 
        }
    }

         stage('testing stage') {
             steps {
                sh "mvn test"
             }
    }
        stage('Analysing Code with SonarQube') {
             steps {
                sh " mvn sonar:sonar"
             }
    }
          /*stage('deployment stage') {
              steps {
                sh "mvn deploy"
        }
    }*/
          stage('Slack End Notification'){
               steps {
                   slackSend baseUrl: 'https://hooks.slack.com/services/', 
                       channel: '#jenkins-build', 
                       color: 'good', 
                       message: 'Spring-boot_with_jwt_sonarqube_jacoco deployment build completed!!', 
                       teamDomain: 'javahomecloud', 
                       tokenCredentialId: 'slack-demo'
            }
        }

  }

}
