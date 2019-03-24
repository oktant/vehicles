package com.rectasolutions.moving.vehicles.controllers;

import com.rectasolutions.moving.vehicles.entities.*;
import com.rectasolutions.moving.vehicles.repositories.*;
import com.rectasolutions.moving.vehicles.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {


    @Autowired
    private VehicleAttributeService vehicleAttributeService;

    @Autowired
    private VehicleCategoryService vehicleCategoryService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private VehiclePhotoService vehiclePhotoService;

    // VEHICLE CATEGORY

    @GetMapping("/categories")
    public ResponseEntity<List<VehicleCategory>> getVehicleCategories() {
        return new ResponseEntity<>(vehicleCategoryService.getAllVehicleCategories(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<VehicleCategory> getVehicleCategoryById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleCategoryService.getVehicleCategoryById(id).get(), HttpStatus.OK);
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




    // VEHICLE MAKES

    @GetMapping("/makes")
    public ResponseEntity<List<VehicleMake>> getVehicleMakes() {
        return new ResponseEntity<>(vehicleMakeService.getAllVehicleMakes(), HttpStatus.OK);
    }

    @GetMapping("/makes/{id}")
    public ResponseEntity<VehicleMake> getVehicleMakeById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleMakeService.getVehicleMakeById(id).get(), HttpStatus.OK);
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


    // VEHICLE MODELS

    @GetMapping("/models")
    public ResponseEntity<List<VehicleModel>> getVehicleModels() {
        return new ResponseEntity<>(vehicleModelService.getAllVehicleModels(), HttpStatus.OK);
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<VehicleModel> getVehicleModelById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleModelService.getVehicleModelById(id).get(), HttpStatus.OK);
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


    // ATTRIBUTES

    @GetMapping("/attributes")
    public ResponseEntity<List<Attribute>> getAllAttributes() {
        return new ResponseEntity<>(attributeService.getAllAttributes(), HttpStatus.OK);
    }

    @GetMapping("/attributes/{id}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable("id") int id) {
        return new ResponseEntity<>(attributeService.getAttributeById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/attributes")
    public ResponseEntity<String> saveAttribute(@RequestBody Attribute attribute) {
        attributeService.saveAttribute(attribute);
        return new ResponseEntity<>("New attribute is added", HttpStatus.OK);
    }

    @PutMapping("/attributes/{id}")
    public ResponseEntity<Object> updateAttribute(@RequestBody Attribute attributeBody, @PathVariable int id) {

        Optional<Attribute> attribute = attributeService.getAttributeById(id);

        if (!attribute.isPresent()) {
            return new ResponseEntity<>("The attribute is not found", HttpStatus.NOT_FOUND);
        }

        attributeBody.setId(id);
        attributeService.saveAttribute(attributeBody);

        return new ResponseEntity<>("The attribute has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/attributes/{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable int id) {
        Optional<Attribute> attribute = attributeService.getAttributeById(id);
        if (!attribute.isPresent()) {
            return new ResponseEntity<>("The attribute is not found", HttpStatus.NOT_FOUND);
        }
        attributeService.deleteAttribute(attribute.get());
        return new ResponseEntity<>("The attribute has been deleted", HttpStatus.OK);
    }


    // VEHICLE

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id).get(), HttpStatus.OK);
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




    // Vehicle ATTRIBUTES

    @GetMapping("/vehicle_attributes")
    public ResponseEntity<List<VehicleAttribute>> getAllVehicleAttributes() {
        return new ResponseEntity<>(vehicleAttributeService.getAllVehicleAttributes(), HttpStatus.OK);
    }

    @GetMapping("/vehicle_attributes/{id}")
    public ResponseEntity<VehicleAttribute> getVehicleAttributeById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehicleAttributeService.getVehicleAttributeById(id).get(), HttpStatus.OK);
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


    // VEHICLE PHOTO

    @GetMapping("/photos")
    public ResponseEntity<List<VehiclePhoto>> getAllVehiclePhotos() {
        return new ResponseEntity<>(vehiclePhotoService.getAllVehiclePhotos(), HttpStatus.OK);
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<VehiclePhoto> getVehiclePhotoById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehiclePhotoService.getVehiclePhotoById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/vehicles/photos/{vehicleId}")
    public ResponseEntity<List<VehiclePhoto>> getVehiclePhotosByVehicleId(@PathVariable("vehicleId") int vehicleId) {
        return new ResponseEntity<>(vehiclePhotoService.getVehiclePhotosByVehicleId(vehicleId), HttpStatus.OK);
    }

    @PostMapping("/photos")
    public ResponseEntity<String> saveVehiclePhoto(@RequestParam(value = "files") MultipartFile[] files, @RequestParam(value = "vehicleId") int vehicleId) {
        return vehiclePhotoService.saveVehiclePhoto(files, vehicleId);
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<String> deleteVehiclePhoto(@PathVariable int id) {
        Optional<VehiclePhoto> vehiclePhoto = vehiclePhotoService.getVehiclePhotoById(id);
        if (!vehiclePhoto.isPresent()) {
            return new ResponseEntity<>("The photo is not found", HttpStatus.NOT_FOUND);
        }
        return vehiclePhotoService.deleteVehiclePhoto(vehiclePhoto.get());
    }


}
