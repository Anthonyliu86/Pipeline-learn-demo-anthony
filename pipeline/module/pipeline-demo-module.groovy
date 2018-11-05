import hudson.model.*;


def find_files(filetype) {
	
	def files = findFiles(glob:filetype)
	for (file : files) {
		println file.name
	}
}