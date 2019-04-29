package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehiclePhoto;
import com.rectasolutions.moving.vehicles.exceptions.FailToUploadException;
import com.rectasolutions.moving.vehicles.exceptions.FileIsEmptyException;
import com.rectasolutions.moving.vehicles.exceptions.WrongTypeException;
import com.rectasolutions.moving.vehicles.repositories.VehiclePhotoRepository;
import com.rectasolutions.moving.vehicles.utils.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VehiclePhotoService {
    private VehiclePhotoRepository vehiclePhotoRepository;
    private VehicleService vehicleService;

    @Autowired
    public VehiclePhotoService(VehiclePhotoRepository vehiclePhotoRepository, VehicleService vehicleService){
        this.vehiclePhotoRepository = vehiclePhotoRepository;
        this.vehicleService = vehicleService;
    }

    public Optional<VehiclePhoto> getVehiclePhotoById(int id){
        return vehiclePhotoRepository.findById(id);
    }

    public List<VehiclePhoto> getAllVehiclePhotos(){
        return vehiclePhotoRepository.findAll();
    }

    public List<VehiclePhoto> getVehiclePhotosByVehicleId(int vehicleId){
        return vehiclePhotoRepository.findByVehicle(vehicleService.getVehicleById(vehicleId).orElse(null));
    }

    public VehiclePhoto saveVehiclePhoto(MultipartFile[] files, int vehicleId) throws FailToUploadException {
        String fileName = null;
        int countOfFiles = files.length;
        VehiclePhoto vehiclePhoto = new VehiclePhoto();
        try{
            if (countOfFiles == 0){
                throw new FileIsEmptyException();
            }
            for (int i = 0; i < countOfFiles; i++) {
                fileName = files[i].getOriginalFilename();
                byte[] bytes = files[i].getBytes();
                String contentType = files[i].getContentType();
                if (!checkPhotoType(contentType)) {
                    throw new WrongTypeException();
                }
                String rootPath = Assistant.getImagesStorePath();
                File dir = new File(rootPath);
                if (!dir.exists())
                    dir.mkdirs();
                String path = rootPath + fileName;

                try(BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(path)))) {
                    buffStream.write(bytes);
                }
                vehiclePhoto.setPhotoPath(path);
                vehiclePhoto.setVehicle(vehicleService.getVehicleById(vehicleId).orElse(null));
                vehiclePhotoRepository.save(vehiclePhoto);
            }
        }
        catch (Exception e) {
            throw new FailToUploadException(fileName + ": " + e.getMessage());
        }
        return vehiclePhoto;
    }

    private boolean checkPhotoType(String contentType) {
        final List<String> contentTypeList = Arrays.asList("image/gif", "image/jpeg", "image/pjpeg", "image/png", "image/svg+xml", "image/tiff", "image/vnd.microsoft.icon", "image/vnd.wap.wbmp", "image/webp");
        return contentTypeList.contains(contentType);
    }

    public ResponseEntity<String> deleteVehiclePhoto(VehiclePhoto vehiclePhoto) {
        Path path = Paths.get(vehiclePhoto.getPhotoPath());
        try {
            Files.delete(path);
            vehiclePhotoRepository.delete(vehiclePhoto);
            return new ResponseEntity<>("The photo has been deleted", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
