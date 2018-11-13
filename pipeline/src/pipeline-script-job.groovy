import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("init") {
			steps{
				script {
					module_method = load env.WORKSP + "/pipeline/module/pipeline-demo-module.groovy"
					println "1 + 1 = 2"
				}
			}
		}
	}
	post{
	    failure {
	        script {
	            module_method.send_email_result("Failed","Master","571072220@qq.com,904194906@qq.com")
	        }
	    }
	    success {
	        script {
	            module_method.send_email_result("Success","Master","571072220@qq.com,904194906@qq.com")
	        }
	    }
	}
}
