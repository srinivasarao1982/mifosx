package org.nirantara.client.ext.data;

import java.math.BigDecimal;

import org.nirantara.client.ext.domain.OccupationDetails;

public class OccupationDetailsData {

	private final Long id;
	private final Long occupationTypeId;
	private final BigDecimal annualRevenue;
	private final BigDecimal annualExpense;
	private final BigDecimal annualSurplus;
	
public static OccupationDetailsData formOccupationDetailsData(final OccupationDetails occupationDetails) {
		
		Long id = occupationDetails.getId();
		BigDecimal annualRevenue = occupationDetails.getAnnulaRevenue();
		BigDecimal annualExpense = occupationDetails.getAnnualExpense();
		BigDecimal annualSurplus = occupationDetails.getAnnualSurplus();

		Long occupationTypeId = null;
		if(occupationDetails.getOccupationType() != null){
			occupationTypeId = occupationDetails.getOccupationType().getId();
		}
		
		return new OccupationDetailsData(id, occupationTypeId, annualRevenue, annualExpense, annualSurplus);
	}
	
	private OccupationDetailsData(final Long id, final Long occupationTypeId, 
			final BigDecimal annualRevenue, final BigDecimal annualExpense,	final BigDecimal annualSurplus) {
		
		this.id = id;
		this.occupationTypeId = occupationTypeId;
		this.annualRevenue = annualRevenue;
		this.annualExpense = annualExpense;
		this.annualSurplus = annualSurplus;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getAnnualRevenue() {
		return annualRevenue;
	}

	public BigDecimal getAnnualExpense() {
		return annualExpense;
	}

	public BigDecimal getAnnualSurplus() {
		return annualSurplus;
	}

	public Long getOccupationTypeId() {
		return occupationTypeId;
	}
	
	
	
}
