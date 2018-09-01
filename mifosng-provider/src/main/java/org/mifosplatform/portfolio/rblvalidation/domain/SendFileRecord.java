package org.mifosplatform.portfolio.rblvalidation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_rblsendfile")
public class SendFileRecord extends AbstractAuditableCustom<AppUser, Long>{
	
	@Column(name = "file_type", nullable =false)
    private Long fileType;	
		    
    @Column(name = "file_Name", nullable =false)
    private String fileName;
    
    @Column(name = "file_path", nullable =false)
    private String filePath;


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

	public SendFileRecord(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}

	

}
