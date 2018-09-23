package org.mifosplatform.portfolio.rblvalidation.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.rblvalidation.data.RblCenterValidateData;
import org.mifosplatform.portfolio.rblvalidation.data.RblGroupValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.mifosplatform.portfolio.rblvalidation.data.RblSavingValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblLoanValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblclientDatValidation;
import org.mifosplatform.portfolio.rblvalidation.domain.ValidateRblFileRepository;
import org.mifosplatform.portfolio.rblvalidation.domain.ValidatefileRecord;




@Service
public class RblDataValidatorServiceImpl implements RblDataValidatorService{
	
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;
	private final RblDataReadplatformService rblDataReadplatformService;
	private final ValidateRblFileRepository validateRblFileRepository;

	@Autowired
	public RblDataValidatorServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource,
			final RblDataReadplatformService rblDataReadplatformService,
			final ValidateRblFileRepository validateRblFileRepository) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
		this.rblDataReadplatformService=rblDataReadplatformService;
		this.validateRblFileRepository= validateRblFileRepository;
	}

	@Override
	public void validateDatafortransfer(String rblcenterId, String groupId, String clientId) {
	    String RBL_BASE_DIR = System.getProperty("user.home") + File.separator + ".mifosx"+File.separator+"RblValidationFile";
	    try {
		List<RblCenterValidateData> rblcenterDatas=this.rblDataReadplatformService.readRblCenterData(rblcenterId,groupId);		
		File rblcentervalidatefile =new File (RBL_BASE_DIR,rblcenterDatas.get(0).getExternalId()+"validatefile"+new DateTime().toString("ddmmyyyhhmmss")+".txt");
		FileWriter fr =null;		 
		fr =new FileWriter(rblcentervalidatefile);
		
		fr.write("===========Start Writing for Center Data============"+"\n");

		for(RblCenterValidateData rblcenterData: rblcenterDatas){
			
			if (rblcenterData.getCenterName()!=null){
				if(!((rblcenterData.getCenterName().length()>0) && (rblcenterData.getCenterName().length()<=40))){
					fr.write("center Name length must be between 0 to 40" +"\n");
				}
			}
			else{
				fr.write("Center Name Cannot be Null  and length must be greater than 0 and lest equal to 40"+"\n");
			}
			if(rblcenterData.getExternalId()!=null){
				if(!((rblcenterData.getExternalId().length()>0) && (rblcenterData.getExternalId().length()<=20))){
					fr.write("center External Id length must be between 0 to 20" +"\n");
				}
			}
			else{
				fr.write("Center External Cannot be Null  and length must be greater than 0 and lest equal to 40"+"\n");
			}
			
			if(rblcenterData.getFormationDate()==null){
					fr.write("center Formation Date Cannot be Null" +"\n");				
			}
			
			if(rblcenterData.getServiceAgent()!=null){
				if(!((rblcenterData.getServiceAgent().length()>0) && (rblcenterData.getServiceAgent().length()<40))){
					fr.write("Center Service Agent length must be greater than 0 and less equal to 40"+"\n");
				}
			}				
			else{
				fr.write("Center Service Agent/Stff Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getMaximumIndividual()!=null){
				if(!((rblcenterData.getMaximumIndividual()>0) && (rblcenterData.getMaximumIndividual()<5000))){
					fr.write("Maximum Individual must be greater than 0 and less equal to 5000"+"\n");
				}
			}				
			else{
				fr.write("Maximum Individual Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getMeetingTime()==null){
				fr.write("Meeting Time Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getAddressline1()!=null){
				if(!((rblcenterData.getAddressline1().length()>0) && (rblcenterData.getAddressline1().length()<500))){
					fr.write("Address Line 1 must be greater than 0 and less equal to 500"+"\n");
				}
			}else{
				fr.write("Address Line 1 Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getAddressline2()!=null){
				if(!((rblcenterData.getAddressline2().length()>0) && (rblcenterData.getAddressline2().length()<500))){
					fr.write("Address Line 2 must be greater than 0 and less equal to 500"+"\n");
				}
			}
			
			if(rblcenterData.getAddressLine3()!=null){
				if(!((rblcenterData.getAddressLine3().length()>0) && (rblcenterData.getAddressLine3().length()<500))){
					fr.write("Address Line 3 must be greater than 0 and less equal to 500"+"\n");
				}
			}
			
			if(rblcenterData.getCityCode()!=null){
				if(!((rblcenterData.getCityCode().length()>0) && (rblcenterData.getCityCode().length()<=6))){
					fr.write("City Code Must be greater than 0 and less equal to 6"+"\n");
				}
			}else{
				fr.write("City Code  Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getStateCode()!=null){
				if(!((rblcenterData.getStateCode().length()>0) && (rblcenterData.getStateCode().length()<=6))){
					fr.write(" State Code Must be greater than 0 and less equal to 6"+"\n");
				}
			}else{
				fr.write("State Code  Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getPincode()!=null){
				if(!((rblcenterData.getPincode().toString().length()>0) && (rblcenterData.getPincode().toString().length()<=6))){
					fr.write(" PinCode Code Must be greater than 0 and less equal to 6"+"\n");
				}
			}else{
				fr.write(" Pin Code  Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getOperatingRegionCode()!=null){
				if(!((rblcenterData.getOperatingRegionCode().length()>0) && (rblcenterData.getOperatingRegionCode().length()<=6))){
					fr.write(" Operating Region Code Must be Must be greater than 0 and less equal to 6"+"\n");
				}
			}else{
				fr.write(" Operating Region Code  Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getBranchCode()!=null){
				if(!((rblcenterData.getBranchCode().length()>0) && (rblcenterData.getBranchCode().length()<=6))){
					fr.write(" Branch Code Must be Must be greater than 0 and less equal to 6"+"\n");
				}
			}else{
				fr.write(" Operating Region Code  Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getDescription()!=null){
				if(!((rblcenterData.getDescription().length()>0) && (rblcenterData.getDescription().length()<=30))){
					fr.write(" Description be Must be greater than 0 and less equal to 30"+"\n");
				}
			}
			
			if(rblcenterData.getPrimaryContact()!=null){
				if(!((rblcenterData.getPrimaryContact().length()>0) && (rblcenterData.getPrimaryContact().length()<=256))){
					fr.write(" Primary Contact Must be Must be greater than 0 and less equal to 256"+"\n");
				}
			}else{
				fr.write(" Primary Contact   Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getPrimaryPhoneNumber()!=null){
				if(!((rblcenterData.getPrimaryPhoneNumber().toString().length()>0) && (rblcenterData.getPrimaryPhoneNumber().toString().length()<=10))){
					fr.write(" Primary Contact Number   length Must be  10"+"\n");
				}
			}else{
				fr.write(" Primary Contact  Number Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getSecondaryContact()!=null){
				if(!((rblcenterData.getSecondaryContact().length()>0) && (rblcenterData.getSecondaryContact().length()<=256))){
					fr.write(" Secondary Contact Must be Must be greater than 0 and less equal to 256"+"\n");
				}
			}else{
				fr.write(" Secondary Contact   Cannot be Null"+"\n");
			}
			
			if(rblcenterData.getSecondaryPhoneNumner()!=null){
				if(!((rblcenterData.getSecondaryPhoneNumner().toString().length()>0) && (rblcenterData.getSecondaryPhoneNumner().toString().length()<=10))){
					fr.write(" Secondary Phone Number   length Must be  10"+"\n");
				}
			}else{
				fr.write(" Secondary Phone   Number Cannot be Null"+"\n");
			}
		}
//======================================== Start Validating Group Data================================================//
		
		fr.write("=====================Start Writing group Data====================================="+"\n");

		
		List<RblGroupValidationData> rblgroupDatas=this.rblDataReadplatformService.readRblGroupData(groupId);
		
           for(RblGroupValidationData rblgroupData: rblgroupDatas){			
			if (rblgroupData.getGroupName()!=null){
				if(!((rblgroupData.getGroupName().length()>0) && (rblgroupData.getGroupName().length()<=40))){
					fr.write(rblgroupData.getGroupName() +"   group Name length must be between 0 to 40" +"\n");
				}
			}
			else{
				fr.write(rblgroupData.getGroupName() +"   Group Name Cannot be Null  and length must be greater than 0 and lest equal to 40"+"\n");
			}
			
			if(rblgroupData.getExternalId()!=null){
				if(!((rblgroupData.getExternalId().length()>0) && (rblgroupData.getExternalId().length()<=20))){
					fr.write(rblgroupData.getExternalId() +"   group External Id length must be between 0 to 20" +"\n");
				}
			}else{
				fr.write(rblgroupData.getExternalId() +"   group External Id  Cannot be Null length must be between 0 to 20" +"\n");
			}
			
			if(rblgroupData.getCenterName()!=null){
				if(!((rblgroupData.getCenterName().length()>0) && (rblgroupData.getCenterName().length()<=40))){
					fr.write(rblgroupData.getCenterName() +"   group Center Name length must be between 0 to 40" +"\n");
				}
			}else{
				fr.write(rblgroupData.getCenterName() +"   group Center  Cannot be Null length must be between 0 to 40" +"\n");
			}
			
			if(rblgroupData.getMaximuncenter()!=null){
				if(!((rblgroupData.getMaximuncenter().length()>0) && (rblgroupData.getMaximuncenter().length()<=9999))){
					fr.write(rblgroupData.getCenterName() +"  Maximum Center length must be between 0 to 4" +"\n");
				}
			}else{
				fr.write(rblgroupData.getCenterName() +"  Maximum Center Cannot be Null length must be between 0 to 4" +"\n");
			}
			
			if(rblgroupData.getMaximumNumber()!=null){
				if(!((rblgroupData.getMaximumNumber()>3) && (rblgroupData.getMaximumNumber()<=10))){
					fr.write(rblgroupData.getMaximumNumber() +"  Maximum Number must be between 3 to 10" +"\n");
				}
			}else{
				fr.write(rblgroupData.getMaximumNumber() +"  Maximum Number Cannot be Null length must be between 3 to 10" +"\n");
			}
			
			if(rblgroupData.getMinumNumber()!=null){
				if(!((rblgroupData.getMinumNumber()>3) && (rblgroupData.getMinumNumber()<=4))){
					fr.write(rblgroupData.getMinumNumber() +"  Minimum Number must be between 3 to 4" +"\n");
				}
			}else{
				fr.write(rblgroupData.getMinumNumber() +"   Minimum Number Cannot be Null length must be between 3 to 4" +"\n");
			}
			
			if(rblgroupData.getFormationDate()==null){				
				fr.write(rblgroupData.getFormationDate() +"  Formation Date Cannot be Null"+ "\n");
			}
			
			if(rblgroupData.getMeetinTime()==null){				
				fr.write(rblgroupData.getMeetinTime() +"  Meeting Time Cannot be Null "+"\n");
			}
			
			if(rblgroupData.getMeetingfrequency()==null){				
				fr.write(rblgroupData.getMeetingfrequency() +"  Meeting Frequency Cannot be Null " +"\n");
			}
			
			if(rblgroupData.getDistancefromBranch()==null){				
				fr.write(rblgroupData.getDistancefromBranch() +"   Distance From Branch Cannot be Null " +"\n");
			}
			
			if(rblgroupData.getBranchCode()==null){				
				fr.write(rblgroupData.getBranchCode() +"   Branch Code Cannot Be Null " +"\n");
			}
			
			if(rblgroupData.getOperatingRegionCode()==null){				
				fr.write(rblgroupData.getOperatingRegionCode() +"   Operating Region Code Cannot Be Null " +"\n");
			}
           }	
    //=========================================	Savings Data Validation===================================//
           
			fr.write("==========================================Start Writing savings Data==================================== " +"\n");

           
           List<RblSavingValidationData> rblsavingsDatas=this.rblDataReadplatformService.readRblSavingData(clientId);
   		
           for(RblSavingValidationData rblsavingsData: rblsavingsDatas){			
			if (rblsavingsData.getExternalId()!=null){
				if(!((rblsavingsData.getExternalId().length()>0) && (rblsavingsData.getExternalId().length()<=20))){
					fr.write(rblsavingsData.getExternalId() +"  Savings External Id length must be between 0 to 20" +"\n");
				}
			}
			else{
				fr.write(rblsavingsData.getExternalId() +"  Savings External Id Cannot be Null  and length must be greater than 0 and lest equal to 20"+"\n");
			}
			
			if (rblsavingsData.getCustomerExternalId()!=null){
				if(!((rblsavingsData.getCustomerExternalId().length()>0) && (rblsavingsData.getCustomerExternalId().length()<=20))){
					fr.write(rblsavingsData.getCustomerExternalId() +"  Savings External  Customer Id length must be between 0 to 20" +"\n");
				}
			}
			else{
				fr.write(rblsavingsData.getCustomerExternalId() +"  Savings External Customer Id Cannot be Null  and length must be greater than 0 and lest equal to 20"+"\n");
			}
			
			if (rblsavingsData.getCenterExternalId()!=null){
				if(!((rblsavingsData.getCenterExternalId().length()>0) && (rblsavingsData.getCenterExternalId().length()<=40))){
					fr.write(rblsavingsData.getCenterExternalId() +"  Savings External  Customer Id length must be between 0 to 40" +"\n");
				}
			}
			else{
				fr.write(rblsavingsData.getCenterExternalId() +"   Savings External Customer Id Cannot be Null  and length must be greater than 0 and lest equal to 40"+"\n");
			}
			
			if (rblsavingsData.getAccountType()!=null){
				if(!((Integer.parseInt(rblsavingsData.getAccountType())>0) && (Integer.parseInt(rblsavingsData.getAccountType())<=4))){
					fr.write(rblsavingsData.getAccountType() +"   Savings Account Type   must be between 0 to 4" +"\n");
				}
			}
			else{
				fr.write(rblsavingsData.getAccountType() +"   Savings Account Type Cannot  Cannot be Null  and length must be greater than 0 and lest equal to 40"+"\n");
			}
			
			if (rblsavingsData.getProductName()==null){
					fr.write(rblsavingsData.getProductName() +"  Savings Product Name  cannot be Null"+"\n");
				
			}
			
			if (rblsavingsData.getAccountOpeningDate()==null){
				fr.write(rblsavingsData.getAccountOpeningDate() +"  Account Openong Date   cannot be Null"+"\n");
		      }
			
			if (rblsavingsData.getNomineeName()==null){
				fr.write(rblsavingsData.getNomineeName() +"   Nominee Name   cannot be Null"+"\n");
		      }
			
			if (rblsavingsData.getNomineeRlation()==null){
				fr.write(rblsavingsData.getNomineeRlation() +"  Nominee Relation   cannot be Null"+"\n");
		      }
			if (rblsavingsData.getNomineeDateOfBirth()==null){
				fr.write(rblsavingsData.getNomineeDateOfBirth() +"  Nominee Date Of Birth   cannot be Null"+"\n");
		      }
			if (rblsavingsData.getNomineeAddressline1()==null){
				fr.write(rblsavingsData.getNomineeAddressline1() +"  Nominee Adress Line1    cannot be Null"+"\n");
		      }
			if (rblsavingsData.getNomineestate()==null){
				fr.write(rblsavingsData.getNomineestate() +"  Nominee State    cannot be Null"+"\n");
		      }
			if (rblsavingsData.getNomineecity()==null){
				fr.write(rblsavingsData.getNomineecity() +"  Nominee City    cannot be Null"+"\n");
		      }
			if (rblsavingsData.getNomineePincode()==null){
				fr.write(rblsavingsData.getNomineePincode() +"  Nominee Pincode   cannot be Null"+"\n");
		      }
			if (rblsavingsData.getCollector()==null){
				fr.write(rblsavingsData.getCollector() +"  Collector  cannot be Null"+"\n");
		      }
			if (rblsavingsData.getApprover()==null){
				fr.write(rblsavingsData.getApprover() +"  Approver  cannot be Null"+"\n");
		      }
			
           }	
           
//===================== Rbl Loan Data Validator=================================================================//      
           
			fr.write("================================ Start  Writing Loan Data======================================" +"\n");

			
           List<RblLoanValidationData> rblloanDatas=this.rblDataReadplatformService.readRblLoanData(clientId);
      		
           for(RblLoanValidationData rblloanData: rblloanDatas){			
			if (rblloanData.getLoanProductCode()==null){				
				fr.write(rblloanData.getLoanProductCode() +"  Loan Product Code Cannot be Null  and length must be greater than 0 and lest equal to 20"+"\n");
			}
			if (rblloanData.getLoanAmount()==null){				
				fr.write(rblloanData.getLoanAmount() +"  Loan Amount  Cannot be Null " +"\n");
			}
			if (rblloanData.getDisbursementMode()==null){				
				fr.write(rblloanData.getDisbursementMode() +"  Loan Disbusement Mode  Cannot be Null " +"\n");
			}
			if (rblloanData.getLoanCycle()==null){				
				fr.write(rblloanData.getLoanCycle() +"  Loan Cycle   Cannot be Null " +"\n");
			}
			if (rblloanData.getBarcodeNo()==null){				
				fr.write(rblloanData.getBarcodeNo() +" Loan BarCode Number Cannot be Null " +"\n");
			}
			if (rblloanData.getLoanStratDate()==null){				
				fr.write(rblloanData.getLoanStratDate() +"  Loan Start Date Cannot be Null " +"\n");
			}
			
			if (rblloanData.getRepaymentStartDate()==null){				
				fr.write(rblloanData.getRepaymentStartDate() +" Loan Repayment Start Date Cannot be Null " +"\n");
			}
			
			if (rblloanData.getBcBranchCode()==null){				
				fr.write(rblloanData.getBcBranchCode() +"  Bc Branch Code Cannot be Null " +"\n");
			}
			if (rblloanData.getColector()==null){				
				fr.write(rblloanData.getColector() +"  Collector Cannot be Null " +"\n");
			}
			if (rblloanData.getApprover()==null){				
				fr.write(rblloanData.getApprover() +"  Approver Cannot be Null " +"\n");
			}
			if (rblloanData.getExceptedDisbursementDate()==null){				
				fr.write(rblloanData.getExceptedDisbursementDate() +"  Expected Disursement Date Cannot be Null " +"\n");
			}
			
			if (rblloanData.getHosiptalCash()!=null){	
				if(!(rblloanData.getHosiptalCash().length()>0) && (rblloanData.getHosiptalCash().length()<=20)){
					fr.write(rblloanData.getHosiptalCash() +"  Hosiptal Cash Must Be between 0 to 20 " +"\n");

				}
			}
			
			if (rblloanData.getPrepaidCharge()!=null){	
				if(!(rblloanData.getPrepaidCharge().length()>0) && (rblloanData.getPrepaidCharge().length()<=20)){
					fr.write(rblloanData.getPrepaidCharge() +"  Prepaid Charge Must be between between 0 to 20 " +"\n");

				}
			}
			}
//======================================================Rbl Client Data Validation================================================//	
			fr.write("==========================Start Writing clientData=======================" +"\n");

			
           List<RblclientDatValidation> rblclientDatas=this.rblDataReadplatformService.readRblClientData(clientId);
     		
           for(RblclientDatValidation rblclientsData: rblclientDatas){			
			if (rblclientsData.getExternalId()==null){				
				fr.write(rblclientsData.getExternalId() +"  External Id Cannot be Null  and length must be greater than 0 and lest equal to 20"+"\n");
			}else{
				if(!(rblclientsData.getExternalId().length()>0)&&(rblclientsData.getExternalId().length()<=20)){
					fr.write(rblclientsData.getExternalId() +"  External Id  length must be greater than 0 and lest equal to 20"+"\n");
				}
			}
			
			if (rblclientsData.getCustomerName()==null){				
				fr.write(rblclientsData.getCustomerName() +"  Customer Name Cannot be Null  and length must be greater than 0 and lest equal to 100"+"\n");
			}else{
				if(!(rblclientsData.getCustomerName().length()>0)&&(rblclientsData.getCustomerName().length()<=100)){
					fr.write(rblclientsData.getCustomerName() +"  Customer Name  length must be greater than 0 and lest equal to 100"+"\n");
				}
			}
           
			if(rblclientsData.getTitle()==null){
				fr.write(rblclientsData.getTitle() +"  Title Cannot Be Null"+"\n");
			}
			
			if(rblclientsData.getAddressline1()!=null){
				if(!(rblclientsData.getAddressline1().length()>0) && (rblclientsData.getAddressline1().length()<=500)){
					fr.write(rblclientsData.getCustomerName() +"  Customer Address  length must be greater than 0 and lest equal to 500"+"\n");
				}
			}
			if(rblclientsData.getCityCode()==null){
				fr.write(rblclientsData.getCityCode() +"  City Code Cannot Be Null"+"\n");
			}
			
			if(rblclientsData.getStateCode()==null){
				fr.write(rblclientsData.getStateCode() +"  State Code Cannot Be Null"+"\n");
			}
			if(rblclientsData.getPincode()==null){
				fr.write(rblclientsData.getPincode() +"  Pin Code Cannot Be Null"+"\n");
			}
			if(rblclientsData.getDateofBirt()==null){
				fr.write(rblclientsData.getDateofBirt() +"  Customer Date of Birth Cannot Be Null"+"\n");
			}
			if(rblclientsData.getMobilNo()==null){
				fr.write(rblclientsData.getMobilNo() +"  Customer Mobile Number Cannot Be Null"+"\n");
			}else{
				if(!(rblclientsData.getMobilNo().length()!=10)){
					fr.write(rblclientsData.getMobilNo() +"  Customer Mobile Number must be equal to 10 digit"+"\n");
				}
			}			
			if(rblclientsData.getGender()==null){
				fr.write(rblclientsData.getMobilNo() +"  Customer Gender Cannot  Be Null"+"\n");
			}
			
			if(rblclientsData.getBranchCode()==null){
				fr.write(rblclientsData.getBranchCode() +"  Branch Code Cannot  Be Null"+"\n");
			}
			else{
			      if(rblclientsData.getBranchCode().length()!=6){
						fr.write(rblclientsData.getBranchCode() +"  Branch Code Cannot  Be 6 digit"+"\n");  
			      }
			}
			if(rblclientsData.getOperatingregionCode()==null){
				fr.write(rblclientsData.getOperatingregionCode() +"  Operating Region Code Cannot  Be Null"+"\n");
			}
			else{
				if(rblclientsData.getOperatingregionCode().length()!=6){
					fr.write(rblclientsData.getOperatingregionCode() +"  Operating Rgion Code  Be 6 digit"+"\n");  
		      }
			}
			
			if(rblclientsData.getBarcodeNumber()==null){
				fr.write(rblclientsData.getBarcodeNumber() +"  BarCode Number  Cannot  Be Null"+"\n");
			}
			if(rblclientsData.getCardIssueFlag()==null){
				fr.write(rblclientsData.getCardIssueFlag() +"  Card Issue Flag Cannot  Be Null"+"\n");
			}
			else{
				if(!(rblclientsData.getCardIssueFlag()>0) && (rblclientsData.getCardIssueFlag()<=2)){
					fr.write(rblclientsData.getCardIssueFlag() +"  card Issue Flag must be between0 and 1"+"\n");
				}
			}
			
			if(rblclientsData.getBcBranchCode()==null){
				fr.write(rblclientsData.getBcBranchCode() +"  Branch Name  Cannot  Be Null"+"\n");
			}
			if(rblclientsData.getCollector()==null){
				fr.write(rblclientsData.getCollector() +"  Collectorr  Cannot  Be Null"+"\n");
			}
			
			if(rblclientsData.getApprover()==null){
				fr.write(rblclientsData.getApprover() +"  Approver  Cannot  Be Null"+"\n");
			}
			
			if(rblclientsData.getNomineeName()==null){
				fr.write(rblclientsData.getNomineeName() +"  Nominee Name  Cannot  Be Null"+"\n");
			}else{
				if(!(rblclientsData.getNomineeName().length()>0) && (rblclientsData.getNomineeName().length()<=100)){
					fr.write(rblclientsData.getNomineeName() +"   Nominee Name  must be between 0 and 100"+"\n");
				}
			}
			
			if(rblclientsData.getNomineeRelation()==null){
				fr.write(rblclientsData.getNomineeRelation() +"  Nominee Relation   Cannot  Be Null"+"\n");
			}
	    }
           fr.close();
      // 	public ValidatefileRecord(Long centerId, String fileType, String fileName, String fileLocation) {

           ValidatefileRecord validatefileRecord = new ValidatefileRecord(Long.parseLong(rblcenterId),"RBL",rblcentervalidatefile.getName(),rblcentervalidatefile.getPath());
           this.validateRblFileRepository.save(validatefileRecord);
	    }
	 catch (IOException e) {
		e.printStackTrace();
	}

}
}
