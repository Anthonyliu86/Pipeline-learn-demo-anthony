import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("dir") {
			steps{
				script{
					println env.WORKSPACE
					sh("ls -al ${env.WORKSPACE}")
					dir("${env.WORKSPACE}/testdata")
					deleterDir()
					sh("ls -al ${env.WORKSPACE}")
				}
			}
		}
		stage("dir") {
			steps{
				println env.WORKSPACE
				dir("${env.WORKSPACE}/testdata"){
					sh "pwd"
				}
			}
		}
	}
}


