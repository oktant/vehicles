package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.entities.VehicleMake;
import com.rectasolutions.moving.vehicles.repositories.VehicleMakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleMakeServiceTest {
    List<VehicleMake> vehicleMakeList = new ArrayList<>();
    
    @Mock
    VehicleMakeRepository vehicleMakeRepository;
    
    @Mock
    VehicleMakeService vehicleMakeService;
    
    @BeforeEach
    void setUp() {
        vehicleMakeService = new VehicleMakeService(vehicleMakeRepository);
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeEach
    public void initVehicleMakeList(){
        VehicleCategory vehicleCategory = new VehicleCategory();
        vehicleCategory.setId(1);
        vehicleCategory.setCallDistance(10);
        vehicleCategory.setDeliveryDistance(20);
        vehicleCategory.setInstantDelOption(true);
        vehicleCategory.setNextTime(2);
        vehicleCategory.setPayload(30);
        vehicleCategory.setType("1");
        vehicleCategory.setVolume(30);

        VehicleMake vehicleMake1 = new VehicleMake();
        vehicleMake1.setId(1);
        vehicleMake1.setCompanyName("BMW");
        vehicleMake1.setVehicleCategory(vehicleCategory);

        VehicleMake vehicleMake2 = new VehicleMake();
        vehicleMake2.setId(2);
        vehicleMake2.setCompanyName("Kia");
        vehicleMake2.setVehicleCategory(vehicleCategory);

        vehicleMakeList.add(vehicleMake1);
        vehicleMakeList.add(vehicleMake2);
    }

    @Test
    void getVehicleMakeById() {
        Optional<VehicleMake> returnedVehicleMake = Optional.of(vehicleMakeList.get(0));
        when(vehicleMakeService.getVehicleMakeById(1)).thenReturn(returnedVehicleMake);
        assertNotNull(vehicleMakeService.getVehicleMakeById(1));
        assertNotNull(vehicleMakeService.getVehicleMakeById(1).get());
        assertNotNull(vehicleMakeService.getVehicleMakeById(1).get().getVehicleCategory());
        assertEquals(1, vehicleMakeService.getVehicleMakeById(1).get().getId());
        assertEquals("BMW", vehicleMakeService.getVehicleMakeById(1).get().getCompanyName());
        assertEquals(1, vehicleMakeService.getVehicleMakeById(1).get().getVehicleCategory().getId());
    }

    @Test
    void getAllVehicleMakes() {
        when(vehicleMakeService.getAllVehicleMakes()).thenReturn(vehicleMakeList);
        assertNotNull(vehicleMakeService.getAllVehicleMakes());
        assertNotNull(vehicleMakeService.getAllVehicleMakes().get(0));
        assertNotNull(vehicleMakeService.getAllVehicleMakes().get(1));
        assertNotNull(vehicleMakeService.getAllVehicleMakes().get(0).getVehicleCategory());
        assertNotNull(vehicleMakeService.getAllVehicleMakes().get(1).getVehicleCategory());
        assertEquals(1, vehicleMakeService.getAllVehicleMakes().get(0).getId());
        assertEquals(2, vehicleMakeService.getAllVehicleMakes().get(1).getId());
    }

    @Test
    void getVehicleMakesByCategoryId() {
        when(vehicleMakeService.getVehicleMakesByCategoryId(1)).thenReturn(vehicleMakeList);
        assertNotNull(vehicleMakeService.getVehicleMakesByCategoryId(1));
        assertNotNull(vehicleMakeService.getVehicleMakesByCategoryId(1).get(0));
        assertNotNull(vehicleMakeService.getVehicleMakesByCategoryId(1).get(1));
        assertEquals(1, vehicleMakeService.getVehicleMakesByCategoryId(1).get(0).getId());
        assertEquals(2, vehicleMakeService.getVehicleMakesByCategoryId(1).get(1).getId());
    }

    @Test
    void saveVehicleMake() {
        when(vehicleMakeService.saveVehicleMake(any(VehicleMake.class))).thenReturn(vehicleMakeList.get(0));
        assertNotNull(vehicleMakeService.saveVehicleMake(new VehicleMake()));
    }

    @Test
    void deleteVehicleMake() {
        doNothing().when(vehicleMakeService).deleteVehicleMake(any(VehicleMake.class));
        vehicleMakeService.deleteVehicleMake(new VehicleMake());
        verify(vehicleMakeService).deleteVehicleMake(any(VehicleMake.class));
    }
}