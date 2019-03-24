package com.rectasolutions.moving.vehicles.repositories;

import com.rectasolutions.moving.vehicles.entities.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory,Integer> {
    List<VehicleCategory> findByParentId(int parentId);
    VehicleCategory save(VehicleCategory vehicleCategory);
//    List<VehicleCategory> findByTypeAllIgnoreCase(String type);
//
//    @Modifying
//    @Query(value = "update vehicle_category set type = :type where id = :id", nativeQuery = true)
//    int updateType(@Param("id") int id, @Param("type") String type);
//
//    @Modifying
//    @Query(value = "delete from vehicle_category where id = :id", nativeQuery = true)
//    int deleteCategory(@Param("id") int id);
}
