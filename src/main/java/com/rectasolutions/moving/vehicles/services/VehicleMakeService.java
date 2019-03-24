package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleMake;
import com.rectasolutions.moving.vehicles.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMakeService {
    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    public Optional<VehicleMake> getVehicleMakeById(int id){
        return vehicleMakeRepository.findById(id);
    }

    public List<VehicleMake> getAllVehicleMakes(){
        return vehicleMakeRepository.findAll();
    }

    public List<VehicleMake> getVehicleMakesByCategoryId(int vehicleCategory){
        return vehicleMakeRepository.findByVehicleCategory(vehicleCategory);
    }

    public VehicleMake saveVehicleMake(VehicleMake vehicleMake){
        return vehicleMakeRepository.save(vehicleMake);
    }

    public void deleteVehicleMake(VehicleMake vehicleMake) {
        vehicleMakeRepository.delete(vehicleMake);
    }
}
