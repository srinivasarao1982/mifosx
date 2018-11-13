package org.nirantara.client.ext.service;

import java.util.List;
import java.util.Set;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.service.ClientIdentifierWritePlatformService;
import org.nirantara.client.ext.api.CoapplicantApiConstants;
import org.nirantara.client.ext.data.CoClientDataValidator;
import org.nirantara.client.ext.domain.Address;
import org.nirantara.client.ext.domain.ClientExtAssembler;
import org.nirantara.client.ext.domain.Coapplicant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class CoClientWritePlatformServiceImpl implements CoClientWritePlatformService {

    private final static Logger logger = LoggerFactory.getLogger(CoClientWritePlatformServiceImpl.class);

    private final PlatformSecurityContext context;
    private final CoClientDataValidator fromApiJsonDeserializer;
    private final ClientRepositoryWrapper clientRepository;
    private final ClientExtAssembler clientExtAssembler;
    private final ClientIdentifierWritePlatformService clientIdentifierWritePlatformService;

    @Autowired
    public CoClientWritePlatformServiceImpl(final PlatformSecurityContext context, final CoClientDataValidator fromApiJsonDeserializer,
            final ClientRepositoryWrapper clientRepository, final ClientExtAssembler clientExtAssembler,
            final ClientIdentifierWritePlatformService clientIdentifierWritePlatformService) {
        this.context = context;
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.clientRepository = clientRepository;
        this.clientExtAssembler = clientExtAssembler;
        this.clientIdentifierWritePlatformService = clientIdentifierWritePlatformService;
    }

    @Override
    public CommandProcessingResult createCoClient(final JsonCommand command) {

        this.fromApiJsonDeserializer.validateForCreateCoClient(command.json());

        final Long clientId = command.longValueOfParameterNamed(CoapplicantApiConstants.clientId);
        final Client clientForUpdate = this.clientRepository.findOneWithNotFoundDetection(clientId);

        final JsonObject object = new JsonParser().parse(command.json()).getAsJsonObject();

        final JsonArray coClientDataArray = object.get("coClientData").getAsJsonArray();
        if (coClientDataArray != null) {
            List<Coapplicant> coapplicant = this.clientExtAssembler.assembleCoClientDataArray(coClientDataArray, clientForUpdate);
            if (coapplicant != null && coapplicant.size() > 0) {
                clientForUpdate.updateCoapplicant(coapplicant);
            }
        }

        final JsonArray addressArray = object.get("naddress").getAsJsonArray();
        if (addressArray != null && addressArray.size() > 0) {
            final Set<Address> address = this.clientExtAssembler.assembleAddress(addressArray, clientForUpdate);
            if (address != null && address.size() > 0) {
                clientForUpdate.addAddressExt(address);
            }
        }

        // For nirantara ClientIdentifierWritePlatformService
        this.clientIdentifierWritePlatformService.addClientIdentifierService(clientForUpdate, command,false);

        this.clientRepository.saveAndFlush(clientForUpdate);

        return new CommandProcessingResultBuilder() //
                .withCommandId(command.commandId()) //
                .withEntityId(clientForUpdate.getId()) //
                .build();
    }

}
