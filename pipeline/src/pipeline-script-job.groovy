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
		stage("read yaml file") {
			steps{
				script{
					yaml_file = env.WORKSPACE + "/testdata/test.yml"
					model_test.read_yaml_file(yaml_file)
					println "=========================="
					yaml_string = """
                    name: 'Lucy'
					age : 18
					city: 'Shanghai'
					isMale: false
                    """
					model_test.read_yaml_file(yaml_string)
				}
			}
		}
	}
}


