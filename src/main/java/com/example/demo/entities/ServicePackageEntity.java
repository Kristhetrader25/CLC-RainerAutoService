package com.example.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Table("service_packages")
public class ServicePackageEntity {
    @Id
    private Long id;
    
    private String code;
    private String name;
    private String description;
    
    @Column("base_price")
    private BigDecimal basePrice;
    
    @Column("duration_mins")
    private Integer durationMins;
    
    private String category;
    
    @Column("recommended_miles")
    private Integer recommendedMiles;
    
    private Boolean active;

    public Long getId() 
    { return id; } 
    
    public void setId(Long id) 
    { 
    	this.id = id; 
    }
    
    public String getCode() 
    { 
    	return code; 
    } 
    
    public void setCode(String code) 
    { 
    	this.code = code; 
    }
    
    public String getName() 
    { 
    	return name; 
    } 
    
    public void setName(String name) 
    { 
    	this.name = name; 
    }
    
    public String getDescription() 
    { 
    	return description; 
    } 
    
    public void setDescription(String description) 
    { 
    	this.description = description; 
    }
    
    public BigDecimal getBasePrice() 
    { 
    	return basePrice; 
    } 
    
    public void setBasePrice(BigDecimal basePrice) 
    { 
    	this.basePrice = basePrice; 
    }
    
    public Integer getDurationMins() 
    { 
    	return durationMins; 
    } 
    
    public void setDurationMins(Integer durationMins) 
    { 
    	this.durationMins = durationMins; 
    }
    
    public String getCategory() 
    { 
    	return category; 
    } 
    
    public void setCategory(String category) 
    { 
    	this.category = category; 
    }
    
    public Integer getRecommendedMiles() 
    { 
    	return recommendedMiles; 
    } 
    
    public void setRecommendedMiles(Integer recommendedMiles) 
    { 
    	this.recommendedMiles = recommendedMiles; 
    }
    
    public Boolean getActive() 
    { 
    	return active; 
    } 
    
    public void setActive(Boolean active) 
    { 
    	this.active = active; 
    }
}

