package org.mifosplatform.portfolio.rblvalidation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;
@SuppressWarnings("serial")
@Entity
@Table(name = "m_rblvalidatefile")
public class ValidatefileRecord extends AbstractAuditableCustom<AppUser, Long>{
	
	@Column(name = "center_id", nullable =false)
    private Long centerId;	
	
	@Column(name = "file_type", nullable =false)
    private String fileType;	
		    
    @Column(name = "file_name", nullable =false)
    private String fileName;
    
    @Column(name = "file_location", nullable =false)
     private String fileLocation;
    
	public ValidatefileRecord(Long centerId, String fileType, String fileName, String fileLocation) {
		super();
		this.centerId = centerId;
		this.fileType = fileType;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
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

    

}
