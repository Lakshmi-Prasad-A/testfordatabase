package com.insignia.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles_and_permissions")
public class RolesAndPermissions {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_name")
	private String roleName;

	private Boolean permission1;

	private Boolean permission2;

	private Boolean permission3;

	private Boolean permission4;

	@Column(name = "role_approved_date")
	private Date roleApprovedDate;

	@Column(name = "role_revoked_date")
	private Date roleRevokedDate;

	@Column(name = "permission_change_date")
	private Date permissionChangeDate;

	@Column(name = "updated_permissions")
	private String updatedPermissions;

//	@Column(name = "customer_sequence_number")
//	private Long customerSequenceNumber;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getPermission1() {
		return permission1;
	}

	public void setPermission1(Boolean permission1) {
		this.permission1 = permission1;
	}

	public Boolean getPermission2() {
		return permission2;
	}

	public void setPermission2(Boolean permission2) {
		this.permission2 = permission2;
	}

	public Boolean getPermission3() {
		return permission3;
	}

	public void setPermission3(Boolean permission3) {
		this.permission3 = permission3;
	}

	public Boolean getPermission4() {
		return permission4;
	}

	public void setPermission4(Boolean permission4) {
		this.permission4 = permission4;
	}

	public Date getRoleApprovedDate() {
		return roleApprovedDate;
	}

	public void setRoleApprovedDate(Date roleApprovedDate) {
		this.roleApprovedDate = roleApprovedDate;
	}

	public Date getRoleRevokedDate() {
		return roleRevokedDate;
	}

	public void setRoleRevokedDate(Date roleRevokedDate) {
		this.roleRevokedDate = roleRevokedDate;
	}

	public Date getPermissionChangeDate() {
		return permissionChangeDate;
	}

	public void setPermissionChangeDate(Date permissionChangeDate) {
		this.permissionChangeDate = permissionChangeDate;
	}

	public String getUpdatedPermissions() {
		return updatedPermissions;
	}

	public void setUpdatedPermissions(String updatedPermissions) {
		this.updatedPermissions = updatedPermissions;
	}

//	public Long getCustomerSequenceNumber() {
//		return customerSequenceNumber;
//	}
//
//	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
//		this.customerSequenceNumber = customerSequenceNumber;
//	}

	


	public RolesAndPermissions(String roleName, Boolean permission1, Boolean permission2, Boolean permission3,
			Boolean permission4, Date roleApprovedDate, Date roleRevokedDate, Date permissionChangeDate,
			String updatedPermissions) {
		super();
		this.roleName = roleName;
		this.permission1 = permission1;
		this.permission2 = permission2;
		this.permission3 = permission3;
		this.permission4 = permission4;
		this.roleApprovedDate = roleApprovedDate;
		this.roleRevokedDate = roleRevokedDate;
		this.permissionChangeDate = permissionChangeDate;
		this.updatedPermissions = updatedPermissions;
	}
	public RolesAndPermissions() {
		// TODO Auto-generated constructor stub
	}

	public RolesAndPermissions(String roleName) {
		 this.roleName=roleName;
	}
	
	

}
