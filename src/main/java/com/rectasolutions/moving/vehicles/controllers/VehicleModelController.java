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
public class VehicleModelController {


    @Autowired
    private VehicleModelService vehicleModelService;

    // VEHICLE MODELS

    @GetMapping("/models")
    public ResponseEntity<List<VehicleModel>> getVehicleModels() {
        return new ResponseEntity<>(vehicleModelService.getAllVehicleModels(), HttpStatus.OK);
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<VehicleModel> getVehicleModelById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleModelService.getVehicleModelById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/makes/models/{modelId}")
    public ResponseEntity<List<VehicleModel>> getVehicleModelsByMakeId(@PathVariable("makeId") int makeId) {
        return new ResponseEntity<>(vehicleModelService.getVehicleModelsByMakeId(makeId), HttpStatus.OK);
    }

    @PostMapping("/models")
    public ResponseEntity<String> saveVehicleModel(@RequestBody VehicleModel vehicleModel) {
        vehicleModelService.saveVehicleModel(vehicleModel);
        return new ResponseEntity<>("New model is added", HttpStatus.OK);
    }

    @PutMapping("/models/{id}")
    public ResponseEntity<Object> updateVehicleModel(@RequestBody VehicleModel vehicleModelBody, @PathVariable int id) {

        Optional<VehicleModel> vehicleModel = vehicleModelService.getVehicleModelById(id);

        if (!vehicleModel.isPresent()) {
            return new ResponseEntity<>("The model is not found", HttpStatus.NOT_FOUND);
        }

        vehicleModelBody.setId(id);
        vehicleModelService.saveVehicleModel(vehicleModelBody);

        return new ResponseEntity<>("The model has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/models/{id}")
    public ResponseEntity<String> deleteVehicleModel(@PathVariable int id) {
        Optional<VehicleModel> vehicleModel = vehicleModelService.getVehicleModelById(id);
        if (!vehicleModel.isPresent()) {
            return new ResponseEntity<>("The model is not found", HttpStatus.NOT_FOUND);
        }
        vehicleModelService.deleteVehicleModel(vehicleModel.get());
        return new ResponseEntity<>("The model has been deleted", HttpStatus.OK);
    }
}