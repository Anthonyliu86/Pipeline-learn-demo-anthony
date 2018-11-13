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
				def a = 9
				if(a == 10) {
					echo("a equal to 10")
				}else {
					error("a is not 10, but a should equal to 10")
				}
			}
		}
		stage('pwd demo') {
			step {
				script{
					sh("pwd")
					println "==========="
					println pwd()
				}
			}
		}
	}
	post{
		failure {
			script {
				module_test.send_email_results("Failed","Master","571072220@qq.com,904194906@qq.com")
			}
		}
		success {
			script {
				module_test.send_email_results("Success","Master","2048398933@qq.com")
			}
		}
	}
}


