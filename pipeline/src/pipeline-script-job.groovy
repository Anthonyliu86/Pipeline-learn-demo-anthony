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
		stage("write into yaml file") {
			steps{
				script{
					def amap = [name: 'Anthony',
                                age : 18,
                                city: 'Beijing',
                                isMale: true
								]
					yaml_file = env.WORKSPACE + "/testdata/new.yml"
					model_test.write_to_yaml(amap, yaml_file)
					println "the contents of yaml file are: "
					model_test.read_yaml_file(yaml_file)
				}
			}
		}
	}
}


