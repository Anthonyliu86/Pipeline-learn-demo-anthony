import hudson.model.*;
import groovy.json.*;

def find_files(filetype) {
	
	def files = findFiles(glob:filetype)
	for (file in files) {
		println file.name
	}
}

def read_json_file(file_path) {
	def propMap = readJSON file : file_path
	propMap.each {
	    println ( it.key + " = " + it.value )
	}
}

def read_json_file2(json_string) {
	def propMap = readJSON text : json_string
	propMap.each {
		println ( it.key + " = " + it.value )
	}
}

def write_json_to_file(input_json, tofile_path) {
	def input = ''
	if(input_json.toString().endsWith(".json")) {
		input = readJSON file : input_json
	}else {
	//def jsonOutput = new JsonOutput()
        //def new_json_object = jsonOutput.toJson(input_json)
		def jsonSlurper = new JsonSlurper(input_json)
                def new_json_object = jsonSlurper.parseText
		input = new_json_object
	}
	writeJSON file: tofile_path, json: input
}

def read_properties(properties_file) {
	 def props = readProperties interpolate: true, file: properties_file
	 props.each {
		println ( it.key + " = " + it.value )
	 }
}

def read_yaml_file(yaml_file) {
	def datas = ""
	if(yaml_file.toString().endsWith(".yml")){
		datas = readYaml file : yaml_file
		
	}else {
		datas = readYaml text : yaml_file
	}
	datas.each {
		println ( it.key + " = " + it.value )
	 }
}

def write_to_yaml(map_data, yaml_path) {
	writeYaml file: yaml_path , data: map_data
}

def send_email_results(status,GITBranch,to_email_address_list) {
    def fileContents = readFile env.WORKSPACE + '/testdata/basic_style.css'
    def subject = "Jenkins Job : " + env.JOB_NAME + "/" + env.BUILD_ID + " has " +  status
    def result_url = env.BUILD_URL + "console"
    

    def text = """
  <html>
  <style type="text/css">
  <!--
  ${fileContents}
  -->
  </style>
  <body>
  <div id="content">
  <h1>Summary</h1>
  <div id="sum2">
      <h2>Jenkins Build</h2>
      <ul>
      <li>Job URL : <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></li>
       <li>Build Result URL : <a href='${result_url}'>${result_url}</a></li>
      </ul>
  </div>
  <div id="sum0">
  <h2>GIT Branch</h2>
  <ul>
  <li>${GITBranch}</li>
  </ul>
  </div>
  </div></body></html>
  """

    mail body: text, subject: subject,  mimeType: 'text/html', to: to_email_address_list
}
return this;
