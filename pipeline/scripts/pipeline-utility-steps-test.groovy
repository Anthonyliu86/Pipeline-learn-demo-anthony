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
		stage("read properties") {
			steps{
				script{
					properties_file = env.WORKSPACE + "/testdata/test.properties"
					model_test.read_properties(properties_file)
					println "================================"
				}
			}
		}
		stage("touch file") {
			steps{
				script{
					touch_file = env.WORKSPACE + "/testdata/"+ env.BUILD_NUMBER +".log"
					touch touch_file
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
                    age: 18
                    city: 'Shanghai'
                    isMale: false
                    name: 'Lucy'
                    """
					model_test.read_yaml_file(yaml_string)
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


