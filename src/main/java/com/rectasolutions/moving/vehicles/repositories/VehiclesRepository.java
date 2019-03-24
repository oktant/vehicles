package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VehiclesRepository extends JpaRepository<Vehicle,Integer> {
    List<Vehicle> findByUser(String user);
    List<Vehicle> findByVehicleCategory(int vehicleCategory);
//    @Modifying
//    @Query(value = "update vehicles set user_id = :userId where id = :id", nativeQuery = true)
//    int updateVehiclesUser(@Param("id") int id, @Param("userId") String userId);
//
//    @Modifying
//    @Query(value = "delete from vehicles where id = :id", nativeQuery = true)
//    int deleteVehicle(@Param("id") int id);

}
