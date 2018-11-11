import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("dir_echo_error_demo") {
			steps{
			    println env.WORKSPACE
			    dir("${env.WORKSPACE}/testdata"){
				    sh "pwd"
			    }
			    echo ("list all files under current workd directory")
			    sh("ls -al ${env.WORKSPACE}")
				echo("some info logs output")
				error("some error method output")
			}
		}
	}
}


