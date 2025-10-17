package com.example.demo.models; 

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Form-backing DTO for the login page.
 * <p>
 * This model is intentionally limited to the credentials required for authentication
 * and is kept separate from the full {@code User} registration model to avoid
 * triggering unrelated validation constraints (e.g., first name, email) during login.
 */
public class LoginRequest {

    /** Username submitted by the user; required for authentication. */
    @NotBlank(message = "Username is required")
    private String username;

    /** Plain-text password submitted by the user; validated for minimum length. */
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    /** @return the submitted username */
    public String getUsername() { return username; }

    /** @param username sets the submitted username */
    public void setUsername(String username) { this.username = username; }

    /** @return the submitted password */
    public String getPassword() { return password; }

    /** @param password sets the submitted password */
    public void setPassword(String password) { this.password = password; }
}


