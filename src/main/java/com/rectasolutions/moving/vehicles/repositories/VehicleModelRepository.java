package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
    List<VehicleModel> findByVehicleMake(int vehicleMake);

//    @Modifying
//    @Query(value = "update vehicle_model set name = :name where id = :id", nativeQuery = true)
//    int updateModelName(@Param("id") int id, @Param("name") String name);
//
//    @Modifying
//    @Query(value = "update vehicle_model set make_id = :makeId where id = :id", nativeQuery = true)
//    int updateModelMake(@Param("id") int id, @Param("makeId") int makeId);
//
//    @Modifying
//    @Query(value = "delete from vehicle_model where id = :id", nativeQuery = true)
//    int deleteModel(@Param("id") int id);
}
