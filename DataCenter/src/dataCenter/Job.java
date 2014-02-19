package dataCenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;




public class Job implements Runnable{
	
	private int cpuID;
	private Long jobID;
	private int complexityID;
	private Date arrivalTime;
	private int jobDuration;
	private Date scheduleTime;
	private Date endTime;
	private String jobStatus;
	private int JOB_TIME_LENGTH = 1000;
	private int batchID;
	static int perSecond = 0;
	
	public static int jobCounter=0;
	

	@Override
	public void run(){
		System.out.println("");
		System.out.println("THREAD RUNNING "+jobID);
		System.out.println("");
		jobCounter++;
		System.out.println("Job counter "+jobCounter);
		processJob(this);

		try {
			
			Thread.sleep(JOB_TIME_LENGTH);
	    	completeJob(this);
			
		} catch (InterruptedException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public Job (){ };
	public Job(int complexity){
		
		// based on complexity alone, instantiate the job with relevant parameters

		if (complexity <=0 || complexity >=6){
			System.out.println("Invalid complexity of "+complexity+" detected... rejecting job");
		}
		else{
			// GET A TIMESTAMP
			Date timestamp = new Date();
			long newId = generateUniqueJobID(timestamp);
			this.setJobId(newId);
			// Set Arrival Time
			this.setArrivalTime(timestamp);
			this.setJobStatus(1);
			//this.setComplexityID(complexity);
			this.setComplexityID(getComplexityFactor(complexity));
			
			this.setJobDuration(JOB_TIME_LENGTH);

			
		
		} // END SUCCESSSFUL
	}

//	public Job(int complexity, int status, Date jobDate, Date arrivalTime){
//		
//		this.setJobid(jobDate);
//		this.setArrivalTime(arrivalTime);
//		this.setJobStatus(status);
//		this.setComplexityID(complexity);
//	}
	public void schedule (TimerTask task, long delay){

	}

	public void completeJob(Job job){	
		setEndTime(new Date());
		setJobStatus(3);
		DataController.jobCompleted(job);
	}

	public void processJob(Job job){
		DataController.addProcessingJobs(job);
		
	}
	public Long getTimeElapsed (){
		Date currentTime = new Date();
		Long elapsed = currentTime.getTime() - this.getArrivalTime().getTime();
		
		// in milliseconds
		return elapsed;
	}

	public int getCpuID() {
		return cpuID;
	}

	public void setCpuID(int cpuID) {
		this.cpuID = cpuID;
		setJobStatus(2);
		//start time
		System.out.println("setting job id "+jobID);
	}
	
	public Long getJobID() {
		return jobID;
	}

	/*
	public void setJobid(Date timestamp) {
		String newID = generateUniqueJobID(timestamp);
		this.jobID = Long.valueOf(newID); // convert string to integer
	}
	*/
	
	public void setJobId(long newid) {
		this.jobID = newid; // convert string to integer
	}

	public long generateUniqueJobID(Date timestamp){		
		// *******************
		// ASSEMBLE JOB ID
		// *******************
		// A - TIMESTAMP                        /// YrMth24HrMinSec
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		String currentTime = ft.format(timestamp);
		
		// B - INCREMENT
		perSecond++;
		if (perSecond == 10000) {perSecond=0;}
		String extension = Integer.toString(perSecond);
		// add leading zeros to increment (consistent length of jobID)
		if (Integer.valueOf(extension) > 999){ extension = ""+extension;}
		else if (Integer.valueOf(extension) > 99){ extension = "0"+extension;}
		else if (Integer.valueOf(extension) > 9){ extension = "00"+extension;}
		else { extension = "000"+extension;}

		// C - COMBINE BOTH AND RETURN IT
		long jobID = Long.valueOf(currentTime+extension);	
		//System.out.println("TIMESTAMP "+currentTime); // debugging
		//System.out.println("JOB ID IS "+jobID); // debugging
		return jobID;
	}
	
//	public String generateUniqueJobID(Date timestamp){		
//		// *******************
//		// ASSEMBLE JOB ID
//		// *******************
//		// A - TIMESTAMP                        /// YrMth24HrMinSec
//		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
//		String currentTime = ft.format(timestamp);
//		
//		// B - INCREMENT
//		perSecond++;
//		if (perSecond == 10000) {perSecond=0;}
//		String extension = Integer.toString(perSecond);
//		// add leading zeros to increment (consistent length of jobID)
//		if (Integer.valueOf(extension) < 1000){ extension = "0"+extension;}
//		else if (Integer.valueOf(extension) < 100){ extension = "00"+extension;}
//		else if (Integer.valueOf(extension) < 10){ extension = "000"+extension;}
//
//		// C - COMBINE BOTH AND RETURN IT
//		String jobID = currentTime+extension;	
//		//System.out.println("TIMESTAMP "+currentTime); // debugging
//		//System.out.println("JOB ID IS "+jobID); // debugging
//		return jobID;
//	}

	public int getComplexityID(){
		return complexityID;
	}

	public void setComplexityID(int complexity){ // run by US and run by scheduling when COPYING complexities
		this.complexityID = complexity;
	}
	
	public int getComplexityFactor(int complexity){ // run by Integ when adding jobs to queue
		int factor = 0;
		switch(complexity){
		case 1: factor = 10; break;
		case 2: factor = 20; break;
		case 3: factor = 40; break;
		case 4: factor = 60; break;
		case 5: factor = 80; break;
		}
		return factor;
		
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date timestamp) {
		// A - TIMESTAMP

		//SimpleDateFormat arrivalTime = new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss:SS");
		//String arrivalTime = tt.format(timestamp);
		this.arrivalTime = timestamp;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJobStatus(){
		return jobStatus;
	}

	public void setJobStatus(int status){
		switch(status){
		case 1: this.jobStatus = "Queued"; break;
		case 2: this.jobStatus = "Assigned"; break;
		case 3: this.jobStatus = "Completed"; break;
		case 4: this.jobStatus = "Rejected"; break;
		}
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public int getJobDuration() {
		return jobDuration;
	}

	public void setJobDuration(int jobDuration) {
		this.jobDuration = jobDuration;
	}

	public Date getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}	

	public String toString(){
		String details = "";
		details += "CPU ID = "+getCpuID()+"\n";
		details += "Job ID = "+getJobID()+"\n";
		details += "Complexity ID = "+getComplexityID()+"\n";
		details += "ArrivalTime = "+getArrivalTime()+"\n";
		details += "Job Duration = "+getJobDuration()+"\n";
		details += "Schedule Time = "+getScheduleTime()+"\n";
		details += "End Time = "+getEndTime()+"\n";
		details += "Job Status = "+getJobStatus()+"\n";
		details += "Batch ID = "+getBatchID()+"\n";
		return details;
	}

	public void setComplexityId(int complexity) {

	this.complexityID = complexity;	
		
	}

	public void setJobStatus(String jobStatus2) {
		// TODO Auto-generated method stub
		
	}

	public static int getPerSecond() {
		// TODO Auto-generated method stub
		return perSecond;
	}
}


