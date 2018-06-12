package org.mifosplatform.portfolio.cgtgrt.domain;

import org.mifosplatform.portfolio.loanaccount.domain.LoanStatus;

public enum TaskStatus {
	    INVALID(0,"taskStatus.invalid"),
	    ACTIVE(100, "taskStatus.active"), //
	    PASS(200, "taskStatus.pass"), //
	    FAILED(300, "taskStatus.failed"), //
	    COMPLETED(400, "taskStatus.completed"); //
	    
	    private final Integer value;
	    private final String code;
	    private TaskStatus(final Integer value, final String code) {
	        this.value = value;
	        this.code = code;
	    }

	    public boolean hasStateOf(final LoanStatus state) {
	        return this.value.equals(state.getValue());
	    }

	    public Integer getValue() {
	        return this.value;
	    }

	    public String getCode() {
	        return this.code;
	    }


	    public static TaskStatus fromInt(final Integer statusValue) {

	    	TaskStatus enumeration = TaskStatus.INVALID;
	        switch (statusValue) {
	            case 100:
	                enumeration = TaskStatus.ACTIVE;
	            break;
	            case 200:
	                enumeration = TaskStatus.PASS;
	            break;
	            case 300:
	                enumeration = TaskStatus.FAILED;
	            break;
	            case 400:
	                enumeration = TaskStatus.COMPLETED;
	            break;
	          }
	        return enumeration;
	    }

	    
	   
}
