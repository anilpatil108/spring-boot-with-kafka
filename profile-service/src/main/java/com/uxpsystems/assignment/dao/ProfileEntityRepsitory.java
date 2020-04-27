package com.uxpsystems.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.ProfileEntity;

@Repository
public interface ProfileEntityRepsitory extends JpaRepository<ProfileEntity, String> {
	
}
