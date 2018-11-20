package org.mifosplatform.portfolio.rblvalidation.data;

import java.util.Date;

import org.joda.time.DateTime;

public class RblNomineeData {
	private String title;
    private String name;
    private String relation;
    private Date dateOfBirth;
        
    
	public RblNomineeData(String title, String name, String relation, Date dateOfBirth) {
		super();
		this.title = title;
		this.name = name;
		this.relation = relation;
		this.dateOfBirth = dateOfBirth;		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
