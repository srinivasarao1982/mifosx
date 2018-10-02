package org.mifosplatform.portfolio.rbl.data;

import java.util.Date;

import org.joda.time.LocalDate;

public class RblCustomerData {

	private final Long clientId;
	private final String pensioncard;
	private final String adharseedingconstant;
	private final String health;
	private final String language;
	private final Integer caedissueflag;
	private final Integer cbchck;
	private final Integer renwalflag;
	private final String mothertoung;
	private final String gurdainName;
	private final LocalDate gurdiandateofBirth;
	private final String spouseName;
	private final LocalDate spousedateofbIrt;
	private final Long gurdiangender;
	private final Long relation;
	private final Long gurdianTitle;
	private final String gurdianMobileNo;
	
		

	public static RblCustomerData create(final Long clientId, final String pensionCard, final String adharsdingconstant,
			final String health, final String language, final Integer cardIssueFlag, final Integer cbCheck,
			final Integer renewalFlag, final String mothertounge, final String gurdianName,
			final LocalDate guedianDateOfBirth, final String spouseName, final LocalDate spouseDateOfBirth,final Long gurdiangender,
			final Long relation,final Long gurdianTitle,final String gurdianMobileNo) {

		return new RblCustomerData(clientId, pensionCard, adharsdingconstant, health, language, cardIssueFlag, cbCheck,
				renewalFlag, mothertounge, gurdianName, guedianDateOfBirth, spouseName, spouseDateOfBirth,gurdiangender
				,relation,gurdianTitle,gurdianMobileNo);

	}

	public RblCustomerData(Long clientId, String pensionCard, String adharsdingconstant, String health, String language,
			Integer cardIssueFlag, Integer cbCheck, Integer renewalFlag, String mothertounge, String gurdianName,
			LocalDate guedianDateOfBirth, String spouseName, LocalDate spouseDateOfBirth,Long gurdiangender,final Long relation,final Long gurdianTitle,final String gurdianMobileNo) {
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
		this.relation=relation;
		this.gurdianTitle=gurdianTitle;
		this.gurdianMobileNo=gurdianMobileNo;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getPensioncard() {
		return pensioncard;
	}

	public String getAdharseedingconstant() {
		return adharseedingconstant;
	}

	public String getHealth() {
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

	public LocalDate getGurdiandateofBirth() {
		return gurdiandateofBirth;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public LocalDate getSpousedateofbIrt() {
		return spousedateofbIrt;
	}

	public Long getGurdiangender() {
		return gurdiangender;
	}

	public Long getRelation() {
		return relation;
	}

	public Long getGurdianTitle() {
		return gurdianTitle;
	}

	public String getGurdianMobileNo() {
		return gurdianMobileNo;
	}

	
}
