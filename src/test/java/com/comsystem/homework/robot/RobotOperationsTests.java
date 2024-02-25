package com.comsystem.homework.robot;

import com.comsystem.homework.model.RobotPlan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RobotOperationsTests {
    @Test
    public void shouldCorrectlyCalculateStonesCollectedAfterMultipleDaysOfCloning() {
        RobotOperations operations = new RobotOperations();
        //3 day check - after 2 days of cloning, we can have 4 robots mining on the third day
        RobotPlan planForThreeDays = operations.excavateStonesForDays(3);
        Assertions.assertEquals(4, planForThreeDays.numberOfStones(), "Should collect 4 stones after 3 days of cloning.");
    }

    @Test
    public void shouldCalculateMinimumDaysRequiredToCollectSpecificNumberOfStones() {
        RobotOperations operations = new RobotOperations();
        //Check to collect 16 stones. It should take 5 days: 4 days to clone and 1 day to mine
        RobotPlan planForSixteenStones = operations.daysRequiredToCollectStones(16);
        Assertions.assertEquals(5, planForSixteenStones.numberOfDays(), "Should require 5 days to collect 16 stones.");
    }
}
