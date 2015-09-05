package org.nirantara.client.ext.data;

import org.nirantara.client.ext.domain.ClientExt;


/**
 * This is for only Nirantara Requirments
 * 
 * @author CT
 *
 */
public class ClientDataExt {

	private final Long id;
	private final Long salutation;
	private final Long maritalStatus;	
	private final Long profession;
	private final String professionOthers;
	private final Long educationalQualification;
	private final Long annualIncome;
	private final Long landholding;
	private final Long houseType;
	private final String aadhaarNo;
	private final String panNo;
	private final Long panForm;	
	private final String nregaNo;
	private final String spfirstname;
	private final String spmiddlename;
	private final String splastname;
	
	public static ClientDataExt formClientDataExt(final ClientExt clientExt) {
		
		Long id = null;
		Long salutation = null;
		Long maritalStatus = null;		
		Long profession = null;
		String professionOthers = null;
		Long educationalQualification = null;
		Long annualIncome = null;
		Long landholding = null;
		Long houseType = null;
		String aadhaarNo = null;
		String panNo = null;
		Long panForm = null;		
		String nregaNo = null;
		String spfirstname = null;
		String spmiddlename = null;
		String splastname = null;
		
		if(clientExt != null){
			
			id = clientExt.getId();
			
			if(clientExt.getSalutation() != null){
				salutation = clientExt.getSalutation().getId();
			}
			
			if(clientExt.getMaritalStatus() != null){
				maritalStatus = clientExt.getMaritalStatus().getId();
			}
			
			if(clientExt.getProfession() != null){
				profession = clientExt.getProfession().getId();
			}
			
			professionOthers = clientExt.getProfessionOthers();
			
			if(clientExt.getEducationalQualification() != null){
				educationalQualification = clientExt.getEducationalQualification().getId();
			}
			
			if(clientExt.getAnnualIncome() != null){
				annualIncome = clientExt.getAnnualIncome().getId();
			}
			
			if(clientExt.getLandholding() != null){
				landholding = clientExt.getLandholding().getId();
			}
			
			if(clientExt.getHouseType() != null){
				houseType = clientExt.getHouseType().getId();
			}	
						
			aadhaarNo = clientExt.getAadhaarNo();
			
			panNo = clientExt.getPanNo();
			
			if(clientExt.getPanForm() != null){
				panForm = clientExt.getPanForm().getId();
			}
			
			nregaNo = clientExt.getNregaNo();
			
			spfirstname = clientExt.getSpfirstname();
			spmiddlename = clientExt.getSpmiddlename();
			splastname = clientExt.getSplastname();
			
		}
		return new ClientDataExt(id, salutation, maritalStatus, profession,
				professionOthers, educationalQualification, annualIncome,
				landholding, houseType, aadhaarNo, panNo, panForm, nregaNo,
				spfirstname, spmiddlename, splastname);
	}

	private ClientDataExt(final Long id, final Long salutation,
			final Long maritalStatus, final Long profession,
			final String professionOthers, final Long educationalQualification,
			final Long annualIncome, final Long landholding,
			final Long houseType, final String aadhaarNo, final String panNo,
			final Long panForm, final String nregaNo, final String spfirstname,
			final String spmiddlename, final String splastname) {
		
		this.id = id;
		this.salutation = salutation;
		this.maritalStatus = maritalStatus;
		this.profession = profession;
		this.professionOthers = professionOthers;
		this.educationalQualification = educationalQualification;
		this.annualIncome = annualIncome;
		this.landholding = landholding;
		this.houseType = houseType;
		this.aadhaarNo = aadhaarNo;
		this.panNo = panNo;
		this.panForm = panForm;
		this.nregaNo = nregaNo;
		this.spfirstname = spfirstname;
		this.spmiddlename = spmiddlename;
		this.splastname = splastname;
	}

	public Long getId() {
		return id;
	}
	
	public Long getSalutation() {
		return salutation;
	}

	public Long getMaritalStatus() {
		return maritalStatus;
	}

	public Long getProfession() {
		return profession;
	}

	public String getProfessionOthers() {
		return professionOthers;
	}

	public Long getEducationalQualification() {
		return educationalQualification;
	}

	public Long getAnnualIncome() {
		return annualIncome;
	}

	public Long getLandholding() {
		return landholding;
	}

	public Long getHouseType() {
		return houseType;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public Long getPanForm() {
		return panForm;
	}

	public String getNregaNo() {
		return nregaNo;
	}

	public String getSpfirstname() {
		return spfirstname;
	}

	public String getSpmiddlename() {
		return spmiddlename;
	}

	public String getSplastname() {
		return splastname;
	}

}
