package org.mifosplatform.portfolio.cgtgrt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_taskdetails")
public class TaskDetails extends AbstractPersistable<Long>{
	
	@ManyToOne
    @JoinColumn(name = "task_id",referencedColumnName = "id",  nullable = false)
    private Tasks tasks;

	@ManyToOne
	@JoinColumn(name = "details_description")
    private CodeValue detailDescription;
	
	@Column(name = "description_value", length = 256)
	private String descriptionValue;
	
	public static TaskDetails createTaskDetails(final Tasks task,final CodeValue detailDescription,final String descriptionValue){
		return new TaskDetails(task,detailDescription,descriptionValue);
	}

	public TaskDetails() {
		super();
	}

	public TaskDetails(Tasks tasK, CodeValue detailDescription, String descriptionValue) {
		super();
		this.tasks = tasK;
		this.detailDescription = detailDescription;
		this.descriptionValue = descriptionValue;
	}

	public Tasks getTasK() {
		return tasks;
	}

	public void setTasK(Tasks tasK) {
		this.tasks = tasK;
	}

	public CodeValue getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(CodeValue detailDescription) {
		this.detailDescription = detailDescription;
	}

	public String getDescriptionValue() {
		return descriptionValue;
	}

	public void setDescriptionValue(String descriptionValue) {
		this.descriptionValue = descriptionValue;
	}
	
	
}
