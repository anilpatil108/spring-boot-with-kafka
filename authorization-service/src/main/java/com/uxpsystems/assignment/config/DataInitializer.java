package com.uxpsystems.assignment.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.dao.UserCredentialEntityRepsitory;
import com.uxpsystems.assignment.model.UserCredentialEntity;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserCredentialEntityRepsitory userCredentialRepositry;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing admin user...");
        String encrypted = passwordEncoder.encode("password");
        log.info("password:{}",encrypted);
        UserCredentialEntity admin = new UserCredentialEntity("admin", encrypted);
        admin.setRoles(new ArrayList<>(Arrays.asList("ROLE_USER", "ROLE_ADMIN")));
        this.userCredentialRepositry.save(admin);
    }
}
