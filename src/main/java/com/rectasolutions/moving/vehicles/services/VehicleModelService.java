package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleModel;
import com.rectasolutions.moving.vehicles.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleModelService {
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    public VehicleModelService(VehicleModelRepository vehicleModelRepository){
        this.vehicleModelRepository = vehicleModelRepository;
    }

    public Optional<VehicleModel> getVehicleModelById(int id){
        return vehicleModelRepository.findById(id);
    }

    public List<VehicleModel> getAllVehicleModels(){
        return vehicleModelRepository.findAll();
    }

    public List<VehicleModel> getVehicleModelsByMakeId(int makeId){
        return vehicleModelRepository.findByVehicleMake(makeId);
    }

    public VehicleModel saveVehicleModel(VehicleModel vehicleModel){
        return vehicleModelRepository.save(vehicleModel);
    }

    public void deleteVehicleModel(VehicleModel vehicleModel) {
        vehicleModelRepository.delete(vehicleModel);
    }
}
