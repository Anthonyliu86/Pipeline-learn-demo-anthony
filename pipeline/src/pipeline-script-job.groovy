import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("init") {
			steps{
				script {
					json_file = "/testdata/test_json.json"
					file_contents = readFile json_file
					pringln file_contents
				}
			}
		}
	}
	
}
