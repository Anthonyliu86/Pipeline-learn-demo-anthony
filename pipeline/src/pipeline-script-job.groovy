import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER
println ${WORKSPACE}

pipeline{
	
	agent any
	stages{
		stage("init") {
			steps{
				script{
					model_test = load ${WORKSPACE} + "/pipeline/module/pipeline-demo-module.groovy"
				}
			}
		}
		stage("Test Method") {
			steps{
				script{
					log_files = model_test.find_files('**/*.log')
				}
			}
		}
	}
}


