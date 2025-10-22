package com.example.demo.config;

import com.example.demo.services.AuthService;
import com.example.demo.services.impl.InMemoryAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring Core configuration for the Login module.
 * Defines explicit beans for the authentication service and its seed data.
 * This demonstrates Java-based configuration and DI without a database.
 */
@Configuration
public class AuthConfig {

    /** Seeded username → password (demo-only; plain text for this milestone). */
    @Bean(name = "loginUsers")
    public Map<String, String> loginUsers() {
        Map<String, String> users = new ConcurrentHashMap<>();
        users.put("demo", "demo123");            // <- use this in your demo
        users.put("employee", "password");       // <- second demo account
        return users;
    }

    /** Seeded username → display name for the navbar greeting. */
    @Bean(name = "loginDisplayNames")
    public Map<String, String> loginDisplayNames() {
        Map<String, String> names = new ConcurrentHashMap<>();
        names.put("demo", "Demo User");
        names.put("employee", "Employee User");
        return names;
    }

    /** Seeded username → role (coarse-grained; used later for Security). */
    @Bean(name = "loginRoles")
    public Map<String, String> loginRoles() {
        Map<String, String> roles = new ConcurrentHashMap<>();
        roles.put("demo", "USER");
        roles.put("employee", "USER");
        return roles;
    }

    /**
     * Authentication service wired via constructor injection using the seed maps above.
     * This replaces component-scanned @Service with explicit @Bean wiring.
     */
    @Bean
    public AuthService authService(
            @Qualifier("loginUsers") Map<String, String> users,
            @Qualifier("loginDisplayNames") Map<String, String> displayNames,
            @Qualifier("loginRoles") Map<String, String> roles) {

        return new InMemoryAuthService(users, displayNames, roles);
    }
}

