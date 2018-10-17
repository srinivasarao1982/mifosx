package org.mifosplatform.portfolio.rblvalidation.data;

import org.joda.time.LocalDate;

public class RblValidatefileData {
	
	public String centerName;
	public String fileType;
	public String fileName;
	public String fileLocation;
	public LocalDate filedate;
	
	public RblValidatefileData(String centerName, String fileType, String fileName, String fileLocation,
			LocalDate fileDate) {
		super();
		this.centerName = centerName;
		this.fileType = fileType;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
		this.filedate = fileDate;
	}
	


	public String getCenterName() {
		return centerName;
	}


	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileLocation() {
		return fileLocation;
	}


	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}


	public LocalDate getFileDate() {
		return filedate;
	}


	public void setFileDate(LocalDate fileDate) {
		this.filedate = fileDate;
	}
	
	
	

}
