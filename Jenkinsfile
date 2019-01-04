@Library('anthony_share_lib') _

pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                script{
                    println "Build" 
                    def result =  TwoNumberAdd 3, 5
                    println result
                }
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
