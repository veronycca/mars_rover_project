package com.veronika.mars_rover.services;

import static com.veronika.mars_rover.utils.MotionAlgorithm.checkIfRobotNotOutOfTheGrid;

import com.veronika.mars_rover.exceptions.GridNotSetException;
import com.veronika.mars_rover.exceptions.InvalidGridArgumentException;
import com.veronika.mars_rover.exceptions.InvalidInstructionException;
import com.veronika.mars_rover.exceptions.InvalidRobotPositionArgumentException;
import com.veronika.mars_rover.model.Coordinates;
import com.veronika.mars_rover.model.Direction;
import com.veronika.mars_rover.model.RobotUserInput;
import com.veronika.mars_rover.model.RoboticRover;
import org.springframework.stereotype.Service;

@Service
public class SetupService {

    public Coordinates inputToGridCoordinates(String coordinates) {
        Coordinates plateauGrid = createPlateauGridCoordinates(coordinates);

        if (plateauGrid.getX() <= 0 || plateauGrid.getY() <= 0) {
            throw new InvalidGridArgumentException();
        }

        return plateauGrid;
    }

    private Coordinates createPlateauGridCoordinates(String coordinates) {
        try {
            String[] splittedCoordinates = splitInput(coordinates);
            int x = Integer.parseInt(splittedCoordinates[0]);
            int y = Integer.parseInt(splittedCoordinates[1]);
            return new Coordinates(x, y);
        } catch (Exception e) {
            throw new InvalidGridArgumentException();
        }
    }

    public RobotUserInput getRobotUserInput(String position, String instruction, Coordinates plateauGrid) {
        if (plateauGrid.getY() == 0 || plateauGrid.getX() == 0) {
            throw new GridNotSetException();
        }

        if (instruction == null) {
            throw new InvalidInstructionException();
        }

        RobotUserInput robotUserInput = createRobotUserInput(position, instruction);

        checkIfRobotNotOutOfTheGrid(robotUserInput.getRoboticRover().getRoverCoordinates(), plateauGrid);

        return robotUserInput;
    }

    private RobotUserInput createRobotUserInput(String position, String instruction) {
        try {
            String[] splittedUserInput = splitInput(position);
            int x = Integer.parseInt(splittedUserInput[0]);
            int y = Integer.parseInt(splittedUserInput[1]);
            Direction direction = Direction.valueOf(splittedUserInput[2]);

            return new RobotUserInput(new RoboticRover(new Coordinates(x, y), direction), instruction);
        } catch (Exception e) {
            throw new InvalidRobotPositionArgumentException();
        }
    }

    private String[] splitInput(String input) {
        return input.split(" ");
    }
}
