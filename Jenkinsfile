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
        docker_experimental_repo = 'experimental_project'
        docker_gateway_repo = 'gateway'
        tag = 'latest'
        DOCKERHUB_CREDENTIALS = credentials('isep-dissertation-docker')
    }

    stages {
        stage('Checkout') {
            steps{
                checkout scm           
            }
        }
        stage('Build control jar'){
            steps{
                dir('Control_Project_JSON/convenienceStore'){
                    chooseOsCommand('mvn clean package -DskipTests')
                }
            }
        }
        stage('Build control dockerfile'){
            steps{
                dir('Control_Project_JSON/convenienceStore'){
                    chooseOsCommand("docker build -t ${dockerhub_id}/${docker_control_repo}:${tag} .")
                }
            }
        }
        stage('Build experimental jar'){
            steps{
                dir('Experimental_Group_ProtoBuff/convenienceStore'){
                    chooseOsCommand('mvn clean compile package -DskipTests')
                }
            }
        }
        stage('Build experimental dockerfile'){
            steps{
                dir('Experimental_Group_ProtoBuff/convenienceStore'){
                    chooseOsCommand("docker build -t ${dockerhub_id}/${docker_experimental_repo}:${tag} .")
                }
            }
        }
        stage('Build gateway jar'){
            steps{
                dir('Gateway/Gateway'){
                    chooseOsCommand('mvn clean package -DskipTests')
                }
            }
        }
        stage('Build gateway dockerfile'){
            steps{
                dir('Gateway/Gateway'){
                    chooseOsCommand("docker build -t ${dockerhub_id}/${docker_gateway_repo}:${tag} .")
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
        stage('Push control project to Dockerhub'){
            steps{
                dir('Control_Project_JSON/convenienceStore'){
                    chooseOsCommand("docker push ${dockerhub_id}/${docker_control_repo}:${tag}")
                }
            }
        }
        stage('Push experimental project to Dockerhub'){
            steps{
                dir('Experimental_Group_ProtoBuff/convenienceStore'){
                    chooseOsCommand("docker push ${dockerhub_id}/${docker_experimental_repo}:${tag}")
                }
            }
        }
        stage('Push gateway project to Dockerhub'){
            steps{
                dir('Gateway/Gateway'){
                    chooseOsCommand("docker push ${dockerhub_id}/${docker_gateway_repo}:${tag}")
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
