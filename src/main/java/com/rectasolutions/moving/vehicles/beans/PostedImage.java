package com.rectasolutions.moving.vehicles.beans;

import java.util.List;

public class PostedImage {
    private List<ImageInfo> imageInfoList;
    private int vehicleId;

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
