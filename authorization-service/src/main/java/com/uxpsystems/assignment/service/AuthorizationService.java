package com.uxpsystems.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserCredentialEntityRepsitory;
import com.uxpsystems.assignment.model.UserCredentialEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorizationService {
	
	private UserCredentialEntityRepsitory userCredentialEntityRepsitory;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthorizationService(UserCredentialEntityRepsitory userCredentialEntityRepsitory, PasswordEncoder passwordEncoder) {
		super();
		this.userCredentialEntityRepsitory = userCredentialEntityRepsitory;
		this.passwordEncoder = passwordEncoder;
	}



	public UserCredentialEntity getUserCredential(String username, String password) {
		String encrypted = passwordEncoder.encode(password);
		log.info("password:{}",encrypted);
		return userCredentialEntityRepsitory.findByUsernameAndPassword(username, encrypted);
	}
}
