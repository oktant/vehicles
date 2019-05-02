package com.rectasolutions.moving.vehicles.services;

import com.rectasolutions.moving.vehicles.entities.*;
import com.rectasolutions.moving.vehicles.repositories.VehicleAttributeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleAttributeServiceTest {
    List<VehicleAttribute> vehicleAttributeList = new ArrayList<>();

    @Mock
    VehicleAttributeRepository vehicleAttributeRepository;

    @Mock
    VehicleAttributeService vehicleAttributeService;

    @BeforeEach
    void setUp() {
        vehicleAttributeService = new VehicleAttributeService(vehicleAttributeRepository);
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void initVehicleAttributes(){
        Attribute attribute1 = new Attribute();
        attribute1.setId(1);
        attribute1.setAttributeName("length");

        Attribute attribute2 = new Attribute();
        attribute2.setId(2);
        attribute2.setAttributeName("weight");

        UserDB user = new UserDB();
        user.setId("1");
        user.setStatus("busy");
        user.setLatitude(123);
        user.setLongitude(456);
        user.setPhotoPath("path");

        VehicleCategory vehicleCategory = new VehicleCategory();
        vehicleCategory.setId(1);
        vehicleCategory.setCallDistance(10.5);
        vehicleCategory.setDeliveryDistance(25.5);
        vehicleCategory.setInstantDelOption(true);
        vehicleCategory.setNextTime(10);
        vehicleCategory.setPayload(50);
        vehicleCategory.setType("1");
        vehicleCategory.setVolume(100);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setUser(user);
        vehicle.setVehicleCategory(vehicleCategory);

        VehicleAttribute vehicleAttribute1 = new VehicleAttribute();
        vehicleAttribute1.setId(1);
        vehicleAttribute1.setAttribute(attribute1);
        vehicleAttribute1.setValue("2");
        vehicleAttribute1.setVehicle(vehicle);

        VehicleAttribute vehicleAttribute2 = new VehicleAttribute();
        vehicleAttribute2.setId(2);
        vehicleAttribute2.setAttribute(attribute2);
        vehicleAttribute2.setValue("4");
        vehicleAttribute2.setVehicle(vehicle);

        vehicleAttributeList.add(vehicleAttribute1);
        vehicleAttributeList.add(vehicleAttribute2);
    }

    @Test
    void getVehicleAttributeById() {
        Attribute expectedAttribute = new Attribute();
        expectedAttribute.setId(1);
        expectedAttribute.setAttributeName("length");

        UserDB expectedUser = vehicleAttributeList.get(0).getVehicle().getUser();

        VehicleCategory expectedVehicleCategory = new VehicleCategory();
        expectedVehicleCategory.setId(1);
        expectedVehicleCategory.setCallDistance(10.5);
        expectedVehicleCategory.setDeliveryDistance(25.5);
        expectedVehicleCategory.setInstantDelOption(true);
        expectedVehicleCategory.setNextTime(10);
        expectedVehicleCategory.setPayload(50);
        expectedVehicleCategory.setType("1");
        expectedVehicleCategory.setVolume(100);

        Vehicle expectedVehicle = new Vehicle();
        expectedVehicle.setId(1);
        expectedVehicle.setUser(expectedUser);
        expectedVehicle.setVehicleCategory(expectedVehicleCategory);

        Optional<VehicleAttribute> exampleVehicleAttribute = Optional.of(vehicleAttributeList.get(0));
        when(vehicleAttributeService.getVehicleAttributeById(1)).thenReturn(exampleVehicleAttribute);
        assertNotNull(vehicleAttributeService.getVehicleAttributeById(1));
        assertNotNull(vehicleAttributeService.getVehicleAttributeById(1).get().getAttribute());
        assertNotNull(vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle());
        assertNotNull(vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getUser());
        assertNotNull(vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory());
        assertEquals(1, vehicleAttributeService.getVehicleAttributeById(1).get().getId());
        assertEquals("2", vehicleAttributeService.getVehicleAttributeById(1).get().getValue());
        assertEquals(expectedAttribute.getId(), vehicleAttributeService.getVehicleAttributeById(1).get().getAttribute().getId());
        assertEquals(expectedAttribute.getAttributeName(), vehicleAttributeService.getVehicleAttributeById(1).get().getAttribute().getAttributeName());
        assertEquals(expectedVehicle.getId(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getId());
        assertEquals("1", vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getUser().getId());
        assertEquals("busy", vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getUser().getStatus());
        assertEquals(123, vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getUser().getLatitude());
        assertEquals(456, vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getUser().getLongitude());
        assertEquals("path", vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getUser().getPhotoPath());
        assertEquals(expectedVehicleCategory.getId(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory().getId());
        assertEquals(expectedVehicleCategory.getType(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory().getType());
        assertEquals(expectedVehicleCategory.getCallDistance(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory().getCallDistance());
        assertEquals(expectedVehicleCategory.getDeliveryDistance(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory().getDeliveryDistance());
        assertEquals(expectedVehicleCategory.getNextTime(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory().getNextTime());
        assertEquals(expectedVehicleCategory.getPayload(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory().getPayload());
        assertEquals(expectedVehicleCategory.getVolume(), vehicleAttributeService.getVehicleAttributeById(1).get().getVehicle().getVehicleCategory().getVolume());
    }

    @Test
    void getAllVehicleAttributes() {
        when(vehicleAttributeService.getAllVehicleAttributes()).thenReturn(vehicleAttributeList);
        assertNotNull(vehicleAttributeService.getAllVehicleAttributes());
        assertNotNull(vehicleAttributeService.getAllVehicleAttributes().get(0));
        assertNotNull(vehicleAttributeService.getAllVehicleAttributes().get(1));
        assertNotNull(vehicleAttributeService.getAllVehicleAttributes().get(0).getVehicle());
        assertNotNull(vehicleAttributeService.getAllVehicleAttributes().get(1).getVehicle());
        assertNotNull(vehicleAttributeService.getAllVehicleAttributes().get(0).getAttribute());
        assertNotNull(vehicleAttributeService.getAllVehicleAttributes().get(1).getAttribute());
        assertEquals(1, vehicleAttributeService.getAllVehicleAttributes().get(0).getId());
        assertEquals(2, vehicleAttributeService.getAllVehicleAttributes().get(1).getId());
        assertEquals("2", vehicleAttributeService.getAllVehicleAttributes().get(0).getValue());
        assertEquals("4", vehicleAttributeService.getAllVehicleAttributes().get(1).getValue());

    }

    @Test
    void getVehicleAttributesByVehicleId() {
        when(vehicleAttributeService.getVehicleAttributesByVehicleId(1)).thenReturn(vehicleAttributeList);
        assertNotNull(vehicleAttributeService.getVehicleAttributesByVehicleId(1));
        assertNotNull(vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(0));
        assertNotNull(vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(1));
        assertNotNull(vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(0).getAttribute());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(0).getVehicle());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(1).getAttribute());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(1).getVehicle());
        assertEquals(1, vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(0).getId());
        assertEquals(2, vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(1).getId());
        assertEquals("2", vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(0).getValue());
        assertEquals("4", vehicleAttributeService.getVehicleAttributesByVehicleId(1).get(1).getValue());
    }

    @Test
    void getVehicleAttributesByAttributeId() {
        when(vehicleAttributeService.getVehicleAttributesByAttributeId(1)).thenReturn(vehicleAttributeList);
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1));
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(0));
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(0).getVehicle());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(0).getVehicle().getUser());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(0).getVehicle().getVehicleCategory());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(0).getAttribute());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(1));
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(1).getVehicle());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(1).getVehicle().getUser());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(1).getVehicle().getVehicleCategory());
        assertNotNull(vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(1).getAttribute());
        assertEquals(1, vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(0).getId());
        assertEquals(2, vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(1).getId());
        assertEquals("2", vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(0).getValue());
        assertEquals("4", vehicleAttributeService.getVehicleAttributesByAttributeId(1).get(1).getValue());
    }

    @Test
    void saveVehicleAttribute() {
        VehicleAttribute vehicleAttribute = vehicleAttributeList.get(0);
        when(vehicleAttributeService.saveVehicleAttribute(any(VehicleAttribute.class))).thenReturn(vehicleAttribute);
        assertNotNull(vehicleAttributeService.saveVehicleAttribute(new VehicleAttribute()));
    }

    @Test
    void deleteVehicleAttribute() {
        doNothing().when(vehicleAttributeService).deleteVehicleAttribute(any(VehicleAttribute.class));
        vehicleAttributeService.deleteVehicleAttribute(new VehicleAttribute());
        verify(vehicleAttributeService).deleteVehicleAttribute(any(VehicleAttribute.class));
    }

}