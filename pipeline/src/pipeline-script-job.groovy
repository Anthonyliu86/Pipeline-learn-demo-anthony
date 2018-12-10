import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		
		stage("git checkout") {
			steps{
				script {
					checkout([$class: 'GitSCM', branches: [[name: '*/master']],
						userRemoteConfigs: [[credentialsId: '6f4fa66c-eb02-46dc-a4b3-3a232be5ef6e', 
							url: 'https://github.com/Anthonyliu86/HelloWorld.git']]])
				}
			}
		}
		stage("Check file download") {
			steps {
				script {
					
					try{
					    out = sh(script: "[ -f /tmp/test1/Python-3.7.1.tgz ]  && echo 'true' || echo 'false' ", returnStdout: true)
					    println out
					    if(out == "true") {
						    println "file download successfully."
					    }
					} catch(Exception e) {
						println e
						error("fond error during check file download.")
					}
				}
			}
		}
	}
}
