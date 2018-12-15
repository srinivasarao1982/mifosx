package org.mifosplatform.organisation.rbi.service;

import java.util.List;

import org.mifosplatform.organisation.rbi.data.BankData;

public interface BankReadPlatformService {

  List<String> getListOfIfscCode();
  BankData getBankDetailsByIfscCode(String ifscCode);
}
