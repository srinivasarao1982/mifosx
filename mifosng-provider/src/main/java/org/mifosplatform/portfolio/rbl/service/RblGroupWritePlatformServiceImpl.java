package org.mifosplatform.portfolio.rbl.service;

import java.util.Map;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.mifosplatform.portfolio.rbl.api.RblGroupDetailsApiConstant;
import org.mifosplatform.portfolio.rbl.domain.RblCustomer;
import org.mifosplatform.portfolio.rbl.domain.RblCustomerRepositoryWrapper;
import org.mifosplatform.portfolio.rbl.domain.RblGroup;
import org.mifosplatform.portfolio.rbl.domain.RblGroupRepositoryWrapper;
import org.mifosplatform.portfolio.rbl.exception.MandatoryParameterException;
import org.mifosplatform.portfolio.rbl.exception.MustbeBetweenException;
import org.mifosplatform.portfolio.rbl.exception.MustbeNumericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
@Service
public class RblGroupWritePlatformServiceImpl  implements RblGroupWritePlatformService{
	
	private final static Logger logger = LoggerFactory.getLogger(RblGroupWritePlatformServiceImpl.class);

    private final PlatformSecurityContext context;
    private final RblGroupRepositoryWrapper rblGroupRepositoryWrapper;
      
    @Autowired
    public RblGroupWritePlatformServiceImpl(final PlatformSecurityContext context,
    		final RblGroupRepositoryWrapper rblGroupRepositoryWrapper)
            {
        this.context = context;
        this.rblGroupRepositoryWrapper=rblGroupRepositoryWrapper;
        	        
    }

	@Override
	public CommandProcessingResult createRblGroup(JsonCommand command) {
		try{
			   final Long groupId=command.longValueOfParameterNamed(RblGroupDetailsApiConstant.groupIdparamname);
			   final Long maxcenter = command.longValueOfParameterNamed(RblGroupDetailsApiConstant.maximumcenterparamname);
			   if(maxcenter!=null){
	            	 String regex = "\\d+";
	            	if(maxcenter.toString().matches(regex)){
	            		if(maxcenter<0 && maxcenter>9999){
	            			throw new MustbeBetweenException("maxcenter",1,9999);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("maxcenter");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("maxcenter");
	             }
			   final Integer groupType = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.grouptypeparamname);
			   if(groupType!=null){
	            	 String regex = "\\d+";
	            	if(groupType.toString().matches(regex)){
	            		if(groupType<1 && groupType>2){
	            			throw new MustbeBetweenException("groupType",1,2);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("groupType");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("groupType");
	             }
			   final Integer minumNumber = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.minnumberparamname);
			   if(minumNumber!=null){
	            	 String regex = "\\d+";
	            	if(minumNumber.toString().matches(regex)){
	            		if(minumNumber<3 && minumNumber>4){
	            			throw new MustbeBetweenException("minum Number",3,4);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("minum Number");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("minum Number");
	             }
			   final Integer maxNumber = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.maxnumberparamname);
			   if(maxNumber!=null){
	            	 String regex = "\\d+";
	            	if(maxNumber.toString().matches(regex)){
	            		if(maxNumber<3 && maxNumber>10){
	            			throw new MustbeBetweenException("max Number",3,10);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("max Number");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("max Number");
	             }
			   final Integer distanceFromCenter = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.distancefrombranch);
			   if(distanceFromCenter!=null){
	            	 String regex = "\\d+";
	            	if(distanceFromCenter.toString().matches(regex)){
	            		if(distanceFromCenter<1 && distanceFromCenter>9999){
	            			throw new MustbeBetweenException("distance From Center",3,9999);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("distance From Center");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("distance From Center");
	             }
			   final String  meetingTime  =command.stringValueOfParameterNamed(RblGroupDetailsApiConstant.meetingtimeparamname);		
		       if(meetingTime==null){
	            	 throw new MandatoryParameterException("Meeting Time");  
		       }
		       RblGroup rblGroup =RblGroup.createRblgroup(groupId,maxcenter, groupType, minumNumber, maxNumber, distanceFromCenter, meetingTime);
		       this.rblGroupRepositoryWrapper.save(rblGroup);
		       return new CommandProcessingResultBuilder() //
		               .build();
		   } catch (final DataIntegrityViolationException dve) {
		       handleClientIdentifierDataIntegrityViolation(null, null,  dve);
		       return CommandProcessingResult.empty();
		   }
	}

	@Override
	public CommandProcessingResult updateRblGroup(Long rblgroupId, JsonCommand command) {
		try{
		    this.context.authenticatedUser();	
		    //for Validation Purpose
		    final Long maxcenter = command.longValueOfParameterNamed(RblGroupDetailsApiConstant.maximumcenterparamname);
			   if(maxcenter!=null){
	            	 String regex = "\\d+";
	            	if(maxcenter.toString().matches(regex)){
	            		if(maxcenter<0 && maxcenter>9999){
	            			throw new MustbeBetweenException("maxcenter",1,9999);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("maxcenter");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("maxcenter");
	             }
			   final Integer groupType = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.grouptypeparamname);
			   if(groupType!=null){
	            	 String regex = "\\d+";
	            	if(groupType.toString().matches(regex)){
	            		if(groupType<1 && groupType>2){
	            			throw new MustbeBetweenException("groupType",1,2);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("groupType");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("groupType");
	             }
			   final Integer minumNumber = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.minnumberparamname);
			   if(minumNumber!=null){
	            	 String regex = "\\d+";
	            	if(minumNumber.toString().matches(regex)){
	            		if(minumNumber<3 && minumNumber>4){
	            			throw new MustbeBetweenException("minum Number",3,4);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("minum Number");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("minum Number");
	             }
			   final Integer maxNumber = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.maxnumberparamname);
			   if(maxNumber!=null){
	            	 String regex = "\\d+";
	            	if(maxNumber.toString().matches(regex)){
	            		if(maxNumber<3 && maxNumber>10){
	            			throw new MustbeBetweenException("max Number",3,10);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("max Number");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("max Number");
	             }
			   final Integer distanceFromCenter = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.distancefrombranch);
			   if(distanceFromCenter!=null){
	            	 String regex = "\\d+";
	            	if(distanceFromCenter.toString().matches(regex)){
	            		if(distanceFromCenter<1 && distanceFromCenter>9999){
	            			throw new MustbeBetweenException("distance From Center",3,9999);
	            		}            		
	            	}
	            	else{
	            		throw new MustbeNumericException("distance From Center");
	            	}

	             }
	             else{
	            	 throw new MandatoryParameterException("distance From Center");
	             }
			   final String  meetingTime  =command.stringValueOfParameterNamed(RblGroupDetailsApiConstant.meetingtimeparamname);		
		       if(meetingTime==null){
	            	 throw new MandatoryParameterException("Meeting Time");  
		       }
          //
		    
		    final  RblGroup rblGroupForUpdate = this.rblGroupRepositoryWrapper.findOneWithNotFoundDetection(rblgroupId);
		     if(rblGroupForUpdate!=null){
		    final Map<String, Object> changes = rblGroupForUpdate.update(command);
		        
		     this.rblGroupRepositoryWrapper.saveAndFlush(rblGroupForUpdate);
		     }
		        return new CommandProcessingResultBuilder() //
		                .withCommandId(command.commandId()) //	                    
		                .build();
		     
		    } catch (final DataIntegrityViolationException dve) {
		        return CommandProcessingResult.empty();
		    }
	}

	@Override
	public CommandProcessingResult deleteRblGroup(Long rblgroupId, JsonCommand command) {
		try{
		  this.context.authenticatedUser();	
		    final  RblGroup rblGroupForDelete = this.rblGroupRepositoryWrapper.findOneWithNotFoundDetection(rblgroupId);
	         this.rblGroupRepositoryWrapper.delete(rblGroupForDelete);
	            return new CommandProcessingResultBuilder() //
	                    .build();
	        } catch (final DataIntegrityViolationException dve) {
	            return CommandProcessingResult.empty();
	        }
	    }
	    private void handleClientIdentifierDataIntegrityViolation(final String accountNumber, final String  ifsc,
	             final DataIntegrityViolationException dve) {

	        if (dve.getMostSpecificCause().getMessage().contains("unique_loan_identifier")) {
	            throw new DuplicateClientIdentifierException(accountNumber);
	        } 
	        logAsErrorUnexpectedDataIntegrityException(dve);
	        throw new PlatformDataIntegrityException("error.msg.loanIdentifier.unknown.data.integrity.issue",
	                "Unknown data integrity issue with resource.");
	    }

	    private void logAsErrorUnexpectedDataIntegrityException(final DataIntegrityViolationException dve) {
	        logger.error(dve.getMessage(), dve);
	    }


}
