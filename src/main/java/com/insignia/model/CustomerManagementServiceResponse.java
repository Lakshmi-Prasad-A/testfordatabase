package com.insignia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerManagementServiceResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<AddressResponse> addressResponseList=new ArrayList<>();

	private List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList;
	
	private List<CustomerPersonalDetailsResponse> customerPersonalDetailsResponseList;


	private String successMessage;

	private String errorCode;

	private String errorMessage;

	
	public List<AddressResponse> getAddressResponseList() {
		return addressResponseList;
	}

	public void setAddressResponseList(List<AddressResponse> addressResponseList) {
		this.addressResponseList = addressResponseList;
	}

	public List<RolesAndPermissionsResponse> getRolesAndPermissionsResponseList() {
		return rolesAndPermissionsResponseList;
	}

	public void setRolesAndPermissionsResponseList(List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList) {
		this.rolesAndPermissionsResponseList = rolesAndPermissionsResponseList;
	}

	

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public List<CustomerPersonalDetailsResponse> getCustomerPersonalDetailsResponseList() {
		return customerPersonalDetailsResponseList;
	}

	public void setCustomerPersonalDetailsResponseList(
			List<CustomerPersonalDetailsResponse> customerPersonalDetailsResponseList) {
		this.customerPersonalDetailsResponseList = customerPersonalDetailsResponseList;
	}
	
	public CustomerManagementServiceResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public CustomerManagementServiceResponse() {

	}

	@Override
	public String toString() {
		return "CustomerManagementServiceResponse [addressResponseList=" + addressResponseList
				+ ", rolesAndPermissionsResponseList=" + rolesAndPermissionsResponseList
				+ ", customerPersonalDetailsResponseList=" + customerPersonalDetailsResponseList + ", successMessage="
				+ successMessage + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

	}
