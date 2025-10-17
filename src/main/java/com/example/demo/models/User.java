package com.example.demo.models;

import jakarta.validation.constraints.*;

// This class represents a user registering for the application
public class User {

	// First Name
	@NotBlank(message = "First name is required.")
	private String firstName;
	
	// Last Name
	@NotBlank(message = "Last name is required.")
	private String lastName;
	
	// Valid Email Formatting
	@Email(message = "Email must be valid.")
	@NotBlank(message = "Email is required.")
	private String email;
	
	// Valid Phone Number
	@Pattern(regexp = "^\\+?[0-9 .\\-()]{7,20}$", message = "Phone must be valid")
	private String phone;
	
	// Username between 4-20 characters
	@Size(min = 4, max = 20, message = "Username must be 4 - 20 characters.")
	@NotBlank(message = "Username is required")
	private String username;
	
	// Password must be at least 6 characters
	@Size(min = 6, message = "Password must be at least 6 characters")
	@NotBlank(message = "Password is required")
	private String password;

	// Getters and Setters for the above fields
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
}
