package org.mifosplatform.portfolio.rbl.service;

import java.util.Map;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.mifosplatform.portfolio.rbl.api.RblLoanDetailsApiConstant;
import org.mifosplatform.portfolio.rbl.domain.RblLoan;
import org.mifosplatform.portfolio.rbl.domain.RblLoanRepositoryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
@Service
public class RblLoanWritePlatformServiceImpl implements RblLoanWritePlatformService {
	
	private final static Logger logger = LoggerFactory.getLogger(RblLoanWritePlatformServiceImpl.class);

    private final PlatformSecurityContext context;
    private final RblLoanRepositoryWrapper rblLoanRepositoryWrapper;
      
    @Autowired
    public RblLoanWritePlatformServiceImpl(final PlatformSecurityContext context,
    		final RblLoanRepositoryWrapper rblLoanRepositoryWrapper)
            {
        this.context = context;
        this.rblLoanRepositoryWrapper=rblLoanRepositoryWrapper;
        	        
    }

	@Override
	public CommandProcessingResult createRblLoan(JsonCommand command) {
		try{
			   final Long loanId = command.longValueOfParameterNamed(RblLoanDetailsApiConstant.loanIdparamname);
		       final Integer pslcode = command.integerValueOfParameterNamed(RblLoanDetailsApiConstant.pslcodeparamName);
		       final Integer topupflag = command.integerValueOfParameterNamed(RblLoanDetailsApiConstant.topuploanfl);
		       final String  hospitalcash  =command.stringValueOfParameterNamed(RblLoanDetailsApiConstant.hospitalcashparamname);		
		       final String  prpaidcharfe  =command.stringValueOfParameterNamed(RblLoanDetailsApiConstant.prepaidchargeparamname);		

		       RblLoan rblLoan =RblLoan.craete(loanId, pslcode, topupflag, hospitalcash, prpaidcharfe);
		    	 this.rblLoanRepositoryWrapper.save(rblLoan);
		       return new CommandProcessingResultBuilder() //
		               .build();
		   } catch (final DataIntegrityViolationException dve) {
		       handleClientIdentifierDataIntegrityViolation(null, null,  dve);
		       return CommandProcessingResult.empty();
		   }
	}

	@Override
	public CommandProcessingResult updateRblLoan(Long rblLoanId, JsonCommand command) {
		try{
		    this.context.authenticatedUser();	
		    final  RblLoan rblLoanForUpdate = this.rblLoanRepositoryWrapper.findOneWithNotFoundDetection(rblLoanId);
		     if(rblLoanForUpdate!=null){
		    final Map<String, Object> changes = rblLoanForUpdate.update(command);
		        
		     this.rblLoanRepositoryWrapper.saveAndFlush(rblLoanForUpdate);
		     }
		        return new CommandProcessingResultBuilder() //
		                .withCommandId(command.commandId()) //	                    
		                .build();
		     
		    } catch (final DataIntegrityViolationException dve) {
		        return CommandProcessingResult.empty();
		    }
	}

	@Override
	public CommandProcessingResult deleteRblRblLoan(Long rblLoanId, JsonCommand command) {
		try{
			  this.context.authenticatedUser();	
			    final  RblLoan rblLoanForDelete = this.rblLoanRepositoryWrapper.findOneWithNotFoundDetection(rblLoanId);
		         this.rblLoanRepositoryWrapper.delete(rblLoanForDelete);
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
