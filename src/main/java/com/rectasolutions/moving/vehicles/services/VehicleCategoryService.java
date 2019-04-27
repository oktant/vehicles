package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.repositories.VehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleCategoryService {
    private VehicleCategoryRepository vehicleCategoryRepository;

    @Autowired
    public VehicleCategoryService(VehicleCategoryRepository vehicleCategoryRepository){
        this.vehicleCategoryRepository = vehicleCategoryRepository;
    }

    public Optional<VehicleCategory> getVehicleCategoryById(int id){
        return vehicleCategoryRepository.findById(id);
    }

    public List<VehicleCategory> getAllVehicleCategories(){
        return vehicleCategoryRepository.findAll();
    }

    public VehicleCategory saveVehicleCategory(VehicleCategory vehicleCategory){
        return vehicleCategoryRepository.save(vehicleCategory);
    }

    public void deleteVehicleCategory(VehicleCategory vehicleCategory) {
        vehicleCategoryRepository.delete(vehicleCategory);
    }
    public  VehicleCategory getVehicleCategoryByDistance(double distance){
        Optional<VehicleCategory> optional = vehicleCategoryRepository.findFirstByDistance(distance);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    public VehicleCategory  getVehicleCategoryByDistanceAndPayload(double distance,double payload){
        Optional<VehicleCategory> optional = vehicleCategoryRepository.findFirstByPayload(distance,payload);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
