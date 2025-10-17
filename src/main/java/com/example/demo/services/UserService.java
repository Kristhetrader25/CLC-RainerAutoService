package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.models.User;

// This class stores the registered users in memory for the moment
// Then checks if a username already exists
@Service
public class UserService {
	
	// Temporary in memory storage
	private final Map<String, User> users = new HashMap<>();
	
	// Check if a username is already taken
	public boolean usernameTaken(String username) {
		return users.containsKey(username.toLowerCase());
	}
	
	// Saving a new user to the memory list
	public void register(User user) {
		users.put(user.getUsername().toLowerCase(), user);
	}

}
