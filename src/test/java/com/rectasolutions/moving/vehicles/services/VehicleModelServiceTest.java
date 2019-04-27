package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.entities.VehicleMake;
import com.rectasolutions.moving.vehicles.entities.VehicleModel;
import com.rectasolutions.moving.vehicles.repositories.VehicleModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleModelServiceTest {
    List<VehicleModel> vehicleModelList = new ArrayList<>();

    @Mock
    VehicleModelService vehicleModelService;

    @Mock
    VehicleModelRepository vehicleModelRepository;

    @BeforeEach
    void setUp() {
        vehicleModelService = new VehicleModelService(vehicleModelRepository);
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void initVehicleModel(){
        VehicleCategory vehicleCategory = new VehicleCategory();
        vehicleCategory.setId(1);
        vehicleCategory.setCallDistance(10);
        vehicleCategory.setDeliveryDistance(20);
        vehicleCategory.setInstantDelOption(true);
        vehicleCategory.setNextTime(2);
        vehicleCategory.setPayload(30);
        vehicleCategory.setType("1");
        vehicleCategory.setVolume(30);

        VehicleMake vehicleMake = new VehicleMake();
        vehicleMake.setId(1);
        vehicleMake.setCompanyName("BMW");
        vehicleMake.setVehicleCategory(vehicleCategory);

        VehicleModel vehicleModel1 = new VehicleModel();
        vehicleModel1.setId(1);
        vehicleModel1.setModelName("X3");
        vehicleModel1.setVehicleMake(vehicleMake);

        VehicleModel vehicleModel2 = new VehicleModel();
        vehicleModel2.setId(2);
        vehicleModel2.setModelName("X5");
        vehicleModel2.setVehicleMake(vehicleMake);

        vehicleModelList.add(vehicleModel1);
        vehicleModelList.add(vehicleModel2);
    }

    @Test
    void getVehicleModelById() {
        Optional<VehicleModel> returnedVehicleModel = Optional.of(vehicleModelList.get(0));
        when(vehicleModelService.getVehicleModelById(1)).thenReturn(returnedVehicleModel);
        assertNotNull(vehicleModelService.getVehicleModelById(1));
        assertNotNull(vehicleModelService.getVehicleModelById(1).get());
        assertNotNull(vehicleModelService.getVehicleModelById(1).get().getVehicleMake());
        assertNotNull(vehicleModelService.getVehicleModelById(1).get().getVehicleMake().getVehicleCategory());
        assertEquals(1, vehicleModelService.getVehicleModelById(1).get().getId());
        assertEquals("X3", vehicleModelService.getVehicleModelById(1).get().getModelName());
        assertEquals(1, vehicleModelService.getVehicleModelById(1).get().getVehicleMake().getId());
        assertEquals(1, vehicleModelService.getVehicleModelById(1).get().getVehicleMake().getVehicleCategory().getId());
    }

    @Test
    void getAllVehicleModels() {
        when(vehicleModelService.getAllVehicleModels()).thenReturn(vehicleModelList);
        assertNotNull(vehicleModelService.getAllVehicleModels());
        assertNotNull(vehicleModelService.getAllVehicleModels().get(0));
        assertNotNull(vehicleModelService.getAllVehicleModels().get(0).getVehicleMake().getVehicleCategory());
        assertNotNull(vehicleModelService.getAllVehicleModels().get(0).getVehicleMake());
        assertNotNull(vehicleModelService.getAllVehicleModels().get(1));
        assertNotNull(vehicleModelService.getAllVehicleModels().get(1).getVehicleMake().getVehicleCategory());
        assertNotNull(vehicleModelService.getAllVehicleModels().get(1).getVehicleMake());
        assertEquals(1, vehicleModelService.getAllVehicleModels().get(0).getId());
        assertEquals("X3", vehicleModelService.getAllVehicleModels().get(0).getModelName());
        assertEquals(2, vehicleModelService.getAllVehicleModels().get(1).getId());
        assertEquals("X5", vehicleModelService.getAllVehicleModels().get(1).getModelName());
    }

    @Test
    void getVehicleModelsByMakeId() {
        when(vehicleModelService.getVehicleModelsByMakeId(1)).thenReturn(vehicleModelList);
        assertNotNull(vehicleModelService.getVehicleModelsByMakeId(1));
        assertNotNull(vehicleModelService.getVehicleModelsByMakeId(1).get(0));
        assertNotNull(vehicleModelService.getVehicleModelsByMakeId(1).get(1));
        assertNotNull(vehicleModelService.getVehicleModelsByMakeId(1).get(0).getVehicleMake());
        assertNotNull(vehicleModelService.getVehicleModelsByMakeId(1).get(0).getVehicleMake().getVehicleCategory());
        assertNotNull(vehicleModelService.getVehicleModelsByMakeId(1).get(1).getVehicleMake());
        assertNotNull(vehicleModelService.getVehicleModelsByMakeId(1).get(1).getVehicleMake().getVehicleCategory());
        assertEquals(1, vehicleModelService.getVehicleModelsByMakeId(1).get(0).getId());
        assertEquals(2, vehicleModelService.getVehicleModelsByMakeId(1).get(1).getId());
    }

    @Test
    void saveVehicleModel() {
        when(vehicleModelService.saveVehicleModel(any(VehicleModel.class))).thenReturn(vehicleModelList.get(0));
        assertNotNull(vehicleModelService.saveVehicleModel(new VehicleModel()));
        assertNotNull(vehicleModelService.saveVehicleModel(new VehicleModel()).getVehicleMake());
        assertNotNull(vehicleModelService.saveVehicleModel(new VehicleModel()).getVehicleMake().getVehicleCategory());
        assertEquals(1,vehicleModelService.saveVehicleModel(new VehicleModel()).getId());
    }

    @Test
    void deleteVehicleModel() {
        doNothing().when(vehicleModelService).deleteVehicleModel(any(VehicleModel.class));
        vehicleModelService.deleteVehicleModel(new VehicleModel());
        verify(vehicleModelService).deleteVehicleModel(any(VehicleModel.class));
    }
}