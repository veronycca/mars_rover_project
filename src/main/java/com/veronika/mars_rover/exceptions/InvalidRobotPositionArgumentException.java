package com.veronika.mars_rover.exceptions;

public class InvalidRobotPositionArgumentException extends RuntimeException {

    public InvalidRobotPositionArgumentException() {
        super("Rover's position and location format is not valid. Rover's position and location is represented by "
                + "a combination of x and y coordinates and a letter representing one of the four cardinal compass "
                + "points, e. g. 1 2 N");
    }
}
