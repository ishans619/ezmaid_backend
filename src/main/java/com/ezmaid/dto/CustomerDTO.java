package com.ezmaid.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {
	
	private String custId;
	
	@NotNull(message = "Mandatory Field")
	@NotBlank(message = "First Name is mandatory")
	private String fName; 
	
	private String mName;
	
	@NotNull(message = "Mandatory Field")
	@NotBlank(message = "Last Name is mandatory")
	private String lName;
	
	@NotNull(message = "Mandatory Field")
	@Pattern(regexp="(^$|[1-9]{1}[0-9]{9})", message = "Contact Number Invalid")
	private String contactNumber;
	
	@NotNull(message = "Mandatory Field")
	private String address;
	
	@NotNull(message = "Mandatory Field")
	@Pattern(regexp="(^$|[0-9]{16})", message = "Aadhaar Card Number Invalid")
	private String adharCardNumber;
	
	@NotNull(message = "Mandatory Field")
	@Pattern(regexp="(^$|[A-Z]{5}[0-9]{4}[A-Z]{1})", message = "Pan Card Number Invalid")
	private String panCardNumber;
	
	@NotNull(message = "Mandatory Field")
	@Email(message = "Invalid email")
	private String email;
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(String custId, String fName, String mName, String lName, String contactNumber, String address,
			String adharCardNumber, String panCardNumber, String email) {
		super();
		this.custId = custId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.adharCardNumber = adharCardNumber;
		this.panCardNumber = panCardNumber;
		this.email = email;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
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
		return "CustomerDTO [custId=" + custId + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName
				+ ", contactNumber=" + contactNumber + ", address=" + address + ", adharCardNumber=" + adharCardNumber
				+ ", panCardNumber=" + panCardNumber + ", email=" + email + "]";
	}
	

}
