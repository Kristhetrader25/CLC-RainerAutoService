package com.example.demo.models;

import java.math.BigDecimal;

/**
 * Domain model representing a service package ("product") offered by Rainier Auto Service.
 * Example: "Synthetic Oil Change", "Brake Pad Replacement".
 * Note: This is not persisted yet (Milestone 4 will add JPA & a database table).
 */
public class ServicePackage {

    private Long id; // null until DB exists
    private String code;           // e.g., OIL-SYN-5W30
    private String name;           // e.g., "Synthetic Oil Change"
    private String description;    // marketing/what's included
    private BigDecimal basePrice;  // e.g., 89.99
    private Integer durationMins;  // estimated shop time, e.g., 45
    private String category;       // "Maintenance" | "Repair" | "Diagnostic"
    private Integer recommendedMiles; // e.g., 5000 for oil change
    private boolean active;        // can customers book this?

    // Getters/setters
    public Long getId() { return id; } 
    
    public void setId(Long id) { this.id = id; }
    
    public String getCode() { return code; } 
    
    public void setCode(String code) { this.code = code; }
    
    public String getName() { return name; } 
    
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; } 
    
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getBasePrice() { return basePrice; } 
    
    public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }
    
    public Integer getDurationMins() { return durationMins; } 
    
    public void setDurationMins(Integer durationMins) { this.durationMins = durationMins; }
    
    public String getCategory() { return category; } 
    
    public void setCategory(String category) { this.category = category; }
    
    public Integer getRecommendedMiles() { return recommendedMiles; } 
    
    public void setRecommendedMiles(Integer recommendedMiles) { this.recommendedMiles = recommendedMiles; }
    
    public boolean isActive() { return active; } 
    
    public void setActive(boolean active) { this.active = active; }
}

