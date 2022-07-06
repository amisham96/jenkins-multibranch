peline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
             git branch: 'ansible', url: 'https://github.com/amisham96/jenkins-multibranch.git'

                // Run Maven on a Unix agent.
                sh "mvn clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts 'target/*.war'
                }
            }
        }
        stage('ansible deploy') {
           steps {
                // Get some code from a GitHub repository
                ansiblePlaybook credentialsId: 'ansible-cred', disableHostKeyChecking: true, inventory: 'dev.inv', playbook: 'playbook.yml'

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            } 
        }
        stage("Deploy war to tomcat"){
            steps{
                sh "scp /var/lib/jenkins/workspace/ansible-tomcat-pipeline/target/*.war root@ansjenkinsnode:/opt/tomcat9/apache-tomcat-9.0.64/webapps"
            }
        }

    }
}
