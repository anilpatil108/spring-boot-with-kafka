package com.uxpsystems.assignment.controller;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.uxpsystems.assignment.pojo.ActionEnum;
import com.uxpsystems.assignment.pojo.Profile;
import com.uxpsystems.assignment.pojo.ProfileEvent;



@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	private KafkaTemplate<String, ProfileEvent> kafkaTemplate;
	private RestTemplate restTemplate;
	private String profileServiceUri;

	@Autowired
	public ProfileController(RestTemplate restTemplate, KafkaTemplate<String, ProfileEvent> kafkaTemplate, @Value(
			"${app.service.profile.url}") String profileServiceUri) {
		super();
		this.kafkaTemplate = kafkaTemplate;
		this.restTemplate = restTemplate;
		this.profileServiceUri = profileServiceUri;
	}
	
	@GetMapping
	public Profile[] findAll() {
		Profile[] profiles = restTemplate.getForObject(profileServiceUri+"/profile", Profile[].class);
		return profiles;
	}
	
	@PostMapping
	public Profile save(@RequestBody Profile profileRequest) {
		Profile profile = restTemplate.postForObject(profileServiceUri+"/profile", profileRequest, Profile.class);
		return profile;
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Profile profileRequest) {
		try {
			sendToKafka(new ProfileEvent(ActionEnum.UPDATE, profileRequest));
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<String>  delete(@PathVariable("username") String username) {
		try {
			sendToKafka(new ProfileEvent(ActionEnum.DELETE, new Profile(username)));
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public void sendToKafka(ProfileEvent event) throws IOException {
		kafkaTemplate.send("profile-service", event);
	}
	
}
