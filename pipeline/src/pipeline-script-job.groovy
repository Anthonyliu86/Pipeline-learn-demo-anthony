import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER

pipeline{
	
	agent any
	stages{
		
		stage("git checkout") {
			steps{
				script {
					checkout([$class: 'GitSCM', branches: [[name: '*/master']],
						userRemoteConfigs: [[credentialsId: '6f4fa66c-eb02-46dc-a4b3-3a232be5ef6e', 
							url: 'https://github.com/Anthonyliu86/HelloWorld.git']]])
				}
			}
		}
		stage("Get failure node") {
			steps {
				script {
					
					try{
					    failure_node_list = []
					    out = sh(script:" ls | grep .status /tmp/test")
					    println out
					    lines = put.tokenize("\n")
					    for(line in lines) {
					        if(!line.contains("STARTED")) {
					             if(line.startWith("ANDROID")){
					                 failure_node_list.add("android")
					             }else if(line.startWith("IOS")){
					                 failure_node_list.add("ios")
					             }else if(line.startWith("DB")){
					                 failure_node_list.add("mysql")
					             }else if(line.startWith("TOMCAT")){
					                 failure_node_list.add("tomcat")
					             }else {
					                 println "unknow host type."
					             }

					        }

					    }
					    
					} catch(Exception e) {
						println e
						error("fond error during get failure node.")
					}
				}
			}
		}
	}
}
