pipeline {
  agent {
    docker {
      image 'pavanswaroopl/djmg:1'
      args '--user root -v /var/run/docker.sock:/var/run/docker.sock' 
    }
  }
  stages {
    stage('Checkout') {
      steps {
        sh 'echo passed'
       //no need since jenkins file present in same directory so it will automatically checakou tthe repo
      }
    }
    stage('Build and Test') {
      steps {
        sh 'ls -ltr'
        sh 'mvn clean package'
      }
    }
    stage('Static Code Analysis') {
      environment {
        SONAR_URL = "http://54.90.17.231:9000"
      }
      steps {
        withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
          sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
        }
      }
    }
    stage('Build and Push Docker Image') {
      environment {
        DOCKER_IMAGE = "pavanswaroopl/devopspipeline:${BUILD_NUMBER}"
        // DOCKERFILE_LOCATION = "java-maven-sonar-argocd-helm-k8s/spring-boot-app/Dockerfile"
        REGISTRY_CREDENTIALS = credentials('dockerCredentials')
      }
      steps {
        script {
            sh 'docker build -t ${DOCKER_IMAGE} .'
            def dockerImage = docker.image("${DOCKER_IMAGE}")
            docker.withRegistry('https://index.docker.io/v1/', "dockerCredentials") {
                dockerImage.push()
            }
        }
      }
    }
    
    stage('Update Deployment File') {
        environment {
            GIT_REPO_NAME = "DevOpsPipeline"
            GIT_USER_NAME = "pavanswaroopl"
        }
        steps {
            withCredentials([string(credentialsId: 'github', variable: 'GITHUB_TOKEN')]) {
              dir("${WORKSPACE}") {
              script{
                 sh 'pwd'
                  sh 'ls -al'
                sh '''
                    git config --global --add safe.directory /var/lib/jenkins/workspace/DevOpsPipeline
                    BUILD_NUMBER=${BUILD_NUMBER}
                    sed -i "s/replaceImageTag/${BUILD_NUMBER}/g" manifest/deployment.yml
                    cat manifest/deployment.yml
                    git add manifest/deployment.yml
                    git config user.email "pavanswaroop.l@gmail.com"
                    git config user.name "pavanswaroopl"
                    git commit -m "Update deployment image to version ${BUILD_NUMBER}"
                    git push https://${GITHUB_TOKEN}@github.com/${GIT_USER_NAME}/${GIT_REPO_NAME} HEAD:main
                '''
            }
            }
        }
        }
    }
  }
    post {
        always {
            script {
                sh 'docker system prune -af'
            }
        }
    }
}

