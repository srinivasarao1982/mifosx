package org.mifosplatform.portfolio.rblvalidation.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.joda.time.DateTime;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.documentmanagement.service.DocumentWritePlatformService;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.domain.OfficeRepositoryWrapper;
import org.mifosplatform.organisation.staff.domain.StaffRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.service.ClientbankDetailsWritePlatformServiceImpl;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.group.domain.GroupRepository;
import org.mifosplatform.portfolio.group.domain.GroupRepositoryWrapper;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanDataValidator;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepositoryWrapper;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProductRepository;
import org.mifosplatform.portfolio.rblvalidation.data.RblCenterValidateData;
import org.mifosplatform.portfolio.rblvalidation.data.RblGroupValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblLoanValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblSavingValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblclientDatValidation;
import org.mifosplatform.portfolio.rblvalidation.domain.ReceiveFileRecord;
import org.mifosplatform.portfolio.rblvalidation.domain.ReceiveFileRepository;
import org.mifosplatform.portfolio.rblvalidation.domain.ReceiveFileRepositoryWrapper;
import org.mifosplatform.portfolio.rblvalidation.domain.SendFileRecord;
import org.mifosplatform.portfolio.rblvalidation.domain.SendFileRepository;
import org.mifosplatform.portfolio.rblvalidation.domain.SendFileRepositoryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RblLosFileGenerationServiceImpl implements RblLosFileGenerationService {

	 private final static Logger logger = LoggerFactory.getLogger(RblLosFileGenerationServiceImpl.class);

	    private final PlatformSecurityContext context;
	    private final RblDataReadplatformService rblDataReadplatformService;	    
	    private final ReceiveFileRepositoryWrapper receiveFileRepositoryWrapper;
	    private final SendFileRepositoryWrapper sendFileRepositoryWrapper;
	    private final ReceiveFileRepository receiveFileRepository;
	    private final DocumentWritePlatformService documentWritePlatformService;
		private final GroupRepository groupRepository;

	    	    
	    @Autowired
	    public RblLosFileGenerationServiceImpl(final PlatformSecurityContext context,
	    		final RblDataReadplatformService rblDataReadplatformService,final SendFileRepository sendFileRepository,
	    		final ReceiveFileRepository receiveFileRepository,
	    		final ReceiveFileRepositoryWrapper receiveFileRepositoryWrapper,
	    		final SendFileRepositoryWrapper sendFileRepositoryWrapper,
	    		final DocumentWritePlatformService documentWritePlatformService,
	    		final GroupRepository groupRepository
	    		)
	            {
	        this.context = context;
	        this.rblDataReadplatformService=rblDataReadplatformService;	        
	        this.receiveFileRepositoryWrapper=receiveFileRepositoryWrapper;
	        this.sendFileRepositoryWrapper=sendFileRepositoryWrapper;
	        this.receiveFileRepository=receiveFileRepository;
	        this.documentWritePlatformService=documentWritePlatformService;
	    	this.groupRepository=groupRepository;

	         }

		@Override
		public void generateLosFile(String clientId, String centerId, String groupId,boolean centerDataTobeSent,boolean groupDataTobesend,boolean isreprocess,boolean isImagetobesent) {
			List<RblCenterValidateData> rblCenterValidateDatas =new ArrayList<RblCenterValidateData>();
			List<RblGroupValidationData>readRblGroupDatas=new ArrayList<RblGroupValidationData>();
			 if(isreprocess){
			if(centerDataTobeSent){
			// rblCenterValidateDatas =this.rblDataReadplatformService.readRblCenterData(centerId,groupId);
			 
			 String[]centerList=centerId.split(",");
	            String[]groupIdArrayList=groupId.split(",");
	            List<Long>groupIdList=new ArrayList<Long>();
	            String groupIdParameter=""; 
				for(int i=0;i<centerList.length;i++){
				   	List<Group>groupList =(List<Group>) this.groupRepository.findByParentId(Long.parseLong(centerList[i]));
				   	for(Group grouplistforIdea:groupList){
			        	groupIdList.add(grouplistforIdea.getId());
			        }
			       
				   	for(Long groupIdforComparasion:groupIdList ){
				   		for(int j=0;j<groupIdArrayList.length;j++){
				   			if(Long.parseLong(groupIdArrayList[j])==groupIdforComparasion){
				   				groupIdParameter=groupIdParameter+","+groupIdforComparasion;
				   			}
				   		}
				   		
				   	}
					List<RblCenterValidateData> rblcenterIntermediateDatas=this.rblDataReadplatformService.readRblCenterData(centerId,groupIdParameter);
					rblCenterValidateDatas.add(rblcenterIntermediateDatas.get(0));
					rblcenterIntermediateDatas.clear();
					groupIdParameter="";
				}
			 
			}
			
		     if(groupDataTobesend){
			readRblGroupDatas=this.rblDataReadplatformService.readRblGroupData(groupId);
		     }
			 }
			 else{
				 //rblCenterValidateDatas =this.rblDataReadplatformService.readRblCenterData(centerId,groupId);
				 String[]centerList=centerId.split(",");
		            String[]groupIdArrayList=groupId.split(",");
		            List<Long>groupIdList=new ArrayList<Long>();
		            String groupIdParameter=""; 
					for(int i=0;i<centerList.length;i++){
					   	List<Group>groupList =(List<Group>) this.groupRepository.findByParentId(Long.parseLong(centerList[i]));
					   	for(Group grouplistforIdea:groupList){
				        	groupIdList.add(grouplistforIdea.getId());
				        }
				       
					   	for(Long groupIdforComparasion:groupIdList ){
					   		for(int j=0;j<groupIdArrayList.length;j++){
					   			if(Long.parseLong(groupIdArrayList[j])==groupIdforComparasion){
					   				groupIdParameter=groupIdParameter+","+groupIdforComparasion;
					   			}
					   		}
					   		
					   	}
						List<RblCenterValidateData> rblcenterIntermediateDatas=this.rblDataReadplatformService.readRblCenterData(centerId,groupIdParameter);
						rblCenterValidateDatas.add(rblcenterIntermediateDatas.get(0));
						rblcenterIntermediateDatas.clear();
						groupIdParameter="";
				 
				 readRblGroupDatas=this.rblDataReadplatformService.readRblGroupData(groupId);

			 }
			 }
			List<RblclientDatValidation>readRblClientDatas=this.rblDataReadplatformService.readRblClientData(clientId);
			List<RblLoanValidationData>readRblLoanDatas=this.rblDataReadplatformService.readRblLoanData(clientId);
			List<RblSavingValidationData>readRblSavingDatas=this.rblDataReadplatformService.readRblSavingData(clientId);
       
			 String RBL_BASE_DIR = System.getProperty("user.home") + File.separator + ".mifosx"+File.separator+"RBLLosFile";
			    try {
				DateTime dt=new DateTime();
				File rblLosFile =new File (RBL_BASE_DIR,"NEXTRU_LOS__"+ dt.toString("yyyyMMdd")+"_"+dt.toString("HH")+"_"+dt.toString("mm")+".txt");
				FileWriter fr =null;
				fr =new FileWriter(rblLosFile);
				
				
				for(RblclientDatValidation rblclientDatValidation:readRblClientDatas){	
					StringBuilder customerData =new StringBuilder();
					if(rblclientDatValidation.getExternalId().startsWith("599")){
						customerData.append("NEWCUSTOMER~"+rblclientDatValidation.getExternalId()+"|") ;
					}
					else{
						customerData.append("EXISTINGCUSTOMER~"+(rblclientDatValidation.getExternalId())+"|");
					}
					String category="3";
					if(rblclientDatValidation.getCategory()!=null){
						if(rblclientDatValidation.getCategory().equalsIgnoreCase("SMALL FARMER")){
							category="1";
						}
                        if(rblclientDatValidation.getCategory().equalsIgnoreCase("FARMER")){
							category="2";

						}
                       if(rblclientDatValidation.getCategory().equalsIgnoreCase("Unemployed")){
                    	   category="4";	
						}
					}
					String extrafieldfour="WS4";
					if(rblclientDatValidation.getCaste().equalsIgnoreCase("03")||rblclientDatValidation.getCaste().equalsIgnoreCase("07")){
						extrafieldfour="WS2";
					}
                    if(rblclientDatValidation.getCaste().equalsIgnoreCase("06")||rblclientDatValidation.getCaste().equalsIgnoreCase("02")){
                    	extrafieldfour="WS1";
					}
                    if(rblclientDatValidation.getCaste().equalsIgnoreCase("08")||rblclientDatValidation.getCaste().equalsIgnoreCase("04")){
                    	extrafieldfour="WS3";
					}
					customerData.append(rblclientDatValidation.getExternalCenterId()).append("|").append(rblclientDatValidation.getTitle()).append("|");
					customerData.append(rblclientDatValidation.getCustomerName()).append("|").append(rblclientDatValidation.getAddressline1()).append("|");
					customerData.append(rblclientDatValidation.getAddressline2()).append("|").append(rblclientDatValidation.getAddressline3()).append("|");
					customerData.append(rblclientDatValidation.getCityCode()).append("|").append(rblclientDatValidation.getStateCode()).append("|");
					customerData.append(rblclientDatValidation.getPincode()).append("|").append(rblclientDatValidation.getDateofBirt()).append("|");
					customerData.append(rblclientDatValidation.getMobilNo()).append("|").append(rblclientDatValidation.getCaste()).append("|");
					customerData.append(rblclientDatValidation.getGender()).append("|").append(rblclientDatValidation.getMaritalStatus()).append("|");
					customerData.append(rblclientDatValidation.getNataonality()).append("|").append(rblclientDatValidation.getRelegion()).append("|");
					customerData.append(rblclientDatValidation.getMotherTounge()).append("|").append(rblclientDatValidation.getBranchCode()).append("|");
					customerData.append(rblclientDatValidation.getOperatingregionCode()).append("|").append(rblclientDatValidation.getAadharNo()).append("|");
					customerData.append(rblclientDatValidation.getPensioncardNo()).append("|").append(rblclientDatValidation.getRationCardNo()).append("|");
					customerData.append(rblclientDatValidation.getVoterId()).append("|").append(rblclientDatValidation.getPanno()).append("|");
					customerData.append(rblclientDatValidation.getBarcodeNumber()).append("|").append(rblclientDatValidation.getAdharseedingConsatnt()).append("|");
					customerData.append(rblclientDatValidation.getHealth()).append("|").append(rblclientDatValidation.getOccupation()).append("|");
					customerData.append(rblclientDatValidation.getEducationQualification()).append("|").append(category).append("|");
					customerData.append(rblclientDatValidation.getLanguage()).append("|").append(rblclientDatValidation.getCardIssueFlag()).append("|");
					customerData.append(rblclientDatValidation.getBcBranchCode()).append("|").append(rblclientDatValidation.getCollector()).append("|");
					customerData.append(rblclientDatValidation.getApprover()).append("|").append(rblclientDatValidation.getSpouseName()).append("|");
					customerData.append(rblclientDatValidation.getSpouseDateOfBirth()).append("|").append(rblclientDatValidation.getNomineeName()).append("|");
					customerData.append(rblclientDatValidation.getNomineeRelation()).append("|").append(rblclientDatValidation.getCbCheck()).append("|");
					customerData.append(rblclientDatValidation.getBankbranchName()).append("|").append(rblclientDatValidation.getBankAccountNo()).append("|");
					customerData.append(rblclientDatValidation.getBankbranchName()).append("|").append(rblclientDatValidation.getRenewalFl());					
					String extrafield=" |"+"|"+"|"+"|"+extrafieldfour+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|" ; 
					String actualfield=customerData.toString().replace("null", "" );
					fr.write(actualfield + extrafield);
					fr.write("\n");
					fr.write("\n");
				}
				
				for(RblLoanValidationData rblLoanValidationData:readRblLoanDatas){	
					StringBuilder customerData =new StringBuilder();
					    String inflag="IF1";
						customerData.append("LOAN~") ;
						if(rblLoanValidationData.getMaritalStatus()!=null){
						 if(rblLoanValidationData.getMaritalStatus().equalsIgnoreCase("Married")){
							 inflag="IF2"; 
						 }else{
							 inflag="IF1";
						 }
						}
						
						customerData.append(rblLoanValidationData.getExternalId()).append("|").append(rblLoanValidationData.getCustomerExternalId()).append("|");
						customerData.append(rblLoanValidationData.getCenterExtrenalId()).append("|").append(rblLoanValidationData.getGroupExternalId()).append("|");
						customerData.append(rblLoanValidationData.getLoanProductCode()).append("|").append(rblLoanValidationData.getLoanPurpose()).append("|");
						customerData.append(rblLoanValidationData.getPslcode()).append("|").append(rblLoanValidationData.getLoanAmount()).append("|");
						customerData.append(rblLoanValidationData.getDisbursementMode()).append("|").append(rblLoanValidationData.getNoofInstallments()).append("|");
						customerData.append(rblLoanValidationData.getRepaymentfrequency()).append("|").append(rblLoanValidationData.getLoanCycle()).append("|");
						customerData.append(rblLoanValidationData.getBarcodeNo()).append("|").append(rblLoanValidationData.getLoanStratDate()).append("|");
						customerData.append(rblLoanValidationData.getRepaymentStartDate()).append("|").append(rblLoanValidationData.getBcBranchCode()).append("|");
						customerData.append(rblLoanValidationData.getColector()).append("|").append(rblLoanValidationData.getApprover()).append("|");
						customerData.append(rblLoanValidationData.getExceptedDisbursementDate()).append("|").append(rblLoanValidationData.getTopUpLoanFlag()).append("|");
						customerData.append(rblLoanValidationData.getHosiptalCash()).append("|").append(rblLoanValidationData.getPrepaidCharge());
						String extrafield=" |"+"|"+"|"+"|"+"|"+"|"+inflag+"|"+rblLoanValidationData.getNomineeName()+"|"+rblLoanValidationData.getGurdianAddressline1()+"|"+rblLoanValidationData.getNomineeAddressline2()+"|"+rblLoanValidationData.getNomineeAddressline3()+"|"+rblLoanValidationData.getNomineeRlation()+"|"+rblLoanValidationData.getNomineeDateOfBirth()+"|"+
						rblLoanValidationData.getNomineeAge()+"|"+rblLoanValidationData.getNomineeGender()+"|"+"|"+"|"+"|"+"|"+rblLoanValidationData.getGurdianTitle()+"|"+rblLoanValidationData.getGurdianName()+"|"+rblLoanValidationData.getGurdianDateofBirth()+"|"+rblLoanValidationData.getGurdianGender()+"|"+rblLoanValidationData.getGurdianAddressline1()+"|"+rblLoanValidationData.getGurdianRelation()+"|"+"|"+"|"+"|"+"|"+"|" ; 
						String actualfield=customerData.toString().replace("null", "" );
						fr.write(actualfield + extrafield);
						fr.write("\n");
						fr.write("\n");
						inflag="";
		        }
				
				
				for(RblSavingValidationData rblSavingValidationData:readRblSavingDatas){	
					StringBuilder customerData =new StringBuilder();
					customerData.append("SAVING~") ;
					
					customerData.append(rblSavingValidationData.getExternalId()).append("|").append(rblSavingValidationData.getCustomerExternalId()).append("|");
					customerData.append(rblSavingValidationData.getCenterExternalId()).append("|").append(rblSavingValidationData.getAccountType()).append("|");
					customerData.append(rblSavingValidationData.getSavingproductCode()).append("|").append(rblSavingValidationData.getProductName()).append("|");
					customerData.append(rblSavingValidationData.getAccountOpeningDate()).append("|").append(rblSavingValidationData.getNomineeTitle()).append("|");
					customerData.append(rblSavingValidationData.getNomineeName()).append("|").append(rblSavingValidationData.getNomineeRlation()).append("|");
					customerData.append(rblSavingValidationData.getNomineeDateOfBirth()).append("|").append(rblSavingValidationData.getNomineeGender()).append("|");
					customerData.append(rblSavingValidationData.getNomineeAddressline1()).append("|").append(rblSavingValidationData.getNomineeAddressline2()).append("|");
					customerData.append(rblSavingValidationData.getNomineeAddressline3()).append("|").append(rblSavingValidationData.getNomineecity()).append("|");
					customerData.append(rblSavingValidationData.getNomineestate()).append("|").append(rblSavingValidationData.getNomineePincode()).append("|");
					customerData.append(rblSavingValidationData.getNomineeMinor()).append("|").append(rblSavingValidationData.getGurdianTitle()).append("|");
					customerData.append(rblSavingValidationData.getGurdianName()).append("|").append(rblSavingValidationData.getGurdianDateofBirth()).append("|");
					customerData.append(rblSavingValidationData.getGurdianGender()).append("|").append(rblSavingValidationData.getGurdianAddressline1()).append("|");
					customerData.append(rblSavingValidationData.getGurdianAddressline2()).append("|").append(rblSavingValidationData.getGurdianAddressline3()).append("|");
					customerData.append(rblSavingValidationData.getGurdianState()).append("|").append(rblSavingValidationData.getGurdianCity()).append("|");
					customerData.append(rblSavingValidationData.getGurdianRelation()).append("|").append(rblSavingValidationData.getGurdianPhoneNo()).append("|");
					customerData.append(rblSavingValidationData.getGurdianPincode()).append("|").append(rblSavingValidationData.getCollector()).append("|");
					customerData.append(rblSavingValidationData.getApprover());
					String extrafield=" |"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|" ; 
					String actualfield=customerData.toString().replace("null", "" );
					fr.write(actualfield + extrafield);					
					fr.write("\n");
					fr.write("\n");
	        }
	    
				for(RblGroupValidationData rblGroupValidationData:readRblGroupDatas){	
					StringBuilder customerData =new StringBuilder();

					customerData.append("GROUP~") ;
					
					customerData.append(rblGroupValidationData.getExternalId()).append("|").append(rblGroupValidationData.getCenterExternalId()).append("|");
					customerData.append(rblGroupValidationData.getCenterName()).append("|").append(rblGroupValidationData.getMaximuncenter()).append("|");
					customerData.append(rblGroupValidationData.getGroupName()).append("|").append(rblGroupValidationData.getGroupType()).append("|");
					customerData.append(rblGroupValidationData.getMinumNumber()).append("|").append(rblGroupValidationData.getMaximumNumber()).append("|");
					customerData.append(rblGroupValidationData.getFormationDate()).append("|").append(rblGroupValidationData.getMeetinTime()).append("|");
					customerData.append(rblGroupValidationData.getMeetingfrequency()).append("|").append(rblGroupValidationData.getDistancefromBranch()).append("|");
					customerData.append(rblGroupValidationData.getBranchCode()).append("|").append(rblGroupValidationData.getOperatingRegionCode());
					String extrafield=" |"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|" ; 
					String actualfield=customerData.toString().replace("null", "" );
					fr.write(actualfield + extrafield);
					fr.write("\n");
					fr.write("\n");
				}
	  
				for(RblCenterValidateData rblCenterValidateData:rblCenterValidateDatas){
					StringBuilder customerData =new StringBuilder();
					customerData.append("CENTER~") ;
					
					customerData.append(rblCenterValidateData.getExternalId()).append("|").append(rblCenterValidateData.getCenterName()).append("|");
					customerData.append(rblCenterValidateData.getFormationDate()).append("|").append(rblCenterValidateData.getServiceAgent()).append("|");
					customerData.append(rblCenterValidateData.getMaximumIndividual()).append("|").append(rblCenterValidateData.getMeetingTime()).append("|");
					customerData.append(rblCenterValidateData.getAddressline1()).append("|").append(rblCenterValidateData.getAddressline2()).append("|");
					customerData.append(rblCenterValidateData.getAddressLine3()).append("|").append(rblCenterValidateData.getCityCode()).append("|");
					customerData.append(rblCenterValidateData.getStateCode()).append("|").append(rblCenterValidateData.getPincode()).append("|");
					customerData.append(rblCenterValidateData.getOperatingRegionCode()).append("|").append(rblCenterValidateData.getBranchCode()).append("|");
					customerData.append(rblCenterValidateData.getDescription()).append("|").append(rblCenterValidateData.getPrimaryContact()).append("|");
					customerData.append(rblCenterValidateData.getPrimaryPhoneNumber()).append("|").append(rblCenterValidateData.getSecondaryContact()).append("|");
					customerData.append(rblCenterValidateData.getSecondaryPhoneNumner());
					String extrafield=" |"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|"+"|" ; 
					String actualfield=customerData.toString().replace("null", "" );
					fr.write(actualfield + extrafield);
					fr.write("\n");
					fr.write("\n");
	        }
				
			fr.close();	
			SendFileRecord sendFileRecord =new SendFileRecord("Send",rblLosFile.getName(),rblLosFile.getPath());
			this.sendFileRepositoryWrapper.save(sendFileRecord);
			List<Long>clientIdList=new ArrayList<Long>();
			for(String id:clientId.split(",")){
				clientIdList.add(Long.parseLong(id));
			}
			this.documentWritePlatformService.uploadDocumentToRemoteHost(rblLosFile.getName(), isreprocess,  isImagetobesent,clientIdList);		
			
		  }
		    catch(Exception e){
			    	
			    e.printStackTrace();
		   }
	  }
}

