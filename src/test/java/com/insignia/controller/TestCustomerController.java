package com.insignia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.insignia.constants.AddressDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.entity.AddressDetails;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.CustomerPersonalDetails;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerBasicDetailsRequest;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.CustomerPersonalDetailsResponse;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.model.RolesAndPermissionsResponse;
import com.insignia.serviceInterface.CustomerServiceInterface;

@ExtendWith(MockitoExtension.class)
public class TestCustomerController {

	@InjectMocks
	private CustomerController customerController;

	@Mock
	private CustomerServiceInterface customerService;

	@Mock
	private TokenExpiredException tokenExpire;

	CustomerManagementServiceRequest customerManagementServiceRequest = new CustomerManagementServiceRequest();
	CustomerManagementServiceResponse customerManagementServiceResponse = new CustomerManagementServiceResponse();

	AddressRequest addressRequest = new AddressRequest();
	AddressResponse addressRes = new AddressResponse();
	AddressDetails addressDetails = new AddressDetails();
	CustomerBasicDetailsRequest customerBasicDetailsRequest = new CustomerBasicDetailsRequest();
	CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity();
	CustomerPersonalDetailsRequest customerPersonalDetailsRequest = new CustomerPersonalDetailsRequest();
	CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails();
	CustomerPersonalDetailsResponse customerPersonalDetailsResponse = new CustomerPersonalDetailsResponse();
	RolesAndPermissionsRequest rolesAndPermissionsRequest = new RolesAndPermissionsRequest();
	RolesAndPermissionsResponse rolesAndPermissionsResponse = new RolesAndPermissionsResponse();

	public void dataInitilization() {

		customerBasicDetailsRequest.setApplicationId("112");
		customerBasicDetailsRequest.setTenantId("1124");
		customerBasicDetailsRequest.setUserId("2545");
		customerBasicDetailsRequest.setPassword("5485");
		customerBasicDetailsRequest.setCustomerSequenceNumber(8L);
		customerBasicDetailsRequest.setEmailId("lakshminagu54@gmail.com");
		customerBasicDetailsRequest.setUserName("Lakshmi");

		customerManagementServiceRequest.setCustomerBasicDetailsRequest(customerBasicDetailsRequest);

		List<RolesAndPermissionsRequest> rolesAndPermissionsList = new ArrayList<>();

		rolesAndPermissionsRequest.setRoleId(5);
		rolesAndPermissionsRequest.setCustomerSequenceNumber(8L);
		rolesAndPermissionsRequest.setPermission1(null);
		rolesAndPermissionsRequest.setPermission2(null);
		rolesAndPermissionsRequest.setPermission3(null);
		rolesAndPermissionsRequest.setPermission4(null);
		rolesAndPermissionsRequest.setPermissionChangeDate(null);
		rolesAndPermissionsRequest.setRoleApprovedDate(null);
		rolesAndPermissionsRequest.setRoleName("admin");
		rolesAndPermissionsRequest.setRoleRevokedDate(null);
		rolesAndPermissionsRequest.setUpdatedPermissions("yes");

		rolesAndPermissionsList.add(rolesAndPermissionsRequest);

		customerManagementServiceRequest.setRolesAndPermissionsRequestList(rolesAndPermissionsList);

		List<CustomerPersonalDetailsRequest> customerPersonalDetailsRequestList = new ArrayList<>();

		customerPersonalDetailsRequest.setCustomerSequenceNumber(8L);
		customerPersonalDetailsRequest.setSequenceNumber(5);
		customerPersonalDetailsRequest.setFirstName("lakshmi");
		customerPersonalDetailsRequest.setLastName("Pragallapati");
		customerPersonalDetailsRequest.setMiddleName("Nagu");
		customerPersonalDetailsRequest.setAge("29");
		customerPersonalDetailsRequest.setGender("female");
		customerPersonalDetailsRequest.setCustomerMobileNumber("1234567891");
		customerPersonalDetailsRequest.setAlternativeMobileNumber("765897653");
		customerPersonalDetailsRequest.setCustomerEmailId("lakshmisidarth4@gmail.com");
		customerPersonalDetailsRequest.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
		customerPersonalDetailsRequest.setCustomerLandlineNumber("8765987");

		customerPersonalDetailsRequestList.add(customerPersonalDetailsRequest);

		customerManagementServiceRequest.setCustomerPersonalDetailsRequestList(customerPersonalDetailsRequestList);

		List<AddressRequest> addressRequestList = new ArrayList<>();

		addressRequest.setCustomerSequenceNumber(8L);
		addressRequest.setAddressLine1("VinayakaTemple");
		addressRequest.setAddressLine2("CinemaRoad");
		addressRequest.setLandmark("Opp:ApolloHospital");
		addressRequest.setCity("Kakinada");
		addressRequest.setState("AndhraPradesh");
		addressRequest.setCountry("India");
		addressRequest.setEmailId("lakshmisidharth678@gmail.com");
		addressRequest.setZipCode("8765");
		addressRequest.setMobileNumber("9875689378");
		addressRequest.setLandlineNumber("98765895");
		addressRequest.setisBillingAddress(false);
		addressRequest.setIsDefaultAddress(false);

		addressRequestList.add(addressRequest);

		customerManagementServiceRequest.setAddressRequestList(addressRequestList);

		List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList = new ArrayList<>();

		rolesAndPermissionsResponse.setPermission1(null);
		rolesAndPermissionsResponse.setPermission2(null);
		rolesAndPermissionsResponse.setPermission3(null);
		rolesAndPermissionsResponse.setPermission4(null);
		rolesAndPermissionsResponse.setPermissionChangeDate(null);
		rolesAndPermissionsResponse.setRoleApprovedDate(null);
		rolesAndPermissionsResponse.setRoleName("admin");
		rolesAndPermissionsResponse.setRoleRevokedDate(null);
		rolesAndPermissionsResponse.setUpdatedPermissions("yes");

		rolesAndPermissionsResponseList.add(rolesAndPermissionsResponse);

		customerManagementServiceResponse.setRolesAndPermissionsResponseList(rolesAndPermissionsResponseList);

		List<AddressResponse> addressResponseList = new ArrayList<>();

		addressRes.setAddressLine1("VinayakaTemple");
		addressRes.setAddressLine2("CinemaRoad");
		addressRes.setLandmark("Opp:ApolloHospital");
		addressRes.setCity("Kakinada");
		addressRes.setState("AndhraPradesh");
		addressRes.setCountry("India");
		addressRes.setEmailId("lakshmisidharth678@gmail.com");
		addressRes.setZipCode("8765");
		addressRes.setMobileNumber("9875689378");
		addressRes.setLandlineNumber("98765895");
		addressRes.setBillingAddress(false);
		addressRes.setDefaultAddress(false);

		addressResponseList.add(addressRes);

		customerManagementServiceResponse.setAddressResponseList(addressResponseList);

		List<CustomerPersonalDetailsResponse> customerPersonalDetailsResponseList = new ArrayList<>();

		customerPersonalDetailsResponse.setFirstName("lakshmi");
		customerPersonalDetailsResponse.setLastName("Pragallapati");
		customerPersonalDetailsResponse.setMiddleName("Nagu");
		customerPersonalDetailsResponse.setAge("29");
		customerPersonalDetailsResponse.setGender("female");
		customerPersonalDetailsResponse.setCustomerMobileNumber("1234567891");
		customerPersonalDetailsResponse.setAlternativeMobileNumber("765897653");
		customerPersonalDetailsResponse.setCustomerEmailId("lakshmisidarth4@gmail.com");
		customerPersonalDetailsResponse.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
		customerPersonalDetailsResponse.setCustomerLandlineNumber("8765987");

		customerPersonalDetailsResponseList.add(customerPersonalDetailsResponse);
		customerManagementServiceResponse.setCustomerPersonalDetailsResponseList(customerPersonalDetailsResponseList);

	}

	@Test
	public void testSaveAndUpdateCustomerDetailsValidations() throws InvalidInputParametersException {
		dataInitilization();
		customerBasicDetailsRequest.setApplicationId(null);
		customerBasicDetailsRequest.setTenantId(null);
		customerBasicDetailsRequest.setUserId(null);
		customerBasicDetailsRequest.setPassword(null);

		addressRequest.setAddressLine1(null);
		addressRequest.setCity(null);
		addressRequest.setState(null);
		addressRequest.setCountry(null);
		addressRequest.setZipCode(null);

		ResponseEntity<?> saveAllCustomerDetails = customerController
				.saveAllCustomerDetails(customerManagementServiceRequest);
		assertEquals(HttpStatus.BAD_REQUEST, saveAllCustomerDetails.getStatusCode());

		ResponseEntity<?> updateCustomerDetails = customerController
				.updateAllCustomerDetails(customerManagementServiceRequest);
		assertEquals(HttpStatus.BAD_REQUEST, updateCustomerDetails.getStatusCode());
	}

	@Test
	public void testForSaveAllCustomerDetails() throws InvalidInputParametersException, Exception {
		dataInitilization();

		when(customerService.saveAllCustomerDetails(customerManagementServiceRequest))
				.thenReturn(customerManagementServiceResponse);
		ResponseEntity<?> response = customerController.saveAllCustomerDetails(customerManagementServiceRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testForUpdateAllCustomerDetails() throws InvalidInputParametersException, Exception {
		dataInitilization();
		when(customerService.updateAllCustomerDetails(customerManagementServiceRequest))
				.thenReturn(customerManagementServiceResponse);

		ResponseEntity<?> updateAllCustomerDetails = customerController
				.updateAllCustomerDetails(customerManagementServiceRequest);
		assertEquals(HttpStatus.OK, updateAllCustomerDetails.getStatusCode());
	}

	@Test
	public void testForDeleteAllCustomerDetails() throws InvalidInputParametersException, Exception {
		

		Long customerSequenceNumber = 8L;
		doNothing().when(customerService).deleteCustomerAssociatedDetails(customerSequenceNumber);

		ResponseEntity<?> deleteCustomerAssociatedDetails = customerController
				.deleteCustomerAssociatedDetails(customerSequenceNumber);
		verify(customerService, times(1)).deleteCustomerAssociatedDetails(customerSequenceNumber);
		assertEquals("Record Successfully Deleted", deleteCustomerAssociatedDetails.getBody());

	}

	@Test
	public void testGetAllCustomerDetails() throws InvalidInputParametersException, Exception {
		

		Long customerSequenceNumber = 8L;

		CustomerManagementServiceResponse response = new CustomerManagementServiceResponse();

		Optional<CustomerManagementServiceResponse> expectedResult = Optional.of(response);

		when(customerService.getAllCustomerData(customerSequenceNumber)).thenReturn(expectedResult);
		ResponseEntity<?> getAllCustomersWithDetails = customerController.getAllCustomerData(customerSequenceNumber);
		assertEquals(HttpStatus.OK, getAllCustomersWithDetails.getStatusCode());

	}

	@Test
	public void testForTokenExpired() throws InvalidInputParametersException, Exception {
		

		Long customerSequenceNumber = 8L;

		// Update CustomerDetails
		when(customerService.updateAllCustomerDetails(customerManagementServiceRequest))
				.thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> updateAllCustomerDetails = customerController
				.updateAllCustomerDetails(customerManagementServiceRequest);

		// Delete CustomerDetails
		doThrow(new TokenExpiredException("", "")).when(customerService)
				.deleteCustomerAssociatedDetails(customerSequenceNumber);

		ResponseEntity<?> responseEntity = customerController.deleteCustomerAssociatedDetails(customerSequenceNumber);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		assertTrue(responseEntity.getBody() instanceof AddressResponse);
		AddressResponse errorResponse = (AddressResponse) responseEntity.getBody();
		assertEquals(AddressDetailsConstants.validateTokenErrorCode, errorResponse.getErrorCode());
		assertEquals(AddressDetailsConstants.validateToken, errorResponse.getErrorMessage());

		verify(customerService, times(1)).deleteCustomerAssociatedDetails(customerSequenceNumber);

		// Get All CustomeDetails
		when(customerService.getAllCustomerData(customerSequenceNumber)).thenThrow(new TokenExpiredException(""));
		ResponseEntity<?> getAllCustomersWithDetails = customerController.getAllCustomerData(customerSequenceNumber);

		assertEquals(HttpStatus.BAD_REQUEST, updateAllCustomerDetails.getStatusCode());
		assertEquals(HttpStatus.BAD_REQUEST, getAllCustomersWithDetails.getStatusCode());

	}

	@Test
	public void testForException() throws InvalidInputParametersException, TokenExpiredException {
	

		Long customerSequenceNumber = 8L;

		// Update CustomerDetails
		when(customerService.updateAllCustomerDetails(customerManagementServiceRequest))
				.thenThrow(new NullPointerException(""));
		ResponseEntity<?> updateAllCustomerDetails = customerController
				.updateAllCustomerDetails(customerManagementServiceRequest);
		assertEquals(HttpStatus.BAD_REQUEST, updateAllCustomerDetails.getStatusCode());

		// Delete CustomerDetails

		doThrow(new NullPointerException("")).when(customerService)
				.deleteCustomerAssociatedDetails(customerSequenceNumber);
		

		ResponseEntity<?> responseEntity = customerController.deleteCustomerAssociatedDetails(customerSequenceNumber);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		// Get All CustomeDetails
		when(customerService.getAllCustomerData(customerSequenceNumber)).thenThrow(new NullPointerException(""));
		ResponseEntity<?> getAllCustomersWithDetails = customerController.getAllCustomerData(customerSequenceNumber);
		assertEquals(HttpStatus.BAD_REQUEST, getAllCustomersWithDetails.getStatusCode());

	}
}
