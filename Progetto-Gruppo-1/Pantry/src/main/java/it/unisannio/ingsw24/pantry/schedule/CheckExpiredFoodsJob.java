package it.unisannio.ingsw24.pantry.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import it.unisannio.ingsw24.pantry.logic.PantryLogicImplementation;

public class CheckExpiredFoodsJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        PantryLogicImplementation logic = new PantryLogicImplementation();
        logic.checkAndSetIsExpiredFoods();
    }
}

