package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.UserDB;
import com.rectasolutions.moving.vehicles.entities.Vehicle;
import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.entities.VehiclePhoto;
import com.rectasolutions.moving.vehicles.exceptions.FailToUploadException;
import com.rectasolutions.moving.vehicles.repositories.VehiclePhotoRepository;
import com.rectasolutions.moving.vehicles.utils.Assistant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehiclePhotoServiceTest {
    List<VehiclePhoto> vehiclePhotoList = new ArrayList<>();

    @Mock
    VehiclePhotoService vehiclePhotoService;

    @Mock
    VehiclePhotoRepository vehiclePhotoRepository;

    @Mock
    VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehiclePhotoService = new VehiclePhotoService(vehiclePhotoRepository, vehicleService);
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void initVehiclePhotoList(){
        UserDB user = new UserDB();
        user.setId("1");
        user.setStatus("busy");
        user.setLatitude(123);
        user.setLongitude(456);

        VehicleCategory vehicleCategory = new VehicleCategory();
        vehicleCategory.setId(1);
        vehicleCategory.setCallDistance(10);
        vehicleCategory.setDeliveryDistance(20);
        vehicleCategory.setInstantDelOption(true);
        vehicleCategory.setNextTime(2);
        vehicleCategory.setPayload(30);
        vehicleCategory.setType("1");
        vehicleCategory.setVolume(30);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setUser(user);
        vehicle.setVehicleCategory(vehicleCategory);

        VehiclePhoto vehiclePhoto1 = new VehiclePhoto();
        vehiclePhoto1.setId(1);
        vehiclePhoto1.setPhotoPath("path");
        vehiclePhoto1.setVehicle(vehicle);

        VehiclePhoto vehiclePhoto2 = new VehiclePhoto();
        vehiclePhoto2.setId(2);
        vehiclePhoto2.setPhotoPath("path2");
        vehiclePhoto2.setVehicle(vehicle);

        vehiclePhotoList.add(vehiclePhoto1);
        vehiclePhotoList.add(vehiclePhoto2);
    }

    @Test
    void getVehiclePhotoById() {
        Optional<VehiclePhoto> returnedVehiclePhoto = Optional.of(vehiclePhotoList.get(0));
        when(vehiclePhotoService.getVehiclePhotoById(1)).thenReturn(returnedVehiclePhoto);
        assertNotNull(vehiclePhotoService.getVehiclePhotoById(1));
        assertNotNull(vehiclePhotoService.getVehiclePhotoById(1).get());
        assertNotNull(vehiclePhotoService.getVehiclePhotoById(1).get().getVehicle());
        assertNotNull(vehiclePhotoService.getVehiclePhotoById(1).get().getVehicle().getVehicleCategory());
        assertNotNull(vehiclePhotoService.getVehiclePhotoById(1).get().getVehicle().getUser());
        assertEquals(1, vehiclePhotoService.getVehiclePhotoById(1).get().getId());
        assertEquals(1, vehiclePhotoService.getVehiclePhotoById(1).get().getVehicle().getId());
        assertEquals("1", vehiclePhotoService.getVehiclePhotoById(1).get().getVehicle().getUser().getId());
        assertEquals(1, vehiclePhotoService.getVehiclePhotoById(1).get().getVehicle().getVehicleCategory().getId());
        assertEquals("path", vehiclePhotoService.getVehiclePhotoById(1).get().getPhotoPath());
    }

    @Test
    void getAllVehiclePhotos() {
        when(vehiclePhotoService.getAllVehiclePhotos()).thenReturn(vehiclePhotoList);
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(0));
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(1));
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(0).getVehicle());
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(0).getVehicle().getUser());
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(0).getVehicle().getVehicleCategory());
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(1).getVehicle());
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(1).getVehicle().getUser());
        assertNotNull(vehiclePhotoService.getAllVehiclePhotos().get(1).getVehicle().getVehicleCategory());
        assertEquals(1, vehiclePhotoService.getAllVehiclePhotos().get(0).getId());
        assertEquals(2, vehiclePhotoService.getAllVehiclePhotos().get(1).getId());
        assertEquals(1, vehiclePhotoService.getAllVehiclePhotos().get(1).getVehicle().getId());
        assertEquals(1, vehiclePhotoService.getAllVehiclePhotos().get(1).getVehicle().getVehicleCategory().getId());
        assertEquals("1", vehiclePhotoService.getAllVehiclePhotos().get(1).getVehicle().getUser().getId());
        assertEquals("path", vehiclePhotoService.getAllVehiclePhotos().get(0).getPhotoPath());
        assertEquals("path2", vehiclePhotoService.getAllVehiclePhotos().get(1).getPhotoPath());
    }

    @Test
    void getVehiclePhotosByVehicleId() {
        when(vehiclePhotoService.getVehiclePhotosByVehicleId(1)).thenReturn(vehiclePhotoList);
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1));
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(0));
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(1));
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(0).getVehicle());
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(0).getVehicle().getUser());
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(0).getVehicle().getVehicleCategory());
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(1).getVehicle().getUser());
        assertNotNull(vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(1).getVehicle().getVehicleCategory());
        assertEquals(1, vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(0).getId());
        assertEquals(2, vehiclePhotoService.getVehiclePhotosByVehicleId(1).get(1).getId());
    }

    @Test
    void saveVehiclePhoto() throws FailToUploadException {
        Path path = Paths.get("/test/resources/myPhoto.jpg");
        String name = "myPhoto.jpg";
        String originalFileName = "myPhoto.jpg";
        String contentType = "image/jpg";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile multipartFile = new MockMultipartFile(name, originalFileName, contentType, content);
        MultipartFile[] multipartFiles = new MultipartFile[1];
        multipartFiles[0] = multipartFile;
        VehiclePhoto returnedVehiclePhoto = vehiclePhotoList.get(0);
        returnedVehiclePhoto.setPhotoPath(Assistant.getImagesStorePath());
        when(vehiclePhotoService.saveVehiclePhoto(multipartFiles, 1)).thenReturn(returnedVehiclePhoto);
        assertNotNull(vehiclePhotoService.saveVehiclePhoto(multipartFiles,1));
        assertEquals(1, vehiclePhotoService.saveVehiclePhoto(multipartFiles,1).getId());
    }

    @Test
    void deleteVehiclePhoto() {
        when(vehiclePhotoService.deleteVehiclePhoto(any(VehiclePhoto.class))).thenReturn(new ResponseEntity<>("The photo has been deleted", HttpStatus.OK));
        assertNotNull(vehiclePhotoService.deleteVehiclePhoto(new VehiclePhoto()));
        assertEquals(HttpStatus.OK, vehiclePhotoService.deleteVehiclePhoto(new VehiclePhoto()).getStatusCode());
    }
}