package com.example.demo.models;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Form-backing bean for creating a new Service Package ("product").
 * Keeps validation separate from persistence concerns.
 */
public class ProductForm {

    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^[A-Z0-9\\-]{3,30}$", message = "Code must be 3-30 chars (A-Z, 0-9, dashes)")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 60, message = "Name must be at most 60 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Base price is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Base price must be greater than 0")
    private BigDecimal basePrice;

    @NotNull(message = "Duration (minutes) is required")
    @Min(value = 10, message = "Duration must be at least 10 minutes")
    @Max(value = 600, message = "Duration must be at most 600 minutes")
    private Integer durationMins;

    @NotBlank(message = "Category is required")
    private String category; // Maintenance, Repair, Diagnostic

    @Min(value = 0, message = "Recommended miles must be 0 or higher")
    private Integer recommendedMiles; // can be 0 or null if not applicable

    private boolean active = true;

    // getters/setters
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

