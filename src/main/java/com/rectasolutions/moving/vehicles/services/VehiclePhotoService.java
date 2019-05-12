package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.beans.ImageInfo;
import com.rectasolutions.moving.vehicles.beans.PostedImage;
import com.rectasolutions.moving.vehicles.entities.VehiclePhoto;
import com.rectasolutions.moving.vehicles.exceptions.FailToUploadException;
import com.rectasolutions.moving.vehicles.repositories.VehiclePhotoRepository;
import com.rectasolutions.moving.vehicles.utils.Assistant;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.*;
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

    public VehiclePhoto saveVehiclePhoto(PostedImage postedImage) throws FailToUploadException {
        String fileName = null;
        int vehicleId = postedImage.getVehicleId();
        VehiclePhoto vehiclePhoto = new VehiclePhoto();
        try{
            for (ImageInfo imageInfo : postedImage.getImageInfoList()) {
                fileName = imageInfo.getFileName();
                byte[] bytes = decodedByteArray(imageInfo.getEncodedImage());
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

    private boolean checkPhotoType(String contentType) {
        final List<String> contentTypeList = Arrays.asList("image/gif", "image/jpeg", "image/pjpeg", "image/png", "image/svg+xml", "image/tiff", "image/vnd.microsoft.icon", "image/vnd.wap.wbmp", "image/webp");
        return contentTypeList.contains(contentType);
    }

    private byte[] decodedByteArray(String imageString){
        return Base64.decodeBase64(imageString.getBytes());
    }
}
