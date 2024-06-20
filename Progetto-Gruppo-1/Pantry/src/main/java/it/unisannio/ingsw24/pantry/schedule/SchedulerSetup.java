package it.unisannio.ingsw24.pantry.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;

public class SchedulerSetup {

    public static void main(String[] args) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail job = JobBuilder.newJob(CheckExpiredFoodsJob.class)
                    .withIdentity("checkExpiredFoodsJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("dailyTrigger", "group1")
                    .withSchedule(dailyAtHourAndMinute(0, 0))
                    .forJob(job)
                    .build();

            scheduler.start();
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}

