package com.uxpsystems.assignment.controller;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException() {
    }

    public ProfileNotFoundException(String userName ) {
        super("UserName: " +userName +" not found.");
    }
}
