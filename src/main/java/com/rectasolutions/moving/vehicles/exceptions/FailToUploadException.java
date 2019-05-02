package com.rectasolutions.moving.vehicles.exceptions;

public class FailToUploadException extends Exception {
    public FailToUploadException(){
        super("You have failed upload");
    }

    public FailToUploadException(String text){
        super("You have failed upload. " + text);
    }
}
