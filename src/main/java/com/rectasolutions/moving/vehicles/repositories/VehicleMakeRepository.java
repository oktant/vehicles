package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake,Integer> {
    List<VehicleMake> findByVehicleCategory(int vehicleCategory);
}
