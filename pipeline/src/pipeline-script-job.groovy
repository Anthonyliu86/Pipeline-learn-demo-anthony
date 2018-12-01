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
					file_contents = "Hello Anthony!! 这是一个测试例子"
					//write into write.txt
					writeFile file: write_file_path, text: file_contents, encoding: "UTF-8"
					// read file and print it out
					fileContents = readFile file: write_file_path, encoding: "UTF-8"
					println fileContents
				}
			}
		}
		
		stage("catchError demo") {
			steps{
				catchError {
					fileContents = readFile file: "./hello.txt", encoding: "UTF-8"
					println fileContents
				}
				script {
					println "here had met some exections, please check"
				}
			}
		}
	}
}
