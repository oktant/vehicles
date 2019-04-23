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
public class VehicleAttributeController {

    @Autowired
    private VehicleAttributeService vehicleAttributeService;

    // Vehicle ATTRIBUTES

    @GetMapping("/vehicle_attributes")
    public ResponseEntity<List<VehicleAttribute>> getAllVehicleAttributes() {
        return new ResponseEntity<>(vehicleAttributeService.getAllVehicleAttributes(), HttpStatus.OK);
    }

    @GetMapping("/vehicle_attributes/{id}")
    public ResponseEntity<VehicleAttribute> getVehicleAttributeById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleAttributeService.getVehicleAttributeById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/attributes/vehicle_attributes/{attributeId}")
    public ResponseEntity<List<VehicleAttribute>> getVehicleAttributeByAttributeId(@PathVariable("attributeId") int attributeId) {
        return new ResponseEntity<>(vehicleAttributeService.getVehicleAttributesByAttributeId(attributeId), HttpStatus.OK);
    }

    @GetMapping("/vehicles/vehicle_attributes/{vehicleId}")
    public ResponseEntity<List<VehicleAttribute>> getVehicleAttributeByVehicleId(@PathVariable("vehicleId") int vehicleId) {
        return new ResponseEntity<>(vehicleAttributeService.getVehicleAttributesByVehicleId(vehicleId), HttpStatus.OK);
    }

    @PostMapping("/vehicle_attributes")
    public ResponseEntity<String> saveVehicleAttribute(@RequestBody VehicleAttribute vehicleAttribute) {
        vehicleAttributeService.saveVehicleAttribute(vehicleAttribute);
        return new ResponseEntity<>("New vehicle attribute is added", HttpStatus.OK);
    }

    @PutMapping("/vehicle_attributes/{id}")
    public ResponseEntity<Object> updateVehicleAttribute(@RequestBody VehicleAttribute vehicleAttributeBody, @PathVariable int id) {

        Optional<VehicleAttribute> vehicleAttribute = vehicleAttributeService.getVehicleAttributeById(id);

        if (!vehicleAttribute.isPresent()) {
            return new ResponseEntity<>("The vehicle attribute is not found", HttpStatus.NOT_FOUND);
        }

        vehicleAttributeBody.setId(id);
        vehicleAttributeService.saveVehicleAttribute(vehicleAttributeBody);

        return new ResponseEntity<>("The vehicle attribute has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/vehicle_attributes/{id}")
    public ResponseEntity<String> deleteVehicleAttribute(@PathVariable int id) {
        Optional<VehicleAttribute> vehicleAttribute = vehicleAttributeService.getVehicleAttributeById(id);
        if (!vehicleAttribute.isPresent()) {
            return new ResponseEntity<>("The vehicle attribute is not found", HttpStatus.NOT_FOUND);
        }
        vehicleAttributeService.deleteVehicleAttribute(vehicleAttribute.get());
        return new ResponseEntity<>("The vehicle attribute has been deleted", HttpStatus.OK);
    }
}