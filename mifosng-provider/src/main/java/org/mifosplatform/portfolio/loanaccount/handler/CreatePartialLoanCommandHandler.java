package org.mifosplatform.portfolio.loanaccount.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.loanaccount.service.PartialLoanWriteplatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@CommandType(entity = "PARTIALLOAN", action = "CREATE")
public class CreatePartialLoanCommandHandler  implements NewCommandSourceHandler {

    private final PartialLoanWriteplatformService partialLoanWriteplatformService;

    @Autowired
    public CreatePartialLoanCommandHandler(final PartialLoanWriteplatformService partialLoanWriteplatformService) {
        this.partialLoanWriteplatformService = partialLoanWriteplatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.partialLoanWriteplatformService.createpartialLoan(command);
    }
}
