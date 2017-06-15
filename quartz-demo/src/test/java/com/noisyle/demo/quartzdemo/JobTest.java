package com.noisyle.demo.quartzdemo;

import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JobTest {
	private static Logger logger = LoggerFactory.getLogger(JobTest.class);
	
	@Test
	public void testJob() {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched;
		try {
			sched = sf.getScheduler();
			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(HelloJob.class)
					.withIdentity("myJob", "group1")
					.build();
			
			// Trigger the job to run now, and then every 40 seconds
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("myTrigger", "group1")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(1)
							.repeatForever())
					.build();
			
			// Tell quartz to schedule the job using our trigger
			sched.scheduleJob(job, trigger);
			sched.start();
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

