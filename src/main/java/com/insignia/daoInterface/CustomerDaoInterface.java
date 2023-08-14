package com.insignia.daoInterface;


import java.util.Optional;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.entity.CustomerBasicDetailsEntity;

public interface CustomerDaoInterface {
	
	public CustomerBasicDetailsEntity saveAllCustomerDetails(CustomerBasicDetailsEntity cbde) throws InvalidInputParametersException;
	
	public Optional<CustomerBasicDetailsEntity> getAllCustomerData(Long customerSequenceNumber);
	
	public CustomerBasicDetailsEntity updateAllCustomerDetails(CustomerBasicDetailsEntity customerBasicDetailsEntity) throws InvalidInputParametersException;
	
	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber);	
	
	public Boolean isTokenNotValid(Long customer_sequence_number);
	
	

	

	

	
	
	
}
