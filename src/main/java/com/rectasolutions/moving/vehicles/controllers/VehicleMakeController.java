package com.rectasolutions.moving.vehicles.controllers;

import com.rectasolutions.moving.vehicles.entities.*;
import com.rectasolutions.moving.vehicles.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleMakeController {

    @Autowired
    private VehicleMakeService vehicleMakeService;

    // VEHICLE MAKES

    @GetMapping("/makes")
    public ResponseEntity<List<VehicleMake>> getVehicleMakes() {
        return new ResponseEntity<>(vehicleMakeService.getAllVehicleMakes(), HttpStatus.OK);
    }

    @GetMapping("/makes/{id}")
    public ResponseEntity<VehicleMake> getVehicleMakeById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleMakeService.getVehicleMakeById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/categories/makes/{categoryId}")
    public ResponseEntity<List<VehicleMake>> getVehicleMakesByCategoryId(@PathVariable("categoryId") int categoryId) {
        return new ResponseEntity<>(vehicleMakeService.getVehicleMakesByCategoryId(categoryId), HttpStatus.OK);
    }

    @PostMapping("/makes")
    public ResponseEntity<String> saveVehicleMake(@RequestBody VehicleMake vehicleMake) {
        vehicleMakeService.saveVehicleMake(vehicleMake);
        return new ResponseEntity<>("New make is added", HttpStatus.OK);
    }

    @PutMapping("/makes/{id}")
    public ResponseEntity<Object> updateVehicleMake(@RequestBody VehicleMake vehicleMakeBody, @PathVariable int id) {

        Optional<VehicleMake> vehicleMake = vehicleMakeService.getVehicleMakeById(id);

        if (!vehicleMake.isPresent()) {
            return new ResponseEntity<>("The make is not found", HttpStatus.NOT_FOUND);
        }

        vehicleMakeBody.setId(id);
        vehicleMakeService.saveVehicleMake(vehicleMakeBody);

        return new ResponseEntity<>("The make has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/makes/{id}")
    public ResponseEntity<String> deleteVehicleMake(@PathVariable int id) {
        Optional<VehicleMake> vehicleMake = vehicleMakeService.getVehicleMakeById(id);
        if (!vehicleMake.isPresent()) {
            return new ResponseEntity<>("The make is not found", HttpStatus.NOT_FOUND);
        }
        vehicleMakeService.deleteVehicleMake(vehicleMake.get());
        return new ResponseEntity<>("The make has been deleted", HttpStatus.OK);
    }
}