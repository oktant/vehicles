package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.repositories.VehicleCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleCategoryServiceTest {
    List<VehicleCategory> vehicleCategoryList = new ArrayList<>();

    @Mock
    VehicleCategoryRepository vehicleCategoryRepository;

    @Mock
    VehicleCategoryService vehicleCategoryService;

    @BeforeEach
    void setUp() {
        vehicleCategoryService = new VehicleCategoryService(vehicleCategoryRepository);
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void initVehicleCategories(){
        VehicleCategory vehicleCategory1 = new VehicleCategory();
        vehicleCategory1.setId(1);
        vehicleCategory1.setCallDistance(10);
        vehicleCategory1.setDeliveryDistance(20);
        vehicleCategory1.setInstantDelOption(true);
        vehicleCategory1.setNextTime(2);
        vehicleCategory1.setPayload(30);
        vehicleCategory1.setType("1");
        vehicleCategory1.setVolume(30);

        VehicleCategory vehicleCategory2 = new VehicleCategory();
        vehicleCategory2.setId(2);
        vehicleCategory2.setCallDistance(15);
        vehicleCategory2.setDeliveryDistance(25);
        vehicleCategory2.setInstantDelOption(true);
        vehicleCategory2.setNextTime(2);
        vehicleCategory2.setPayload(30);
        vehicleCategory2.setType("1");
        vehicleCategory2.setVolume(30);
        vehicleCategory2.setParentId(vehicleCategory1);

        vehicleCategoryList.add(vehicleCategory1);
        vehicleCategoryList.add(vehicleCategory2);
    }

    @Test
    void getVehicleCategoryById() {
        Optional<VehicleCategory> exampleVehicleCategory = Optional.of(vehicleCategoryList.get(0));
        when(vehicleCategoryService.getVehicleCategoryById(1)).thenReturn(exampleVehicleCategory);
        assertNotNull(vehicleCategoryService.getVehicleCategoryById(1));
        assertNotNull(vehicleCategoryService.getVehicleCategoryById(1).get());
        assertEquals(1, vehicleCategoryService.getVehicleCategoryById(1).get().getId());
        assertEquals(10, vehicleCategoryService.getVehicleCategoryById(1).get().getCallDistance());
        assertEquals(20, vehicleCategoryService.getVehicleCategoryById(1).get().getDeliveryDistance());
        assertEquals(2, vehicleCategoryService.getVehicleCategoryById(1).get().getNextTime());
        assertEquals(30, vehicleCategoryService.getVehicleCategoryById(1).get().getPayload());
        assertEquals("1", vehicleCategoryService.getVehicleCategoryById(1).get().getType());
        assertEquals(30, vehicleCategoryService.getVehicleCategoryById(1).get().getVolume());
        assertNull(vehicleCategoryService.getVehicleCategoryById(1).get().getParentId());
    }

    @Test
    void getAllVehicleCategories() {
        when(vehicleCategoryService.getAllVehicleCategories()).thenReturn(vehicleCategoryList);
        assertNotNull(vehicleCategoryService.getAllVehicleCategories());
        assertNotNull(vehicleCategoryService.getAllVehicleCategories().get(0));
        assertNotNull(vehicleCategoryService.getAllVehicleCategories().get(1));
        assertNotNull(vehicleCategoryService.getAllVehicleCategories().get(1).getParentId());
        assertNull(vehicleCategoryService.getAllVehicleCategories().get(0).getParentId());
        assertEquals(1, vehicleCategoryService.getAllVehicleCategories().get(0).getId());
        assertEquals(2, vehicleCategoryService.getAllVehicleCategories().get(1).getId());
        assertEquals(1, vehicleCategoryService.getAllVehicleCategories().get(1).getParentId().getId());
    }

    @Test
    void saveVehicleCategory() {
        VehicleCategory returnedVehicleCategory = vehicleCategoryList.get(0);
        when(vehicleCategoryService.saveVehicleCategory(any(VehicleCategory.class))).thenReturn(returnedVehicleCategory);
        assertNotNull(vehicleCategoryService.saveVehicleCategory(new VehicleCategory()));
    }

    @Test
    void deleteVehicleCategory() {
        doNothing().when(vehicleCategoryService).deleteVehicleCategory(any(VehicleCategory.class));
        vehicleCategoryService.deleteVehicleCategory(new VehicleCategory());
        verify(vehicleCategoryService).deleteVehicleCategory(any(VehicleCategory.class));
    }

    @Test
    void getVehicleCategoryByDistance() {
        when(vehicleCategoryService.getVehicleCategoryByDistance(20)).thenReturn(vehicleCategoryList.get(0));
        assertNotNull(vehicleCategoryService.getVehicleCategoryByDistance(20));
        assertNull(vehicleCategoryService.getVehicleCategoryByDistance(20).getParentId());
        assertEquals(1, vehicleCategoryService.getVehicleCategoryByDistance(20).getId());
        assertEquals(30, vehicleCategoryService.getVehicleCategoryByDistance(20).getVolume());
        assertEquals("1", vehicleCategoryService.getVehicleCategoryByDistance(20).getType());
        assertEquals(30, vehicleCategoryService.getVehicleCategoryByDistance(20).getPayload());
        assertEquals(2, vehicleCategoryService.getVehicleCategoryByDistance(20).getNextTime());
        assertEquals(20, vehicleCategoryService.getVehicleCategoryByDistance(20).getDeliveryDistance());
        assertEquals(10, vehicleCategoryService.getVehicleCategoryByDistance(20).getCallDistance());
    }

    @Test
    void getVehicleCategoryByDistanceAndPayload() {
        when(vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30)).thenReturn(vehicleCategoryList.get(0));
        assertNotNull(vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30));
        assertNull(vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getParentId());
        assertEquals(1, vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getId());
        assertEquals(30, vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getVolume());
        assertEquals("1", vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getType());
        assertEquals(30, vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getPayload());
        assertEquals(2, vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getNextTime());
        assertEquals(20, vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getDeliveryDistance());
        assertEquals(10, vehicleCategoryService.getVehicleCategoryByDistanceAndPayload(20,30).getCallDistance());
    }
}