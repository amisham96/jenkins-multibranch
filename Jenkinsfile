pipeline {
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
        stage('Checkstyle') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
        }
        stage('Checkstyle Report') {
            steps {
                recordIssues(tools: [checkStyle(pattern: 'target/checkstyle-result.xml')])
            }
        }
        stage('Code Coverage') {
            steps {
                jacoco()
            }
        }
        stage('SonarQube Analysis'){
            steps{
                dir("/var/lib/jenkins/workspace/static-code-analysis"){
                withSonarQubeEnv('sonarqube'){
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=candyshopapp -Dsonar.host.url=http://52.255.184.246:9000 -Dsonar.login=sqa_13948fa3dc8e6a0a9cbe053c03ed15558ca2f3b6'
                }
                }
            }
        }
        stage("Quality Gate"){
            steps{
                script{
                timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
                def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                if (qg.status != 'OK') {
                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
                }
                }
            }
            }
        }
        stage ('Upload jar to Nexus repository'){
          steps {
          nexusArtifactUploader(
          nexusVersion: 'nexus3',
          protocol: 'http',
          nexusUrl: '20.232.19.3:8081',
          groupId: 'candyshopapp',
          version: '0.0.1-SNAPSHOT',
          repository: 'maven-snapshots',
          credentialsId: 'nexus-cred',
          artifacts: [
            [artifactId: 'candyshopapp',
             classifier: '',
             file: 'target/candyshop-0.0.1-SNAPSHOT.jar',
             type: 'jar']
        ]
        )
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
