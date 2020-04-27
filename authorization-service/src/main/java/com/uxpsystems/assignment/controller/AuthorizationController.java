package com.uxpsystems.assignment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.model.UserCredentialEntity;
import com.uxpsystems.assignment.pojo.LoginRequest;
import com.uxpsystems.assignment.security.jwt.JwtTokenProvider;
import com.uxpsystems.assignment.service.AuthorizationService;

@RestController
@RequestMapping("/api")
public class AuthorizationController {
	
	private AuthorizationService authorizationService;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
	
	
	@Autowired
	public AuthorizationController(AuthorizationService authorizationService) {
		super();
		this.authorizationService = authorizationService;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		UserCredentialEntity entity = authorizationService.getUserCredential(loginRequest.getUsername(), loginRequest.getPassword());
        String token = jwtTokenProvider.createToken(loginRequest.getUsername(), entity.getRoles());
        Map<Object, Object> model = new HashMap<>();
        model.put("token", token);
        return ResponseEntity.ok(model);
    } catch (AuthenticationException e) {
        throw new BadCredentialsException("Invalid username/password.");
    }
	}
	
	

	
	

}
