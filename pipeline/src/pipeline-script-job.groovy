import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("init") {
			steps{
				script{
					model_test = load env.WORKSPACE + "/pipeline/module/pipeline-demo-module.groovy"
				}
			}
		}
		stage("write json") {
			steps{
				script{
					properties_file = env.WORKSPACE + "/testdata/test.properties"
					model_test.read_properties(properties_file)
					println "================================"
					properties = [name: 'Anthony', age: 18, city: 'Beijing']
					model_test.read_properties(properties)
				}
			}
		}
	}
}


