package com.insignia.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="tokens_table")
public class TokensEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="token_sequence_number")
	private Long tokenSequenceNumber;

	@Column(name="token_type")
	private String tokenType;

	@Column(name="token_details")
	private String tokenDetails;

	
	@Column(name="token_expires_at")
	private Date tokenExpiresAt;

	
	@Column(name="token_created_at")
	private Date tokenCreatedAt;

	
	@Column(name="token_revoked_at")
	private Date tokenRevokedAt;
	
	
	@Column(name="is_token_expired")
	private Boolean isTokenExpired;
	
	
	@Column(name="is_long_lived_token")
	private Boolean isLongLivedToken;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_sequence_number")
	private CustomerBasicDetailsEntity customerBasicDetailsEntity;
	
	public Long getTokenSequenceNumber() {
		return tokenSequenceNumber;
	}

	public void setTokenSequenceNumber(Long tokenSequenceNumber) {
		this.tokenSequenceNumber = tokenSequenceNumber;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenDetails() {
		return tokenDetails;
	}

	public void setTokenDetails(String tokenDetails) {
		this.tokenDetails = tokenDetails;
	}

	public Date getTokenExpiresAt() {
		return tokenExpiresAt;
	}

	public void setTokenExpiresAt(Date tokenExpiresAt) {
		this.tokenExpiresAt = tokenExpiresAt;
	}

	public Date getTokenCreatedAt() {
		return tokenCreatedAt;
	}

	public void setTokenCreatedAt(Date tokenCreatedAt) {
		this.tokenCreatedAt = tokenCreatedAt;
	}

	public Date getTokenRevokedAt() {
		return tokenRevokedAt;
	}

	public void setTokenRevokedAt(Date tokenRevokedAt) {
		this.tokenRevokedAt = tokenRevokedAt;
	}

	public Boolean getIsTokenExpired() {
		return isTokenExpired;
	}

	public void setIsTokenExpired(Boolean isTokenExpired) {
		this.isTokenExpired = isTokenExpired;
	}

	public Boolean getIsLongLivedToken() {
		return isLongLivedToken;
	}

	public void setIsLongLivedToken(Boolean isLongLivedToken) {
		this.isLongLivedToken = isLongLivedToken;
	}

	public CustomerBasicDetailsEntity getCustomerBasicDetailsEntity() {
		return customerBasicDetailsEntity;
	}

	public void setCustomerBasicDetailsEntity(CustomerBasicDetailsEntity customerBasicDetailsEntity) {
		this.customerBasicDetailsEntity = customerBasicDetailsEntity;
	}

	public TokensEntity( String tokenType, String tokenDetails, Date tokenExpiresAt,
			Date tokenCreatedAt, Date tokenRevokedAt, Boolean isTokenExpired, Boolean isLongLivedToken) {
		super();
		this.tokenType = tokenType;
		this.tokenDetails = tokenDetails;
		this.tokenExpiresAt = tokenExpiresAt;
		this.tokenCreatedAt = tokenCreatedAt;
		this.tokenRevokedAt = tokenRevokedAt;
		this.isTokenExpired = isTokenExpired;
		this.isLongLivedToken = isLongLivedToken;
	}

	public TokensEntity() {
		super();
	}
	
}
