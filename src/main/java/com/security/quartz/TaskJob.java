package com.security.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spring中简单的定时任务,可以看做一个简单的Quartz;
 */
@Component
public class TaskJob {

	@Scheduled(cron="0/5 * * * * ?")
	public void job(){
		System.out.println("Spring中简单的定时任务,可以看做一个简单的Quartz;");
	}
}
