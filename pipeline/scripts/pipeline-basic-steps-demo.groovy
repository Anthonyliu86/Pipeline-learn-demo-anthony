import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("dir") {
			steps{
			    println env.WORKSPACE
			    dir("${env.WORKSPACE}/testdata"){
				    sh "pwd"
			    }
			    echo ("list all files under current workd directory")
			    sh("ls -al ${env.WORKSPACE}")
				def a = 9
				if(a == 10) {
					echo("a equal to 10")
				}else {
					error("a is not 10, but a should equal to 10")
				}
			}
		}
	}
}


