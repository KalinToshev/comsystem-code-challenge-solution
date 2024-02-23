package com.comsystem.homework.rest;

import com.comsystem.homework.model.RobotPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.comsystem.homework.robot.RobotOperations;

@RestController()
@RequestMapping("/api/v1/robot/operation")
public final class RobotRestController {
    //RobotOperations service for executing business logic
    private final RobotOperations robotOperations;

    /**
    * Constructs a RobotRestController with the necessary RobotOperations service
    * @param robotOperations The service to handle robot logic
    */
    @Autowired
    public RobotRestController(RobotOperations robotOperations) {
        this.robotOperations = robotOperations;
    }

    /**
     * This method exposes the functionality of {@link RobotOperations#excavateStonesForDays(int)} via HTTP
     */
    @PostMapping("/excavation")
    public ResponseEntity<RobotPlan> excavateStones(@RequestParam Integer numberOfDays) {
        //Use the RobotOperations service to calculate the excavation plan
        RobotPlan plan = robotOperations.excavateStonesForDays(numberOfDays);

        //Return the plan within the ResponseEntity
        return ResponseEntity.ok(plan);
    }

    /**
     * This method exposes the functionality of {@link RobotOperations#daysRequiredToCollectStones(int)} via HTTP
     */
    @PostMapping("/approximation")
    public ResponseEntity<RobotPlan> approximateDays(@RequestParam Integer numberOfStones) {
        // TODO
        throw new ErrorResponseException(HttpStatus.I_AM_A_TEAPOT);
    }

}
