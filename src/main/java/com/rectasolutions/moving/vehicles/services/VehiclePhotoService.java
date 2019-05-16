package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.beans.ImageInfo;
import com.rectasolutions.moving.vehicles.beans.PostedImage;
import com.rectasolutions.moving.vehicles.entities.VehiclePhoto;
import com.rectasolutions.moving.vehicles.exceptions.FailToUploadException;
import com.rectasolutions.moving.vehicles.repositories.VehiclePhotoRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class VehiclePhotoService {
  private VehiclePhotoRepository vehiclePhotoRepository;
  private VehicleService vehicleService;

  @Value("D:/VehiclePhotos/")
  private String imagesStorePath;

  @Value("/")
  private String folderSeperator;

  @Autowired
  public VehiclePhotoService(
      VehiclePhotoRepository vehiclePhotoRepository, VehicleService vehicleService) {
    this.vehiclePhotoRepository = vehiclePhotoRepository;
    this.vehicleService = vehicleService;
  }

  public Optional<VehiclePhoto> getVehiclePhotoById(int id) {
    return vehiclePhotoRepository.findById(id);
  }

  public List<VehiclePhoto> getAllVehiclePhotos() {
    return vehiclePhotoRepository.findAll();
  }

  public List<VehiclePhoto> getVehiclePhotosByVehicleId(int vehicleId) {
    return vehiclePhotoRepository.findByVehicle(
        vehicleService.getVehicleById(vehicleId).orElse(null));
  }

  public VehiclePhoto saveVehiclePhoto(PostedImage postedImage) throws FailToUploadException {
    String fileName = null;
    int vehicleId = postedImage.getVehicleId();
    VehiclePhoto vehiclePhoto = new VehiclePhoto();
    try {
      for (ImageInfo imageInfo : postedImage.getImageInfoList()) {
        fileName = imageInfo.getFileName();
        byte[] bytes = decodedByteArray(imageInfo.getEncodedImage());
        File dir = new File(imagesStorePath);
        if (!dir.exists()) dir.mkdirs();
        String path = imagesStorePath + folderSeperator + fileName;

        try (BufferedOutputStream buffStream =
            new BufferedOutputStream(new FileOutputStream(new File(path)))) {
          buffStream.write(bytes);
        }
        vehiclePhoto.setPhotoPath(path);
        vehiclePhoto.setVehicle(vehicleService.getVehicleById(vehicleId).orElse(null));
        vehiclePhotoRepository.save(vehiclePhoto);
      }
    } catch (Exception e) {
      throw new FailToUploadException(fileName + ": " + e.getMessage());
    }
    return vehiclePhoto;
  }

  public void deleteVehiclePhoto(VehiclePhoto vehiclePhoto) throws IOException {
    Path path = Paths.get(vehiclePhoto.getPhotoPath());
    vehiclePhotoRepository.delete(vehiclePhoto);
    Files.delete(path);
  }

  private byte[] decodedByteArray(String imageString) {
    return Base64.decodeBase64(imageString.getBytes());
  }
}
