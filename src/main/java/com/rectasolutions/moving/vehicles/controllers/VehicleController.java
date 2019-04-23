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
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // VEHICLE

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/categories/vehicles/{categoryId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCategoryId(@PathVariable("categoryId") int categoryId) {
        return new ResponseEntity<>(vehicleService.getVehiclesByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/users/vehicles/{user}")
    public ResponseEntity<List<Vehicle>> getVehiclesByUserId(@PathVariable("user") String user) {
        return new ResponseEntity<>(vehicleService.getVehiclesByUserId(user), HttpStatus.OK);
    }

    @PostMapping("/vehicles")
    public ResponseEntity<String> saveVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>("New vehicle is added", HttpStatus.OK);
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Object> updateVehicle(@RequestBody Vehicle vehiclelBody, @PathVariable int id) {

        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);

        if (!vehicle.isPresent()) {
            return new ResponseEntity<>("The vehicle is not found", HttpStatus.NOT_FOUND);
        }

        vehiclelBody.setId(id);
        vehicleService.saveVehicle(vehiclelBody);

        return new ResponseEntity<>("The vehicle has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (!vehicle.isPresent()) {
            return new ResponseEntity<>("The vehicle is not found", HttpStatus.NOT_FOUND);
        }
        vehicleService.deleteVehicle(vehicle.get());
        return new ResponseEntity<>("The vehicle has been deleted", HttpStatus.OK);
    }
}
