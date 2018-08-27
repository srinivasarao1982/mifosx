package org.mifosplatform.portfolio.rbl.data;

import java.util.Date;

public class RblCustomerData {

	private final Long clientId;
	private final String pensioncard;
	private final Long adharseedingconstant;
	private final Integer health;
	private final String language;
	private final Integer caedissueflag;
	private final Integer cbchck;
	private final Integer renwalflag;
	private final String mothertoung;
	private final String gurdainName;
	private final Date gurdiandateofBirth;
	private final String spouseName;
	private final Date spousedateofbIrt;
	private final Long gurdiangender;
	
	

	public static RblCustomerData create(final Long clientId, final String pensionCard, final Long adharsdingconstant,
			final Integer health, final String language, final Integer cardIssueFlag, final Integer cbCheck,
			final Integer renewalFlag, final String mothertounge, final String gurdianName,
			final Date guedianDateOfBirth, final String spouseName, final Date spouseDateOfBirth,final Long gurdiangender) {

		return new RblCustomerData(clientId, pensionCard, adharsdingconstant, health, language, cardIssueFlag, cbCheck,
				renewalFlag, mothertounge, gurdianName, guedianDateOfBirth, spouseName, spouseDateOfBirth,gurdiangender);

	}

	public RblCustomerData(Long clientId, String pensionCard, Long adharsdingconstant, Integer health, String language,
			Integer cardIssueFlag, Integer cbCheck, Integer renewalFlag, String mothertounge, String gurdianName,
			Date guedianDateOfBirth, String spouseName, Date spouseDateOfBirth,Long gurdiangender) {
		super();
		this.clientId = clientId;
		this.pensioncard = pensionCard;
		this.adharseedingconstant = adharsdingconstant;
		this.health = health;
		this.language = language;
		this.caedissueflag = cardIssueFlag;
		this.cbchck = cbCheck;
		this.renwalflag = renewalFlag;
		this.mothertoung = mothertounge;
		this.gurdainName = gurdianName;
		this.gurdiandateofBirth = guedianDateOfBirth;
		this.spouseName = spouseName;
		this.spousedateofbIrt = spouseDateOfBirth;
		this.gurdiangender=gurdiangender;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getPensioncard() {
		return pensioncard;
	}

	public Long getAdharseedingconstant() {
		return adharseedingconstant;
	}

	public Integer getHealth() {
		return health;
	}

	public String getLanguage() {
		return language;
	}

	public Integer getCaedissueflag() {
		return caedissueflag;
	}

	public Integer getCbchck() {
		return cbchck;
	}

	public Integer getRenwalflag() {
		return renwalflag;
	}

	public String getMothertoung() {
		return mothertoung;
	}

	public String getGurdainName() {
		return gurdainName;
	}

	public Date getGurdiandateofBirth() {
		return gurdiandateofBirth;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public Date getSpousedateofbIrt() {
		return spousedateofbIrt;
	}

	
}
