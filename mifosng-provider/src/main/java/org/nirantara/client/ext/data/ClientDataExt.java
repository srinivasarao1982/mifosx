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
    private final String salutationLabel;
    private final Long maritalStatus;
    private final String maritalStatusLabel;
    private final Long profession;
    private final String professionLabel;
    private final String professionOthers;
    private final Long educationalQualification;
    private final String educationalQualificationLabel;
    private final Long annualIncome;
    private final String annualIncomeLabel;
    private final Long landholding;
    private final String landholdingLabel;
    private final Long houseType;
    private final String houseTypeLabel;
    private final String aadhaarNo;
    private final String panNo;
    private final Long panForm;
    private final String panFormLabel;
    private final String nregaNo;
    private final String spfirstname;
    private final String spmiddlename;
    private final String splastname;
    private final Long spouseRelationShip;
    private final String spouseRelationShipLabel;
    private final String externalId2;
    private final String maidenName;
    private final String customerMaidenName;

    public static ClientDataExt formClientDataExt(final ClientExt clientExt) {

        Long id = null;
        Long salutation = null;
        String salutationLabel = null;
        Long maritalStatus = null;
        String maritalStatusLabel = null;
        Long profession = null;
        String professionLabel = null;
        String professionOthers = null;
        Long educationalQualification = null;
        String educationalQualificationLabel = null;
        Long annualIncome = null;
        String annualIncomeLabel = null;
        Long landholding = null;
        String landholdingLabel = null;
        Long houseType = null;
        String houseTypeLabel = null;
        String aadhaarNo = null;
        String panNo = null;
        Long panForm = null;
        String panFormLabel = null;
        String nregaNo = null;
        String spfirstname = null;
        String spmiddlename = null;
        String splastname = null;
        Long spouseRelationShip = null;
        String spouseRelationShipLabel = null;
        String externalId2 = null;
        String maidenName = null;
        String customerMaidenName = null;
        
        if (clientExt != null) {

            id = clientExt.getId();

            if (clientExt.getSalutation() != null) {
                salutation = clientExt.getSalutation().getId();
                salutationLabel = clientExt.getSalutation().label();
            }

            if (clientExt.getMaritalStatus() != null) {
                maritalStatus = clientExt.getMaritalStatus().getId();
                maritalStatusLabel = clientExt.getMaritalStatus().label();
            }
            if (clientExt.getSpouseRelationShip() != null) {
                spouseRelationShip = clientExt.getSpouseRelationShip().getId();
                spouseRelationShipLabel = clientExt.getSpouseRelationShip().label();
            }
            if (clientExt.getProfession() != null) {
                profession = clientExt.getProfession().getId();
                professionLabel = clientExt.getProfession().label();
            }

            professionOthers = clientExt.getProfessionOthers();

            if (clientExt.getEducationalQualification() != null) {
                educationalQualification = clientExt.getEducationalQualification().getId();
                educationalQualificationLabel = clientExt.getEducationalQualification().label();
            }

            if (clientExt.getAnnualIncome() != null) {
                annualIncome = clientExt.getAnnualIncome().getId();
                annualIncomeLabel = clientExt.getAnnualIncome().label();
            }

            if (clientExt.getLandholding() != null) {
                landholding = clientExt.getLandholding().getId();
                landholdingLabel = clientExt.getLandholding().label();
            }

            if (clientExt.getHouseType() != null) {
                houseType = clientExt.getHouseType().getId();
                houseTypeLabel = clientExt.getHouseType().label();
            }

            aadhaarNo = clientExt.getAadhaarNo();

            panNo = clientExt.getPanNo();

            if (clientExt.getPanForm() != null) {
                panForm = clientExt.getPanForm().getId();
                panFormLabel = clientExt.getPanForm().label();
            }

            nregaNo = clientExt.getNregaNo();

            spfirstname = clientExt.getSpfirstname();
            spmiddlename = clientExt.getSpmiddlename();
            splastname = clientExt.getSplastname();
            externalId2 = clientExt.getExternalId2();
            maidenName=clientExt.getMaidenname();
            customerMaidenName=clientExt.getCustomermotherName();		

        }
        return new ClientDataExt(id, salutation, salutationLabel, maritalStatus, maritalStatusLabel, profession, professionLabel,
                professionOthers, educationalQualification, educationalQualificationLabel, annualIncome, annualIncomeLabel, landholding,
                landholdingLabel, houseType, houseTypeLabel, aadhaarNo, panNo, panForm, panFormLabel, nregaNo, spfirstname, spmiddlename,
                splastname, spouseRelationShip, spouseRelationShipLabel, externalId2,maidenName,customerMaidenName);
    }

    private ClientDataExt(final Long id, final Long salutation, final String salutationLabel, final Long maritalStatus,
            final String maritalStatusLabel, final Long profession, final String professionLabel, final String professionOthers,
            final Long educationalQualification, final String educationalQualificationLabel, final Long annualIncome,
            final String annualIncomeLabel, final Long landholding, final String landholdingLabel, final Long houseType,
            final String houseTypeLabel, final String aadhaarNo, final String panNo, final Long panForm, final String panFormLabel,
            final String nregaNo, final String spfirstname, final String spmiddlename, final String splastname,
            final Long spouseRelationShip, final String spouseRelationShipLabel, final String externalId2,
            final String maidenName,final String customerMaidenName) {

        this.id = id;
        this.salutation = salutation;
        this.salutationLabel = salutationLabel;
        this.maritalStatus = maritalStatus;
        this.maritalStatusLabel = maritalStatusLabel;
        this.profession = profession;
        this.professionLabel = professionLabel;
        this.professionOthers = professionOthers;
        this.educationalQualification = educationalQualification;
        this.educationalQualificationLabel = educationalQualificationLabel;
        this.annualIncome = annualIncome;
        this.annualIncomeLabel = annualIncomeLabel;
        this.landholding = landholding;
        this.landholdingLabel = landholdingLabel;
        this.houseType = houseType;
        this.houseTypeLabel = houseTypeLabel;
        this.aadhaarNo = aadhaarNo;
        this.panNo = panNo;
        this.panForm = panForm;
        this.panFormLabel = panFormLabel;
        this.nregaNo = nregaNo;
        this.spfirstname = spfirstname;
        this.spmiddlename = spmiddlename;
        this.splastname = splastname;
        this.spouseRelationShip = spouseRelationShip;
        this.spouseRelationShipLabel = spouseRelationShipLabel;
        this.externalId2 = externalId2;
        this.maidenName=maidenName;
        this.customerMaidenName=customerMaidenName;
    }

    public Long getId() {
        return id;
    }

    public Long getSalutation() {
        return salutation;
    }

    public String getSalutationLabel() {
        return salutationLabel;
    }

    public Long getMaritalStatus() {
        return maritalStatus;
    }

    public String getMaritalStatusLabel() {
        return maritalStatusLabel;
    }

    public Long getProfession() {
        return profession;
    }

    public String getProfessionLabel() {
        return professionLabel;
    }

    public String getProfessionOthers() {
        return professionOthers;
    }

    public Long getEducationalQualification() {
        return educationalQualification;
    }

    public String getEducationalQualificationLabel() {
        return educationalQualificationLabel;
    }

    public Long getAnnualIncome() {
        return annualIncome;
    }

    public String getAnnualIncomeLabel() {
        return annualIncomeLabel;
    }

    public Long getLandholding() {
        return landholding;
    }

    public String getLandholdingLabel() {
        return landholdingLabel;
    }

    public Long getHouseType() {
        return houseType;
    }

    public String getHouseTypeLabel() {
        return houseTypeLabel;
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

    public String getPanFormLabel() {
        return panFormLabel;
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

    public Long getSpouseRelationShip() {
        return spouseRelationShip;
    }

    public String getSpouseRelationShipLabel() {
        return spouseRelationShipLabel;
    }

    public String getExternalId2() {
        return externalId2;
    }

	public String getMaidenName() {
		return maidenName;
	}

	public String getCustomerMaidenName() {
		return customerMaidenName;
	}
    

}
