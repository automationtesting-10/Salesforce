pipeline {
    agent any 
    stages {
        stage('Assemble') { 
            steps {
                echo 'Compiling...'
                sh './gradlew assemble'
                archiveArtifacts './build/libs/*.jar'
            }
        }

        stage('Checks') { 
            steps {
                echo 'Executing Checks.'
                sh './gradlew check'
            }
        }

    post {
        always {
            echo 'This is a post action.'
            mail to: 'Cristian.lujan@fundacion-jala.org',
                 subject: "Test Pipeline: ${currentBuild.fullDisplayName}",
                 body: "The pipeline ${currentBuild.fullDisplayName} completed successfully."
        }
    }
}