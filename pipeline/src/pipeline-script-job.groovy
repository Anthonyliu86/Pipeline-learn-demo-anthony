import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER
println env.WORKSPACE

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
		stage("Parse json") {
			steps{
				script{
					json_file = env.WORKSPACE + "/testdata/test_json.json"
					model_test.read_json_file(json_file)
					println "================================"
					json_string = '{"NAME":"Anthony","AGE":18,"CITY":"Beijing","GENDER":"male"}'
					model_test.read_json_file2(json_string)
				}
			}
		}
	}
}


