package com.insignia.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insignia.entity.TokensEntity;

public interface TokensTableRepository extends JpaRepository<TokensEntity, Serializable> {

}
