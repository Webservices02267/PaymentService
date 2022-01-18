pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh 'cd Messaging-utilities'
                sh 'git branch --set-upstream-to=origin/main'
                sh 'cd ..'
                sh './update.sh'
                sh './completeBuild.sh'
            }
        }
    }
    post {
        always {
            sh 'echo "pipeline complete"'
        }
    }
}
