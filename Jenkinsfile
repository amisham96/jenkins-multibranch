peline {
    agent any 
    environment{
        imageName = '20.232.19.3:8085/candyshopapp'
        dockerImage = ''
    }
    // agent is where my pipeline will be eexecuted
    tools {
        //install the maven version configured as m2 and add it to the path
        maven "maven"
    }
    stages {
        stage('Pull From SCM') {
            steps {
            
            git branch: 'master', url: 'https://github.com/amisham96/RestApi_Spring_JPA.git'
            }
        }
        stage('Run Maven Build') {
            steps {
            sh "mvn clean package"
            }
            post {
                //if maven build was able to run the test we will create a test report and archive the jar in local machine
                success {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
     stage('Building image of the project'){
        steps{
            echo 'Starting to build docker image'
            script{
                dockerImage = docker.build imageName + ":$BUILD_NUMBER"
            }
        }
     }
     stage('Deploy Image to Nexus Repository'){
         steps{
            //  sh 'docker login -u admin -p Amit@1996 20.232.19.3:8085'
             script{
                 withDockerRegistry(credentialsId: 'nexus-cred', url: 'http://20.232.19.3:8085') {
                     dockerImage.push()
                 }
             }
         }
     }
    }
}
