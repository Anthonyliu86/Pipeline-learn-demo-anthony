import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("deleteDir") {
			steps{
				script{
					sh("ls -al ${env.WORKSPACE}")
					deleteDir()  // clean up current work directory
					sh("ls -al ${env.WORKSPACE}")
				}
			}
		}
	}
}


