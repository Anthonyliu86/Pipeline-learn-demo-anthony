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
					def amap = [name: 'Anthony',
                                age : 18,
                                city: 'Beijing',
                                isMale: true
								]
					yaml_file = env.WORKSPACE + "/testdata/new.yml"
					model_test.write_to_yaml(amap, aml_file)
				}
			}
		}
	}
}


