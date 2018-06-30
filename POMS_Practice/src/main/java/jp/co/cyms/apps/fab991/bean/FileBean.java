package jp.co.cyms.apps.fab991.bean;

public class FileBean {
	 private String type;
	 private String linkToFile;
	 private String fileName;
	 private String fileSize;
	 
	 public FileBean() {
		// TODO Auto-generated constructor stub
	}

	public FileBean(String type, String linkToFile, String fileName, String fileSize) {
		//super();
		this.type = type;
		this.linkToFile = linkToFile;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLinkToFile() {
		return linkToFile;
	}

	public void setLinkToFile(String linkToFile) {
		this.linkToFile = linkToFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	
	 
	
}
