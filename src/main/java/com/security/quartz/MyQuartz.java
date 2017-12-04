package com.security.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 作业类继承自特定的基类QuartzJobBean
 */
public class MyQuartz extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("作业类继承自特定的基类QuartzJobBean");
	}

}
