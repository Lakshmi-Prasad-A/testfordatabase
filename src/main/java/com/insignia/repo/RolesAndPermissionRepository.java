package com.insignia.repo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insignia.entity.RolesAndPermissions;

public interface RolesAndPermissionRepository extends JpaRepository<RolesAndPermissions, Serializable> {

	public final static String saveRolesAndPermissions = "Insert Into roles_and_permissions (role_name,permission1,permission2,permission3,permission4,role_approved_date,role_revoked_date,permission_change_date,updated_permissions,customer_sequence_number) Values (:role_name,:permission1,:permission2,:permission3,:permission4,:role_approved_date,:role_revoked_date,:permission_change_date,:updated_permissions,:customer_sequence_number)";
	public final static String isTokenValid = "SELECT token_expires_at FROM tokens_table WHERE customer_sequence_number = :customer_sequence_number AND token_type = 'JWT' AND token_expires_at > CURRENT_TIMESTAMP";
	public final static String getRolesAndPermissionsList = "from RolesAndPermissions WHERE customerSequenceNumber = :customerSequenceNumber";

	@Transactional
	@Modifying
	@Query(value = saveRolesAndPermissions, nativeQuery = true)
	public void saveRolesAndPermissions(@Param("role_name") String applicationId,
			@Param("permission1") Boolean permission1, @Param("permission2") Boolean permission2,
			@Param("permission3") Boolean permission3, @Param("permission4") Boolean permission4,
			@Param("role_approved_date") Date roleApprovedDate, @Param("role_revoked_date") Date roleRevokedDate,
			@Param("permission_change_date") Date permissionChangeDate,
			@Param("updated_permissions") String updatedPermissions,
			@Param("customer_sequence_number") Long customerSequenceNumber);

	@Query(value = isTokenValid, nativeQuery = true)
	public List<Object[]> isTokenNotValid(Long customer_sequence_number);
	
	
	@Query(value = getRolesAndPermissionsList)
	public List<RolesAndPermissions> getRolesAndPermissionsList(@Param ("customerSequenceNumber") Long customerSequenceNumber);
}
