pipeline {
         stage('Test') { 
            steps {
                 sh 'docker-compose up -d'
             }
             post {
                 always {
                     junit 'reports/surefire-reports/*.xml'
                 }
             }
         }
 }
