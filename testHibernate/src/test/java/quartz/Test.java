package quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerListener;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

public class Test implements Job {
	
	public static void main(String[] args) {
		try{

		  // define the job and tie it to our HelloJob class
		  JobDetail job = JobBuilder.newJob(Test.class)
		      .withIdentity("myJob", "group1")
		      .build();
		  
		  String sDate1="10/08/2018";  
		  Date startDate=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		  
		  System.out.println(startDate);
		  String eDate = "11/08/2018 00:00";
		  Date endDate=new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(eDate);
		  
		  System.out.println(startDate.compareTo(new Date()));
		  System.out.println(endDate.compareTo(new Date()));

		  // Trigger the job to run now, and then every 40 seconds
		  Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("myTrigger", "group1")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(5).withRepeatCount(1))
					.startAt(startDate)
					.endAt(endDate)		
					.build();
		  
		 /* Trigger trigger = TriggerBuilder.newTrigger()
			         .withIdentity("myTrigger", "group1")
			         .withSchedule(CronScheduleBuilder.cronSchedule("0 56 19 ? * *"))
			         .startAt(startDate)
			         .endAt(endDate).build();*/

		  // Tell quartz to schedule the job using our trigger
		  SchedulerFactory schedFact = new StdSchedulerFactory();
		  Scheduler sched = schedFact.getScheduler();
		  
		  
		//Listener attached to jobKey
		  sched.getListenerManager().addSchedulerListener(new SchedulerListener() {
			
			@Override
			public void triggersResumed(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void triggersPaused(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void triggerResumed(TriggerKey arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void triggerPaused(TriggerKey arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void triggerFinalized(Trigger arg0) {
				// TODO Auto-generated method stub
				System.out.println("triggerFinalized "+arg0.getKey().getName());
			}
			
			@Override
			public void schedulingDataCleared() {
				// TODO Auto-generated method stub
				
			}
			
			
			public void schedulerStarting() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void schedulerStarted() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void schedulerShuttingdown() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void schedulerShutdown() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void schedulerInStandbyMode() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void schedulerError(String arg0, SchedulerException arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobsResumed(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobsPaused(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobUnscheduled(TriggerKey arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobScheduled(Trigger arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobResumed(JobKey arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobPaused(JobKey arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobDeleted(JobKey arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void jobAdded(JobDetail arg0) {
				// TODO Auto-generated method stub
				
			}
		});;
		  
		  
		  sched.start();
		  
		  
		  sched.scheduleJob(job, trigger);
		  
		  System.out.println(sched.getJobDetail(new JobKey("myJob")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
    public void execute(JobExecutionContext context)throws JobExecutionException {
		
		System.out.println("Hello Quartz!");	
	}

}


