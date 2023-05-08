package com.ezmaid.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SignUpRequest {
	
	private String id;
	
	private boolean isCustomer;
	
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
	@Pattern(regexp="(^$|[0-9]{12})", message = "Aadhaar Card Number Invalid")
	private String adharCardNumber;
	
	@NotNull(message = "Mandatory Field")
	@Pattern(regexp="(^$|[A-Z]{5}[0-9]{4}[A-Z]{1})", message = "Pan Card Number Invalid")
	private String panCardNumber;
	
	@NotNull(message = "Mandatory Field")
	@Email(message = "Invalid email")
	private String email;
	
	@NotNull(message = "Mandatory Field")
	private String username;
	
	@NotNull(message = "Mandatory Field")
	private String password;
	
	public SignUpRequest() {
		// TODO Auto-generated constructor stub
	}

	public SignUpRequest(String id, boolean isCustomer, String fName, String mName, String lName, String contactNumber, String address,
			String adharCardNumber, String panCardNumber, String email, String username, String password) {
		super();
		this.id = id;
		this.isCustomer=isCustomer;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.adharCardNumber = adharCardNumber;
		this.panCardNumber = panCardNumber;
		this.email = email;
		this.username=username;
		this.password=password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignUpRequest [id=" + id + ", isCustomer=" + isCustomer + ", fName=" + fName + ", mName=" + mName
				+ ", lName=" + lName + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", adharCardNumber=" + adharCardNumber + ", panCardNumber=" + panCardNumber + ", email=" + email
				+ ", username=" + username + ", password=" + password + "]";
	}

}
