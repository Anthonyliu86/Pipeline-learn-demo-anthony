import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("fileExists") {
			steps{
			    json_file = "${env.WORKSPACE}/testdata/test_json.json"
				if(fileExists(json_file) == true) {
					echo("json file is exists")
				}else {
					error("here haven't find json file")
				}
			}
		}
	}
}


