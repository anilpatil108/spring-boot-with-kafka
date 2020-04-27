package com.uxpsystems.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.ProfileEntityRepsitory;
import com.uxpsystems.assignment.model.ProfileEntity;

@Service
public class ProfileService {
	
	private ProfileEntityRepsitory profileEntityRepsitory;
	
	@Autowired
	public ProfileService(ProfileEntityRepsitory profileEntityRepsitory) {
		super();
		this.profileEntityRepsitory = profileEntityRepsitory;
	}



	public Optional<ProfileEntity> findById(String username) {
		return profileEntityRepsitory.findById(username);
	}
	
	public List<ProfileEntity> findAll() {
		return profileEntityRepsitory.findAll();
	}
	
	public void deleteById(String username) {
		 profileEntityRepsitory.deleteById(username);
	}
	
	public void delete(ProfileEntity entity) {
		profileEntityRepsitory.delete(entity);
	}
	
	public ProfileEntity save(ProfileEntity entity) {
		return profileEntityRepsitory.save(entity);
	}
}
