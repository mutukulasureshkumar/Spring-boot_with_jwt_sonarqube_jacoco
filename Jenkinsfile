pipeline {

    agent any
    tools {
        maven 'Maven_3.5.2' 
    }
    stages {
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
          stage('Slack Notification'){
               steps {
                   slackSend baseUrl: 'https://hooks.slack.com/services/', 
                       channel: '#jenkins-build', 
                       color: 'good', 
                       message: 'Hi Greetings from Jenkins', 
                       teamDomain: 'javahomecloud', 
                       tokenCredentialId: 'slack-demo'
            }
        }

  }

}
