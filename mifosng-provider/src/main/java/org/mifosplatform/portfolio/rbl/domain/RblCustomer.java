package org.mifosplatform.portfolio.rbl.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.rbl.api.RblCustomerDetailsApiConstant;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_rblcustomer") 
public class RblCustomer extends AbstractAuditableCustom<AppUser, Long>{
	
	@ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
	@Column(name = "pension_card", length = 8)
    private String pensionCard;
	
	@Column(name = "adharSeeding_constant", length = 8)
    private Long adharseedingconstant;
	
	@Column(name = "health", length = 8)
    private Integer health;
	
	@Column(name = "gurdian_gender", length = 8)
    private Integer gurdiangender;
	
	@Column(name = "language", length = 8)
    private String language;
	
	@Column(name = "card_issue_fl", length = 8)
    private Integer cardIssueFl;
	
	@Column(name = "cb_check", length = 8)
    private Integer cbCheck;
	
	@Column(name = "renewal_fl", length = 8)
    private Integer renewalFl;
	
	@Column(name = "mother_tounge", length = 8)
    private String motheroung;
	
	
	 @Column(name = "gurdian_name", length = 256)
	 private String gurdianName;
	
	@Column(name = "gurdian_DatofBirth" )
    private Date gurdianDateOfBirth;
	
	 @Column(name = "spouse_name", length = 256)
	 private String spouseName;
	
	@Column(name = "spouse_DatofBirth" )
    private Date spouseDateOfBirth;
	
	
	public static RblCustomer create(final Client client, final String pensionCard, final Long adharseedingconstant,final  Integer health, final String language,
			final Integer cardIssueFl,final Integer cbCheck, final Integer renewalFl, final String motheroung, final String gurdianName,
			final Date gurdianDateOfBirth, final String spouseName, final Date spouseDateOfBirth,final Integer gurdiangender){
		 return new RblCustomer(client,pensionCard,adharseedingconstant,health,language,cardIssueFl,cbCheck,renewalFl,motheroung,gurdianName,gurdianDateOfBirth,spouseName,spouseDateOfBirth,gurdiangender); 
	}
	
	public Map<String, Object> update(final JsonCommand command) {
		
        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

        if (command.isChangeInLongParameterNamed(RblCustomerDetailsApiConstant.clintIdparamnam, this.client.getId())) {
            final Long newValue = command.longValueOfParameterNamed(RblCustomerDetailsApiConstant.clintIdparamnam);
            actualChanges.put(RblCustomerDetailsApiConstant.clintIdparamnam, newValue);
           }
        if (command.isChangeInStringParameterNamed(RblCustomerDetailsApiConstant.pensioncardnoparamName, this.pensionCard)) {
            final String newValue = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.pensioncardnoparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.pensioncardnoparamName, newValue);
            this.pensionCard=newValue;
        }
        
        if (command.isChangeInLongParameterNamed(RblCustomerDetailsApiConstant.addaharseedingconstantparamName, this.adharseedingconstant)) {
            final Long newValue = command.longValueOfParameterNamed(RblCustomerDetailsApiConstant.addaharseedingconstantparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.addaharseedingconstantparamName, newValue);
            this.adharseedingconstant=newValue;
        }
        
        if (command.isChangeInIntegerParameterNamed(RblCustomerDetailsApiConstant.healthparamName, this.health)) {
            final Integer newValue = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.healthparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.healthparamName, newValue);
            this.health=newValue;
        }

        if (command.isChangeInStringParameterNamed(RblCustomerDetailsApiConstant.languagparamName, this.language)) {
            final String newValue = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.languagparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.languagparamName, newValue);
            this.language=newValue;
        }
        
        if (command.isChangeInIntegerParameterNamed(RblCustomerDetailsApiConstant.cardIssueflagparamName, this.cardIssueFl)) {
            final Integer newValue = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.cardIssueflagparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.cardIssueflagparamName, newValue);
            this.cardIssueFl=newValue;
        }
        
        if (command.isChangeInIntegerParameterNamed(RblCustomerDetailsApiConstant.gurdiangenderparamname, this.gurdiangender)) {
            final Integer newValue = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.gurdiangenderparamname);
            actualChanges.put(RblCustomerDetailsApiConstant.gurdiangenderparamname, newValue);
            this.gurdiangender=newValue;
        }
        if (command.isChangeInIntegerParameterNamed(RblCustomerDetailsApiConstant.cbcheckparamName, this.cbCheck)) {
            final Integer newValue = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.cbcheckparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.cbcheckparamName, newValue);
            this.cbCheck=newValue;
        }
        
        if (command.isChangeInIntegerParameterNamed(RblCustomerDetailsApiConstant.renewalFlag, this.renewalFl)) {
            final Integer newValue = command.integerValueOfParameterNamed(RblCustomerDetailsApiConstant.renewalFlag);
            actualChanges.put(RblCustomerDetailsApiConstant.renewalFlag, newValue);
            this.renewalFl=newValue;
        }
        
        if (command.isChangeInStringParameterNamed(RblCustomerDetailsApiConstant.mothertoungparamname, this.motheroung)) {
            final String newValue = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.mothertoungeparamname);
            actualChanges.put(RblCustomerDetailsApiConstant.mothertoungeparamname, newValue);
            this.motheroung=newValue;
        }
        
        if (command.isChangeInStringParameterNamed(RblCustomerDetailsApiConstant.gurdainnameparamName, this.gurdianName)) {
            final String newValue = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.gurdainnameparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.gurdainnameparamName, newValue);
            this.gurdianName = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        if (command.isChangeInStringParameterNamed(RblCustomerDetailsApiConstant.spouseNameparamName, this.spouseName)) {
            final String newValue = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.spouseNameparamName);
            actualChanges.put(RblCustomerDetailsApiConstant.spouseNameparamName, newValue);
            this.spouseName = StringUtils.defaultIfEmpty(newValue, null);
        }
        
        final String dateFormatAsInput = command.dateFormat();
        final String localeAsInput = command.locale();

        
        if (command.isChangeInLocalDateParameterNamed(RblCustomerDetailsApiConstant.gurdiandateofBirt, getGurdianDateofBirt())) {
            final String valueAsInput = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.gurdiandateofBirt);
            actualChanges.put(RblCustomerDetailsApiConstant.gurdiandateofBirt, valueAsInput);
            actualChanges.put(RblCustomerDetailsApiConstant.gurdiandateofBirt, dateFormatAsInput);
            actualChanges.put(PartialLoanApiConstant.localeParamName, localeAsInput);

            final LocalDate newValue = command.localDateValueOfParameterNamed(RblCustomerDetailsApiConstant.gurdiandateofBirt);
            this.gurdianDateOfBirth = newValue.toDate();
        }
        if (command.isChangeInLocalDateParameterNamed(RblCustomerDetailsApiConstant.spousedateofbirtparamname, getSpouseDateofBirthDate())) {
            final String valueAsInput = command.stringValueOfParameterNamed(RblCustomerDetailsApiConstant.spousedateofbirtparamname);
            actualChanges.put(RblCustomerDetailsApiConstant.gurdiandateofBirt, valueAsInput);
            actualChanges.put(RblCustomerDetailsApiConstant.gurdiandateofBirt, dateFormatAsInput);
            actualChanges.put(PartialLoanApiConstant.localeParamName, localeAsInput);

            final LocalDate newValue = command.localDateValueOfParameterNamed(RblCustomerDetailsApiConstant.spousedateofbirtparamname);
            this.spouseDateOfBirth = newValue.toDate();
        }
        
       
       return actualChanges;
    }

	 public LocalDate getGurdianDateofBirt() {
         return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.gurdianDateOfBirth), null);
     }
     public LocalDate getSpouseDateofBirthDate() {
         return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.spouseDateOfBirth), null);
     }
	public RblCustomer(Client client, String pensionCard, Long adharseedingconstant, Integer health, String language,
			Integer cardIssueFl, Integer cbCheck, Integer renewalFl, String motheroung, String gurdianName,
			Date gurdianDateOfBirth, String spouseName, Date spouseDateOfBirth,Integer gurdiangender) {
		super();
		this.client = client;
		this.pensionCard = pensionCard;
		this.adharseedingconstant = adharseedingconstant;
		this.health = health;
		this.language = language;
		this.cardIssueFl = cardIssueFl;
		this.cbCheck = cbCheck;
		this.renewalFl = renewalFl;
		this.motheroung = motheroung;
		this.gurdianName = gurdianName;
		this.gurdianDateOfBirth = gurdianDateOfBirth;
		this.spouseName = spouseName;
		this.spouseDateOfBirth = spouseDateOfBirth;
		this.gurdiangender=gurdiangender;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getPensionCard() {
		return pensionCard;
	}

	public void setPensionCard(String pensionCard) {
		this.pensionCard = pensionCard;
	}

	public Long getAdharseedingconstant() {
		return adharseedingconstant;
	}

	public void setAdharseedingconstant(Long adharseedingconstant) {
		this.adharseedingconstant = adharseedingconstant;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getCardIssueFl() {
		return cardIssueFl;
	}

	public void setCardIssueFl(Integer cardIssueFl) {
		this.cardIssueFl = cardIssueFl;
	}

	public Integer getCbCheck() {
		return cbCheck;
	}

	public void setCbCheck(Integer cbCheck) {
		this.cbCheck = cbCheck;
	}

	public Integer getRenewalFl() {
		return renewalFl;
	}

	public void setRenewalFl(Integer renewalFl) {
		this.renewalFl = renewalFl;
	}

	public String getMotheroung() {
		return motheroung;
	}

	public void setMotheroung(String motheroung) {
		this.motheroung = motheroung;
	}

	public String getGurdianName() {
		return gurdianName;
	}

	public void setGurdianName(String gurdianName) {
		this.gurdianName = gurdianName;
	}

	public Date getGurdianDateOfBirth() {
		return gurdianDateOfBirth;
	}

	public void setGurdianDateOfBirth(Date gurdianDateOfBirth) {
		this.gurdianDateOfBirth = gurdianDateOfBirth;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Date getSpouseDateOfBirth() {
		return spouseDateOfBirth;
	}

	public void setSpouseDateOfBirth(Date spouseDateOfBirth) {
		this.spouseDateOfBirth = spouseDateOfBirth;
	}
    
	
}
