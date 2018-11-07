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
					json_file = env.WORKSPACE + "/testdata/test_json.json"
					tojson_file = env.WORKSPACE + "/testdata/new_json.json"
					model_test.write_json_to_file(json_file,tojson_file)
					println "================================"
					json_string = '{"NAME":"Anthony","AGE":18,"CITY":"Beijing","GENDER":"male"}'
					tojson_file = env.WORKSPACE + "/testdata/new_json1.json"
					model_test.read_json_file2(json_string,tojson_file)
				}
			}
		}
	}
}


