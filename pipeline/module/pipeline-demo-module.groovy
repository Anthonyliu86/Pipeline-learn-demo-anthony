import hudson.model.*;


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
	def propMap = readJSON string : json_string
	propMap.each {
		println ( it.key + " = " + it.value )
	}
}
return this;
