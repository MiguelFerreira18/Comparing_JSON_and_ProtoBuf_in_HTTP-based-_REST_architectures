def chooseOsCommand(command){
    if(isUnix()){
        sh command
    }else{
        bat command
    }
}
pipeline {
    agent any

    environment {
        branch = 'main'
        repo = 'https://github.com/MiguelFerreira18/Comparing_JSON_and_ProtoBuf_in_HTTP-based-_REST_architectures'
        credentials = 'isep-dissertation'
        dockerhub_id = '1230199'
        docker_control_repo = 'control_project'
        tag = 'latest'
        DOCKERHUB_CREDENTIALS = credentials('isep-dissertation-docker')
    }

    stages {
        stage('Checkout') {
            steps{
                checkout scm           
            }
        }
        stage('Build jar'){
            steps{
                dir('Control_Project_JSON/base_project'){
                    chooseOsCommand('mvn clean package -DskipTests')
                }
            }
        }
        stage('Build dockerfile'){
            steps{
                dir('Control_Project_JSON/base_project'){
                    chooseOsCommand("docker build -t ${dockerhub_id}/${docker_control_repo}:${tag} .")
                }
            }
        }
        stage('Login to Dockerhub'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'isep-dissertation-docker', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_NAME')]) {
                    chooseOsCommand("docker login -u ${DOCKERHUB_NAME} -p ${DOCKERHUB_PASSWORD}")
                }
            }
        }
        stage('Push to Dockerhub'){
            steps{
                dir('Control_Project_JSON/base_project'){
                    chooseOsCommand("docker push ${dockerhub_id}/${docker_control_repo}:${tag}")
                }
            }
        }
    }
    post {
        always {
            chooseOsCommand('docker logout')
        }
    }
}
