package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

}
