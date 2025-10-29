package com.example.demo.repositories;

import com.example.demo.entities.ServicePackageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePackageRepository extends CrudRepository<ServicePackageEntity, Long> 
{
    ServicePackageEntity findByCode(String code);
}

