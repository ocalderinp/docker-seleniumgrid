pipeline {
    agent any 
    stages {
        stage('Test') { 
            steps {
                sh 'docker-compose up -d' 
            }
        }
    }
    post {
        always {
            junit 'reports/surefire-reports/*.xml'
        }
    }     
}
