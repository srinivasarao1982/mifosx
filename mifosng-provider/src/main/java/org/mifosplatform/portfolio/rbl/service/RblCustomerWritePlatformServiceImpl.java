package org.mifosplatform.portfolio.rbl.service;

import java.util.Map;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.mifosplatform.portfolio.rbl.api.RblCustomerDetailsApiConstant;
import org.mifosplatform.portfolio.rbl.domain.RblCustomer;
import org.mifosplatform.portfolio.rbl.domain.RblCustomerRepositoryWrapper;
import org.mifosplatform.portfolio.rbl.exception.MandatoryParameterException;
import org.mifosplatform.portfolio.rbl.exception.MustbeBetweenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RblCustomerWritePlatformServiceImpl implements RblCustomerWritePlatformService {

	private final static Logger logger = LoggerFactory.getLogger(RblCustomerWritePlatformServiceImpl.class);

	private final PlatformSecurityContext context;
	private final RblCustomerRepositoryWrapper rblCustomerRepositoryWrapper;
	private final ClientRepositoryWrapper clientRepositoryWrapper;

	@Autowired
	public RblCustomerWritePlatformServiceImpl(final PlatformSecurityContext context,
			final RblCustomerRepositoryWrapper rblCustomerRepositoryWrapper,
			final ClientRepositoryWrapper clientRepositoryWrapper) {
		this.context = context;
		this.rblCustomerRepositoryWrapper = rblCustomerRepositoryWrapper;
		this.clientRepositoryWrapper = clientRepositoryWrapper;

	}

	@Override
	public CommandProcessingResult createRblCustomer(JsonCommand command) {
		try {
			final Long clientId = command.longValueOfParameterNamed(RblCustomerDetailsApiConstant.clintIdparamnam);
			final Client client = this.clientRepositoryWrapper.findOneWithNotFoundDetection(clientId);
			final String pensionCard = command
					.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.pensioncardnoparamName);
			if (pensionCard != null) {
				if (pensionCard.length() > 50) {
					throw new MustbeBetweenException("pension card length", 1, 50);
				}
			}
			final Long adharseedingConstant = command
					.longValueOfParameterNamed(RblCustomerDetailsApiConstant.addaharseedingconstantparamName);
			if (adharseedingConstant != null) {
				if (adharseedingConstant > 2) {
					throw new MustbeBetweenException("adhar seeding Constan", 1, 2);
				}
			}
			final Integer health = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.healthparamName);
			if (health != null) {
				if (health > 7) {
					throw new MustbeBetweenException("health", 1, 7);
				}
			}
			final String language = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.languagparamName);
			final Integer cardIssueFlag = command
					.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.cardIssueflagparamName);
			if (cardIssueFlag != null) {
				if (cardIssueFlag > 1) {
					throw new MustbeBetweenException("Card Issue Flag", 1, 7);
				}
			} else {
				throw new MandatoryParameterException("card Issue Flag");
			}
			final Integer cbCheck = command
					.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.cbcheckparamName);
			if (cbCheck != null) {
				if (cbCheck > 1) {
					throw new MustbeBetweenException("CB check", 1, 7);
				}
			}
			final Integer renewalFlag = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.renewalFlag);
			final Integer gurdiangender = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.gurdiangenderparamname);

			final String motherTounge = command
					.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.mothertoungeparamname);
			final String gurdianName = command
					.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.gurdainnameparamName);
			final String spouseName = command
					.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.spouseNameparamName);
			final LocalDate gurdianDateOfBirth = command
					.localDateValueOfParameterNamed(RblCustomerDetailsApiConstant.gurdiandateofBirt);
			final LocalDate spouseDateOfBirth = command
					.localDateValueOfParameterNamed(RblCustomerDetailsApiConstant.spousedateofbirtparamname);

			RblCustomer rblCustomer = RblCustomer.create(client, pensionCard, adharseedingConstant, health, language,
					cardIssueFlag, cbCheck, renewalFlag, motherTounge, gurdianName, gurdianDateOfBirth.toDate(),
					spouseName, spouseDateOfBirth.toDate(),gurdiangender);
			this.rblCustomerRepositoryWrapper.save(rblCustomer);
			return new CommandProcessingResultBuilder() //
					.withCommandId(rblCustomer.getId()) //
					.withClientId(rblCustomer.getClient().getId()) //
					.build();
		} catch (final DataIntegrityViolationException dve) {
			handleClientIdentifierDataIntegrityViolation(null, null, dve);
			return CommandProcessingResult.empty();
		}
	}

	@Override
	public CommandProcessingResult updateRblCustomer(Long rblcustomerId, JsonCommand command) {

		try {
			this.context.authenticatedUser();
			// for Validation Purpose

			final String pensionCard = command
					.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.pensioncardnoparamName);
			if (pensionCard != null) {
				if (pensionCard.length() > 50) {
					throw new MustbeBetweenException("pension card length", 1, 50);
				}
			}
			final Long adharseedingConstant = command
					.longValueOfParameterNamed(RblCustomerDetailsApiConstant.addaharseedingconstantparamName);
			if (adharseedingConstant != null) {
				if (adharseedingConstant > 2) {
					throw new MustbeBetweenException("adhar seeding Constan", 1, 2);
				}
			}
			final Integer health = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.healthparamName);
			if (health != null) {
				if (health > 7) {
					throw new MustbeBetweenException("health", 1, 7);
				}
			}
			final String language = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.languagparamName);
			final Integer cardIssueFlag = command
					.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.cardIssueflagparamName);
			if (cardIssueFlag != null) {
				if (cardIssueFlag > 1) {
					throw new MustbeBetweenException("Card Issue Flag", 1, 7);
				}
			} else {
				throw new MandatoryParameterException("card Issue Flag");
			}
			final Integer cbCheck = command
					.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.cbcheckparamName);
			if (cbCheck != null) {
				if (cbCheck > 1) {
					throw new MustbeBetweenException("CB check", 1, 7);
				}
			}

			final RblCustomer rblCustomerforUpdate = this.rblCustomerRepositoryWrapper
					.findOneWithNotFoundDetection(rblcustomerId);
			if (rblCustomerforUpdate != null) {
				final Map<String, Object> changes = rblCustomerforUpdate.update(command);

				this.rblCustomerRepositoryWrapper.saveAndFlush(rblCustomerforUpdate);
			}
			return new CommandProcessingResultBuilder() //
					.withCommandId(command.commandId()) //
					.withEntityId(rblCustomerforUpdate.getId()) //
					.build();

		} catch (final DataIntegrityViolationException dve) {
			return CommandProcessingResult.empty();
		}
	}

	@Override
	public CommandProcessingResult deleteRblCustomer(Long rblcustomerId, JsonCommand command) {
		try {
			this.context.authenticatedUser();
			final RblCustomer rblCustomerforDelete = this.rblCustomerRepositoryWrapper
					.findOneWithNotFoundDetection(rblcustomerId);
			this.rblCustomerRepositoryWrapper.delete(rblCustomerforDelete);
			return new CommandProcessingResultBuilder() //
					.withEntityId(rblCustomerforDelete.getId()) //
					.build();
		} catch (final DataIntegrityViolationException dve) {
			return CommandProcessingResult.empty();
		}
	}

	private void handleClientIdentifierDataIntegrityViolation(final String accountNumber, final String ifsc,
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
