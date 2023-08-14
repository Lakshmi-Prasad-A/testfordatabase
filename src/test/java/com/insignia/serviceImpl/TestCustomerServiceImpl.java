
package com.insignia.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.CustomerDaoInterface;
import com.insignia.entity.AddressDetails;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.CustomerPersonalDetails;
import com.insignia.entity.RolesAndPermissions;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerBasicDetailsRequest;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.CustomerPersonalDetailsResponse;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.model.RolesAndPermissionsResponse;
import com.insignia.repo.CustomerBasicDetailsRepository;

@ExtendWith(MockitoExtension.class)
public class TestCustomerServiceImpl {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerDaoInterface customerRepo;
	@Mock
	private CustomerBasicDetailsRepository customerBasicDetailsRepo;
	
	@InjectMocks
	private CustomerManagementServiceResponse customerManagementService;
	
	

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
	CustomerBasicDetailsEntity initializeCustmerDetailsEntity=new CustomerBasicDetailsEntity();
	
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
		addressRequest.setSequenceNumber(8);
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
	
	public CustomerBasicDetailsEntity getDetailsEntity() {
		
		
		AddressDetails addressDetails=new AddressDetails();
	
		
		addressDetails.setSequenceNumber(8);
		addressDetails.setAddressLine1("VinayakaTemple");
		addressDetails.setAddressLine2("CinemaRoad");
		addressDetails.setLandmark("Opp:ApolloHospital");
		addressDetails.setCity("Kakinada");
		addressDetails.setState("AndhraPradesh");
		addressDetails.setCountry("India");
		addressDetails.setEmailId("lakshmisidharth678@gmail.com");
		addressDetails.setZipCode("8765");
		addressDetails.setMobileNumber("9875689378");
		addressDetails.setLandlineNumber("98765895");
		addressDetails.setBillingAddress(false);
		addressDetails.setDefaultAddress(false);
		
		
		RolesAndPermissions rolesAndPermissions = new RolesAndPermissions();
		
		rolesAndPermissions.setRoleId(5);
		rolesAndPermissions.setPermission1(null);
		rolesAndPermissions.setPermission2(null);
		rolesAndPermissions.setPermission3(null);
		rolesAndPermissions.setPermission4(null);
		rolesAndPermissions.setPermissionChangeDate(null);
		rolesAndPermissions.setRoleApprovedDate(null);
		rolesAndPermissions.setRoleName("admin");
		rolesAndPermissions.setRoleRevokedDate(null);
		rolesAndPermissions.setUpdatedPermissions("yes");
		
		CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails();
				
		customerPersonalDetails.setSequenceNumber(8);
		customerPersonalDetails.setSequenceNumber(5);
		customerPersonalDetails.setFirstName("lakshmi");
		customerPersonalDetails.setLastName("Pragallapati");
		customerPersonalDetails.setMiddleName("Nagu");
		customerPersonalDetails.setAge("29");
		customerPersonalDetails.setGender("female");
		customerPersonalDetails.setCustomerMobileNumber("1234567891");
		customerPersonalDetails.setAlternativeMobileNumber("765897653");
		customerPersonalDetails.setCustomerEmailId("lakshmisidarth4@gmail.com");
		customerPersonalDetails.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
		customerPersonalDetails.setCustomerLandlineNumber("8765987");
		
		initializeCustmerDetailsEntity.setAddressDetails(Arrays.asList(addressDetails));
		initializeCustmerDetailsEntity.setRolesAndPermissions(Arrays.asList(rolesAndPermissions));
		initializeCustmerDetailsEntity.setCustomerPersonalDetails(Arrays.asList(customerPersonalDetails));
		initializeCustmerDetailsEntity.setCustomerSequenceNumber(8l);
		
		
		return initializeCustmerDetailsEntity;
	}
	
	@Test
	public void testUpdateAllCustomerDetails() throws Exception {
	    
		dataInitilization();
		getDetailsEntity();
		EntityManager entityManager = mock(EntityManager.class);
		
		
		CustomerBasicDetailsEntity basicDetailsEntityResponse=customerBasicDetailsEntity;
		basicDetailsEntityResponse.setApplicationId("123l");
	 
	    
	    when(customerRepo.getAllCustomerData(8l)).thenReturn(Optional.ofNullable(initializeCustmerDetailsEntity));
	    
	    when(customerRepo.isTokenNotValid(8l)).thenReturn(false);
	  // when(entityManager.merge(customerBasicDetailsEntity)).thenReturn(customerBasicDetailsEntity);
	   when(customerRepo.updateAllCustomerDetails(customerBasicDetailsEntity)).thenReturn(customerBasicDetailsEntity);
		
	  // customerServiceImpl.updateAllCustomerDetails(customerManagementServiceRequest);
	    assertNotNull(customerServiceImpl.updateAllCustomerDetails(customerManagementServiceRequest));
	}
	
	
	
	@Test
	public void testSaveAllCustomerDetails() throws Exception {
	    
		dataInitilization();
		
	    CustomerBasicDetailsEntity cbde = new CustomerBasicDetailsEntity();

	    when(customerRepo.saveAllCustomerDetails(any())).thenReturn(cbde);

	    assertNotNull(customerServiceImpl.saveAllCustomerDetails(customerManagementServiceRequest));
	}
	
	@Test
	public void testGetAllCustomerData() throws Exception {

		
	    Long customerSequenceNumber = 123L;

	    CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity(/* Set necessary properties */);

	    Optional<CustomerBasicDetailsEntity> response3 = Optional.ofNullable(customerBasicDetailsEntity);

	    when(customerRepo.getAllCustomerData(customerSequenceNumber)).thenReturn(response3);
	    Optional<CustomerManagementServiceResponse> responseOptional = customerServiceImpl.getAllCustomerData(customerSequenceNumber);

	
	    assertTrue(responseOptional.isPresent());
	    
	    CustomerManagementServiceResponse response = responseOptional.get();
	    assertNotNull(response);
	}
	
	@Test
    public void testDeleteCustomerAssociatedDetails_Success() throws Exception {
        Long customerSequenceNumber = 123L;

        
        when(customerRepo.isTokenNotValid(customerSequenceNumber)).thenReturn(false);

        customerServiceImpl.deleteCustomerAssociatedDetails(customerSequenceNumber);

       
        verify(customerRepo, times(1)).deleteCustomerAssociatedDetails(customerSequenceNumber);
    }
	
	@Test
	void testTokenExpiredException() {
		Long customer_sequence_number = 8L;
		dataInitilization();
		
		when(customerRepo.isTokenNotValid(customer_sequence_number)).thenReturn(true);
		
	assertThrows(TokenExpiredException.class, () -> {
		customerServiceImpl.updateAllCustomerDetails(customerManagementServiceRequest);
	});

	assertThrows(TokenExpiredException.class, () -> {
		customerServiceImpl.deleteCustomerAssociatedDetails(customer_sequence_number);
	});
	assertThrows(TokenExpiredException.class, () -> {
		customerServiceImpl.getAllCustomerData(customer_sequence_number);
	});
	}
	
	@Test
    public void testCreateResponse() throws Exception {
        CustomerBasicDetailsEntity customerEntity = new CustomerBasicDetailsEntity();
        // Set up customerEntity with desired properties

       // when(customerEntity.getAddressDetails()).thenReturn(new ArrayList<>());
        // Set up similar mock behavior for other methods

        CustomerServiceImpl serviceImpl = spy(CustomerServiceImpl.class);
        
        doNothing().when(serviceImpl).createResponse
        
        
       // CustomerManagementServiceResponse result = customerManagementService.createResponse(customerEntity);

       // assertEquals("Successfully saved data", result.getSuccessMessage());
        // Assert other properties in the result based on the mock setup
    }
}


