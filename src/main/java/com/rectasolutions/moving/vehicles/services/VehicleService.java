package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.Vehicle;
import com.rectasolutions.moving.vehicles.repositories.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dell on 21-Aug-18.
 */
@Service
public class VehicleService {
    private VehiclesRepository vehiclesRepository;

    @Autowired
    public VehicleService(VehiclesRepository vehiclesRepository){
        this.vehiclesRepository = vehiclesRepository;
    }

    public Optional<Vehicle> getVehicleById(int id){
        return vehiclesRepository.findById(id);
    }

    public List<Vehicle> getAllVehicles(){
        return vehiclesRepository.findAll();
    }

    public List<Vehicle> getVehiclesByUserId(String userId){
        return vehiclesRepository.findByUser(userId);
    }

    public List<Vehicle> getVehiclesByCategoryId(int categoryId){
        return vehiclesRepository.findByVehicleCategory(categoryId);
    }

    public Vehicle saveVehicle(Vehicle vehicle){
        return vehiclesRepository.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle) {
        vehiclesRepository.delete(vehicle);
    }
}
