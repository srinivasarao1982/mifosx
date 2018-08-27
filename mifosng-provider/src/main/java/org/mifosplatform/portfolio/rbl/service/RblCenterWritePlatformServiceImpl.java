package org.mifosplatform.portfolio.rbl.service;

import java.math.BigDecimal;
import java.util.Map;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.office.domain.OfficeRepositoryWrapper;
import org.mifosplatform.organisation.staff.domain.Staff;
import org.mifosplatform.organisation.staff.domain.StaffRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.mifosplatform.portfolio.client.service.ClientbankDetailsWritePlatformServiceImpl;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.group.domain.GroupRepositoryWrapper;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanDataValidator;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoan;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepositoryWrapper;
import org.mifosplatform.portfolio.loanaccount.service.PartialLoanWriteplatformService;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProduct;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProductRepository;
import org.mifosplatform.portfolio.rbl.api.RblCenterDeatilsApiConstant;
import org.mifosplatform.portfolio.rbl.domain.RblCenter;
import org.mifosplatform.portfolio.rbl.domain.RblCenterRepositoryWrapper;
import org.mifosplatform.portfolio.rbl.domain.RblCustomer;
import org.mifosplatform.portfolio.rbl.exception.MandatoryParameterException;
import org.mifosplatform.portfolio.rbl.exception.MustbeBetweenException;
import org.mifosplatform.portfolio.rbl.exception.MustbeNumericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
@Service
public class RblCenterWritePlatformServiceImpl implements RblCenterWritePlatformService{
	 private final static Logger logger = LoggerFactory.getLogger(RblCenterWritePlatformServiceImpl.class);

	    private final PlatformSecurityContext context;
	    private final RblCenterRepositoryWrapper rblCenterRepositoryWrapper;
	      
	    @Autowired
	    public RblCenterWritePlatformServiceImpl(final PlatformSecurityContext context,
	            final RblCenterRepositoryWrapper rblCenterRepositoryWrapper)
	            {
	        this.context = context;
	        this.rblCenterRepositoryWrapper = rblCenterRepositoryWrapper;
	        	        
	    }

       @Override
     public CommandProcessingResult createRblCenter(JsonCommand command) {
    	   try{
    	   final Long centerId = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.centerIdparamname);
           final Long maximumIndividual = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.maximumindividualParamName);
             if(maximumIndividual!=null){
            	 String regex = "\\d+";
            	if(maximumIndividual.toString().matches(regex)){
            		if(maximumIndividual<0 && maximumIndividual>5000){
            			throw new MustbeBetweenException("maximumIndividual",1,5000);
            		}            		
            	}
            	else{
            		throw new MustbeNumericException("maximumIndividual");
            	}

             }
             else{
            	 throw new MandatoryParameterException("Maximum Individual");
             }
           final String meetingTime = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.meetingtimeParamName);	
            if(meetingTime==null){
           	 throw new MandatoryParameterException("Meeting Time");

            }
           final String houseNo =command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.housenumberParamname);
           if(houseNo==null){
          	 throw new MandatoryParameterException("House No");  
           }
           if(houseNo.length()<=0 &&houseNo.length()>500){
   			throw new MustbeBetweenException("House",1,500); 
           }
           final String  streetNo =command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.streetnumberParamname);		
           if(streetNo==null){
            	 throw new MandatoryParameterException("street No");  
             }
             if(streetNo.length()<=0 && houseNo.length()>500){
     			throw new MustbeBetweenException("street No",1,500); 
             }
           final String arelocality= command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.arealocalityParamName);
           if(arelocality==null){
          	 throw new MandatoryParameterException("arelocality");  
           }
           if(arelocality.length()<=0 && houseNo.length()>500){
   			throw new MustbeBetweenException("arelocality",1,500); 
           }
           final String landmark=command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.landmarkparamname);
           final String village=command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.villageparamname);
           if(village==null){
            	 throw new MandatoryParameterException("village");  
             }
             if(village.length()<=0 && village.length()>500){
     			throw new MustbeBetweenException("village",1,500); 
             }
           final String taluk=command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.talukparamname);
           final Long district =command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.districtparamname);                
           final Long state=command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.stateparamname);
           final Long pin=command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.pincodeparamName);
           if(pin!=null){
          	 String regex = "\\d+";
          	if(pin.toString().matches(regex)){
          		if(pin<0 && pin>6){
          			throw new MustbeBetweenException("pinCode",1,6);
          		}            		
          	}
          	else{
          		throw new MustbeNumericException("pinCode");
          	}

           }
           else{
          	 throw new MandatoryParameterException("pinCode");
           }
           RblCenter rblCenter =RblCenter.createrblcenter(centerId, maximumIndividual, meetingTime, houseNo, streetNo, arelocality, landmark, village, taluk, district, state,pin);
           this.rblCenterRepositoryWrapper.save(rblCenter);
           return new CommandProcessingResultBuilder() //
                   .withCommandId(rblCenter.getId()) //
                   .withClientId(rblCenter.getCenterId()) //
                   .build();
       } catch (final DataIntegrityViolationException dve) {
           handleClientIdentifierDataIntegrityViolation(null, null,  dve);
           return CommandProcessingResult.empty();
       } 
     }
          @Override
         public CommandProcessingResult updateRblCenter(Long rblcenterId, JsonCommand command) {
        	  try{
        		    this.context.authenticatedUser();	
                 //For Validation Purpose
        		    final Long maximumIndividual = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.maximumindividualParamName);
                    if(maximumIndividual!=null){
                   	 String regex = "\\d+";
                   	if(maximumIndividual.toString().matches(regex)){
                   		if(maximumIndividual<0 && maximumIndividual>5000){
                   			throw new MustbeBetweenException("maximumIndividual",1,5000);
                   		}            		
                   	}
                   	else{
                   		throw new MustbeNumericException("maximumIndividual");
                   	}

                    }
                    else{
                   	 throw new MandatoryParameterException("Maximum Individual");
                    }
                  final String meetingTime = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.meetingtimeParamName);	
                   if(meetingTime==null){
                  	 throw new MandatoryParameterException("Meeting Time");

                   }
                  final String houseNo =command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.housenumberParamname);
                  if(houseNo==null){
                 	 throw new MandatoryParameterException("House No");  
                  }
                  if(houseNo.length()<=0 &&houseNo.length()>500){
          			throw new MustbeBetweenException("House",1,500); 
                  }
                  final String  streetNo =command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.streetnumberParamname);		
                  if(streetNo==null){
                   	 throw new MandatoryParameterException("street No");  
                    }
                    if(streetNo.length()<=0 && houseNo.length()>500){
            			throw new MustbeBetweenException("street No",1,500); 
                    }
                  final String arelocality= command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.arealocalityParamName);
                  if(arelocality==null){
                 	 throw new MandatoryParameterException("arelocality");  
                  }
                  if(arelocality.length()<=0 && houseNo.length()>500){
          			throw new MustbeBetweenException("arelocality",1,500); 
                  }
                  final String landmark=command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.landmarkparamname);
                  final String village=command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.villageparamname);
                  if(village==null){
                   	 throw new MandatoryParameterException("village");  
                    }
                    if(village.length()<=0 && village.length()>500){
            			throw new MustbeBetweenException("village",1,500); 
                    }
                  final String taluk=command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.talukparamname);
                  final Long district =command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.districtparamname);                
                  final Long state=command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.stateparamname);
                  final Long pin=command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.pincodeparamName);
                  if(pin!=null){
                 	 String regex = "\\d+";
                 	if(pin.toString().matches(regex)){
                 		if(pin<0 && pin>6){
                 			throw new MustbeBetweenException("pinCode",1,6);
                 		}            		
                 	}
                 	else{
                 		throw new MustbeNumericException("pinCode");
                 	}

                  }
                  else{
                 	 throw new MandatoryParameterException("pinCode");
                  }

        		    
        		    final  RblCenter rblCenterforUpdate = this.rblCenterRepositoryWrapper.findOneWithNotFoundDetection(rblcenterId);
        		     if(rblCenterforUpdate!=null){
        		    final Map<String, Object> changes = rblCenterforUpdate.update(command);
        		        
        		     this.rblCenterRepositoryWrapper.saveAndFlush(rblCenterforUpdate);
        		     }
        		        return new CommandProcessingResultBuilder() //
        		                .withCommandId(command.commandId()) //	                    
        		                .withEntityId(rblCenterforUpdate.getId()) //
        		                .build();
        		     
        		    } catch (final DataIntegrityViolationException dve) {
        		        return CommandProcessingResult.empty();
        		    }
       }
      @Override
         public CommandProcessingResult deleteRblCenter(Long rblcenterId, JsonCommand command) {
    	  try{
    	        this.context.authenticatedUser();	
    		    final  RblCenter rblCenterforDelete = this.rblCenterRepositoryWrapper.findOneWithNotFoundDetection(rblcenterId);
    	         this.rblCenterRepositoryWrapper.delete(rblCenterforDelete);
    	            return new CommandProcessingResultBuilder() //
    	                    .withEntityId(rblCenterforDelete.getId()) //
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
