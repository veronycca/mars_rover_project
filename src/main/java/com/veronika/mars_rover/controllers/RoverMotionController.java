package com.veronika.mars_rover.controllers;

import com.veronika.mars_rover.model.Coordinates;
import com.veronika.mars_rover.model.RobotUserInput;
import com.veronika.mars_rover.services.RobotTasksService;
import com.veronika.mars_rover.services.SetupService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rover")
@RequiredArgsConstructor
public class RoverMotionController {

    private final RobotTasksService robotTasksService;
    private final SetupService setupService;

    private List<RobotUserInput> allStartPositions = new ArrayList<>();
    private Coordinates plateauGrid = new Coordinates();

    @PostMapping("/grid")
    public void setPlateauGrid(String coordinates) {
        plateauGrid = setupService.inputToGridCoordinates(coordinates);
    }

    @PostMapping("/mars_rover")
    public void setRobotStartPosition(String position, String instruction) {
        allStartPositions.add(setupService.getRobotUserInput(position, instruction, plateauGrid));
    }

    @GetMapping("/result")
    public List<String> getRobotsFinalPositions() {
        return robotTasksService.getAllFinalPositions(allStartPositions, plateauGrid);
    }
}
