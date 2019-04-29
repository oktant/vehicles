package com.rectasolutions.moving.vehicles.exceptions;

public class FileIsEmptyException extends Exception {
    public FileIsEmptyException() { super("Unable to upload. File is empty"); }
}
