package com.uxpsystems.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.UserCredentialEntity;

@Repository
public interface UserCredentialEntityRepsitory extends JpaRepository<UserCredentialEntity, String> {

	UserCredentialEntity findByUsernameAndPassword(String username, String password);
}
