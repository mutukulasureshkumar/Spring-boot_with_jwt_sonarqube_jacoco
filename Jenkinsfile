pipeline {

    agent any
    tools {
        maven 'Maven_3.5.2' 
    }
    stages {
        
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
          stage('deployment stage') {
              steps {
                sh "mvn deploy"
        }
    }
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
