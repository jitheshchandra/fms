package com.ssrv.fms.model.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//import org.quartz.*;


public class FirstJob implements Job{

	 @Override
	 	    public void execute(JobExecutionContext arg0) throws JobExecutionException {
	 	        System.out.println("Hello World !! this is chitti Memory 1 tera Hertz ,speed one Zetta Byte .");
	 	    }
	 
}
