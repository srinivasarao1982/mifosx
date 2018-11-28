/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.documentmanagement.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.documentmanagement.command.DocumentCommand;
import org.mifosplatform.infrastructure.documentmanagement.command.DocumentCommandValidator;
import org.mifosplatform.infrastructure.documentmanagement.contentrepository.ContentRepository;
import org.mifosplatform.infrastructure.documentmanagement.contentrepository.ContentRepositoryFactory;
import org.mifosplatform.infrastructure.documentmanagement.data.DocumentData;
import org.mifosplatform.infrastructure.documentmanagement.data.FileData;
import org.mifosplatform.infrastructure.documentmanagement.domain.Document;
import org.mifosplatform.infrastructure.documentmanagement.domain.DocumentRepository;
import org.mifosplatform.infrastructure.documentmanagement.domain.StorageType;
import org.mifosplatform.infrastructure.documentmanagement.exception.ContentManagementException;
import org.mifosplatform.infrastructure.documentmanagement.exception.DocumentNotFoundException;
import org.mifosplatform.infrastructure.documentmanagement.exception.DuplicateDocumentNameFoundException;
import org.mifosplatform.infrastructure.documentmanagement.exception.InvalidEntityTypeForDocumentManagementException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepository;
import org.mifosplatform.portfolio.rblvalidation.domain.ReceiveFileRecord;
import org.mifosplatform.portfolio.rblvalidation.domain.ReceiveFileRepository;
import org.mifosplatform.portfolio.rblvalidation.domain.ReceiveFileRepositoryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
@Service
public class DocumentWritePlatformServiceJpaRepositoryImpl implements DocumentWritePlatformService {

    private final static Logger logger = LoggerFactory.getLogger(DocumentWritePlatformServiceJpaRepositoryImpl.class);

    private final PlatformSecurityContext context;
    private final DocumentRepository documentRepository;
    private final ContentRepositoryFactory contentRepositoryFactory;
    private final ClientRepository clientRepository;
    private final DocumentReadPlatformService documentReadPlatformService;
    private final ReceiveFileRepository receiveFileRepository;
    private final ReceiveFileRepositoryWrapper receiveFileRepositoryWrapper;

    
    //nextru-specific
    //upload documents
    private final String hostName;
    private final String hostUserName;
    private final String hostPassword;
    private final String hostPortNumber;
    private final String imageDestinationPath;
    private final String imageSourcePath;
    private final String losFileSourcePath;
    private final String losFileDestinationPath;
    private final String hostPassphrase;
    private final String hostKeyName;
    //download documents
    private final String downloadFromDirPath;
    private final String downloadToDirPath;

    @Autowired
    public DocumentWritePlatformServiceJpaRepositoryImpl(final PlatformSecurityContext context,
            final DocumentRepository documentRepository, final ContentRepositoryFactory documentStoreFactory,
            final ClientRepository clientRepository, final DocumentReadPlatformService documentReadPlatformService,
    	    final ReceiveFileRepository receiveFileRepository,final ReceiveFileRepositoryWrapper receiveFileRepositoryWrapper,
            @Value("${hostName}") final String hostName, @Value("${hostUserName}") final String hostUserName, 
            @Value("${hostPassword}") final String hostPassword,
            @Value("${hostPortNumber}") final String hostPortNumber, @Value("${imageDestinationPath}") final String imageDestinationPath,
            @Value("${imageSourcePath}") final String imageSourcePath, @Value("${losFileSourcePath}") final String losFileSourcePath,
            @Value("${losFileDestinationPath}") final String losFileDestinationPath, @Value("${downloadFromDirPath}") final String downloadFromDirPath,
            @Value("${downloadToDirPath}") final String downloadToDirPath, @Value("${hostPassphrase}") final String hostPassphrase,
            @Value("${hostKeyName}") final String hostKeyName) {
        this.context = context;
        this.documentRepository = documentRepository;
        this.contentRepositoryFactory = documentStoreFactory;
        this.clientRepository = clientRepository;
        this.documentReadPlatformService = documentReadPlatformService;
        this.receiveFileRepository=receiveFileRepository;
        this.receiveFileRepositoryWrapper=receiveFileRepositoryWrapper;
        
        //netru specific 
        
        this.hostName = hostName;
        this.hostPassword = hostPassword;
        this.hostUserName = hostUserName;
        this.hostPortNumber = hostPortNumber;
        this.imageSourcePath = imageSourcePath;
        this.imageDestinationPath = imageDestinationPath;
        this.losFileSourcePath = losFileSourcePath;
        this.losFileDestinationPath = losFileDestinationPath;
        this.downloadFromDirPath = downloadFromDirPath;
        this.downloadToDirPath = downloadToDirPath;
        this.hostPassphrase = hostPassphrase;
        this.hostKeyName = hostKeyName;
    }

    @Transactional
    @Override
    public Long createDocument(final DocumentCommand documentCommand, final InputStream inputStream) {
        try {
            this.context.authenticatedUser();

            final DocumentCommandValidator validator = new DocumentCommandValidator(documentCommand);

            validateParentEntityType(documentCommand);

            validator.validateForCreate();
            validateForDuplicateDocumentName(documentCommand);

            final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository();
            final String fileLocation = contentRepository.saveFile(inputStream, documentCommand);

            final Document document = Document.createNew(documentCommand.getParentEntityType(), documentCommand.getParentEntityId(),
                    documentCommand.getName(), documentCommand.getFileName(), documentCommand.getSize(), documentCommand.getType(),
                    documentCommand.getDescription(), fileLocation, contentRepository.getStorageType());

            this.documentRepository.save(document);

            return document.getId();
        } catch (final DataIntegrityViolationException dve) {
            logger.error(dve.getMessage(), dve);
            throw new PlatformDataIntegrityException("error.msg.document.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource.");
        }
    }

    @Transactional
    @Override
    public CommandProcessingResult updateDocument(final DocumentCommand documentCommand, final InputStream inputStream) {
        try {
            this.context.authenticatedUser();

            String oldLocation = null;
            final DocumentCommandValidator validator = new DocumentCommandValidator(documentCommand);
            validator.validateForUpdate();
            // TODO check if entity id is valid and within data scope for the
            // user
            final Document documentForUpdate = this.documentRepository.findOne(documentCommand.getId());
            if (documentForUpdate == null) { throw new DocumentNotFoundException(documentCommand.getParentEntityType(),
                    documentCommand.getParentEntityId(), documentCommand.getId()); }

            final StorageType documentStoreType = documentForUpdate.storageType();
            oldLocation = documentForUpdate.getLocation();
            if (inputStream != null && documentCommand.isFileNameChanged()) {
                final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository();
                documentCommand.setLocation(contentRepository.saveFile(inputStream, documentCommand));
                documentCommand.setStorageType(contentRepository.getStorageType().getValue());
            }

            documentForUpdate.update(documentCommand);

            if (inputStream != null && documentCommand.isFileNameChanged()) {
                final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository(documentStoreType);
                contentRepository.deleteFile(documentCommand.getName(), oldLocation);
            }

            this.documentRepository.saveAndFlush(documentForUpdate);

            return new CommandProcessingResult(documentForUpdate.getId());
        } catch (final DataIntegrityViolationException dve) {
            logger.error(dve.getMessage(), dve);
            throw new PlatformDataIntegrityException("error.msg.document.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource.");
        } catch (final ContentManagementException cme) {
            logger.error(cme.getMessage(), cme);
            throw new ContentManagementException(documentCommand.getName(), cme.getMessage());
        }
    }

    @Transactional
    @Override
    public CommandProcessingResult deleteDocument(final DocumentCommand documentCommand) {
        this.context.authenticatedUser();

        validateParentEntityType(documentCommand);
        // TODO: Check document is present under this entity Id
        final Document document = this.documentRepository.findOne(documentCommand.getId());
        if (document == null) { throw new DocumentNotFoundException(documentCommand.getParentEntityType(),
                documentCommand.getParentEntityId(), documentCommand.getId()); }
        this.documentRepository.delete(document);

        final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository(document.storageType());
        contentRepository.deleteFile(document.getName(), document.getLocation());
        return new CommandProcessingResult(document.getId());
    }

    private void validateParentEntityType(final DocumentCommand documentCommand) {
        if (!checkValidEntityType(documentCommand.getParentEntityType())) { throw new InvalidEntityTypeForDocumentManagementException(
                documentCommand.getParentEntityType()); }
    }

    private static boolean checkValidEntityType(final String entityType) {
        for (final DOCUMENT_MANAGEMENT_ENTITY entities : DOCUMENT_MANAGEMENT_ENTITY.values()) {
            if (entities.name().equalsIgnoreCase(entityType)) { return true; }
        }
        return false;
    }

    /*** Entities for document Management **/
    public static enum DOCUMENT_MANAGEMENT_ENTITY {
        CLIENTS, CLIENT_IDENTIFIERS, STAFF, LOANS, SAVINGS, GROUPS;

        @Override
        public String toString() {
            return name().toString().toLowerCase();
        }
    }
    
    /*** Nextru Specific - Generate document Name as per RBI Policies **/
	@Override
	public String documentNameGenerator(final long clientId,final String documentType, String fileName) {
		    final Client client = this.clientRepository.findOne(clientId);
		    final String [] fileNameArray = fileName.split("\\.");
		    final String fileFormat = fileNameArray[1];
	        final String bcName = "Nextru";
	        final String clientExternalId = client.getExternalId();
	        final String newFileName = bcName + '_' + clientExternalId + '_' + documentType + "." +  fileFormat;
	        return newFileName;
	}
	
	/*** Nextru specific - Validate for duplicate document name**/
	private void validateForDuplicateDocumentName(final DocumentCommand documents){
	   boolean isDuplicateDocumentNameFound = this.documentReadPlatformService.validateDuplicateDoducmentName(documents.getParentEntityType(),documents.getParentEntityId(),documents.getFileName());
	   if(isDuplicateDocumentNameFound == true)
		   throw new DuplicateDocumentNameFoundException(documents.getParentEntityType(),documents.getParentEntityId(),documents.getFileName());
	}

	/*** Nextru Specific - Method to uploadDocument to Remote server **/
	@Override
	public void uploadDocumentToRemoteHost(String rblTextFileName, boolean isReprocess, boolean isImageToBeSend, List<Long> clientIds) {
		JSch jsch = new JSch();
        Session session = null;
        String losFileSourcePath = System.getProperty("user.home") + File.separator + ".mifosx"+File.separator+"RBLLosFile";
        String remoteHostKey = System.getProperty("user.home") + File.separator + ".mifosx" +File.separator+ hostKeyName;
        File keyFile = new File(remoteHostKey);
        
        try {
        	jsch.addIdentity(keyFile.getAbsolutePath(),hostPassphrase);
	    	session = jsch.getSession(hostUserName,hostName,Integer.parseInt(hostPortNumber));
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(hostPassword);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            //case - A : images and text document file both will be send to remote host        
            if((isReprocess==true && isImageToBeSend == true)|| (isReprocess==false && isImageToBeSend == false)){
            	//to transfer the clients document to remote server
                if(!clientIds.isEmpty()){
                	for(long clientId : clientIds){
                    	Collection<DocumentData> documentDataList = this.documentReadPlatformService.retrieveAllDocuments("clients", clientId);
                    	if(documentDataList != null && !documentDataList.isEmpty() ){
                    		for(DocumentData documentData : documentDataList){
                            	FileData fileData = this.documentReadPlatformService.retrieveFileData(documentData.getParentEntityType(),documentData.getParentEntityId(),documentData.getId());
                            	if(fileData!=null && fileData.getFile().exists()){
                            		sftpChannel.put(fileData.getFile().getAbsolutePath(),imageDestinationPath);	
                            	}
                            }
                    	}
                    }
                }
                //transfer rbl text document to remote server
                if(rblTextFileName!=null){
                	 DocumentData documentData = new DocumentData(losFileSourcePath,"Text/File",rblTextFileName);
                	 final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository(StorageType.FILE_SYSTEM);
                     FileData rblTextFiledoc = contentRepository.fetchFile(documentData);
                     String rblTextFilePath = rblTextFiledoc.getFile().getPath() + "/" + rblTextFileName;
                     if(rblTextFiledoc != null && rblTextFiledoc.getFile().exists()){
                    	 sftpChannel.put(rblTextFilePath, losFileDestinationPath);
                     }
                }
                
            }
            
            //Case - B : Only Images will be send to remote host
            if(isReprocess == false && isImageToBeSend == true){
            	//to transfer the clients document to remote server
                if(!clientIds.isEmpty()){
                	for(long clientId : clientIds){
                    	Collection<DocumentData> documentDataList = this.documentReadPlatformService.retrieveAllDocuments("clients", clientId);
                    	if(documentDataList!= null && !documentDataList.isEmpty()){
                    		for(DocumentData documentData : documentDataList){
                            	FileData fileData = this.documentReadPlatformService.retrieveFileData(documentData.getParentEntityType(),documentData.getParentEntityId(),documentData.getId());
                            	if(fileData!=null && fileData.getFile().exists()){
                            		sftpChannel.put(fileData.getFile().getAbsolutePath(),imageDestinationPath);
                            	}
                            }	
                    	}
                    }
                }
            }
            
            
            //Case - C : Only RBLText File will be send to remote host
            
            if(isReprocess == true && isImageToBeSend == false ){
            	if(rblTextFileName!=null){
               	 DocumentData documentData = new DocumentData(losFileSourcePath,"Text/File",rblTextFileName);
               	 final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository(StorageType.FILE_SYSTEM);
                    FileData rblTextFiledoc = contentRepository.fetchFile(documentData);
                    String rblTextFilePath = rblTextFiledoc.getFile().getPath() + "/" + rblTextFileName;
                    if(rblTextFiledoc!=null && rblTextFiledoc.getFile().exists()){
                    	sftpChannel.put(rblTextFilePath,losFileDestinationPath);
                    }
               }
            	
            }
            
            sftpChannel.exit();
        } catch (JSchException e) {
            logger.error("Error while transfer file :" +  e);  
        } catch (SftpException e) {
        	logger.error("Error while transfer file :" +  e);
        }finally{
            session.disconnect();
        }
	}

	/*** Nextru Specific - Method to download document from Remote server and save in RBL Received Document Directory **/
	
	@Override
	public void downloadDocumentFromRemoteHost() {
		
		 String downloadToDirPath = System.getProperty("user.home") + File.separator + ".mifosx" +File.separator+ "RBLReceivedFiles";
		 String remoteHostKey = System.getProperty("user.home") + File.separator + ".mifosx" +File.separator+ hostKeyName;
	     Session session = null;
	     List<String> listOfFileNames = new ArrayList<>();
	     byte[] buffer = new byte[1024];
		 BufferedInputStream bis;
		 JSch jsch = new JSch();
		 File keyFile = new File(remoteHostKey);
	     try{
	    	 jsch.addIdentity(keyFile.getAbsolutePath(),hostPassphrase);
	    	 session = jsch.getSession(hostUserName,hostName,Integer.parseInt(hostPortNumber));
	         session.setConfig("StrictHostKeyChecking", "no");
	         session.setPassword(hostPassword);
	         session.connect();
	         Channel channel = session.openChannel("sftp");
	         channel.connect();
	         ChannelSftp sftpChannel = (ChannelSftp) channel;
	        @SuppressWarnings("unchecked")
			Vector<ChannelSftp.LsEntry> fileList = sftpChannel.ls(downloadFromDirPath);
	         // iterate through objects in list, identifying specific file names
	         for (ChannelSftp.LsEntry oListItem : fileList) {
	        	   // If it is a file (not a directory)
	             if (!oListItem.getAttrs().isDir()) {
	            	 listOfFileNames.add(oListItem.getFilename());
	            	 File file = new File(oListItem.getFilename());
	     			 bis = new BufferedInputStream(sftpChannel.get(downloadFromDirPath + "/" + file.getName()));
	     			 File newFile = new File(downloadToDirPath + "/" + file.getName());
	     			
	     			// Download file
	     			 OutputStream os = new FileOutputStream(newFile);
	     			 BufferedOutputStream bos = new BufferedOutputStream(os);
	     			 int readCount;
	     			 while ((readCount = bis.read(buffer)) > 0) {
	     				 bos.write(buffer, 0, readCount);
	     			 }
	     			 bis.close();
	     			 bos.close();
	     	    
	     			 //Storing file In DataBase
	     			//Receive File Logic	     			
	     			List<ReceiveFileRecord>receiveFileRecords =this.receiveFileRepository.getExistingFile(file.getName());
	     			if(receiveFileRecords.size()==0){
	     				ReceiveFileRecord receiveFileRecord =new ReceiveFileRecord("received",file.getName(),downloadToDirPath);
	                     this.receiveFileRepositoryWrapper.save(receiveFileRecord);
	     			}
	             }
	         }
	        
	     }catch(Exception e){
	    	 logger.error("Error While Downloading File :" + e);
	     }
	}	    
}