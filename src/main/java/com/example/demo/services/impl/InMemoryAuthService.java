package com.example.demo.services.impl;

import com.example.demo.models.LoginPrincipal;
import com.example.demo.services.AuthService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code InMemoryAuthService} provides a temporary, in-memory implementation of {@link AuthService}
 * for early milestones where no database is used.
 * <p>
 * Credentials and lightweight user display/role metadata are stored in thread-safe maps.
 * This class is intended for demo and development only and will be replaced by a JPA-backed
 * implementation in later milestones.
 */
@Service
public class InMemoryAuthService implements AuthService {

    /** Username → plain-text password (demo-only; to be replaced with hashed passwords later). */
    private final Map<String, String> users = new ConcurrentHashMap<>();
    /** Username → display name shown in the UI header after login. */
    private final Map<String, String> displayNames = new ConcurrentHashMap<>();
    /** Username → role label (e.g., "USER", "ADMIN"). */
    private final Map<String, String> roles = new ConcurrentHashMap<>();

    /**
     * Seeds a demo account for local testing.
     * <p>
     * Username: {@code demo} — Password: {@code demo123}
     */
    @PostConstruct
    public void seed() {
        users.put("demo", "demo123");
        displayNames.put("demo", "Demo User");
        roles.put("demo", "USER");
    }

    /**
     * Attempts to authenticate a user by validating the supplied credentials against
     * the in-memory store. On success, returns a lightweight {@link LoginPrincipal}
     * suitable for storing in the HTTP session; otherwise returns {@code null}.
     *
     * @param username   the submitted username; may be {@code null}
     * @param rawPassword the submitted (plain) password; may be {@code null}
     * @return a {@link LoginPrincipal} when credentials are valid; {@code null} otherwise
     */
    @Override
    public LoginPrincipal authenticate(String username, String rawPassword) {
        // Null-guard: fail fast if either field is missing.
        if (username == null || rawPassword == null) return null;

        // Lookup stored password and compare using simple equals (demo-only).
        String stored = users.get(username);
        if (stored != null && stored.equals(rawPassword)) {
            // Build a minimal principal for session storage and UI rendering.
            return new LoginPrincipal(
                username,
                displayNames.getOrDefault(username, username),
                roles.getOrDefault(username, "USER")
            );
        }
        // Invalid credentials.
        return null;
    }

    /**
     * Invalidates the current HTTP session to log the user out.
     *
     * @param session the active HTTP session to invalidate
     */
    @Override
    public void logout(jakarta.servlet.http.HttpSession session) {
        session.invalidate();
    }

    /**
     * Temporary utility to allow the Registration module to add users to the
     * in-memory store prior to database implementation.
     *
     * @param username    new user's username
     * @param password    new user's (plain) password (demo-only)
     * @param displayName friendly display name for header greeting
     */
    // temp hook for Registration module before DB
    public void registerTemp(String username, String password, String displayName) {
        users.put(username, password);
        displayNames.put(username, displayName);
        roles.put(username, "USER");
    }
}
