package com.krispam.property.services;

import com.krispam.property.domain.entities.PropertyEntity;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    PropertyEntity create(PropertyEntity property);

    Optional<PropertyEntity> getPropertyById(Long id);

    List<PropertyEntity> findAll();

    List<PropertyEntity> searchByTitle(String title);

    List<PropertyEntity> searchByAddress(String address);

    void deleteById(Long id);
}

