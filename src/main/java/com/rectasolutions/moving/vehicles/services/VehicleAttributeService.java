package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleAttribute;
import com.rectasolutions.moving.vehicles.repositories.VehicleAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleAttributeService {
    private VehicleAttributeRepository vehicleAttributeRepository;

    @Autowired
    public VehicleAttributeService(VehicleAttributeRepository vehicleAttributeRepository){
        this.vehicleAttributeRepository = vehicleAttributeRepository;
    }

    public Optional<VehicleAttribute> getVehicleAttributeById(int id){
        return vehicleAttributeRepository.findById(id);
    }

    public List<VehicleAttribute> getAllVehicleAttributes(){
        return vehicleAttributeRepository.findAll();
    }

    public List<VehicleAttribute> getVehicleAttributesByVehicleId(int vehicleId){
        return vehicleAttributeRepository.findByVehicle(vehicleId);
    }

    public List<VehicleAttribute> getVehicleAttributesByAttributeId(int attributeId){
        return vehicleAttributeRepository.findByAttribute(attributeId);
    }

    public VehicleAttribute saveVehicleAttribute(VehicleAttribute vehicleAttribute){
        return vehicleAttributeRepository.save(vehicleAttribute);
    }

    public void deleteVehicleAttribute(VehicleAttribute vehicleAttribute) {
        vehicleAttributeRepository.delete(vehicleAttribute);
    }

}
