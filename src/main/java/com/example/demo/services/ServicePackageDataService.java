package com.example.demo.services;

import com.example.demo.entities.ServicePackageEntity;
import com.example.demo.models.ProductForm;
import com.example.demo.repositories.ServicePackageRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicePackageDataService {

    private final ServicePackageRepository repo;

    public ServicePackageDataService(ServicePackageRepository repo) 
    {
        this.repo = repo;
    }

    public ServicePackageEntity save(ProductForm form) 
    {
        ServicePackageEntity e = new ServicePackageEntity();
        e.setCode(form.getCode());
        e.setName(form.getName());
        e.setDescription(form.getDescription());
        e.setBasePrice(form.getBasePrice());
        e.setDurationMins(form.getDurationMins());
        e.setCategory(form.getCategory());
        e.setRecommendedMiles(form.getRecommendedMiles());
        e.setActive(form.isActive());
        return repo.save(e);
    }
}
