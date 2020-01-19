package com.veronika.mars_rover.utils;

import com.veronika.mars_rover.exceptions.InvalidInstructionException;
import com.veronika.mars_rover.exceptions.OutOfPlateauGridException;
import com.veronika.mars_rover.model.Coordinates;
import com.veronika.mars_rover.model.Direction;
import com.veronika.mars_rover.model.Instructions;
import com.veronika.mars_rover.model.RoboticRover;


public class MotionAlgorithm {

    private MotionAlgorithm() {
    }

    public static RoboticRover getFinalPosition(RoboticRover roboticRoverPosition, String instructions,
            Coordinates grid) {
        for (int i = 0; i < instructions.length(); i++) {
            String letter = Character.toString(instructions.charAt(i));

            try {
                roboticRoverPosition = moveOneStep(Instructions.valueOf(letter), roboticRoverPosition);
                checkIfRobotNotOutOfTheGrid(roboticRoverPosition.getRoverCoordinates(), grid);
            } catch (IllegalArgumentException e) {
                throw new InvalidInstructionException();
            }
        }

        return roboticRoverPosition;
    }

    private static RoboticRover moveOneStep(Instructions step, RoboticRover position) {
        switch (step) {
            case L:
                position.setDirection(turnLeft(position.getDirection()));
                break;
            case R:
                position.setDirection(turnRight(position.getDirection()));
                break;
            case M:
                position.setRoverCoordinates(goForward(position));
                break;
            default:
                throw new IllegalArgumentException();
        }
        return position;
    }

    private static Coordinates goForward(RoboticRover position) {
        Coordinates coordinates = position.getRoverCoordinates();
        Direction direction = position.getDirection();

        switch (direction) {
            case E:
                coordinates.setX(coordinates.getX() + 1);
                break;
            case S:
                coordinates.setY(coordinates.getY() - 1);
                break;
            case W:
                coordinates.setX(coordinates.getX() - 1);
                break;
            case N:
                coordinates.setY(coordinates.getY() + 1);
                break;
            default:
                throw new IllegalArgumentException();
        }

        return coordinates;
    }

    private static Direction turnRight(Direction direction) {
        switch (direction) {
            case E:
                return Direction.S;
            case S:
                return Direction.W;
            case W:
                return Direction.N;
            case N:
                return Direction.E;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static Direction turnLeft(Direction direction) {
        switch (direction) {
            case E:
                return Direction.N;
            case S:
                return Direction.E;
            case W:
                return Direction.S;
            case N:
                return Direction.W;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void checkIfRobotNotOutOfTheGrid(Coordinates robotPosition, Coordinates plateauGrid) {
        if (robotPosition.getX() > plateauGrid.getX() || robotPosition.getY() > plateauGrid.getY()
                || robotPosition.getX() < 0 || robotPosition.getY() < 0) {
            throw new OutOfPlateauGridException();
        }
    }
}
