package com.uxpsystems.assignment.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.dao.UserCredentialEntityRepsitory;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserCredentialEntityRepsitory userCredentialRepositry;

    public CustomUserDetailsService(UserCredentialEntityRepsitory userCredentialRepositry) {
        this.userCredentialRepositry = userCredentialRepositry;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userCredentialRepositry.findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
