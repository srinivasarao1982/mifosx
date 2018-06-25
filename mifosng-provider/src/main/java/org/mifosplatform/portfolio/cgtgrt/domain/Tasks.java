package org.mifosplatform.portfolio.cgtgrt.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.staff.domain.Staff;
import org.mifosplatform.portfolio.cgtgrt.api.TaskApiConstant;
import org.mifosplatform.portfolio.cgtgrt.exception.FieldCannotbeBlankException;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.loanaccount.domain.LoanCharge;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProduct;
import org.mifosplatform.useradministration.domain.AppUser;
@SuppressWarnings("serial")
@Entity
@Table(name = "m_task") 
public class Tasks  extends AbstractAuditableCustom<AppUser, Long>{
		    
	@ManyToOne
	@JoinColumn(name = "center_id", nullable = false)
    private Group group;    
	    
	@ManyToOne
	@JoinColumn(name = "office_id", nullable = false)
    private Office office;
	
	@ManyToOne
	@JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
	@ManyToOne
	@JoinColumn(name = "task_type")
    private CodeValue tasktype;

    @Column(name = "note", length = 256)
    private String note;
    
    @Column(name = "task_date", nullable =false)
    private Date taskDate;
    
    @Column(name = "task_completed_date", nullable =false)
    private Date taskCompletedDate;
    
    
    
    @Column(name = "task_status", nullable =false)
    private Integer taskstatus;
    
    
   @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tasks", orphanRemoval = true)
    private Set<TaskDetails> taskdetails = new HashSet<>();
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tasks", orphanRemoval = true)
    private Set<TaskClientAttendence> taskClientAttendence = new HashSet<>();
    
    public static Tasks createtask( final Group group,final Office office,final Staff staff, final CodeValue tasktype,
    		 final String note ,final Date taskDate,final Date taskCompletedDate,final Integer taskstatus){
    	return new Tasks (group,office,staff,tasktype,note,taskDate,taskCompletedDate,taskstatus,null,null);
    }
    
    public Tasks() {
		super();
	}

	public Map<String, Object> update(final JsonCommand command) {

        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

       
        if (command.isChangeInStringParameterNamed(TaskApiConstant.noteparamname, this.note)) {
            final String newValue = command.stringValueOfParameterNamed(TaskApiConstant.noteparamname);
            actualChanges.put(TaskApiConstant.noteparamname, newValue);
            this.note = StringUtils.defaultIfEmpty(newValue, null);
        }
        if(command.longValueOfParameterNamed(TaskApiConstant.staffIdparamname)==null){
        	throw new FieldCannotbeBlankException(" staff ");

        }
        if (command.isChangeInLongParameterNamed(TaskApiConstant.staffIdparamname, this.staff.getId())) {
            final Long newValue = command.longValueOfParameterNamed(TaskApiConstant.staffIdparamname);
            actualChanges.put(TaskApiConstant.staffIdparamname, newValue);
        }
        if (command.isChangeInIntegerParameterNamed(TaskApiConstant.taskstatusparamname, this.taskstatus)) {
            final Integer newValue = command.integerValueOfParameterNamed(TaskApiConstant.taskstatusparamname);
            actualChanges.put(TaskApiConstant.taskstatusparamname, newValue);
        }
        if(command.integerValueOfParameterNamed(TaskApiConstant.taskstatusparamname)==null){
        	throw new FieldCannotbeBlankException(" Task Status");
        }
        if (command.isChangeInLongParameterNamed(TaskApiConstant.tasktypeparamname, this.tasktype.getId())) {
            final Long newValue = command.longValueOfParameterNamed(TaskApiConstant.tasktypeparamname);
            actualChanges.put(TaskApiConstant.tasktypeparamname, newValue);
        }
        
        if(command.integerValueOfParameterNamed(TaskApiConstant.tasktypeparamname)==null){
        	throw new FieldCannotbeBlankException(" Task Type");
        }
       
        final String dateFormatAsInput = command.dateFormat();
        final String localeAsInput = command.locale();
    
       return actualChanges;
    }
   public LocalDate getLastTransactionOnDate() {
        return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.taskCompletedDate), null);
    }

   public void  updatetaskdeatisl(final Set<TaskDetails> taskdetails){
	   if(this.taskdetails!=null){
	   this.taskdetails.clear();
	   this.taskdetails.addAll(taskdetails);
	   }else{
		   this.taskdetails=taskdetails;

	   }
  }
   
   public void  updateattendencedeatisl(final Set<TaskClientAttendence> taskClientAttendence){
	   if(this.taskClientAttendence!=null){
	   this.taskClientAttendence.clear();
		this.taskClientAttendence.addAll(taskClientAttendence);
	   }else{
			this.taskClientAttendence=taskClientAttendence;

	   }
	  }
   
   public void updateStatus(final Integer status){
	   this.taskstatus=status;
   }

   public void updateCompleteDate( final Date completedDate){
	   this.taskCompletedDate=completedDate;
   }
	public Tasks(Group group, Office office, Staff staff, CodeValue tasktype, String note, Date taskDate,Date taskCompletedDate,
			Integer taskstatus, Set<TaskDetails> taskdetails, Set<TaskClientAttendence> taskClientAttendence) {
		super();
		this.group = group;
		this.office = office;
		this.staff = staff;
		this.tasktype = tasktype;
		this.note = note;
		this.taskDate = taskDate;
		this.taskCompletedDate=taskCompletedDate;
		this.taskstatus = taskstatus;
		this.taskdetails = taskdetails;
		this.taskClientAttendence = taskClientAttendence;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public CodeValue getTasktype() {
		return tasktype;
	}

	public void setTasktype(CodeValue tasktype) {
		this.tasktype = tasktype;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public Integer getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(Integer taskstatus) {
		this.taskstatus = taskstatus;
	}

	/*public Set<TaskDetails> getTaskdetails() {
		return taskdetails;
	}

	public void setTaskdetails(Set<TaskDetails> taskdetails) {
		this.taskdetails = taskdetails;
	}*/

	/*public Set<TaskClientAttendence> getTaskClientAttendence() {
		return taskClientAttendence;
	}

	public void setTaskClientAttendence(Set<TaskClientAttendence> taskClientAttendence) {
		this.taskClientAttendence = taskClientAttendence;
	}*/

    
    
    

}
