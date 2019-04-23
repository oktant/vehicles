package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.Vehicle;
import com.rectasolutions.moving.vehicles.repositories.VehicleAttributeRepository;
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

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private VehicleAttributeRepository vehicleAttributeRepository;

    @Autowired
    private VehicleCategoryService vehicleCategoryService;

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

//    public ResponseEntity<String> addNewVehicle(String userId, Map<Attribute, String> attrs, int vehicleCategoryId) {
//
//        Vehicle v1=vehiclesRepository.save(addIntoVehicle(userId, vehicleCategoryId));
//        addVehicleAttrs(v1,attrs);
//        return new ResponseEntity<>("Vehicle is added", HttpStatus.OK);
//    }
//    private Vehicle addIntoVehicle(String userId, int vehicleCategoryId){
//        Vehicle vehicle = new Vehicle();
//        UserDB userDB = new UserDB();
//        userDB.setId(userId);
//        userDB.setStatus("offline");
//        vehicle.setUser(userDB);
//        vehicle.setVehicleCategory(vehicleCategoryService.getVehicleCategoryById(vehicleCategoryId).get());
//        return vehicle;
//    }
//    private void addVehicleAttrs(Vehicle vehicle, Map<Attribute, String> attrs){
//
//        attrs.forEach((attribute,value) -> {
//            VehicleAttribute vehicleAttribute = new VehicleAttribute();
//            vehicleAttribute.setVehicle(vehicle);
//            vehicleAttribute.setAttribute(attribute);
//            vehicleAttribute.setValue(value);
//            vehicleAttributeRepository.save(vehicleAttribute);
//            System.out.println("attribute: "+attribute+" value:"+value);
//        });
//    }
}
