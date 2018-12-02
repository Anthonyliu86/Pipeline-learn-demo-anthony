import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("Hello Demo") {
			steps{
				script {
					println "Hello Demo!"
				}
			}
		}
		
		stage("git checkout") {
			steps{
				script {
					checkout([$class: 'GitSCM', branches: [[name: '*/master']],
						userRemoteConfigs: [[credentialsId: '6f4fa66c-eb02-46dc-a4b3-3a232be5ef6e', 
							url: 'https://github.com/Anthonyliu86/HelloWorld.git']]])
				}
			}
		}
	}
}
