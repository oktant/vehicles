package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehiclePhoto;
import com.rectasolutions.moving.vehicles.repositories.VehiclePhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VehiclePhotoService {
    @Autowired
    private VehiclePhotoRepository vehiclePhotoRepository;

    @Autowired
    private VehicleService vehicleService;

    public Optional<VehiclePhoto> getVehiclePhotoById(int id){
        return vehiclePhotoRepository.findById(id);
    }

    public List<VehiclePhoto> getAllVehiclePhotos(){
        return vehiclePhotoRepository.findAll();
    }

    public List<VehiclePhoto> getVehiclePhotosByVehicleId(int vehicleId){
        return vehiclePhotoRepository.findByVehicle(vehicleService.getVehicleById(vehicleId).orElse(null));
    }

    public ResponseEntity<String> saveVehiclePhoto(MultipartFile[] files, int vehicleId){
        String fileName = null;
        if (files != null && files.length > 0) {
            int k = 0;
            for (int i = 0; i < files.length; i++) {
                try {
                    k = k + 1;
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    String contentType = files[i].getContentType();
                    if (checkPhotoType(contentType)) {
                        String rootPath = "D:/VehiclePhotos";
                        File dir = new File(rootPath);
                        if (!dir.exists())
                            dir.mkdirs();
                        String path = "D:/VehiclePhotos/" + fileName;

                        try(BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(path)))) {
                            buffStream.write(bytes);
                        }
                        VehiclePhoto vehiclePhoto = new VehiclePhoto();
                        vehiclePhoto.setPhotoPath(path);
                        vehiclePhoto.setVehicle(vehicleService.getVehicleById(vehicleId).orElse(null));
                        vehiclePhotoRepository.save(vehiclePhoto);
                    } else {
                        return new ResponseEntity<>("Wrong type for picture", HttpStatus.BAD_REQUEST);
                    }

                } catch (Exception e) {
                    return new ResponseEntity<>("You failed to upload " + fileName + ": " + e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>("Number of added photos: " + k, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to upload. File is empty.", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean checkPhotoType(String contentType) {
        final List<String> contentTypeList = Arrays.asList("image/gif", "image/jpeg", "image/pjpeg", "image/png", "image/svg+xml", "image/tiff", "image/vnd.microsoft.icon", "image/vnd.wap.wbmp", "image/webp");
        if (contentTypeList.contains(contentType)) {
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<String> deleteVehiclePhoto(VehiclePhoto vehiclePhoto) {
        File file = new File(vehiclePhoto.getPhotoPath());
        if (file.delete()){
            vehiclePhotoRepository.delete(vehiclePhoto);
            return new ResponseEntity<>("The photo has been deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Delete operation is failed", HttpStatus.BAD_REQUEST);
        }
    }
}
