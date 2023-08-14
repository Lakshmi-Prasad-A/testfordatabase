package com.insignia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address_details")
public class AddressDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sequence_number")
	private Integer sequenceNumber;

//	@Column(name = "customer_sequence_number")
//	private Long customerSequenceNumber;

	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "Zip_code", length = 6, nullable = false)
	private String zipCode;

	@Column(name = "mobile_number", length = 10)
	private String mobileNumber;

	@Column(name = "landline_number", length = 16)
	private String landlineNumber;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "is_default_address")
	private boolean isDefaultAddress;

	@Column(name = "is_billing_address")
	private boolean isBillingAddress;

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

//	public Long getCustomerSequenceNumber() {
//		return customerSequenceNumber;
//	}
//
//	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
//		this.customerSequenceNumber = customerSequenceNumber;
//	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getLandlineNumber() {
		return landlineNumber;
	}

	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isDefaultAddress() {
		return isDefaultAddress;
	}

	public void setDefaultAddress(boolean isDefaultAddress) {
		this.isDefaultAddress = isDefaultAddress;
	}

	public boolean isBillingAddress() {
		return isBillingAddress;
	}

	public void setBillingAddress(boolean isBillingAddress) {
		this.isBillingAddress = isBillingAddress;
	}

	public AddressDetails(String addressLine1, String addressLine2, String landmark,
			String city, String state, String country, String zipCode, String mobileNumber, String landlineNumber,
			String emailId, boolean isDefaultAddress, boolean isBillingAddress) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.mobileNumber = mobileNumber;
		this.landlineNumber = landlineNumber;
		this.emailId = emailId;
		this.isDefaultAddress = isDefaultAddress;
		this.isBillingAddress = isBillingAddress;
	}

	public AddressDetails() {
		super();
	}

}
