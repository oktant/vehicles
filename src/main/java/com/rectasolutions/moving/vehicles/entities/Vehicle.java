package com.rectasolutions.moving.vehicles.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private UserDB user;

    @ManyToOne
    @JoinColumn(name = "vehicle_category_id", nullable = false)
    @NotNull
    private VehicleCategory vehicleCategory;

    public int getId() {
        return id;
    }

    public UserDB getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(UserDB user) {
        this.user = user;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }
}
