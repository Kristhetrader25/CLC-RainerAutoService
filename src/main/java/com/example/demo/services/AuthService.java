package com.example.demo.services;

import com.example.demo.models.LoginPrincipal;

/**
 * Defines the authentication boundary for the application.
 * <p>
 * An {@code AuthService} implementation is responsible for verifying user
 * credentials and establishing a lightweight session identity for downstream
 * use (e.g., toggling UI state). Implementations may vary by milestone
 * (in-memory for early milestones, JPA-backed later, Spring Security eventually).
 */
public interface AuthService {

    /**
     * Attempts to authenticate a user with the provided credentials.
     *
     * @param username    the submitted username (may be {@code null})
     * @param rawPassword the submitted plain-text password (may be {@code null})
     * @return a {@link LoginPrincipal} representing the authenticated user when
     *         credentials are valid; {@code null} otherwise
     */
    LoginPrincipal authenticate(String username, String rawPassword);

    /**
     * Logs out the current user by invalidating the active HTTP session.
     *
     * @param session the HTTP session to invalidate
     */
    void logout(jakarta.servlet.http.HttpSession session);
}


