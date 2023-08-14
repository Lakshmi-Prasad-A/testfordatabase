package com.insignia.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CustomerManagementServiceRequest {
	
	private CustomerBasicDetailsRequest customerBasicDetailsRequest;
	
	private List<AddressRequest> addressRequestList=new ArrayList<>();

	private List<RolesAndPermissionsRequest> rolesAndPermissionsRequestList=new ArrayList<>();
	
	private List<CustomerPersonalDetailsRequest> customerPersonalDetailsRequestList=new ArrayList<>();

	
}
