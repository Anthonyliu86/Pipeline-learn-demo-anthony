import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		stage("writeFile demo") {
			steps{
				script {
					write_file_path = "${env.WORKSPACE}/testdata/write.txt"
					file_contents = "Hello Anthony!!"
					//write into write.txt
					writeFile write_file_path file_contents
					// read file and print it out
					fileContents = readFile write_file_path
					println fileContents
				}
			}
		}
	}
}
