package com.krispam.property.repositories;

import com.krispam.property.domain.entities.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

    Iterable<PropertyEntity> findByTitleContainingIgnoreCase(String title);

    Iterable<PropertyEntity> findByAddressContainingIgnoreCase(String address);
}
