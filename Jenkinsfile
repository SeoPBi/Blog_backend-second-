pipeline {
    agent any

    stages {
        stage('Build and Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        def appImage = docker.build("shinhyeonseop/docker:latest")
                        appImage.push()
                    }
                }
            }
        }
    }
}
