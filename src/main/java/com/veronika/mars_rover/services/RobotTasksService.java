package com.veronika.mars_rover.services;

import static com.veronika.mars_rover.utils.MotionAlgorithm.getFinalPosition;

import com.veronika.mars_rover.exceptions.InvalidInstructionException;
import com.veronika.mars_rover.exceptions.OutOfPlateauGridException;
import com.veronika.mars_rover.model.Coordinates;
import com.veronika.mars_rover.model.RobotUserInput;
import com.veronika.mars_rover.model.RoboticRover;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RobotTasksService {

    public List<String> getAllFinalPositions(List<RobotUserInput> allStartPositions, Coordinates plateauGrid) {

        List<String> result = new ArrayList<>();

        allStartPositions.forEach(robotUserInput -> {
            try {
                RoboticRover finalPosition = getFinalPosition(robotUserInput.getRoboticRover(),
                        robotUserInput.getInstructions(), plateauGrid);
                result.add(finalPosition.getRoverCoordinates().getX() + " " +
                        finalPosition.getRoverCoordinates().getY() + " " + finalPosition.getDirection().toString());
            } catch (OutOfPlateauGridException | InvalidInstructionException e) {
                result.add(e.getMessage());
            }
        });

        return result;
    }
}
