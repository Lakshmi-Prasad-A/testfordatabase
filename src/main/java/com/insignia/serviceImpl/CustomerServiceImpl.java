package com.insignia.serviceImpl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.insignia.constants.AddressDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.CustomerDaoInterface;
import com.insignia.encryption.PasswordEncoder;
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
import com.insignia.serviceInterface.CustomerServiceInterface;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface {

	@Autowired
	private CustomerDaoInterface customerDao;

	@Transactional(rollbackOn = InvalidInputParametersException.class)
	@Modifying
	@Override
	public CustomerManagementServiceResponse saveAllCustomerDetails(
			CustomerManagementServiceRequest customerManagementServiceDetails)
			throws InvalidInputParametersException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		CustomerBasicDetailsEntity cbde = new CustomerBasicDetailsEntity();
		
		if (customerManagementServiceDetails.getCustomerBasicDetailsRequest() != null) {

			String password = PasswordEncoder.getEncryptedPassword(
					customerManagementServiceDetails.getCustomerBasicDetailsRequest().getPassword());

			CustomerBasicDetailsRequest request = customerManagementServiceDetails.getCustomerBasicDetailsRequest();

			cbde = new CustomerBasicDetailsEntity(request.getApplicationId(), request.getTenantId(),
					request.getUserId(), password, request.getEmailId(), request.getUserName(), null, null, null, null);

		}
		if (customerManagementServiceDetails.getRolesAndPermissionsRequestList() != null) {

			List<RolesAndPermissionsRequest> rolesAndPermissionsRequestList = customerManagementServiceDetails
					.getRolesAndPermissionsRequestList();

			List<RolesAndPermissions> rapList = new ArrayList<>();

			for (RolesAndPermissionsRequest rolesAndPermissionsRequest : rolesAndPermissionsRequestList) {
				RolesAndPermissions rap = new RolesAndPermissions();

				rap.setRoleName(rolesAndPermissionsRequest.getRoleName());
				rap.setPermission1(rolesAndPermissionsRequest.getPermission1());
				rap.setPermission2(rolesAndPermissionsRequest.getPermission2());
				rap.setPermission3(rolesAndPermissionsRequest.getPermission3());
				rap.setPermission4(rolesAndPermissionsRequest.getPermission4());
				rap.setRoleApprovedDate(rolesAndPermissionsRequest.getRoleApprovedDate());
				rap.setRoleRevokedDate(rolesAndPermissionsRequest.getRoleRevokedDate());
				rap.setPermissionChangeDate(rolesAndPermissionsRequest.getPermissionChangeDate());
				rap.setUpdatedPermissions(rolesAndPermissionsRequest.getUpdatedPermissions());

				rapList.add(rap);

			}

			cbde.setRolesAndPermissions(rapList);

		}

		if (customerManagementServiceDetails.getCustomerPersonalDetailsRequestList() != null) {
			List<CustomerPersonalDetailsRequest> customerPersonalDetailsRequestList2 = customerManagementServiceDetails
					.getCustomerPersonalDetailsRequestList();
			List<CustomerPersonalDetails> cpdList = new ArrayList<>();

			for (CustomerPersonalDetailsRequest customerPersonalDetailsRequest : customerPersonalDetailsRequestList2) {
				CustomerPersonalDetails cpde = new CustomerPersonalDetails();
				cpde.setFirstName(customerPersonalDetailsRequest.getFirstName());
				cpde.setLastName(customerPersonalDetailsRequest.getLastName());
				cpde.setMiddleName(customerPersonalDetailsRequest.getMiddleName());
				cpde.setAge(customerPersonalDetailsRequest.getAge());
				cpde.setGender(customerPersonalDetailsRequest.getGender());
				cpde.setCustomerEmailId(customerPersonalDetailsRequest.getCustomerEmailId());
				cpde.setAlternativeEmailId(customerPersonalDetailsRequest.getAlternativeEmailId());
				cpde.setCustomerMobileNumber(customerPersonalDetailsRequest.getCustomerMobileNumber());
				cpde.setAlternativeMobileNumber(customerPersonalDetailsRequest.getAlternativeMobileNumber());
				cpde.setCustomerLandlineNumber(customerPersonalDetailsRequest.getCustomerLandlineNumber());
				cpdList.add(cpde);

			}
			cbde.setCustomerPersonalDetails(cpdList);
		}

		if (customerManagementServiceDetails.getAddressRequestList() != null) {
			List<AddressRequest> addressRequestList = customerManagementServiceDetails.getAddressRequestList();
			List<AddressDetails> adList = new ArrayList<>();

			for (AddressRequest addressRequest : addressRequestList) {
				AddressDetails ad = new AddressDetails();
				ad.setAddressLine1(addressRequest.getAddressLine1());
				ad.setAddressLine2(addressRequest.getAddressLine2());
				ad.setLandmark(addressRequest.getLandmark());
				ad.setCity(addressRequest.getCity());
				ad.setState(addressRequest.getState());
				ad.setCountry(addressRequest.getCountry());
				ad.setZipCode(addressRequest.getZipCode());
				ad.setMobileNumber(addressRequest.getMobileNumber());
				ad.setLandlineNumber(addressRequest.getLandlineNumber());
				ad.setEmailId(addressRequest.getEmailId());
				ad.setDefaultAddress(addressRequest.getIsDefaultAddress());
				ad.setBillingAddress(addressRequest.getIsBillingAddress());

				adList.add(ad);

			}

			cbde.setAddressDetails(adList);

		}

		CustomerBasicDetailsEntity customerBasicDetails = customerDao.saveAllCustomerDetails(cbde);

		return createResponse(customerBasicDetails);

	}

	public Optional<CustomerManagementServiceResponse> getAllCustomerData(Long customerSequenceNumber)
			throws TokenExpiredException, InvalidInputParametersException {
		
		CustomerBasicDetailsEntity customerBasicDetailsEntity = getCustomerData(customerSequenceNumber);

		CustomerManagementServiceResponse customerManagementServiceResponse = new CustomerManagementServiceResponse();

		customerManagementServiceResponse.setAddressResponseList(
				createAddressDetailsResponseForEntity(customerBasicDetailsEntity.getAddressDetails()));

		customerManagementServiceResponse.setRolesAndPermissionsResponseList(
				createRolesAndPermissionsResponseForEntity(customerBasicDetailsEntity.getRolesAndPermissions()));

		customerManagementServiceResponse
				.setCustomerPersonalDetailsResponseList(createCustomerPersonalDetailsResponseForEntity(
						customerBasicDetailsEntity.getCustomerPersonalDetails()));

		return Optional.ofNullable(customerManagementServiceResponse);

	}

	private CustomerBasicDetailsEntity getCustomerData(Long customerSequenceNumber) throws TokenExpiredException {
		if (isTokenNotValid(customerSequenceNumber))
			throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
					AddressDetailsConstants.validateToken);

		Optional<CustomerBasicDetailsEntity> customerData = customerDao.getAllCustomerData(customerSequenceNumber);

		CustomerBasicDetailsEntity customerBasicDetailsEntity = customerData.get();
		return customerBasicDetailsEntity;
	}

	@Override
	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber) throws TokenExpiredException {
		if (isTokenNotValid(customerSequenceNumber))
			throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
					AddressDetailsConstants.validateToken);

		customerDao.deleteCustomerAssociatedDetails(customerSequenceNumber);

	}

	

	@Transactional(rollbackOn = InvalidInputParametersException.class)
	@Override
	public CustomerManagementServiceResponse updateAllCustomerDetails(
			CustomerManagementServiceRequest customerManagementServiceRequest)
			throws TokenExpiredException, InvalidInputParametersException {

		CustomerBasicDetailsEntity customerBasicDetailsEntity = getCustomerData(
				customerManagementServiceRequest.getCustomerBasicDetailsRequest().getCustomerSequenceNumber());

		if (customerManagementServiceRequest.getAddressRequestList() != null) {

			List<AddressRequest> addressRequestList = customerManagementServiceRequest.getAddressRequestList();

			for (AddressRequest addressRequest : addressRequestList) {

				for (AddressDetails addressDetails : customerBasicDetailsEntity.getAddressDetails()) {

					if (addressRequest.getSequenceNumber() == addressDetails.getSequenceNumber()) {

						if (addressRequest.isAddressLine1Updated()) {
							addressDetails.setAddressLine1(addressRequest.getAddressLine1());
						}
						if (addressRequest.isAddressLine2Updated()) {
							addressDetails.setAddressLine2(addressRequest.getAddressLine2());
						}
						if (addressRequest.isLandMarkUpdated()) {
							addressDetails.setLandmark(addressRequest.getLandmark());
						}
						if (addressRequest.isCityUpdated()) {
							addressDetails.setCity(addressRequest.getCity());
						}
						if (addressRequest.isStateUpdated()) {
							addressDetails.setState(addressRequest.getState());
						}
						if (addressRequest.isCountryUpdated()) {
							addressDetails.setCountry(addressRequest.getCountry());
						}
						if (addressRequest.isZipCodeUpdated()) {
							addressDetails.setZipCode(addressRequest.getZipCode());
						}
						if (addressRequest.isMobileNumberUpdated()) {
							addressDetails.setMobileNumber(addressRequest.getMobileNumber());
						}
						if (addressRequest.isLandLineNumberUpdated()) {
							addressDetails.setLandlineNumber(addressRequest.getLandlineNumber());
						}
						if (addressRequest.isEmailAddressUpdated()) {
							addressDetails.setEmailId(addressRequest.getEmailId());
						}
						if (addressRequest.isDefaultAddressUpdated()) {
							addressDetails.setDefaultAddress(addressRequest.getIsDefaultAddress());
						}
						if (addressRequest.isBillingAddressUpdated()) {
							addressDetails.setBillingAddress(addressRequest.getIsBillingAddress());
						}

						break;
					}

				}

			}
		}

		if (customerManagementServiceRequest.getRolesAndPermissionsRequestList() != null) {

			List<RolesAndPermissionsRequest> rolesAndPermissionsRequestList = customerManagementServiceRequest
					.getRolesAndPermissionsRequestList();

			for (RolesAndPermissionsRequest rolesAndPermissionsRequest : rolesAndPermissionsRequestList) {

				for (RolesAndPermissions rolesAndPermissions : customerBasicDetailsEntity.getRolesAndPermissions()) {

					if (rolesAndPermissionsRequest.getRoleId() == rolesAndPermissions.getRoleId()) {

						if (rolesAndPermissionsRequest.isRoleNameUpdated()) {
							rolesAndPermissions.setRoleName(rolesAndPermissionsRequest.getRoleName());
						}
						if (rolesAndPermissionsRequest.isPermission1Updated()) {
							rolesAndPermissions.setPermission1(rolesAndPermissionsRequest.getPermission1());
						}
						if (rolesAndPermissionsRequest.isPermission2Updated()) {
							rolesAndPermissions.setPermission2(rolesAndPermissionsRequest.getPermission2());
						}
						if (rolesAndPermissionsRequest.isPermission3Updated()) {
							rolesAndPermissions.setPermission3(rolesAndPermissionsRequest.getPermission3());
						}
						if (rolesAndPermissionsRequest.isPermission4Updated()) {
							rolesAndPermissions.setPermission4(rolesAndPermissionsRequest.getPermission4());
						}
						if (rolesAndPermissionsRequest.isRoleApprovedDateUpdated()) {
							rolesAndPermissions.setRoleApprovedDate(rolesAndPermissionsRequest.getRoleApprovedDate());
						}
						if (rolesAndPermissionsRequest.isRoleRevokedDateUpdated()) {
							rolesAndPermissions.setRoleRevokedDate(rolesAndPermissionsRequest.getRoleRevokedDate());
						}
						if (rolesAndPermissionsRequest.isPermissionChangeDateUpdated()) {
							rolesAndPermissions
									.setPermissionChangeDate(rolesAndPermissionsRequest.getPermissionChangeDate());
						}
						if (rolesAndPermissionsRequest.isUpdatedPermissionsUpdated()) {
							rolesAndPermissions
									.setUpdatedPermissions(rolesAndPermissionsRequest.getUpdatedPermissions());
						}
						break;
					}

				}

			}
		}
		if (customerManagementServiceRequest.getCustomerPersonalDetailsRequestList() != null) {

			List<CustomerPersonalDetailsRequest> customerPersonalDetailsRequestList = customerManagementServiceRequest
					.getCustomerPersonalDetailsRequestList();

			for (CustomerPersonalDetailsRequest customerPersonalDetailsRequest : customerPersonalDetailsRequestList) {

				for (CustomerPersonalDetails customerPersonalDetails : customerBasicDetailsEntity
						.getCustomerPersonalDetails()) {

					if (customerPersonalDetailsRequest.getSequenceNumber() == customerPersonalDetails
							.getSequenceNumber()) {

						if (customerPersonalDetailsRequest.isFirstNameUpdated()) {
							customerPersonalDetails.setFirstName(customerPersonalDetailsRequest.getFirstName());
						}
						if (customerPersonalDetailsRequest.isLastNameUpdated()) {
							customerPersonalDetails.setLastName(customerPersonalDetailsRequest.getLastName());
						}
						if (customerPersonalDetailsRequest.isMiddleNameUpdated()) {
							customerPersonalDetails.setMiddleName(customerPersonalDetailsRequest.getMiddleName());
						}
						if (customerPersonalDetailsRequest.isAgeUpdated()) {
							customerPersonalDetails.setAge(customerPersonalDetailsRequest.getAge());
						}
						if (customerPersonalDetailsRequest.isGenderUpdated()) {
							customerPersonalDetails.setGender(customerPersonalDetailsRequest.getGender());
						}
						if (customerPersonalDetailsRequest.isCustomerEmailIdUpdated()) {
							customerPersonalDetails
									.setCustomerEmailId(customerPersonalDetailsRequest.getCustomerEmailId());
						}
						if (customerPersonalDetailsRequest.isAlternativeEmailIdUpdated()) {
							customerPersonalDetails
									.setAlternativeEmailId(customerPersonalDetailsRequest.getAlternativeEmailId());
						}
						if (customerPersonalDetailsRequest.isCustomerMobileNumberUpdated()) {
							customerPersonalDetails
									.setCustomerMobileNumber(customerPersonalDetailsRequest.getCustomerMobileNumber());
						}
						if (customerPersonalDetailsRequest.isAlternativeMobileNumberUpdated()) {
							customerPersonalDetails.setAlternativeMobileNumber(
									customerPersonalDetailsRequest.getAlternativeMobileNumber());
						}
						if (customerPersonalDetailsRequest.isCustomerLandlineUpdated()) {
							customerPersonalDetails.setCustomerLandlineNumber(
									customerPersonalDetailsRequest.getCustomerLandlineNumber());
						}
						break;
					}

				}

			}
		}

		CustomerBasicDetailsEntity customerBasicDetail = customerDao
				.updateAllCustomerDetails(customerBasicDetailsEntity);
		
		return createResponse(customerBasicDetail);
	}

	private CustomerManagementServiceResponse createResponse(CustomerBasicDetailsEntity customerBasicDetailsEntity) {

		CustomerManagementServiceResponse customerManagementServiceResponse = new CustomerManagementServiceResponse();

		customerManagementServiceResponse.setSuccessMessage("Successfully saved data");
		
		// AddressResponse//
		if (customerBasicDetailsEntity!=null && customerBasicDetailsEntity.getAddressDetails()!=null&&!customerBasicDetailsEntity.getAddressDetails().isEmpty())
			customerManagementServiceResponse.setAddressResponseList(
					createAddressDetailsResponseForEntity(customerBasicDetailsEntity.getAddressDetails()));

		// CustomerPersonalDetailsResponse//
		if (customerBasicDetailsEntity!=null&&customerBasicDetailsEntity.getCustomerPersonalDetails()!=null && !customerBasicDetailsEntity.getCustomerPersonalDetails().isEmpty())
			customerManagementServiceResponse
					.setCustomerPersonalDetailsResponseList(createCustomerPersonalDetailsResponseForEntity(
							customerBasicDetailsEntity.getCustomerPersonalDetails()));

		if (customerBasicDetailsEntity!=null&&customerBasicDetailsEntity.getRolesAndPermissions()!=null && !customerBasicDetailsEntity.getRolesAndPermissions().isEmpty())
			customerManagementServiceResponse.setRolesAndPermissionsResponseList(
					createRolesAndPermissionsResponseForEntity(customerBasicDetailsEntity.getRolesAndPermissions()));

		return customerManagementServiceResponse;
	}

	private List<RolesAndPermissionsResponse> createRolesAndPermissionsResponseForEntity(
			List<RolesAndPermissions> rolesAndPermissions) {

		List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList = new ArrayList<>();

		for (RolesAndPermissions rolesAndPermissionsList : rolesAndPermissions) {

			RolesAndPermissionsResponse rolesAndPermissionsResponse = new RolesAndPermissionsResponse();

			rolesAndPermissionsResponse.setRoleId(rolesAndPermissionsList.getRoleId());
			rolesAndPermissionsResponse.setRoleName(rolesAndPermissionsList.getRoleName());
			rolesAndPermissionsResponse.setPermission1(rolesAndPermissionsList.getPermission1());
			rolesAndPermissionsResponse.setPermission2(rolesAndPermissionsList.getPermission2());
			rolesAndPermissionsResponse.setPermission3(rolesAndPermissionsList.getPermission3());
			rolesAndPermissionsResponse.setPermission4(rolesAndPermissionsList.getPermission4());
			rolesAndPermissionsResponse.setRoleApprovedDate(rolesAndPermissionsList.getRoleApprovedDate());
			rolesAndPermissionsResponse.setRoleRevokedDate(rolesAndPermissionsList.getRoleRevokedDate());
			rolesAndPermissionsResponse.setPermissionChangeDate(rolesAndPermissionsList.getPermissionChangeDate());
			rolesAndPermissionsResponse.setUpdatedPermissions(rolesAndPermissionsList.getUpdatedPermissions());

			rolesAndPermissionsResponseList.add(rolesAndPermissionsResponse);
		}
		return rolesAndPermissionsResponseList;
	}

	private List<CustomerPersonalDetailsResponse> createCustomerPersonalDetailsResponseForEntity(
			List<CustomerPersonalDetails> customerPersonalDetails) {
		List<CustomerPersonalDetailsResponse> customerPersonalDetailsResponseList = new ArrayList<>();

		for (CustomerPersonalDetails customerPersonalDetailsList : customerPersonalDetails) {

			CustomerPersonalDetailsResponse customerPersonalDetailsResponse = new CustomerPersonalDetailsResponse();

			customerPersonalDetailsResponse.setSequenceNumber(customerPersonalDetailsList.getSequenceNumber());
			customerPersonalDetailsResponse.setFirstName(customerPersonalDetailsList.getFirstName());
			customerPersonalDetailsResponse.setLastName(customerPersonalDetailsList.getLastName());
			customerPersonalDetailsResponse.setMiddleName(customerPersonalDetailsList.getMiddleName());
			customerPersonalDetailsResponse.setAge(customerPersonalDetailsList.getAge());
			customerPersonalDetailsResponse.setGender(customerPersonalDetailsList.getGender());
			customerPersonalDetailsResponse.setCustomerEmailId(customerPersonalDetailsList.getCustomerEmailId());
			customerPersonalDetailsResponse.setAlternativeEmailId(customerPersonalDetailsList.getAlternativeEmailId());
			customerPersonalDetailsResponse
					.setCustomerMobileNumber(customerPersonalDetailsList.getCustomerMobileNumber());
			customerPersonalDetailsResponse
					.setAlternativeMobileNumber(customerPersonalDetailsList.getAlternativeMobileNumber());
			customerPersonalDetailsResponse
					.setCustomerLandlineNumber(customerPersonalDetailsList.getCustomerLandlineNumber());

			customerPersonalDetailsResponseList.add(customerPersonalDetailsResponse);
		}
		return customerPersonalDetailsResponseList;

	}

	private List<AddressResponse> createAddressDetailsResponseForEntity(List<AddressDetails> addressDetailsList) {
		List<AddressResponse> addressResponseList = new ArrayList<>();

		for (AddressDetails addressDetails : addressDetailsList) {

			AddressResponse addressResponse = new AddressResponse();

			addressResponse.setSequenceNumber(addressDetails.getSequenceNumber());
			addressResponse.setAddressLine1(addressDetails.getAddressLine1());
			addressResponse.setAddressLine2(addressDetails.getAddressLine2());
			addressResponse.setLandmark(addressDetails.getLandmark());
			addressResponse.setCity(addressDetails.getCity());
			addressResponse.setState(addressDetails.getState());
			addressResponse.setCountry(addressDetails.getCountry());
			addressResponse.setZipCode(addressDetails.getZipCode());
			addressResponse.setMobileNumber(addressDetails.getMobileNumber());
			addressResponse.setLandlineNumber(addressDetails.getLandlineNumber());
			addressResponse.setEmailId(addressDetails.getEmailId());

			addressResponseList.add(addressResponse);
		}

		return addressResponseList;
	}
	
	private Boolean isTokenNotValid(Long customer_sequence_number) {
		return customerDao.isTokenNotValid(customer_sequence_number);
	}

}
