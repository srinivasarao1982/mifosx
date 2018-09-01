package org.mifosplatform.portfolio.rblvalidation.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.joda.time.DateTime;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.domain.OfficeRepositoryWrapper;
import org.mifosplatform.organisation.staff.domain.StaffRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.service.ClientbankDetailsWritePlatformServiceImpl;
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
import org.mifosplatform.portfolio.rblvalidation.domain.SendFileRepository;
import org.mifosplatform.portfolio.rblvalidation.domain.SendFileRepositoryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
*/
@Service
public class RblLosFileGenerationServiceImpl implements RblLosFileGenerationService {

	 private final static Logger logger = LoggerFactory.getLogger(RblLosFileGenerationServiceImpl.class);

	    private final PlatformSecurityContext context;
	    private final RblDataReadplatformService rblDataReadplatformService;
	    //private final SendFileRepository sendFileRepository;
	   // private final ReceiveFileRepository receiveFileRepository;
	    private final ReceiveFileRepositoryWrapper receiveFileRepositoryWrapper;
	    private final SendFileRepositoryWrapper sendFileRepositoryWrapper;
	    	    
	    @Autowired
	    public RblLosFileGenerationServiceImpl(final PlatformSecurityContext context,
	    		final RblDataReadplatformService rblDataReadplatformService,final SendFileRepository sendFileRepository,
	    		final ReceiveFileRepository receiveFileRepository,
	    		final ReceiveFileRepositoryWrapper receiveFileRepositoryWrapper,
	    		final SendFileRepositoryWrapper sendFileRepositoryWrapper)
	            {
	        this.context = context;
	        this.rblDataReadplatformService=rblDataReadplatformService;
	        //this.sendFileRepository =sendFileRepository;
	        //this.receiveFileRepository =receiveFileRepository;
	        this.receiveFileRepositoryWrapper=receiveFileRepositoryWrapper;
	        this.sendFileRepositoryWrapper=sendFileRepositoryWrapper;
	         }

		@Override
		public void generateLosFile(String clientId, String centerId, String groupId) {
			
			List<RblCenterValidateData> rblCenterValidateDatas =this.rblDataReadplatformService.readRblCenterData(centerId);
			List<RblclientDatValidation>readRblClientDatas=this.rblDataReadplatformService.readRblClientData(clientId);
			List<RblGroupValidationData>readRblGroupDatas=this.rblDataReadplatformService.readRblGroupData(groupId);
			List<RblLoanValidationData>readRblLoanDatas=this.rblDataReadplatformService.readRblLoanData(clientId);
			List<RblSavingValidationData>readRblSavingDatas=this.rblDataReadplatformService.readRblSavingData(clientId);
       
			 String RBL_BASE_DIR = System.getProperty("user.home") + File.separator + ".mifosx"+File.separator+"RBLLosFile";
			    try {
				
				File rblLosFile =new File (RBL_BASE_DIR,"Nextru_Los__"+new DateTime().toString("yyyyMMdd")+"_"+new DateTime().toString("HH")+"_"+ new DateTime("MM")+".txt");
				FileWriter fr =null;
				fr =new FileWriter(rblLosFile);
				
				
				for(RblclientDatValidation rblclientDatValidation:readRblClientDatas){	
					StringBuilder customerData =new StringBuilder();
					if(rblclientDatValidation.getExternalId().startsWith("555")){
						customerData.append("NEWCUSTOMER~"+rblclientDatValidation.getExternalId()) ;
					}
					else{
						customerData.append("EXISTINGCUSTOMER~"+rblclientDatValidation.getExternalId());
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
					customerData.append(rblclientDatValidation.getEducationQualification()).append("|").append(rblclientDatValidation.getCategory()).append("|");
					customerData.append(rblclientDatValidation.getLanguage()).append("|").append(rblclientDatValidation.getCardIssueFlag()).append("|");
					customerData.append(rblclientDatValidation.getBcBranchCode()).append("|").append(rblclientDatValidation.getCollector()).append("|");
					customerData.append(rblclientDatValidation.getApprover()).append("|").append(rblclientDatValidation.getSpouseName()).append("|");
					customerData.append(rblclientDatValidation.getSpouseDateOfBirth()).append("|").append(rblclientDatValidation.getNomineeName()).append("|");
					customerData.append(rblclientDatValidation.getNomineeRelation()).append("|").append(rblclientDatValidation.getCbCheck()).append("|");
					customerData.append(rblclientDatValidation.getBankbranchName()).append("|").append(rblclientDatValidation.getBankAccountNo()).append("|");
					customerData.append(rblclientDatValidation.getBankbranchName()).append("|").append(rblclientDatValidation.getRenewalFl());
					fr.write(customerData.toString());
					fr.write("\n");

				}
				
				for(RblLoanValidationData rblLoanValidationData:readRblLoanDatas){	
					StringBuilder customerData =new StringBuilder();
						customerData.append("LOAN~") ;
						
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
				
						fr.write(customerData.toString());
						fr.write("\n");

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
					customerData.append(rblSavingValidationData.getGurdianName()).append("|").append(rblSavingValidationData.getGurdianDateofBirth()).append("|");
					customerData.append(rblSavingValidationData.getGurdianGender()).append("|").append(rblSavingValidationData.getGurdianAddressline1()).append("|");
					customerData.append(rblSavingValidationData.getGurdianAddressline2()).append("|").append(rblSavingValidationData.getGurdianAddressline3()).append("|");
					customerData.append(rblSavingValidationData.getGurdianState()).append("|").append(rblSavingValidationData.getGurdianCity()).append("|");
					customerData.append(rblSavingValidationData.getGurdianRelation()).append("|").append(rblSavingValidationData.getGurdianPhoneNo()).append("|");
					customerData.append(rblSavingValidationData.getGurdianPincode()).append("|").append(rblSavingValidationData.getCollector()).append("|");
					customerData.append(rblSavingValidationData.getApprover());
					fr.write(customerData.toString());
					fr.write("\n");
				
	        }
	    
				for(RblGroupValidationData rblGroupValidationData:readRblGroupDatas){	
					StringBuilder customerData =new StringBuilder();

					customerData.append("SAVING~") ;
					
					customerData.append(rblGroupValidationData.getExternalId()).append("|").append(rblGroupValidationData.getCenterExternalId()).append("|");
					customerData.append(rblGroupValidationData.getCenterName()).append("|").append(rblGroupValidationData.getMaximuncenter()).append("|");
					customerData.append(rblGroupValidationData.getGroupName()).append("|").append(rblGroupValidationData.getGroupType()).append("|");
					customerData.append(rblGroupValidationData.getMinumNumber()).append("|").append(rblGroupValidationData.getMaximumNumber()).append("|");
					customerData.append(rblGroupValidationData.getFormationDate()).append("|").append(rblGroupValidationData.getMeetinTime()).append("|");
					customerData.append(rblGroupValidationData.getMeetingfrequency()).append("|").append(rblGroupValidationData.getDistancefromBranch()).append("|");
					customerData.append(rblGroupValidationData.getBranchCode()).append("|").append(rblGroupValidationData.getOperatingRegionCode());
					fr.write(customerData.toString());
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
					fr.write(customerData.toString());
					fr.write("\n");
	        }		
				
			fr.close();	
		  }
		    catch(Exception e){
			    	
			    
		   }
	  }
	
//public Void Transfer File
		public void transferfile(){
			/*FileReader reader=new FileReader("db.properties");		      
		    Properties p=new Properties();  
		    p.load(reader);
		     
		        String SFTPHOST =p.getProperty("host");
		        int SFTPPORT = 22;
		        String SFTPUSER = p.getProperty("user");
		        String SFTPPASS =  p.getProperty("password");
		        String SFTPWORKINGDIR = p.getProperty("dir");;

				 
		  JSch jsch = new JSch();
		        Session session = null;
		        try {
		            session = jsch.getSession(SFTPUSER, SFTPHOST, 22);
		            java.util.Properties config = new java.util.Properties();
		            config.put("StrictHostKeyChecking", "no");
		            session.setConfig(config);
		            session.setPassword(SFTPPASS);
		            session.connect();
		            
		            Channel channel = session.openChannel("sftp");
		            channel.connect();
		            ChannelSftp sftpChannel = (ChannelSftp) channel;
		            sftpChannel.put("/tmplocal/testUpload.txt", "/tmpremote/testUpload.txt");  
		            sftpChannel.exit();
		            session.disconnect();
		        } catch (JSchException e) {
		            e.printStackTrace();  
		        } catch (SftpException e) {
		            e.printStackTrace();
		        }
		*/ 
		 }
		
// public void ReceiveFile	
		
		public void receivedfile(){
			/*
			FileReader reader=new FileReader("db.properties");		      
		    Properties p=new Properties();  
		    p.load(reader);
		     
		        String SFTPHOST =p.getProperty("host");
		        int SFTPPORT = 22;
		        String SFTPUSER = p.getProperty("user");
		        String SFTPPASS =  p.getProperty("password");
		        String SFTPWORKINGDIR = p.getProperty("dir");;

		        Session session = null;
		        Channel channel = null;
		        ChannelSftp channelSftp = null;

		        try {
		            JSch jsch = new JSch();
		            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
		            session.setPassword(SFTPPASS);
		            java.util.Properties config = new java.util.Properties();
		            config.put("StrictHostKeyChecking", "no");
		            session.setConfig(config);
		            session.connect();
		            channel = session.openChannel("sftp");
		            channel.connect();
		            channelSftp = (ChannelSftp) channel;
		            channelSftp.cd(SFTPWORKINGDIR);
		            Vector filelist = channelSftp.ls(SFTPWORKINGDIR);
		            for (int i = 0; i < filelist.size(); i++) {
		            	List<ReceiveFileRecord>	receiveFileRecord=this.receiveFileRepository.getExistingFile(filelist.get(i).toString());
		                if(receiveFileRecord.size()>0){
				            sftpChannel.put("/tmplocal/testUpload.txt", "/tmpremote/testUpload.txt");  

		                	ReceiveFileRecord ReceiveFileRecord =new ReceiveFileRecord().
		            }

		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
*/		//}
		 


		 
		

		//}
		 
		//}
 }
}
//}

