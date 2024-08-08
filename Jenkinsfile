pipeline {
    agent any

    environment {
        // Définir les variables d'environnement JAVA_HOME, MAVEN_HOME, et Docker Hub credentials
        JAVA_HOME = 'C:\\Java\\jdk-17.0.9_windows-x64_bin\\jdk-17.0.9'
        MAVEN_HOME = 'C:\\apache-maven-3.8.8\\apache-maven-3.8.8'
        PATH = "${env.PATH};${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin"
        DOCKER_CREDENTIALS_ID = 'FranceNanterre@2023'
        DOCKER_IMAGE = 'zitouni/bsn'
        DOCKER_TAG = "${env.GIT_BRANCH ?: 'latest'}"
    }

    stages {
stage('Checkout') {
    steps {
        // Cloner le code source depuis le dépôt, en utilisant la branche develop
        checkout([$class: 'GitSCM', branches: [[name: 'develop']], userRemoteConfigs: [[url: 'https://github.com/zitouniaymen/spring-boot-angular-17-crud-example.git']]])
    }
}


        stage('Build') {
            steps {
                // Se déplacer dans le répertoire du projet avant de lancer Maven
                dir('spring-boot-server') {
                    bat "${MAVEN_HOME}\\bin\\mvn clean install"
                }
            }
        }

//         stage('Build Docker Image') {
//             steps {
//                 script {
//                     // Construire l'image Docker
//                     dir('docker') { // Si le Dockerfile est dans un sous-répertoire 'docker'
//                         bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
//                     }
//                 }
//             }
//         }
//
//         stage('Push Docker Image') {
//             steps {
//                 script {
//                     // Login to Docker Hub
//                     withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
//                         bat "docker login -u %DOCKER_USER% -p %DOCKER_PASSWORD%"
//                     }
//                     // Pusher l'image sur Docker Hub
//                     bat "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
//                 }
//             }
//         }
//
//         stage('Run Docker Container') {
//             steps {
//                 script {
//                     // Exécuter un conteneur à partir de l'image Docker poussée
//                     bat "docker run -d --name my-app-container -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
//                 }
//             }
//         }
//
//         stage('Test Docker Container') {
//             steps {
//                 // Ajouter des tests spécifiques pour vérifier le bon fonctionnement du conteneur
//                 echo 'Test du conteneur en cours...'
//                 // Exemple: vérifier que l'application est bien déployée
//             }
//         }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Déploiement de l\'application...'
                // Ajouter des étapes de déploiement si nécessaire
            }
        }
    }

    post {
        always {
            script {
                // Nettoyer le conteneur et l'image Docker après exécution du pipeline
                bat "docker stop my-app-container || true"
                bat "docker rm my-app-container || true"
            }
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
        success {
            echo 'Le pipeline a réussi.'
        }
    }
}
