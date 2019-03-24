package com.rectasolutions.moving.vehicles.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Dell on 21-Aug-18.
 */
@Entity
@Table(name="vehicle_attribute")
public class VehicleAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    @NotNull
    private Attribute attribute;

    @Column(name = "value", nullable = false)
    @NotNull
    private String value;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @NotNull
    private Vehicle vehicle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
