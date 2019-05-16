package com.rectasolutions.moving.vehicles.controllers;

import com.rectasolutions.moving.vehicles.beans.ImageInfo;
import com.rectasolutions.moving.vehicles.beans.PostedImage;
import com.rectasolutions.moving.vehicles.entities.*;
import com.rectasolutions.moving.vehicles.exceptions.FileIsEmptyException;
import com.rectasolutions.moving.vehicles.exceptions.WrongPhotoTypeException;
import com.rectasolutions.moving.vehicles.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehiclePhotoController {

    @Autowired
    private VehiclePhotoService vehiclePhotoService;

    // VEHICLE PHOTO

    @GetMapping("/photos")
    public ResponseEntity<List<VehiclePhoto>> getAllVehiclePhotos() {
        return new ResponseEntity<>(vehiclePhotoService.getAllVehiclePhotos(), HttpStatus.OK);
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<VehiclePhoto> getVehiclePhotoById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vehiclePhotoService.getVehiclePhotoById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/vehicles/photos/{vehicleId}")
    public ResponseEntity<List<VehiclePhoto>> getVehiclePhotosByVehicleId(@PathVariable("vehicleId") int vehicleId) {
        return new ResponseEntity<>(vehiclePhotoService.getVehiclePhotosByVehicleId(vehicleId), HttpStatus.OK);
    }

    @PostMapping(value = "/photos")
    public ResponseEntity<String> saveVehiclePhoto(@RequestBody PostedImage postedImage) {
        try {
            validatePostedImage(postedImage);
            vehiclePhotoService.saveVehiclePhoto(postedImage);
            return new ResponseEntity<>("Photos have been added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<String> deleteVehiclePhoto(@PathVariable int id) {
        Optional<VehiclePhoto> vehiclePhoto = vehiclePhotoService.getVehiclePhotoById(id);
        if (!vehiclePhoto.isPresent()) {
            return new ResponseEntity<>("The photo is not found", HttpStatus.NOT_FOUND);
        }
        try {
            vehiclePhotoService.deleteVehiclePhoto(vehiclePhoto.get());
        } catch (IOException e) {
            return new ResponseEntity<>("The photo is not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("The photo has been deleted", HttpStatus.OK);
    }

    private void validatePostedImage(PostedImage postedImage) throws FileIsEmptyException, WrongPhotoTypeException {
        if (postedImage == null || postedImage.getImageInfoList().isEmpty()){
            throw new FileIsEmptyException();
        }
        for (ImageInfo imageInfo : postedImage.getImageInfoList()){
            if (!checkPhotoType(imageInfo.getContentType())) {
                throw new WrongPhotoTypeException();
            }
        }
    }

    private boolean checkPhotoType(String contentType) {
        final List<String> contentTypeList = Arrays.asList("image/gif", "image/jpeg", "image/pjpeg", "image/png", "image/svg+xml", "image/tiff", "image/vnd.microsoft.icon", "image/vnd.wap.wbmp", "image/webp");
        return contentTypeList.contains(contentType);
    }
}