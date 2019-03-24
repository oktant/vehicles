package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.repositories.VehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleCategoryService {
    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    public Optional<VehicleCategory> getVehicleCategoryById(int id){
        return vehicleCategoryRepository.findById(id);
    }

    public List<VehicleCategory> getAllVehicleCategories(){
        return vehicleCategoryRepository.findAll();
    }

//    public List<VehicleCategory> getVehicleCategoriesByParentId(int parentId){
//        return vehicleCategoryRepository.findByVehicleCategory(parentId);
//    }

    public VehicleCategory saveVehicleCategory(VehicleCategory vehicleCategory){
        return vehicleCategoryRepository.save(vehicleCategory);
    }

    public void deleteVehicleCategory(VehicleCategory vehicleCategory) {
        vehicleCategoryRepository.delete(vehicleCategory);
    }
}
