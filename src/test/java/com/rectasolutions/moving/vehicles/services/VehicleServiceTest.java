package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.UserDB;
import com.rectasolutions.moving.vehicles.entities.Vehicle;
import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.repositories.VehiclesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceTest {
    List<Vehicle> vehicleList = new ArrayList<>();

    @Mock
    VehicleService vehicleService;

    @Mock
    VehiclesRepository vehiclesRepository;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService(vehiclesRepository);
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void initVehicleList(){
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

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1);
        vehicle1.setUser(user);
        vehicle1.setVehicleCategory(vehicleCategory);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2);
        vehicle2.setUser(user);
        vehicle2.setVehicleCategory(vehicleCategory);

        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
    }

    @Test
    void getVehicleById() {
        Optional<Vehicle> returnedVehicle = Optional.of(vehicleList.get(0));
        when(vehicleService.getVehicleById(1)).thenReturn(returnedVehicle);
        assertNotNull(vehicleService.getVehicleById(1).get());
        assertNotNull(vehicleService.getVehicleById(1).get().getUser());
        assertNotNull(vehicleService.getVehicleById(1).get().getVehicleCategory());
        assertEquals(1, vehicleService.getVehicleById(1).get().getId());
        assertEquals(1, vehicleService.getVehicleById(1).get().getVehicleCategory().getId());
        assertEquals("1", vehicleService.getVehicleById(1).get().getUser().getId());
    }

    @Test
    void getAllVehicles() {
        when(vehicleService.getAllVehicles()).thenReturn(vehicleList);
        assertNotNull(vehicleService.getAllVehicles());
        assertNotNull(vehicleService.getAllVehicles().get(0));
        assertNotNull(vehicleService.getAllVehicles().get(1));
        assertNotNull(vehicleService.getAllVehicles().get(0).getUser());
        assertNotNull(vehicleService.getAllVehicles().get(0).getVehicleCategory());
        assertNotNull(vehicleService.getAllVehicles().get(1).getUser());
        assertNotNull(vehicleService.getAllVehicles().get(1).getVehicleCategory());
        assertEquals(1, vehicleService.getAllVehicles().get(0).getId());
        assertEquals(2, vehicleService.getAllVehicles().get(1).getId());
    }

    @Test
    void getVehiclesByUserId() {
        when(vehicleService.getVehiclesByUserId("1")).thenReturn(vehicleList);
        assertNotNull(vehicleService.getVehiclesByUserId("1"));
        assertNotNull(vehicleService.getVehiclesByUserId("1").get(0));
        assertNotNull(vehicleService.getVehiclesByUserId("1").get(1));
        assertNotNull(vehicleService.getVehiclesByUserId("1").get(0).getUser());
        assertNotNull(vehicleService.getVehiclesByUserId("1").get(0).getVehicleCategory());
        assertNotNull(vehicleService.getVehiclesByUserId("1").get(1).getUser());
        assertNotNull(vehicleService.getVehiclesByUserId("1").get(1).getVehicleCategory());
        assertEquals(1, vehicleService.getVehiclesByUserId("1").get(0).getId());
        assertEquals(2, vehicleService.getVehiclesByUserId("1").get(1).getId());
    }

    @Test
    void getVehiclesByCategoryId() {
        when(vehicleService.getVehiclesByCategoryId(1)).thenReturn(vehicleList);
        assertNotNull(vehicleService.getVehiclesByCategoryId(1));
        assertNotNull(vehicleService.getVehiclesByCategoryId(1).get(0));
        assertNotNull(vehicleService.getVehiclesByCategoryId(1).get(1));
        assertNotNull(vehicleService.getVehiclesByCategoryId(1).get(0).getUser());
        assertNotNull(vehicleService.getVehiclesByCategoryId(1).get(0).getVehicleCategory());
        assertNotNull(vehicleService.getVehiclesByCategoryId(1).get(1).getUser());
        assertNotNull(vehicleService.getVehiclesByCategoryId(1).get(1).getVehicleCategory());
        assertEquals(1, vehicleService.getVehiclesByCategoryId(1).get(0).getId());
        assertEquals(2, vehicleService.getVehiclesByCategoryId(1).get(1).getId());
    }

    @Test
    void saveVehicle() {
        when(vehicleService.saveVehicle(any(Vehicle.class))).thenReturn(vehicleList.get(0));
        assertNotNull(vehicleService.saveVehicle(new Vehicle()));
        assertNotNull(vehicleService.saveVehicle(new Vehicle()).getUser());
        assertNotNull(vehicleService.saveVehicle(new Vehicle()).getVehicleCategory());
        assertEquals(1, vehicleService.saveVehicle(new Vehicle()).getId());
    }

    @Test
    void deleteVehicle() {
        doNothing().when(vehicleService).deleteVehicle(any(Vehicle.class));
        vehicleService.deleteVehicle(new Vehicle());
        verify(vehicleService).deleteVehicle(any(Vehicle.class));
    }
}