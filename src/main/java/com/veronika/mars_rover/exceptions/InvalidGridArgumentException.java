package com.veronika.mars_rover.exceptions;

public class InvalidGridArgumentException extends RuntimeException {

    public InvalidGridArgumentException() {
        super("Plateau grid is not valid. Grid is represented by a combination of x and y coordinates separated by "
                + "space. x and y should be higher than 0, e. g. 5 5");
    }
}
