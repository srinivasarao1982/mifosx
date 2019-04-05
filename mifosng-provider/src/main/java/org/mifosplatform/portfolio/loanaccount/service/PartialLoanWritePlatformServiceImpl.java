package org.mifosplatform.portfolio.loanaccount.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.data.OfficeData;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.office.domain.OfficeRepositoryWrapper;
import org.mifosplatform.organisation.office.domain.OrganasitionSequenceNumber;
import org.mifosplatform.organisation.office.domain.SequenceNumberRepository;
import org.mifosplatform.organisation.office.service.OfficeReadPlatformService;
import org.mifosplatform.organisation.staff.domain.Staff;
import org.mifosplatform.organisation.staff.domain.StaffRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.mifosplatform.portfolio.client.service.ClientbankDetailsWritePlatformServiceImpl;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.group.domain.GroupRepositoryWrapper;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanDataValidator;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoan;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepositoryWrapper;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanAlreadyActive;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProduct;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProductRepository;
import org.mifosplatform.portfolio.savings.domain.SavingsAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PartialLoanWritePlatformServiceImpl  implements PartialLoanWriteplatformService{
	 private final static Logger logger = LoggerFactory.getLogger(ClientbankDetailsWritePlatformServiceImpl.class);

	    private final PlatformSecurityContext context;
	    private final PartialLoanRepositoryWrapper partialLoanRepositoryWrapper;
	    private final PartialLoanDataValidator partialLoanDataValidator;
	    private final CodeValueRepositoryWrapper codeValueRepositoryWrapper;
	    private final OfficeRepositoryWrapper officeRepositoryWrapper;
	    private final ClientRepositoryWrapper clientRepositoryWrapper;
	    private final GroupRepositoryWrapper groupRepositoryWrapper;
	    private final StaffRepositoryWrapper staffRepositoryWrapper;
	    private final LoanProductRepository loanProductRepository;
	    private final SequenceNumberRepository sequenceNumberRepository;
	    private final OfficeReadPlatformService officeReadPlatformService;


	    
	    @Autowired
	    public PartialLoanWritePlatformServiceImpl(final PlatformSecurityContext context,
	            final ClientRepositoryWrapper clientRepositoryWrapper,final PartialLoanRepositoryWrapper partialLoanRepositoryWrapper,
	            final PartialLoanDataValidator partialLoanDataValidator,final CodeValueRepositoryWrapper codeValueRepositoryWrapper,
	            final OfficeRepositoryWrapper officeRepositoryWrapper,
	            final GroupRepositoryWrapper groupRepositoryWrapper,final StaffRepositoryWrapper staffRepositoryWrapper,
	            final LoanProductRepository loanProductRepository,
	            final SequenceNumberRepository sequenceNumberRepository,
	            final OfficeReadPlatformService officeReadPlatformService)
	            {
	        this.context = context;
	        this.clientRepositoryWrapper = clientRepositoryWrapper;
	        this.codeValueRepositoryWrapper=codeValueRepositoryWrapper;
	        this.partialLoanRepositoryWrapper=partialLoanRepositoryWrapper;
	        this.groupRepositoryWrapper=groupRepositoryWrapper;
	        this.staffRepositoryWrapper=staffRepositoryWrapper;
	        this.loanProductRepository=loanProductRepository;
	        this.officeRepositoryWrapper=officeRepositoryWrapper;
	        this.partialLoanDataValidator=partialLoanDataValidator;
	        this.sequenceNumberRepository=sequenceNumberRepository;
	        this.officeReadPlatformService=officeReadPlatformService;
	        
	    }
	    
	    @Transactional
	    @Override
	    public CommandProcessingResult createpartialLoan( final JsonCommand command) {

	        this.context.authenticatedUser();	        
	        partialLoanDataValidator.validateForCreateBankDetails(command);
            
	        try {

	            final Long clientId = command.longValueOfParameterNamed(PartialLoanApiConstant.clientidparamname);
	            final Client client = this.clientRepositoryWrapper.findOneWithNotFoundDetection(clientId);
	             Long groupId = command.longValueOfParameterNamed(PartialLoanApiConstant.groupidparamname);
	            if(groupId==0){
	            for(Group gropus:client.getGroups()){
	            	groupId =gropus.getId();
	            }
	            }
                final Group group=this.groupRepositoryWrapper.findOneWithNotFoundDetection(groupId);
                final Long staffId = command.longValueOfParameterNamed(PartialLoanApiConstant.loanofficeridparamname);	
                final Staff staff=this.staffRepositoryWrapper.findOneWithNotFoundDetection(staffId);
                final Long longpurposeId =command.longValueOfParameterNamed(PartialLoanApiConstant.loanpurposeparamname);
                CodeValue purpose=null;
                if(longpurposeId!=null){
	              purpose =this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(longpurposeId);
                }
	             final Long officeId =command.longValueOfParameterNamed(PartialLoanApiConstant.officeidparamname);		
                final Office office =this.officeRepositoryWrapper.findOneWithNotFoundDetection(officeId) ;
                final Long productId= command.longValueOfParameterNamed(PartialLoanApiConstant.productidparamname);
                final LoanProduct product=this.loanProductRepository.findOne(productId)	;	
                final BigDecimal principal=command.bigDecimalValueOfParameterNamed(PartialLoanApiConstant.principalparamname);
                final BigDecimal fixedEmi=command.bigDecimalValueOfParameterNamed(PartialLoanApiConstant.fixedemiAmountparamname);
                final long loantenure=command.longValueOfParameterNamed(PartialLoanApiConstant.loantenureparamname);
                 String rpdoNumber =command.stringValueOfParameterNamed(PartialLoanApiConstant.rpdonumberparamname);                
                final LocalDate submittedDate=command.localDateValueOfParameterNamed(PartialLoanApiConstant.submitteddateparamname);
                final CodeValue status=null;
                final boolean isfreashImport=command.booleanPrimitiveValueOfParameterNamed("freshImport");
                PartialLoan partialLoanforDelete=null;
                partialLoanforDelete= this.partialLoanRepositoryWrapper.findActiveLoansByLoanIdAndGroupId(clientId, groupId, 1,0);

                if(isfreashImport){
                    if(partialLoanforDelete!=null){
                this.partialLoanRepositoryWrapper.delete(partialLoanforDelete);
		        }
                }else{
                    if(partialLoanforDelete!=null){
                	throw new PartialLoanAlreadyActive(clientId);
                    }
                }
                 int isDisburse=0;
               //newly Added 
                 
                  ArrayList<OfficeData> rbloffices= (ArrayList<OfficeData>) this.officeReadPlatformService.retrieverblOffice((long) 35);
                  for(OfficeData off:rbloffices){
                  	if(office.getId()==off.getId()){
                        rpdoNumber=SeqNumber();
                  	}
                  }

                PartialLoan partialLoan =PartialLoan.createpartialloan(client, group, product,office,staff,purpose,rpdoNumber,principal,loantenure,fixedEmi,submittedDate.toDate(),status,null,1,isDisburse);
	            this.partialLoanRepositoryWrapper.save(partialLoan);
	            return new CommandProcessingResultBuilder() //
	                    .withCommandId(command.commandId()) //
	                    .withOfficeId(client.officeId()) //
	                    .withClientId(clientId) //
	                    .withEntityId(partialLoan.getId()) //
	                    .build();
	        } catch (final DataIntegrityViolationException dve) {
	            handleClientIdentifierDataIntegrityViolation(null, null,  dve);
	            return CommandProcessingResult.empty();
	        }
	    }
      
        @Transactional
        private synchronized String SeqNumber() {
          String extId = null;
          Long seqId = (long) 4;
          OrganasitionSequenceNumber organasitionSequenceNumber =
              this.sequenceNumberRepository.findOne(seqId);
          BigDecimal seqNumber = organasitionSequenceNumber.getSeqNumber();
          extId = seqNumber.toString();
      
          organasitionSequenceNumber.updateSeqNumber(seqNumber.add(new BigDecimal(1)));
          this.sequenceNumberRepository.saveAndFlush(organasitionSequenceNumber);
          return extId;
      
        }
	    @Transactional
	    @Override
	    public CommandProcessingResult updatePartialLoan( final Long clientId,final Long groupId,final JsonCommand command) {
           
	    	try{
	        this.context.authenticatedUser();	
	        final  PartialLoan partialLoanforUpdate = this.partialLoanRepositoryWrapper.findActiveLoansByLoanIdAndGroupId(clientId, groupId, 1,0);
             if(partialLoanforUpdate!=null){
	        this.partialLoanDataValidator.validateUpdateBankDetails(command);
            final Map<String, Object> changes = partialLoanforUpdate.update(command);
            
            /*if (changes.containsKey(PartialLoanApiConstant.loanofficeridparamname)) {

                final Long newValue = command.longValueOfParameterNamed(PartialLoanApiConstant.loanofficeridparamname);
                Staff newStaff = null;
                if (newValue != null) {
                    newStaff = this.staffRepositoryWrapper.findOneWithNotFoundDetection(newValue);
                            
                }
                partialLoanforUpdate.updateStaff(newStaff);
            }*/
           /* if (changes.containsKey(PartialLoanApiConstant.loanpurposeparamname)) {

                final Long newValue = command.longValueOfParameterNamed(PartialLoanApiConstant.loanpurposeparamname);
                CodeValue purpose = null;
                if (newValue != null) {
                	purpose = this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(newValue);
                            
                }
                partialLoanforUpdate.updateLoanPurpose(purpose);
            }*/
            if (changes.containsKey(PartialLoanApiConstant.statusparamname)) {

                final Long newValue = command.longValueOfParameterNamed(PartialLoanApiConstant.statusparamname);
                CodeValue status = null;
                if (newValue != null) {
                	status = this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(newValue);
                            
                }
                partialLoanforUpdate.updatestatus(status);
            }
            partialLoanforUpdate.updateisActive(0);

            
             this.partialLoanRepositoryWrapper.saveAndFlush(partialLoanforUpdate);
             }
	            return new CommandProcessingResultBuilder() //
	                    .withCommandId(command.commandId()) //	                    
	                    .withEntityId(partialLoanforUpdate.getId()) //
	                    .build();
             
	        } catch (final DataIntegrityViolationException dve) {
	            return CommandProcessingResult.empty();
	        }
	    }
	    @Transactional
	    @Override
	    public CommandProcessingResult deletePartialLoan(  final Long clientId,final Long groupId,final JsonCommand command ) {
           
	    	try{
	        this.context.authenticatedUser();	
	        final PartialLoan partialLoanforDelete = this.partialLoanRepositoryWrapper.findActiveLoansByLoanIdAndGroupId(clientId, groupId, 1,0);
             this.partialLoanRepositoryWrapper.delete(partialLoanforDelete);
	            return new CommandProcessingResultBuilder() //
	                    .withEntityId(partialLoanforDelete.getId()) //
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
