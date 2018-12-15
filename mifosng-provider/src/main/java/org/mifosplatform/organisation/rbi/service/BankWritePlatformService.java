package org.mifosplatform.organisation.rbi.service;

import java.io.InputStream;

import org.springframework.security.access.prepost.PreAuthorize;
public interface BankWritePlatformService {
	@PreAuthorize(value = "hasAnyRole('ALL_FUNCTIONS', 'CREATE_DOCUMENT')")
	boolean uploadBankDetails(InputStream inputStream);
}
