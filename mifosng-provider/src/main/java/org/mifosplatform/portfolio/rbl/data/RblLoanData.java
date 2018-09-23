package org.mifosplatform.portfolio.rbl.data;

public class RblLoanData {

	private Long id;
	private Integer pslcode;
	private Integer topUpflag;
	private String hosiptalCash;
	private String prpaidcharge;

	public static RblLoanData create(final Long id,final Integer pslcode, final Integer topUpflag, final String hosiptalCash,
			final String prpaidcharge) {

		return new RblLoanData(id,pslcode, topUpflag, hosiptalCash, prpaidcharge);
	}

	public RblLoanData(Long id,Integer pslcode, Integer topUpflag, String hosiptalCash, String prpaidcharge) {
		super();
		this.id=id;
		this.pslcode = pslcode;
		this.topUpflag = topUpflag;
		this.hosiptalCash = hosiptalCash;
		this.prpaidcharge = prpaidcharge;
	}

	public Integer getPslcode() {
		return pslcode;
	}

	public void setPslcode(Integer pslcode) {
		this.pslcode = pslcode;
	}

	public Integer getTopUpflag() {
		return topUpflag;
	}

	public void setTopUpflag(Integer topUpflag) {
		this.topUpflag = topUpflag;
	}

	public String getHosiptalCash() {
		return hosiptalCash;
	}

	public void setHosiptalCash(String hosiptalCash) {
		this.hosiptalCash = hosiptalCash;
	}

	public String getPrpaidcharge() {
		return prpaidcharge;
	}

	public void setPrpaidcharge(String prpaidcharge) {
		this.prpaidcharge = prpaidcharge;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
