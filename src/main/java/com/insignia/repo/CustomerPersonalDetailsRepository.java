package com.insignia.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insignia.entity.CustomerPersonalDetails;

@Repository
public interface CustomerPersonalDetailsRepository extends JpaRepository<CustomerPersonalDetails, Serializable> {

//	public final static String saveCustomerPersonalDetails = "INSERT INTO customer_personal_details (customer_sequence_number,first_name,last_name,middle_name,age,gender,customer_email_id,alternative_email_id,customer_mobile_number,alternative_mobile_number,customer_landline_number) VALUES (:customer_sequence_number,:first_name,:last_name,:middle_name,:age,:gender,:customer_email_id,:alternative_email_id,:customer_mobile_number,:alternative_mobile_number,:customer_landline_number)";
//	public final static String isTokenValid = "SELECT token_expires_at FROM tokens_table WHERE customer_sequence_number = :customer_sequence_number AND token_type = 'JWT' AND token_expires_at > CURRENT_TIMESTAMP";
//	public final static String getCustomerPersonalDetails = "from CustomerPersonalDetails where customerSequenceNumber = :customerSequenceNumber";
//
//	@Transactional
//	@Modifying
//	@Query(value = saveCustomerPersonalDetails, nativeQuery = true)
//	public void saveCustomerPersonalDetails(@Param("customer_sequence_number") Long customerSequenceNumber,
//			@Param("first_name") String firstName, @Param("last_name") String lastName,
//			@Param("middle_name") String middleName, @Param("age") String age, @Param("gender") String gender,
//			@Param("customer_email_id") String customerEmailId,
//			@Param("alternative_email_id") String alternativeEmailId,
//			@Param("customer_mobile_number") String customerMobileNumber,
//			@Param("alternative_mobile_number") String alternativeMobileNumber,
//			@Param("customer_landline_number") String customerLandlineNumber);
//
//	@Query(value = isTokenValid, nativeQuery = true)
//	public List<Object[]> isTokenNotValid(Long customer_sequence_number);
//	
//	@Query(value = getCustomerPersonalDetails)
//	public CustomerPersonalDetails getCustomerPersonalDetails(@Param("customerSequenceNumber") Long customerSequenceNumber);
}
