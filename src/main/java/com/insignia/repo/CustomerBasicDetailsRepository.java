package com.insignia.repo;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insignia.entity.CustomerBasicDetailsEntity;

public interface CustomerBasicDetailsRepository extends JpaRepository<CustomerBasicDetailsEntity, Serializable> {
//
//	public final static String saveCustomerBasicDetails = "Insert Into customer_basic_details (application_id,tenant_id,customer_id,customer_password,customer_email,user_name) Values (:application_id,:tenant_id,:customer_id,:customer_password,:customer_email,:user_name)";
	public final static String isTokenValid = "SELECT count(*) FROM tokens_table WHERE customer_sequence_number = :customer_sequence_number AND token_type = 'JWT' AND (token_expires_at > CURRENT_TIMESTAMP or token_expires_at is null)";
//	public final static String getCustomerBasicDetails= "from CustomerBasicDetailsEntity WHERE customerSequenceNumber = :customerSequenceNumber";
//	
//	
//	
//
//	@Transactional
//	@Modifying
//	@Query(value = saveCustomerBasicDetails, nativeQuery = true)
//	public void saveCustomerBasicDetails(@Param("application_id") String applicationId,
//			@Param("tenant_id") String tenantId, @Param("customer_id") String customerId,
//			@Param("customer_password") String customerPassword, @Param("customer_email") String customerEmail,
//			@Param("user_name") String userName);
//
	@Query(value = isTokenValid, nativeQuery = true)
	public List<Object[]> isTokenNotValid(Long customer_sequence_number);
//	
//	@Query(value = getCustomerBasicDetails)
//	public CustomerBasicDetailsEntity getCustomerBasicDetails(@Param("customerSequenceNumber") Long customerSequenceNumber);
//	

}
