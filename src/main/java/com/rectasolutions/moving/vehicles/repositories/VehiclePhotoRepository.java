package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.Vehicle;
import com.rectasolutions.moving.vehicles.entities.VehiclePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiclePhotoRepository extends JpaRepository<VehiclePhoto, Integer> {
    public List<VehiclePhoto> findByVehicle(Vehicle vehicle);
}
