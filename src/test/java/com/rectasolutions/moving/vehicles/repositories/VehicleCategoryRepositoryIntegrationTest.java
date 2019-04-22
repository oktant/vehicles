package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VehicleCategoryRepositoryIntegrationTest {

    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;

    @Test
    public void testFindFirstByDistance(){
        VehicleCategory aVehicleCategory = new VehicleCategory("3", true, 10, 22, 77, 55, 100, null);
        VehicleCategory expectedVehicleCategory = vehicleCategoryRepository.save(aVehicleCategory);

        VehicleCategory actualVehicleCategory = vehicleCategoryRepository.findFirstByDistance(60).get();

        assertNotNull(actualVehicleCategory);
        assertNotNull(actualVehicleCategory.getId());
        assertNotNull(actualVehicleCategory.getType());
        assertNotNull(actualVehicleCategory.isInstantDelOption());
        assertEquals(expectedVehicleCategory.getType(), actualVehicleCategory.getType());
        assertEquals(expectedVehicleCategory.getCallDistance(), actualVehicleCategory.getCallDistance(), 0.0);
        assertEquals(expectedVehicleCategory.getDeliveryDistance(), actualVehicleCategory.getDeliveryDistance(), 0.0);
        assertEquals(expectedVehicleCategory.getNextTime(), actualVehicleCategory.getNextTime(), 0.0);
        assertEquals(expectedVehicleCategory.getParentId(), actualVehicleCategory.getParentId());
        assertEquals(expectedVehicleCategory.getPayload(), actualVehicleCategory.getPayload(), 0.0);
        assertEquals(expectedVehicleCategory.getVolume(), actualVehicleCategory.getVolume(), 0.0);
    }
}
