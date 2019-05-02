package com.rectasolutions.moving.vehicles.entities;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rectauser")
public class UserDB {
    @Id
    @Column(name="id")
    private String id;


    @Column(name = "photo_path")
    private String photoPath;
    @Column(nullable = false)
    @NotNull
    private double longitude;
    @Column(nullable = false)
    @NotNull
    private double latitude;

    @Column(nullable = false)
    @NotNull
    @Check(constraints = "status in ('online','busy','offline')")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
