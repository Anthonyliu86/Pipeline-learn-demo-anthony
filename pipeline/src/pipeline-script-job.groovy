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
		stage("deleteDir") {
			steps{
				script{
					//crate a directory for test
					def create_dir_command = "${env.WORKSPACE}+/testdata/testdir"
					sh '${create_dir_command}'
				}
			}
		}
	}
}


