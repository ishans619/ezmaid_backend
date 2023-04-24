package com.ezmaid.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "maid")
public class Maid {
	
	@Id
	@Column(name = "maid_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String maidId;
	
	@Column(name = "f_name")
	private String fName;
	
	@Column(name = "m_name")
	private String mName;
	
	@Column(name = "l_name")
	private String lName;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "adhar_card_number")
	private String adharCardNumber;
	
	@Column(name = "pan_card_number")
	private String panCardNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "is_first_login")
	private Boolean isFirstLogin;
	
	@Column(name = "is_verified")
	private Boolean isVerified;
	
	@Column(name = "crtd_date")
	private LocalDate crtdDate;
	
	@Column(name = "lst_updt_date")
	private LocalDate lstUpdtDate;
	
	@Column(name = "lst_updt_by")
	private String lstUpdtBy; 
	
	
    public Maid() {
    	
    }
    
    


	public Maid(String maidId, String fName, String mName, String lName, String contactNumber, String address,
			String adharCardNumber, String panCardNumber, String email, String password, Boolean isFirstLogin,
			Boolean isVerified, LocalDate crtdDate, LocalDate lstUpdtDate, String lstUpdtBy) {
		super();
		this.maidId = maidId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.adharCardNumber = adharCardNumber;
		this.panCardNumber = panCardNumber;
		this.email = email;
		this.password = password;
		this.isFirstLogin = isFirstLogin;
		this.isVerified = isVerified;
		this.crtdDate = crtdDate;
		this.lstUpdtDate = lstUpdtDate;
		this.lstUpdtBy = lstUpdtBy;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}


	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}


	public Boolean getIsVerified() {
		return isVerified;
	}


	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}


	public LocalDate getCrtdDate() {
		return crtdDate;
	}


	public void setCrtdDate(LocalDate crtdDate) {
		this.crtdDate = crtdDate;
	}


	public LocalDate getLstUpdtDate() {
		return lstUpdtDate;
	}


	public void setLstUpdtDate(LocalDate lstUpdtDate) {
		this.lstUpdtDate = lstUpdtDate;
	}


	public String getLstUpdtBy() {
		return lstUpdtBy;
	}


	public void setLstUpdtBy(String lstUpdtBy) {
		this.lstUpdtBy = lstUpdtBy;
	}



	@Override
	public String toString() {
		return "Maid [maidId=" + maidId + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName
				+ ", contactNumber=" + contactNumber + ", address=" + address + ", adharCardNumber=" + adharCardNumber
				+ ", panCardNumber=" + panCardNumber + ", email=" + email + ", password=" + password + ", isFirstLogin="
				+ isFirstLogin + ", isVerified=" + isVerified + ", crtdDate=" + crtdDate + ", lstUpdtDate="
				+ lstUpdtDate + ", lstUpdtBy=" + lstUpdtBy + "]";
	}
 
    
    
}