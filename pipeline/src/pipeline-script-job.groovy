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
					    out = sh(script: "ls /tmp/test ", returnStdout: true).toString().trim()
					    println out
					    if(out.contains("Python-3.7.1.tgz")) {
						    println "file download successfully."
					    }else {
							sh("exit 1")
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
