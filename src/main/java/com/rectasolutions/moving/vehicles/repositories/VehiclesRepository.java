package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VehiclesRepository extends JpaRepository<Vehicle,Integer> {
    List<Vehicle> findByUser(String user);
    List<Vehicle> findByVehicleCategory(int vehicleCategory);
}
