package com.veronika.mars_rover.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.veronika.mars_rover.model.Coordinates;
import com.veronika.mars_rover.model.Direction;
import com.veronika.mars_rover.model.RobotUserInput;
import com.veronika.mars_rover.model.RoboticRover;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class RobotTasksServiceTest {

    RobotTasksService robotTasksService = new RobotTasksService();

    @Test
    void testAllFinalPositions() {
        Coordinates plateauGrid = new Coordinates(5, 5);
        List<RobotUserInput> robotUserInputs = Arrays.asList(
                new RobotUserInput(new RoboticRover(new Coordinates(1, 2), Direction.N), "LMLMLMLMM"),
                new RobotUserInput(new RoboticRover(new Coordinates(3, 3), Direction.E), "MMRMMRMRRM"),
                new RobotUserInput(new RoboticRover(new Coordinates(1, 1), Direction.S), "MM"),
                new RobotUserInput(new RoboticRover(new Coordinates(1, 1), Direction.S), "PP")
        );

        List<String> result = robotTasksService.getAllFinalPositions(robotUserInputs, plateauGrid);
        List<String> expectedResult = Arrays.asList("1 3 N", "5 1 E", "Robot is out of the plateau grid",
                "Robot control instruction is not provided or instruction is not valid. Possible letters are L, R "
                        + "and M");

        assertEquals(expectedResult, result);
    }
}
