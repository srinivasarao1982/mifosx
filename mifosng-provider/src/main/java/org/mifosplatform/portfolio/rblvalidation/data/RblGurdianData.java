package org.mifosplatform.portfolio.rblvalidation.data;

public class RblGurdianData {
	private String title;
    private String name;
    private String relation;
    private String dateOfBirth;
    private String gender;
    private  RblAddressData address;
    
    
    public static  RblGurdianData create(String title, String name, String relation, String dateOfBirth, String gender,
			RblAddressData address){
    	return new RblGurdianData(title,name,relation,dateOfBirth,gender,address);
    }
    
	public RblGurdianData(String title, String name, String relation, String dateOfBirth, String gender,
			RblAddressData address) {
		super();
		this.title = title;
		this.name = name;
		this.relation = relation;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public RblAddressData getAddress() {
		return address;
	}
	public void setAddress(RblAddressData address) {
		this.address = address;
	}
    
    
    
    

}
