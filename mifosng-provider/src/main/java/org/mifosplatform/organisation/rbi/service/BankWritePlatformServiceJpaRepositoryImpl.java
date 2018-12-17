package org.mifosplatform.organisation.rbi.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mifosplatform.infrastructure.configuration.domain.ConfigurationDomainService;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.holiday.service.HolidayWritePlatformServiceJpaRepositoryImpl;
import org.mifosplatform.organisation.rbi.domain.Bank;
import org.mifosplatform.organisation.rbi.domain.BankRepositoryWrapper;
import org.mifosplatform.organisation.rbi.exception.ErrorInBankDetailsUpload;
import org.mifosplatform.organisation.rbi.exception.InvalidFileFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BankWritePlatformServiceJpaRepositoryImpl implements BankWritePlatformService{
	private final static Logger logger = LoggerFactory.getLogger(HolidayWritePlatformServiceJpaRepositoryImpl.class);
	
	private final BankRepositoryWrapper bankRepositoryWrapper;
	private final ConfigurationDomainService configurationDomainService;
    private final PlatformSecurityContext context;
    private final FromJsonHelper fromApiJsonHelper;
    private final BankReadPlatformService bankReadPlatformService;
    
    @Autowired
	public BankWritePlatformServiceJpaRepositoryImpl(BankRepositoryWrapper bankRepositoryWrapper,
			ConfigurationDomainService configurationDomainService, PlatformSecurityContext context,
			FromJsonHelper fromApiJsonHelper,BankReadPlatformService bankReadPlatformService) {
		super();
		this.bankRepositoryWrapper = bankRepositoryWrapper;
		this.configurationDomainService = configurationDomainService;
		this.context = context;
		this.fromApiJsonHelper = fromApiJsonHelper;
		this.bankReadPlatformService = bankReadPlatformService;
	}
	@Override
	public boolean uploadBankDetails(InputStream inputStream) {
		this.context.authenticatedUser();
		try {
			List<String> listOfIfscCode = this.bankReadPlatformService.getListOfIfscCode();
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
		    Iterator<Row> rowIterator = sheet.iterator();
		    rowIterator.next();
		    while(rowIterator.hasNext()){
		    	Row row = rowIterator.next();
		    	//note - index of cell must be same as of table columns
		    	Bank bank = Bank.createNew(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),row.getCell(2).getCellType()==0?String.valueOf(Math.round(row.getCell(2).getNumericCellValue())):row.getCell(2).getStringCellValue(), 
		    			                   row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(),row.getCell(5).getCellType()==0?String.valueOf(Math.round(row.getCell(5).getNumericCellValue())):row.getCell(5).getStringCellValue(),
		    			                   row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue());
		    	//boolean check to avoid duplicate entries for ifsc
		    	if(!listOfIfscCode.contains(bank.getIfscCode())){
		    		this.bankRepositoryWrapper.save(bank);	
		    	}
		    }
		}catch (InvalidFormatException  | IllegalArgumentException e) {
			throw new InvalidFileFormatException();
		}catch(Exception e){
			logger.info("Error in RBI Bank details upload: " + e);
			throw new ErrorInBankDetailsUpload();
		}
		return true;
	}
}
