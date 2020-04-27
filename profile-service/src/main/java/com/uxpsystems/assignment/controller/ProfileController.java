package com.uxpsystems.assignment.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.uxpsystems.assignment.pojo.ActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.web.bind.annotation.*;
import com.uxpsystems.assignment.model.ProfileEntity;
import com.uxpsystems.assignment.pojo.ProfileEvent;
import com.uxpsystems.assignment.pojo.ProfileRequest;
import com.uxpsystems.assignment.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	private ProfileService profileService;
	
	@Autowired
	public ProfileController(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}
	
	@Bean
	public RecordMessageConverter converter() {
		return new StringJsonMessageConverter();
	}

	@Bean
	public BatchMessagingMessageConverter batchConverter() {
		return new BatchMessagingMessageConverter(converter());
	}

	@Autowired
	private KafkaTemplate<String, ProfileEvent> template;

	@GetMapping
	public List<ProfileEntity> findAll() {
		return profileService.findAll();
	}
	
	@PostMapping
	public ProfileEntity save(@RequestBody ProfileRequest profileRequest) {
		ProfileEntity entity = profileService.save(
				new ProfileEntity(profileRequest.getUsername(), 
						profileRequest.getAddress(), 
						profileRequest.getPhoneNumber())
				);
		return entity;
	}
	
	@PutMapping
	public ProfileEntity update(@RequestBody ProfileRequest profileRequest) {
		ProfileEntity entity = profileService.save(
				new ProfileEntity(profileRequest.getUsername(), 
						profileRequest.getAddress(), 
						profileRequest.getPhoneNumber())
				);
		return entity;
	}
	
	@DeleteMapping("/{username}")
	public ProfileEntity delete(@PathVariable("username") String username) {
		Optional<ProfileEntity> optionalEntity = profileService.findById(username);
		if(optionalEntity.isPresent()) {
			profileService.delete(optionalEntity.get());
		} else {
			throw new RuntimeException("Username not present");
		}
		return optionalEntity.get();
	}
	
	@KafkaListener(topics = "profile-service", groupId = "profile-consumer-group")
    public void consumeJson(ProfileEvent event) {
		System.out.println("Consumed JSON Message: " + event);
		if (event.getAction()== ActionEnum.UPDATE) {
			String username = event.getProfile().getUsername();
			String address = event.getProfile().getAddress();
			String phoneNumber = event.getProfile().getPhoneNumber();
			profileService.save(new ProfileEntity(username, address, phoneNumber));
		} else if (event.getAction()== ActionEnum.DELETE) {
			String username = event.getProfile().getUsername();
			profileService.deleteById(username);
		}
    }
}
