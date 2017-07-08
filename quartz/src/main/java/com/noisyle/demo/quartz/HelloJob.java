package com.noisyle.demo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements StatefulJob {
	private static Logger logger = LoggerFactory.getLogger(HelloJob.class);


	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("hello start");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("hello end");
	}

}
