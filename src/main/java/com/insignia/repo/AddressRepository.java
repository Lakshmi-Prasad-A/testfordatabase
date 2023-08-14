package com.insignia.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.insignia.entity.AddressDetails;

public interface AddressRepository extends JpaRepository<AddressDetails, Serializable> {

//	public final static String saveAddressDetails = "INSERT INTO address_details (customer_sequence_number,address_line_1,address_line_2,landmark,city,state,country,Zip_code,mobile_number,email_id,is_default_address,is_billing_address,landline_number)Values(:customer_sequence_number,:address_line_1,:address_line_2,:landmark,:city,:state,:country,:Zip_code,:mobile_number,:email_id,:is_default_address,:is_billing_address,:landline_number)";
//	public final static String isTokenValid = "SELECT token_expires_at FROM tokens_table WHERE customer_sequence_number = :customer_sequence_number AND token_type = 'JWT' AND token_expires_at > CURRENT_TIMESTAMP";
//	public final static String getAddress = "from AddressDetails WHERE customerSequenceNumber = :customerSequenceNumber";
//
//	@Transactional
//	@Modifying
//	@Query(value = saveAddressDetails, nativeQuery = true)
//	public void saveAddressDetails(@Param("customer_sequence_number") Long customerSequenceNumber,
//			@Param("address_line_1") String addressLine1, @Param("address_line_2") String addressLine2,
//			@Param("landmark") String landmark, @Param("city") String city, @Param("state") String state,
//			@Param("country") String country, @Param("Zip_code") String zipCode,
//			@Param("mobile_number") String mobileNumber, @Param("email_id") String emailId,
//			@Param("is_default_address") Boolean isDefaultAddress,
//			@Param("is_billing_address") Boolean isBillingAddress, @Param("landline_number") String landlineNumber);
//
//	@Query(value = isTokenValid, nativeQuery = true)
//	public List<Object[]> isTokenNotValid(Long customer_sequence_number);
//	
//	@Query(value = getAddress)
//	public List<AddressDetails> getAddressList(@Param("customerSequenceNumber") Long customerSequenceNumber);
//	

}