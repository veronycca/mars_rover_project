package com.veronika.mars_rover.exceptions;

public class OutOfPlateauGridException extends RuntimeException {

    public OutOfPlateauGridException() {
        super("Robot is out of the plateau grid");
    }
}
