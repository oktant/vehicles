package com.rectasolutions.moving.vehicles.utils;

public final class Assistant {
    private Assistant(){}

    private static boolean windows;

    static  {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            windows = true;
        } else {
            windows = false;
        }
    }

    public static String getImagesStorePath() {
        if (windows) {
            return "D:/VehiclePhotos";
        }
        return "linux path";
    }

}
