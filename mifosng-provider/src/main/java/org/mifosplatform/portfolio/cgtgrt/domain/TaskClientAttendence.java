package org.mifosplatform.portfolio.cgtgrt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;
@Entity
@Table(name = "m_taskAttendence")
public class TaskClientAttendence extends AbstractPersistable<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "task_id" , referencedColumnName = "id", nullable = false)
	private Tasks tasks;
	
	@ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
	
   @Column(name = "attendance_type_enum", nullable = false)
   private Integer attendanceTypeId;
   
   
   public static TaskClientAttendence createClientAttendance(final Tasks task ,final Client client, final Integer attendanceTypeId) {
       return new TaskClientAttendence(task,client, attendanceTypeId);
   }
   
  public TaskClientAttendence() {
	super();
}

public TaskClientAttendence(Tasks tasK, Client client, Integer attendanceTypeId) {
	super();
	this.tasks = tasK;
	this.client = client;
	this.attendanceTypeId = attendanceTypeId;
 }

 /*public Task getTasK() {
	return tasK;
 }

 public void setTasK(Task tasK) {
	this.tasK = tasK;
 }*/

 public Client getClient() {
	return client;
 }

public void setClient(Client client) {
	this.client = client;
}

public Integer getAttendanceTypeId() {
	return attendanceTypeId;
}

public void setAttendanceTypeId(Integer attendanceTypeId) {
	this.attendanceTypeId = attendanceTypeId;
}
   
    
}
