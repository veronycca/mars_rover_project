package com.veronika.mars_rover.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.veronika.mars_rover.exceptions.GridNotSetException;
import com.veronika.mars_rover.exceptions.InvalidGridArgumentException;
import com.veronika.mars_rover.exceptions.InvalidInstructionException;
import com.veronika.mars_rover.exceptions.InvalidRobotPositionArgumentException;
import com.veronika.mars_rover.exceptions.OutOfPlateauGridException;
import com.veronika.mars_rover.model.Coordinates;
import com.veronika.mars_rover.model.Direction;
import com.veronika.mars_rover.model.RobotUserInput;
import com.veronika.mars_rover.model.RoboticRover;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SetupServiceTest {

    SetupService setupService = new SetupService();

    @Test
    void testPlateauGridSetup() {
        Coordinates result = setupService.inputToGridCoordinates("5 10");

        assertEquals(5, result.getX());
        assertEquals(10, result.getY());
    }

    @ParameterizedTest
    @MethodSource("testValuesForInvalidGridSetupProvider")
    void testPlateauGridInvalidSetup(String inputCoordinates) {
        assertThrows(InvalidGridArgumentException.class, () -> setupService.inputToGridCoordinates(inputCoordinates));
    }

    private static Stream<Arguments> testValuesForInvalidGridSetupProvider() {
        return Stream.of(
                arguments("-10 2"), arguments("10 -2"), arguments("-10 -2"), arguments("0 0"), arguments("510")
                );
    }

    @Test
    void testRobotUserInputSetup() {
        String position = "1 1 N";
        String instruction = "MMMM";
        Coordinates plateauGrid = new Coordinates(5, 5);

        RobotUserInput result = setupService.getRobotUserInput(position, instruction, plateauGrid);
        RobotUserInput expectedResult = new RobotUserInput(new RoboticRover(new Coordinates(1,1), Direction.N), instruction);

        assertEquals(result, expectedResult);
    }

    @Test
    void testRobotStartPositionOutOfTheGrid() {
        String position = "6 6 N";
        String instruction = "M";
        Coordinates plateauGrid = new Coordinates(5, 5);

        assertThrows(OutOfPlateauGridException.class, () -> setupService.getRobotUserInput(position, instruction, plateauGrid));
    }

    @Test
    void testRobotInvalidPositionInput() {
        String position = "66N";
        String instruction = "M";
        Coordinates plateauGrid = new Coordinates(5, 5);

        assertThrows(InvalidRobotPositionArgumentException.class, () -> setupService.getRobotUserInput(position, instruction, plateauGrid));

    }

    @Test
    void testNotSettedGrid() {
        String position = "6 6 N";
        String instruction = "M";
        Coordinates plateauGrid = new Coordinates(0, 0);

        assertThrows(GridNotSetException.class, () -> setupService.getRobotUserInput(position, instruction, plateauGrid));
    }

    @Test
    void testNotProvidedInstruction() {
        String position = "6 6 N";
        String instruction = null;
        Coordinates plateauGrid = new Coordinates(1, 1);

        assertThrows(InvalidInstructionException.class, () -> setupService.getRobotUserInput(position, instruction, plateauGrid));
    }
}