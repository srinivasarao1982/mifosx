package org.mifosplatform.portfolio.rblvalidation.data;

import org.joda.time.LocalDate;

public class RblLosFileData {
	
	public LocalDate fileDate;
	public String fileName;
	public String filePath;
	public String fileType;
	
	
	public RblLosFileData(LocalDate fileDate, String fileName, String filePath, String fileType) {
		super();
		this.fileDate = fileDate;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
	}
	public LocalDate getFileDate() {
		return fileDate;
	}
	public void setFileDate(LocalDate fileDate) {
		this.fileDate = fileDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
		
	
	

}
