package com.rectasolutions.moving.vehicles.controllers;

import com.rectasolutions.moving.vehicles.entities.*;
import com.rectasolutions.moving.vehicles.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleCategoryController {

    @Autowired
    private VehicleCategoryService vehicleCategoryService;

    // VEHICLE CATEGORY

    @GetMapping("/hello")
    public String hello (Principal principal) {
        return "Hello "+ principal.getName();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<VehicleCategory>> getVehicleCategories() {
        return new ResponseEntity<>(vehicleCategoryService.getAllVehicleCategories(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<VehicleCategory> getVehicleCategoryById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleCategoryService.getVehicleCategoryById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<String> saveVehicleCategory(@RequestBody VehicleCategory vehicleCategory) {
        vehicleCategoryService.saveVehicleCategory(vehicleCategory);
        return new ResponseEntity<>("New Category is added", HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateVehicleCategory(@RequestBody VehicleCategory vehicleCategoryBody, @PathVariable int id) {

        Optional<VehicleCategory> vehicleCategory = vehicleCategoryService.getVehicleCategoryById(id);

        if (!vehicleCategory.isPresent()) {
            return new ResponseEntity<>("The category is not found", HttpStatus.NOT_FOUND);
        }

        vehicleCategoryBody.setId(id);
        vehicleCategoryService.saveVehicleCategory(vehicleCategoryBody);

        return new ResponseEntity<>("The category has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteVehicleCategory(@PathVariable int id) {
        Optional<VehicleCategory> vehicleCategory = vehicleCategoryService.getVehicleCategoryById(id);
        if (!vehicleCategory.isPresent()) {
            return new ResponseEntity<>("The category is not found", HttpStatus.NOT_FOUND);
        }
        vehicleCategoryService.deleteVehicleCategory(vehicleCategory.get());
        return new ResponseEntity<>("The category has been deleted", HttpStatus.OK);
    }
    @GetMapping("/categories/distance/{distance}")
    public VehicleCategory getVehicleCategoryByDistance(@PathVariable double distance){
        return vehicleCategoryService.getVehicleCategoryByDistance(distance);
    }

    @GetMapping("/categories/distance/{distance}/payload/{payload}")
    public VehicleCategory getVehicleCategoryByPayload(@PathVariable double distance,@PathVariable double payload){
        return vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(distance,payload);
    }
}
