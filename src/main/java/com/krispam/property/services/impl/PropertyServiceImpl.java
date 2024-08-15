package com.krispam.property.services.impl;

import com.krispam.property.domain.entities.PropertyEntity;
import com.krispam.property.repositories.PropertyRepository;
import com.krispam.property.services.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    @Override
    public PropertyEntity create(PropertyEntity property) {
        return propertyRepository.save(property);
    }

    @Override
    public Optional<PropertyEntity> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<PropertyEntity> findAll() {
        return StreamSupport
                .stream(propertyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyEntity> searchByTitle(String title) {
        Iterable<PropertyEntity> result = propertyRepository.findByTitleContainingIgnoreCase(title);
        return StreamSupport
                .stream(result.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyEntity> searchByAddress(String address) {
        Iterable<PropertyEntity> result = propertyRepository
                .findByAddressContainingIgnoreCase(address);
        return StreamSupport
                .stream(result.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        propertyRepository.deleteById(id);
    }

}
