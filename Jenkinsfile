@Library('anthony_share_lib') _

pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                println "Build" 
                println TwoNumberAdd 3, 5
            }
        }
        stage('Test') { 
            steps {
                println "Test" 
            }
        }
        stage('Deploy') { 
            steps {
                println "Deploy" 
            }
        }
    }
}
