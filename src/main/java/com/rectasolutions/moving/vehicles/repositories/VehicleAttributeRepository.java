package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dell on 21-Aug-18.
 */
@Repository
public interface VehicleAttributeRepository extends JpaRepository<VehicleAttribute,Integer>{
    List<VehicleAttribute> findByVehicle(int vehicle);
    List<VehicleAttribute> findByAttribute(int attribute);
}
