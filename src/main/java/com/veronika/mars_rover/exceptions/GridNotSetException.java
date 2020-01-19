package com.veronika.mars_rover.exceptions;

public class GridNotSetException extends RuntimeException {

    public GridNotSetException() {
        super("Plateau grid was not set");
    }
}
