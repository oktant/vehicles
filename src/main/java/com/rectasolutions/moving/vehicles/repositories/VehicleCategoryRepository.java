package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory,Integer> {
    List<VehicleCategory> findByParentId(int parentId);
    VehicleCategory save(VehicleCategory vehicleCategory);

    @Query(value = "SELECT u.* FROM Vehicle_Category u WHERE u.delivery_Distance >= ?1 order by u.id limit 1",
            nativeQuery = true)
    Optional<VehicleCategory> findFirstByDistance(double distance);

    @Query(value = "SELECT u.* FROM Vehicle_Category u WHERE u.delivery_Distance >= ?1" +
            " and u.payload >=?2 order by u.id limit 1",
            nativeQuery = true)
    Optional<VehicleCategory> findFirstByPayload (double distance,double payload);
}
