package com.comsystem.homework.robot;

import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RobotOperations {

    /**
     * An algorithm that converts a number of days into an action plan.
     * @param days the number of days that the robot can work
     * @return The action plan <em>must maximize</em> the number of stones that the robot will dig. In other words, this
     *         algorithm must try to achieve the highest value of {@link RobotPlan#numberOfStones} possible for the
     *         number of provided days. The value of {@link RobotPlan#numberOfDays} is equal to the input
     *         days parameter
     * @see RobotPlan
     */
    public RobotPlan excavateStonesForDays(int days) {
        int numberOfStones;

        //Initialize a list to hold the sequence of robot actions
        List<RobotAction> robotActions = new ArrayList<>();

        //Handle the simple cases where days are 1 or 2 directly
        //as they do not require cloning, only digging
        if (days <= 2) {
            numberOfStones = days;
            //Add DIG action for each day available
            for (int i = 0; i < days; i++) {
                robotActions.add(RobotAction.DIG);
            }
        } else {
            //For more than two days, robots will clone themselves every day, without the last day
            for (int i = 1; i < days; i++) {
                robotActions.add(RobotAction.CLONE);
            }
            //On the last day, all robots dig for stones
            robotActions.add(RobotAction.DIG);

            //Calculate the total number of stones collected as 2^(days-1)
            //representing the exponential growth of robots due to cloning
            numberOfStones = (int) Math.pow(2, days - 1);
        }

        //Return a new RobotPlan with the total days, number of stones collected
        //and the sequence of actions taken
        return new RobotPlan(days, numberOfStones, robotActions);
    }

    /**
     * An algorithm that converts a number of stones into an action plan. Essentially this algorithm is the inverse of
     * {@link #excavateStonesForDays(int)}.
     * @param numberOfStones the number of stones the robot has to collect
     * @return The action plan <em>must minimize</em> the number of days necessary for the robot to dig the
     *         provided number of stones. In other words, this algorithm must try to achieve the lowest value of
     *         {@link RobotPlan#numberOfDays} possible for the number of provided stones. The value of
     *         {@link RobotPlan#numberOfStones} is equal to the numberOfStones parameter
     * @see RobotPlan
     */
    public RobotPlan daysRequiredToCollectStones(int numberOfStones) {
        //Initialize the count of days and robots
        int days = 0;
        List<RobotAction> robotActions = new ArrayList<>();

        //Check if the number of stones is 2 or less
        if (numberOfStones <= 2) {
            //If 2 or fewer stones are required, simply dig for each stone
            for (int i = 0; i < numberOfStones; i++) {
                //Add a DIG action for each stone
                robotActions.add(RobotAction.DIG);
                //Each dig takes one day
                days++;
            }
        } else {
            //Start with one robot for cases where more than 2 stones are needed
            int robots = 1;

            //Continue cloning robots until we have enough robots to collect the stones.
            while (robots < numberOfStones) {
                //Add a CLONE action for each day we clone
                robotActions.add(RobotAction.CLONE);
                //Double the number of robots each day through cloning
                robots *= 2;
                //Increment the day count for each day spent cloning
                days++;
            }

            //After we have enough robots to collect the stones in one day, add one day for digging

            //Add a DIG action for the final day when all robots dig
            robotActions.add(RobotAction.DIG);
            //Increment the day count for the digging day
            days++;
        }

        //Return a new RobotPlan object that encapsulates the total number of days,
        //the number of stones to be collected, and the list of actions taken
        return new RobotPlan(days, numberOfStones, robotActions);
    }
}
