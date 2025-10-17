package com.example.demo.models;

/**
 * Lightweight session identity captured after a successful authentication.
 * <p>
 * {@code LoginPrincipal} is intentionally minimal and decoupled from any
 * persistence concerns so it can be safely stored in the HTTP session and
 * consumed by the UI (e.g., header greeting, menu toggling) without exposing
 * sensitive details. A richer {@code User} entity will be introduced when the
 * database layer is implemented in later milestones.
 */
public class LoginPrincipal {

    /** Database identifier of the user; {@code null} until a real DB is introduced. */
    private Long userId; // null until DB exists

    /** Unique username used for authentication and identification. */
    private String username;

    /** Friendly name rendered in the UI after login (e.g., "Hello, Chris"). */
    private String displayName;

    /** Logical role label (e.g., "USER", "ADMIN") for coarse-grained UI logic. */
    private String role;

    /**
     * Constructs a principal snapshot with the essential identity attributes.
     *
     * @param username    unique username
     * @param displayName friendly, UI-facing display name
     * @param role        role label (e.g., "USER")
     */
    public LoginPrincipal(String username, String displayName, String role) {
        this.username = username;
        this.displayName = displayName;
        this.role = role;
    }

    /** @return database identifier or {@code null} if not assigned yet */
    public Long getUserId() { return userId; }

    /** @param userId database identifier to associate when available */
    public void setUserId(Long userId) { this.userId = userId; }
    
    /** @return unique username */
    public String getUsername() { return username; }

    /** @param username unique username */
    public void setUsername(String username) { this.username = username; }
    
    /** @return friendly display name shown in the UI */
    public String getDisplayName() { return displayName; }

    /** @param displayName UI-facing friendly name */
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    /** @return role label for coarse-grained access/UI logic */
    public String getRole() { return role; }

    /** @param role role label (e.g., "USER", "ADMIN") */
    public void setRole(String role) { this.role = role; }
}

