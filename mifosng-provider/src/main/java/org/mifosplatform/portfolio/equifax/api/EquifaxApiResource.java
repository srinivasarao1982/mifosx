package org.mifosplatform.portfolio.equifax.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mifosplatform.infrastructure.configuration.service.ConfigurationReadPlatformService;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.equifax.service.EquifaxService;
import org.mifosplatform.portfolio.group.domain.GroupRepository;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/equifaxIntregation")
@Component
@Scope("singleton")
public class EquifaxApiResource {
	private final PlatformSecurityContext context;    
    private final GroupRepository groupRepository;
    private final ConfigurationReadPlatformService configurationReadPlatformService;
    private final PartialLoanRepository partialLoanRepository;
    private final EquifaxService equifaxService;
      
    @Autowired
    public EquifaxApiResource(final PlatformSecurityContext context,     		
    		final GroupRepository groupRepository,
    		final ConfigurationReadPlatformService configurationReadPlatformService,
    		final PartialLoanRepository partialLoanRepository,
    		final EquifaxService equifaxService)           
        {
        this.context = context;     
        this.groupRepository=groupRepository;
        this.configurationReadPlatformService=configurationReadPlatformService;
        this.partialLoanRepository=partialLoanRepository;
        this.equifaxService=equifaxService;
      }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(final String apiRequestBodyAsJson,@QueryParam("clientId") String clientId) {
    	String errorMsg="";
    	Boolean isError =false;
    	/*if(clintId.length()>1){
    	String [] stringclientsId =clintId.split(",");    	
    	for(int i=0 ;i<stringclientsId.length;i++){
    	 Long clientId =Long.parseLong(stringclientsId[i]);    	 
        PartialLoan partialLoan= this.partialLoanRepository.findByClientIdAndLoanStatus(clientId,1, 0);
    	if(partialLoan==null){
    		errorMsg=errorMsg+"Partial Loan is Not Create for Client"+ clientId+"\n";
    		isError=true;
    	}
    	}
    	}
    	else{
       	 Long clientId =Long.parseLong(clintId);    	 
        // PartialLoan partialLoan= this.partialLoanRepository.findByClientIdAndLoanStatus(clientId,1, 0);
     	if(partialLoan==null){
     		errorMsg=errorMsg+"Partial Loan is Not Create for Client"+ clientId+"\n";
     		isError=true;
     	}
    	}    	
    	if(isError){
    		throw new PartialLoanNotCreatedException(errorMsg);
    	}*/
        Response response = Response.status(200).build();  	
   	     Long clientIds =Long.parseLong(clientId);    	 

    	this.equifaxService.getCreditBureauResult(clientIds);     
        return response;			
	}
       // Los File Send And Receive Logic
    

}
