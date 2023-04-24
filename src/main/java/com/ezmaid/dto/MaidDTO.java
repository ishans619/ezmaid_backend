package com.ezmaid.dto;

public class MaidDTO {

	private String maidId;
	private String fName; 
	private String mName; 
	private String lName; 
	private String contactNumber; 
	private String address; 
	private String adharCardNumber; 
	private String panCardNumber; 
	private String email;
	
	public MaidDTO() {
		
		
	}
	
	public MaidDTO(String maidId, String fName, String mName, String lName, String contactNumber, String address,
			String adharCardNumber, String panCardNumber, String email) {
		this.maidId = maidId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.adharCardNumber = adharCardNumber;
		this.panCardNumber = panCardNumber;
		this.email = email;
		
	}
	
	
	
	public String getMaidId() {
		return maidId;
	}

	public void setMaidId(String maidId) {
		this.maidId = maidId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdharCardNumber() {
		return adharCardNumber;
	}

	public void setAdharCardNumber(String adharCardNumber) {
		this.adharCardNumber = adharCardNumber;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MaidDTO [maidId=" + maidId + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", contactNumber=" + contactNumber
				+ ", address=" + address + ", adharCardNumber=" + adharCardNumber + ", panCardNumber=" + panCardNumber
				+ ", email=" + email + "]";
	}
	
}
