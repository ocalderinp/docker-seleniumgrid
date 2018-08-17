pipeline {
    parameters { 
        choice(name: 'BROWSER', description: 'Choose a browser', choices: 'chrome\nfirefox')
        choice(name: 'SUITE', description: 'Chose a test suite to run', choices: 'SMOKE_TEST\nREGRESSION_TEST')
        choice(name: 'ENVIRONMENT', description: 'Choose an environment', choices: 'QA\nPROD')
    }
    options { 
        buildDiscarder(logRotator(numToKeepStr: '10'))

    }
    agent any
    stages { 
        stage('SCM: code update') {
            checkout ([
                $class: 'GitSCM', branches: [[name: 'master']],
                userRemoteConfigs: [[url: 'https://github.com/ocalderinp/docker-seleniumgrid']]
            ])
        }
        stage('Execute test') {
            step([
            $class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StartSrvice'], useCustomDockerComposeFile: true
        ])   
        } 
    }
    post {
        always {
            junit 'reports/surefire-reports/*.xml'
        }    
    }
    
}
