package com.insignia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.constant.CustomerManagementConstants;
import com.insignia.constants.AddressDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.serviceInterface.CustomerServiceInterface;
import com.insignia.validations.AddressDetailsValidation;
import com.insignia.validations.CustomerBasicDetailsValidation;
import com.insignia.validations.CustomerPersonalDetailsValidation;
import com.insignia.validations.RolesAndPermissonsValidations;

@RestController
public class CustomerController {

	@Autowired
	private CustomerServiceInterface serviceRepo;

	@PostMapping("/saveCustomerDetails")
	public ResponseEntity<?> saveAllCustomerDetails(
			@RequestBody CustomerManagementServiceRequest customerManagementDetails) {
		try {
			
			validateCustomerAllDetails(customerManagementDetails);

			return ResponseEntity.ok(serviceRepo.saveAllCustomerDetails(customerManagementDetails));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
							AddressDetailsConstants.validateUnexpectedErrorMessage));
		}
	}

	@PutMapping("/updateCustomerDetails")
	public ResponseEntity<?> updateAllCustomerDetails(
			@RequestBody CustomerManagementServiceRequest customerManagementDetails) {

		try {
			validateCustomerAllDetails(customerManagementDetails);

			return ResponseEntity.ok(serviceRepo.updateAllCustomerDetails(customerManagementDetails));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(ex.getErrorCode(), ex.getStrMsg()));
		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AddressResponse(
					AddressDetailsConstants.validateTokenErrorCode, AddressDetailsConstants.validateToken));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
							AddressDetailsConstants.validateUnexpectedErrorMessage));
		}
	}

	@DeleteMapping("/deleteCustomerDetails/{customerSequenceNumber}")
	public ResponseEntity<?> deleteCustomerAssociatedDetails(@PathVariable Long customerSequenceNumber) {
		try {
			serviceRepo.deleteCustomerAssociatedDetails(customerSequenceNumber);
			return ResponseEntity.ok("Record Successfully Deleted");

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AddressResponse(
					AddressDetailsConstants.validateTokenErrorCode, AddressDetailsConstants.validateToken));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
							AddressDetailsConstants.validateUnexpectedErrorMessage));
		}

	}

	@GetMapping("/getCustomerDetails/{customerSequenceNumber}")
	public ResponseEntity<?> getAllCustomerData(@PathVariable Long customerSequenceNumber) {
		try {
			Optional<CustomerManagementServiceResponse> customerDetails = serviceRepo
					.getAllCustomerData(customerSequenceNumber);
			return ResponseEntity
					.ok(customerDetails.isPresent() ? customerDetails.get() : new CustomerBasicDetailsEntity());

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AddressResponse(
					AddressDetailsConstants.validateTokenErrorCode, AddressDetailsConstants.validateToken));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
							AddressDetailsConstants.validateUnexpectedErrorMessage));
		}
	}

	private void validateCustomerAllDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {
		
		validateCustomerBasicDetails(customerManagementDetails);

		validateAddressDetails(customerManagementDetails);

		validateCustomerPersonalDetails(customerManagementDetails);

		validateRolesAndPermissions(customerManagementDetails);
	}

	private void validateRolesAndPermissions(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {
		if (customerManagementDetails.getRolesAndPermissionsRequestList() != null) {
			for (RolesAndPermissionsRequest rolesAndPermissionsRequest : customerManagementDetails
					.getRolesAndPermissionsRequestList()) {

				RolesAndPermissonsValidations.ValidateRoleName(rolesAndPermissionsRequest.getRoleName(),
						CustomerManagementConstants.roleNameLength);
				RolesAndPermissonsValidations.ValidateUpdatePermissions(
						rolesAndPermissionsRequest.getUpdatedPermissions(),
						CustomerManagementConstants.updatePermissionsLength);

			}
		}
	}

	private void validateCustomerPersonalDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {
		
		if (customerManagementDetails.getCustomerPersonalDetailsRequestList() != null) {

			for (CustomerPersonalDetailsRequest customerPersonalRequest : customerManagementDetails
					.getCustomerPersonalDetailsRequestList()) {

				CustomerPersonalDetailsValidation.ValidateFirstName(customerPersonalRequest.getFirstName(),
						CustomerManagementConstants.firstNameLength);
				CustomerPersonalDetailsValidation.ValidateLastName(customerPersonalRequest.getLastName(),
						CustomerManagementConstants.secondNameLength);
				CustomerPersonalDetailsValidation.ValidateMiddleName(customerPersonalRequest.getMiddleName(),
						CustomerManagementConstants.middleNameLength);
				CustomerPersonalDetailsValidation.ValidateAge(customerPersonalRequest.getAge(),
						CustomerManagementConstants.ageLength);
				CustomerPersonalDetailsValidation.ValidateGender(customerPersonalRequest.getGender(),
						CustomerManagementConstants.genderLength);
				CustomerPersonalDetailsValidation.ValidateCustomerEmailId(customerPersonalRequest.getCustomerEmailId(),
						CustomerManagementConstants.customerEmailIdLength);
				CustomerPersonalDetailsValidation.ValidateAlternativeEmailId(
						customerPersonalRequest.getAlternativeEmailId(),
						CustomerManagementConstants.alternativeEmailIdLength);
				CustomerPersonalDetailsValidation.ValidateCustomeMobileNumber(
						customerPersonalRequest.getCustomerMobileNumber(),
						CustomerManagementConstants.customerMobileNumberLength);
				CustomerPersonalDetailsValidation.ValidateAlternativeMobileNumber(
						customerPersonalRequest.getAlternativeMobileNumber(),
						CustomerManagementConstants.alternativeMobileNumberLength);
				CustomerPersonalDetailsValidation.ValidateCustomerLandlineNumber(
						customerPersonalRequest.getCustomerLandlineNumber(),
						CustomerManagementConstants.customerLandlineNumberLength);

			}
		}
	}

	private void validateAddressDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {
		if (customerManagementDetails.getAddressRequestList() != null) {

			for (AddressRequest addressRequest : customerManagementDetails.getAddressRequestList()) {

				AddressDetailsValidation.ValidateAddressLine1(addressRequest.getAddressLine1(),
						CustomerManagementConstants.addresLine1length);
				AddressDetailsValidation.ValidateCity(addressRequest.getCity(), CustomerManagementConstants.cityLength);
				AddressDetailsValidation.ValidateLandmark(addressRequest.getLandmark(),
						CustomerManagementConstants.landMarkLength);
				AddressDetailsValidation.ValidateState(addressRequest.getState(),
						CustomerManagementConstants.stateLength);
				AddressDetailsValidation.ValidateCountry(addressRequest.getCountry(),
						CustomerManagementConstants.countryLength);
				AddressDetailsValidation.ValidateZipCode(addressRequest.getZipCode(),
						CustomerManagementConstants.zipCodeLength);
				AddressDetailsValidation.ValidateAddressLine2(addressRequest.getZipCode(),
						CustomerManagementConstants.addresLine2length);
				AddressDetailsValidation.ValidateEmailId(addressRequest.getZipCode(),
						CustomerManagementConstants.emailIdLength);
				AddressDetailsValidation.ValidateMobileNumber(addressRequest.getMobileNumber(),
						CustomerManagementConstants.mobilenumberLength);
				AddressDetailsValidation.ValidateLandLineNumber(addressRequest.getLandlineNumber(),
						CustomerManagementConstants.landlineNumberLength);
			}
		}
	}

	private void validateCustomerBasicDetails(CustomerManagementServiceRequest customerManagementDetails)
			throws InvalidInputParametersException {
		if (customerManagementDetails.getCustomerBasicDetailsRequest() != null) {

			CustomerBasicDetailsValidation.ValidateUserId(
					customerManagementDetails.getCustomerBasicDetailsRequest().getUserId(),
					CustomerManagementConstants.userIdlength);
			CustomerBasicDetailsValidation.ValidateApplicationId(
					customerManagementDetails.getCustomerBasicDetailsRequest().getApplicationId(),
					CustomerManagementConstants.applicationIdlength);
			CustomerBasicDetailsValidation.ValidateTenantId(
					customerManagementDetails.getCustomerBasicDetailsRequest().getTenantId(),
					CustomerManagementConstants.tenantIdlength);
			CustomerBasicDetailsValidation.ValidatePassword(
					customerManagementDetails.getCustomerBasicDetailsRequest().getPassword(),
					CustomerManagementConstants.passwordlength);
			CustomerBasicDetailsValidation.ValidateCustomerEmail(
					customerManagementDetails.getCustomerBasicDetailsRequest().getEmailId(),
					CustomerManagementConstants.customerEmailLength);
			CustomerBasicDetailsValidation.ValidateUsername(
					customerManagementDetails.getCustomerBasicDetailsRequest().getUserName(),
					CustomerManagementConstants.userNameLength);
		}
	}
}
