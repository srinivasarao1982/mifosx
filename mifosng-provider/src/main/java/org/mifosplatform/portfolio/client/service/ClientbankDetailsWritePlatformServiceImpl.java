package org.mifosplatform.portfolio.client.service;

import java.math.BigDecimal;
import java.util.Map;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.api.ClientsBankDetailsApiConstants;
import org.mifosplatform.portfolio.client.data.ClientDataValidator;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientBankDetails;
import org.mifosplatform.portfolio.client.domain.ClientIdentifierRepository;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.ClientsBankDetailsRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientbankDetailsWritePlatformServiceImpl implements ClientbankDetailsWritePlatformService {

	 private final static Logger logger = LoggerFactory.getLogger(ClientbankDetailsWritePlatformServiceImpl.class);

	    private final PlatformSecurityContext context;
	    private final ClientRepositoryWrapper clientRepository;
	    private final ClientsBankDetailsRepositoryWrapper clientsBankDetailsRepositoryWrapper;
	    private final ClientDataValidator clientDataValidator;
	    
	    @Autowired
	    public ClientbankDetailsWritePlatformServiceImpl(final PlatformSecurityContext context,
	            final ClientRepositoryWrapper clientRepository, final ClientIdentifierRepository clientIdentifierRepository,
	            final ClientsBankDetailsRepositoryWrapper clientsBankDetailsRepositoryWrapper,
	            final ClientDataValidator clientDataValidator) {
	        this.context = context;
	        this.clientRepository = clientRepository;
	        this.clientsBankDetailsRepositoryWrapper=clientsBankDetailsRepositoryWrapper;
	        this.clientDataValidator= clientDataValidator;
	        
	    }

	    @Transactional
	    @Override
	    public CommandProcessingResult registerBankDetails( final JsonCommand command) {

	        this.context.authenticatedUser();	        
	        clientDataValidator.validateForCreateBankDetails(command);
            final String accountNumber =command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.accountnumberparamname);
            final String ifscCode=command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.ifsccodeparamname);

	        try {
	        	
	            final Long clientId = command.longValueOfParameterNamed(ClientsBankDetailsApiConstants.clientidparamname);
	            final Client client = this.clientRepository.findOneWithNotFoundDetection(clientId);
                final String benefeciaryName =command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.beneficiarynameparamname);
                final String bankName=command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.branchnameparamname);
                final String branchAddress=command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.branchaddressparamname);
                final BigDecimal lastTransactionAmount=command.bigDecimalValueOfParameterNamed(ClientsBankDetailsApiConstants.lasttransactionamountparamname);
                
	            ClientBankDetails clientBankDetails =ClientBankDetails.registerbankdetails(client, benefeciaryName, accountNumber, lastTransactionAmount, ifscCode, bankName, branchAddress, command);
	            this.clientsBankDetailsRepositoryWrapper.save(clientBankDetails);
	            return new CommandProcessingResultBuilder() //
	                    .withCommandId(command.commandId()) //
	                    .withOfficeId(client.officeId()) //
	                    .withClientId(clientId) //
	                    .withEntityId(clientBankDetails.getId()) //
	                    .build();
	        } catch (final DataIntegrityViolationException dve) {
	            handleClientIdentifierDataIntegrityViolation(accountNumber, ifscCode,  dve);
	            return CommandProcessingResult.empty();
	        }
	    }
	    
	    @Transactional
	    @Override
	    public CommandProcessingResult updateBankDetails( final Long bankdetailId ,final JsonCommand command) {
           
	    	try{
	        this.context.authenticatedUser();	
	        final ClientBankDetails ClientBankDetailsForUpdate = this.clientsBankDetailsRepositoryWrapper.findOneWithNotFoundDetection(bankdetailId);
	        clientDataValidator.validateUpdateBankDetails(command);

            final Map<String, Object> changes = ClientBankDetailsForUpdate.update(command);
             this.clientsBankDetailsRepositoryWrapper.saveAndFlush(ClientBankDetailsForUpdate);
	            return new CommandProcessingResultBuilder() //
	                    .withCommandId(command.commandId()) //	                    
	                    .withEntityId(ClientBankDetailsForUpdate.getId()) //
	                    .build();
	        } catch (final DataIntegrityViolationException dve) {
	            return CommandProcessingResult.empty();
	        }
	    }
	    
	    @Transactional
	    @Override
	    public CommandProcessingResult deleteBankDetails( final Long bankdetailId ) {
           
	    	try{
	        this.context.authenticatedUser();	
	        final ClientBankDetails ClientBankDetailsForDelete = this.clientsBankDetailsRepositoryWrapper.findOneWithNotFoundDetection(bankdetailId);
             this.clientsBankDetailsRepositoryWrapper.delete(ClientBankDetailsForDelete);
	            return new CommandProcessingResultBuilder() //
	                    .withEntityId(ClientBankDetailsForDelete.getId()) //
	                    .build();
	        } catch (final DataIntegrityViolationException dve) {
	            return CommandProcessingResult.empty();
	        }
	    }
	    
	    private void handleClientIdentifierDataIntegrityViolation(final String accountNumber, final String  ifsc,
	             final DataIntegrityViolationException dve) {

	        if (dve.getMostSpecificCause().getMessage().contains("unique_client_identifier")) {
	            throw new DuplicateClientIdentifierException(accountNumber);
	        } 
	        logAsErrorUnexpectedDataIntegrityException(dve);
	        throw new PlatformDataIntegrityException("error.msg.clientIdentifier.unknown.data.integrity.issue",
	                "Unknown data integrity issue with resource.");
	    }

	    private void logAsErrorUnexpectedDataIntegrityException(final DataIntegrityViolationException dve) {
	        logger.error(dve.getMessage(), dve);
	    }

}
