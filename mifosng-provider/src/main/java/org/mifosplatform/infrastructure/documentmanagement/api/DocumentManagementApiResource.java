
package org.mifosplatform.infrastructure.documentmanagement.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.ToApiJsonSerializer;
import org.mifosplatform.infrastructure.documentmanagement.command.DocumentCommand;
import org.mifosplatform.infrastructure.documentmanagement.data.DocumentData;
import org.mifosplatform.infrastructure.documentmanagement.data.FileData;
import org.mifosplatform.infrastructure.documentmanagement.service.DocumentReadPlatformService;
import org.mifosplatform.infrastructure.documentmanagement.service.DocumentWritePlatformService;
import org.mifosplatform.infrastructure.documentmanagement.service.DocumentWritePlatformServiceJpaRepositoryImpl.DOCUMENT_MANAGEMENT_ENTITY;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("{entityType}/{entityId}/documents")
@Component
@Scope("singleton")
public class DocumentManagementApiResource {

    private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id", "parentEntityType", "parentEntityId",
            "name", "fileName", "size", "type", "description"));

    private final String SystemEntityType = "DOCUMENT";

    private final PlatformSecurityContext context;
    private final DocumentReadPlatformService documentReadPlatformService;
    private final DocumentWritePlatformService documentWritePlatformService;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final ToApiJsonSerializer<DocumentData> toApiJsonSerializer;
    private final ClientRepository clientRepository;
    
    private final String validationFilePath;
    private final String rblLosSendFilePath;
    private final String rblLosReceiveFilePath;

    @Autowired
    public DocumentManagementApiResource(final PlatformSecurityContext context,
            final DocumentReadPlatformService documentReadPlatformService, final DocumentWritePlatformService documentWritePlatformService,
            final ApiRequestParameterHelper apiRequestParameterHelper, final ToApiJsonSerializer<DocumentData> toApiJsonSerializer,
            final ClientRepository clientRepository,
            @Value("${validationFilePath}") final String validationFilePath, @Value("${rblLosSendFilePath}") final String rblLosSendFilePath, 
            @Value("${rblLosReceiveFilePath}") final String rblLosReceiveFilePath) {
        this.context = context;
        this.documentReadPlatformService = documentReadPlatformService;
        this.documentWritePlatformService = documentWritePlatformService;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.clientRepository = clientRepository;
        this.validationFilePath=validationFilePath;
        this.rblLosSendFilePath=rblLosSendFilePath;
        this.rblLosReceiveFilePath=rblLosReceiveFilePath;
    }

    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retreiveAllDocuments(@Context final UriInfo uriInfo, @PathParam("entityType") final String entityType,
            @PathParam("entityId") final Long entityId) {
    	// Nextru Specific - Clients and loans related documents storing into single folder - RBI related changes
        final String commonEntityType = DOCUMENT_MANAGEMENT_ENTITY.CLIENTS.toString();
        this.context.authenticatedUser().validateHasReadPermission(this.SystemEntityType);

        final Collection<DocumentData> documentDatas = this.documentReadPlatformService.retrieveAllDocuments(commonEntityType, entityId);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, documentDatas, this.RESPONSE_DATA_PARAMETERS);
    }

    @POST
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createDocument(@PathParam("entityType") final String entityType, @PathParam("entityId") final Long entityId,
            @HeaderParam("Content-Length") final Long fileSize, @FormDataParam("file")  InputStream inputStream,
            @FormDataParam("file") final FormDataContentDisposition fileDetails, @FormDataParam("file") final FormDataBodyPart bodyPart,
            @FormDataParam("name") final String name, @FormDataParam("description") final String description,
            @FormDataParam("documentType") final String documentType) throws IOException {

        /**
         * TODO: also need to have a backup and stop reading from stream after
         * max size is reached to protect against malicious clients
         **/

        /**
         * TODO: need to extract the actual file type and determine if they are
         * permissable
         **/
    	// Nextru Specific - Clients and loans related documents storing into single folder - RBI related changes
        final String commonEntityType = DOCUMENT_MANAGEMENT_ENTITY.CLIENTS.toString();
        String fileName = null;
        String documentName = documentType.replaceAll(" ", ""); // removing whitespace in documentType name
        if(entityType.equals(DOCUMENT_MANAGEMENT_ENTITY.CLIENTS.toString()) || entityType.equals(DOCUMENT_MANAGEMENT_ENTITY.LOANS.toString())){
        	fileName = this.documentWritePlatformService.documentNameGenerator(entityId, documentName,fileDetails.getFileName());
        }else{
        	fileName = fileDetails.getFileName();
        }
        BufferedImage originalImage = ImageIO.read(inputStream);
        
        if(documentName.equalsIgnoreCase("Photo")||documentName.equalsIgnoreCase("Sign") ){
            originalImage= Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 200, 200);
        }
        else{
        originalImage= Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 500, 500);
        }
         //To save with original ratio uncomment next line and comment the above.
         //originalImage= Scalr.resize(originalImage, 153, 128);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ImageIO.write(originalImage, "jpg", baos);
         inputStream = new ByteArrayInputStream(baos.toByteArray());
        final DocumentCommand documentCommand = new DocumentCommand(null, null, commonEntityType,entityId, name,fileName,
                fileSize, bodyPart.getMediaType().toString(), description, null);
        
        final Long documentId = this.documentWritePlatformService.createDocument(documentCommand, inputStream);

        return this.toApiJsonSerializer.serialize(CommandProcessingResult.resourceResult(documentId, null));
    }

    @PUT
    @Path("{documentId}")
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateDocument(@PathParam("entityType") final String entityType, @PathParam("entityId") final Long entityId,
            @PathParam("documentId") final Long documentId, @HeaderParam("Content-Length") final Long fileSize,
            @FormDataParam("file") final InputStream inputStream, @FormDataParam("file") final FormDataContentDisposition fileDetails,
            @FormDataParam("file") final FormDataBodyPart bodyPart, @FormDataParam("name") final String name,
            @FormDataParam("description") final String description) {

        final Set<String> modifiedParams = new HashSet<>();
        modifiedParams.add("name");
        modifiedParams.add("description");
     // Nextru Specific - Clients and loans related documents storing into single folder - RBI related changes
        final String commonEntityType = DOCUMENT_MANAGEMENT_ENTITY.CLIENTS.toString();

        /***
         * Populate Document command based on whether a file has also been
         * passed in as a part of the update
         ***/
        DocumentCommand documentCommand = null;
        if (inputStream != null && fileDetails.getFileName() != null) {
            modifiedParams.add("fileName");
            modifiedParams.add("size");
            modifiedParams.add("type");
            modifiedParams.add("location");
            documentCommand = new DocumentCommand(modifiedParams, documentId, commonEntityType, entityId, name, fileDetails.getFileName(),
                    fileSize, bodyPart.getMediaType().toString(), description, null);
        } else {
            documentCommand = new DocumentCommand(modifiedParams, documentId, commonEntityType, entityId, name, null, null, null, description,
                    null);
        }
        /***
         * TODO: does not return list of changes, should be done for consistency
         * with rest of API
         **/
        final CommandProcessingResult identifier = this.documentWritePlatformService.updateDocument(documentCommand, inputStream);

        return this.toApiJsonSerializer.serialize(identifier);
    }

    @GET
    @Path("{documentId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getDocument(@PathParam("entityType") final String entityType, @PathParam("entityId") final Long entityId,
            @PathParam("documentId") final Long documentId, @Context final UriInfo uriInfo) {
    	// Nextru Specific - Clients and loans related documents storing into single folder - RBI related changes
        final String commonEntityType = DOCUMENT_MANAGEMENT_ENTITY.CLIENTS.toString();
        this.context.authenticatedUser().validateHasReadPermission(this.SystemEntityType);

        final DocumentData documentData = this.documentReadPlatformService.retrieveDocument(commonEntityType, entityId, documentId);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, documentData, this.RESPONSE_DATA_PARAMETERS);
    }

    @GET
    @Path("{documentId}/attachment")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_OCTET_STREAM })
    public Response downloadFile(@PathParam("entityType") final String entityType, @PathParam("entityId") final Long entityId,
            @PathParam("documentId") final Long documentId) {
    	// Nextru Specific - Clients and loans related documents storing into single folder - RBI related changes
        final String commonEntityType = DOCUMENT_MANAGEMENT_ENTITY.CLIENTS.toString();
        this.context.authenticatedUser().validateHasReadPermission(this.SystemEntityType);

        final FileData fileData = this.documentReadPlatformService.retrieveFileData(commonEntityType, entityId, documentId);
        final ResponseBuilder response = Response.ok(fileData.file());
        response.header("Content-Disposition", "attachment; filename=\"" + fileData.name() + "\"");
        response.header("Content-Type", fileData.contentType());

        return response.build();
    }
    
    @GET
    @Path("{documentId}/rblattachment/{fileName}/{filelocation}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_OCTET_STREAM })
    public Response downloadrblFile(@PathParam("entityType") final String entityType, @PathParam("entityId") final Long entityId,
            @PathParam("documentId") final Long documentId,@PathParam("fileName") final String fileName,@PathParam("filelocation") final String filelocation
            ) throws IOException {

        this.context.authenticatedUser().validateHasReadPermission(this.SystemEntityType);
        File my_file =null;
        File my_file1=null;//"C:/Users/Keshav/.mifosx/RblValidationFile/"
        if (entityId==1){
        	my_file = new File(validationFilePath+fileName); // We are downloading .txt file, in the format of doc with name check - check.doc
            my_file1 = new File(validationFilePath+fileName+"test");
          
           FileOutputStream out = new  FileOutputStream(my_file1);
            FileInputStream in = new FileInputStream(my_file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0){
               out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            out.close();
        }
       	else{
       	if(entityType.equalsIgnoreCase("send")){
        my_file = new File(rblLosSendFilePath+fileName); // We are downloading .txt file, in the format of doc with name check - check.doc
        my_file1 = new File(rblLosSendFilePath+fileName+"test");
       	}
       	if (entityType.equalsIgnoreCase("receive")){
       		my_file = new File(rblLosReceiveFilePath+fileName); // We are downloading .txt file, in the format of doc with name check - check.doc
            my_file1 = new File(rblLosReceiveFilePath+fileName+"test");
     	
       	}
        
       FileOutputStream out = new  FileOutputStream(my_file1);
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
           out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        out.close();
        }
        final ResponseBuilder response = Response.ok(my_file);
        
        
        response.header("Content-Disposition", "attachment; filename=\"" +fileName + "\"");
        response.header("Content-Type", "text/plain");

        return response.build();
    }

    @DELETE
    @Path("{documentId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteDocument(@PathParam("entityType") final String entityType, @PathParam("entityId") final Long entityId,
            @PathParam("documentId") final Long documentId) {
    	// Nextru Specific - Clients and loans related documents storing into single folder - RBI related changes
        final String commonEntityType = DOCUMENT_MANAGEMENT_ENTITY.CLIENTS.toString();
        final DocumentCommand documentCommand = new DocumentCommand(null, documentId, commonEntityType, entityId, null, null, null, null, null,
                null);

        final CommandProcessingResult documentIdentifier = this.documentWritePlatformService.deleteDocument(documentCommand);

        return this.toApiJsonSerializer.serialize(documentIdentifier);
    }
}
