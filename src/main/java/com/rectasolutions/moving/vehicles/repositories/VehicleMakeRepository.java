package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import com.rectasolutions.moving.vehicles.entities.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake,Integer> {
    List<VehicleMake> findByVehicleCategory(int vehicleCategory);

//    @Modifying
//    @Query(value = "update vehicle_make set name = :name where id = :id", nativeQuery = true)
//    int updateMakeName(@Param("id") int id, @Param("name") String name);
//
//    @Modifying
//    @Query(value = "update vehicle_make set category_id = :categoryId where id = :id", nativeQuery = true)
//    int updateMakeCategory(@Param("id") int id, @Param("categoryId") int categoryId);
//
//    @Modifying
//    @Query(value = "delete from vehicle_make where id = :id", nativeQuery = true)
//    int deleteMake(@Param("id") int id);
}
