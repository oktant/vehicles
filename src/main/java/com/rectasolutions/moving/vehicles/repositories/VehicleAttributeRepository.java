package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dell on 21-Aug-18.
 */
@Repository
public interface VehicleAttributeRepository extends JpaRepository<VehicleAttribute,Integer>{
    List<VehicleAttribute> findByVehicle(int vehicle);
    List<VehicleAttribute> findByAttribute(int attribute);

//    @Modifying
//    @Query(value = "update vehicle_attribute set vehicle_id = :vehicleId where id = :id", nativeQuery = true)
//    int updateVehicleAttrVehicleId(@Param("id") int id, @Param("vehicleId") int vehicleId);
//
//    @Modifying
//    @Query(value = "delete from vehicle_attribute where id = :id", nativeQuery = true)
//    int deleteAttribute(@Param("id") int id);
}
