package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehiclePhoto;
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
        int countOfFiles = files.length;
        if (countOfFiles == 0){
            return new ResponseEntity<>("Unable to upload. File is empty.", HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < countOfFiles; i++) {
            try {
                fileName = files[i].getOriginalFilename();
                byte[] bytes = files[i].getBytes();
                String contentType = files[i].getContentType();
                if (!checkPhotoType(contentType)) {
                    return new ResponseEntity<>("Wrong type for picture", HttpStatus.BAD_REQUEST);
                }
                String rootPath = Assistant.getImagesStorePath();
                File dir = new File(rootPath);
                if (!dir.exists())
                    dir.mkdirs();
                String path = rootPath + fileName;

                try(BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(path)))) {
                    buffStream.write(bytes);
                }
                VehiclePhoto vehiclePhoto = new VehiclePhoto();
                vehiclePhoto.setPhotoPath(path);
                vehiclePhoto.setVehicle(vehicleService.getVehicleById(vehicleId).orElse(null));
                vehiclePhotoRepository.save(vehiclePhoto);
            } catch (Exception e) {
                return new ResponseEntity<>("You failed to upload " + fileName + ": " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Number of added photos: " + countOfFiles, HttpStatus.OK);
    }

    private boolean checkPhotoType(String contentType) {
        final List<String> contentTypeList = Arrays.asList("image/gif", "image/jpeg", "image/pjpeg", "image/png", "image/svg+xml", "image/tiff", "image/vnd.microsoft.icon", "image/vnd.wap.wbmp", "image/webp");
        if (contentTypeList.contains(contentType)) {
            return true;
        }
        return false;
    }

    public ResponseEntity<String> deleteVehiclePhoto(VehiclePhoto vehiclePhoto) {
        File file = new File(vehiclePhoto.getPhotoPath());
        if (file.delete()){
            vehiclePhotoRepository.delete(vehiclePhoto);
            return new ResponseEntity<>("The photo has been deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete operation is failed", HttpStatus.BAD_REQUEST);
    }
}
