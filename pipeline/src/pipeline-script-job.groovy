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
					    out = sh(script:" ls /tmp/test | grep .status ").toString().trim()
					    println out
					    lines = out.tokenize("\n")
					    for(line in lines) {
					        if(!line.contains("STARTED")) {
					             if(line.startsWith("ANDROID")){
					                 failure_node_list.add("android")
					             }else if(line.startsWith("IOS")){
					                 failure_node_list.add("ios")
					             }else if(line.startsWith("DB")){
					                 failure_node_list.add("mysql")
					             }else if(line.startsWith("TOMCAT")){
					                 failure_node_list.add("tomcat")
					             } else if(line.startsWith("WEB")){
					                 failure_node_list.add("ngix")
					             }else {
					                 println "unknow host type."
					             }

					        }

					    }
					    println failure_node_list.toString()
					    
					} catch(Exception e) {
						println e
						error("fond error during get failure node.")
					}
				}
			}
		}
	}
}
