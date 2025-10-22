package com.example.demo.services.impl;

import com.example.demo.models.LoginPrincipal;
import com.example.demo.services.AuthService;

import java.util.Map;

/**
 * In-memory authentication service used for early milestones.
 * This class is configured via Spring JavaConfig (@Bean) instead of component scanning.
 * All seed data is injected through the constructor.
 */
public class InMemoryAuthService implements AuthService {

    private final Map<String, String> users;         // username → password
    private final Map<String, String> displayNames;  // username → display name
    private final Map<String, String> roles;         // username → role

    /** Constructor injection from AuthConfig. */
    public InMemoryAuthService(Map<String, String> users,
                               Map<String, String> displayNames,
                               Map<String, String> roles) {
        this.users = users;
        this.displayNames = displayNames;
        this.roles = roles;
    }

    @Override
    public LoginPrincipal authenticate(String username, String rawPassword) {
        if (username == null || rawPassword == null) return null;
        String stored = users.get(username);
        if (stored != null && stored.equals(rawPassword)) {
            return new LoginPrincipal(
                username,
                displayNames.getOrDefault(username, username),
                roles.getOrDefault(username, "USER")
            );
        }
        return null;
    }

    @Override
    public void logout(jakarta.servlet.http.HttpSession session) {
        session.invalidate();
    }

    /**
     * Temporary hook for Registration (pre-DB): add a new in-memory user at runtime.
     */
    public void registerTemp(String username, String password, String displayName) {
        users.put(username, password);
        displayNames.put(username, displayName);
        roles.put(username, "USER");
    }
}
