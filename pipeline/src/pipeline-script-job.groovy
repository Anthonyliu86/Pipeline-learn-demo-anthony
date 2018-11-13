import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("init") {
			steps{
				script {
					module_test = load env.WORKSPACE + "/pipeline/module/pipeline-demo-module.groovy"
					println "1 + 1 = 2"
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
