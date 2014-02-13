package com.datacenter.integ;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimerTask;


public class Job {
	private int cpuID;
	private long jobID; // vs Long object
	//enum complexityID{Queued, Assigned, Completed, Rejected};
	private int complexityID;
	private Date arrivalTime;
	private int jobDuration;
	private Date scheduleTime;
	private Date endTime;
	//enum jobStatus{Queued, Assigned, Completed, Rejected};
	private String jobStatus;
	private final int JOB_TIME_LENGTH = 1000;
	private int batchID;

	// jobid extension counter
	private static int perSecond = 0; // used to generate part of JobID


	// ************************
	// CONSTRUCTOR - blank copies for scheduling
	// ************************
	public Job (){ }; // constructor used by Scheduling to create blank jobs which are then populated (i.e. 2-step copies)
	

	// ************************
	// CONSTRUCTOR - job received from external source
	// ************************
	public Job(int complexity){

		// based on complexity alone, instantiate the job with relevant parameters

		if (complexity <=0 || complexity >=6){
			System.out.println("Invalid complexity of "+complexity+" detected... rejecting job");
		}
		else{
			// GET A TIMESTAMP
			Date timestamp = new Date();

			// ***********************
			// UPDATE FIELDS
			// ***********************

			// Set Job ID
			this.setJobid(timestamp);

			// Set Arrival Time
			this.setArrivalTime(timestamp);

			// Set Job Status to Queued (setting 1)
			this.setJobStatus(1);
			
			// set complexity
			this.setComplexityID(complexity);
			
			// set job duration
			this.setJobDuration(JOB_TIME_LENGTH); // constant time of 1 sec

			// SET BATCH ID - n/a -- called by DataController, pass schedule (maurice)
			
			// SET CPUID - n/a --- called by scheduling
			
			// SET SCHEDULE TIME - n/a
			// SET END TIME - n/a 
			
			
			/* 
			//TESTING ONLY
			// Get a CPU ID
			int cpuNo = Cpu.getCpuID(this.complexityID); // seek an available CPU no from cpu class
			this.setCpuID(cpuNo); // update CPU with response		
			// VS hard coded
			//this.setCpuID(50);
			 */
		} // END SUCCESSSFUL
	}

	// ****************************
	// Key functionality
	// ****************************
	public void schedule (TimerTask task, long delay){

	}

	public void completeJob(){
		// - A TIMESTAMP
		Date completedTime = new Date();
		// update completionDate
		this.endTime = completedTime;
	}

	public long getTimeElapsed (){
		Date currentTime = new Date();
		long elapsed = currentTime.getTime() - this.getArrivalTime().getTime();
		
		// in milliseconds
		return elapsed;
	}

	// ****************************
	// Generic getters and setters
	// ****************************

	public int getCpuID() {
		return cpuID;
	}

	public void setCpuID(int cpuID) {
		this.cpuID = cpuID;
	}

	public long getJobID() {
		return jobID;
	}

	public void setJobid(Date timestamp) {
		String newID = generateUniqueJobID(timestamp);
		this.jobID = Long.valueOf(newID); // convert string to integer
	}


	public String generateUniqueJobID(Date timestamp){		
		// *******************
		// ASSEMBLE JOB ID
		// *******************
		// A - TIMESTAMP                        /// YrMth24HrMinSec
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		String currentTime = ft.format(timestamp);
		
		// B - INCREMENT
		perSecond++;
		if (perSecond == 10000) {perSecond=1;}
		System.out.println("DEBUGGING: current persecond = "+perSecond); // debugging
		String extension = Integer.toString(perSecond);
		// add leading zeros to increment (consistent length of jobID)
		
		if (Integer.valueOf(extension) > 1000){ extension = ""+extension;}
		else if (Integer.valueOf(extension) > 100){ extension = "0"+extension;}
		else if (Integer.valueOf(extension) > 10){ extension = "00"+extension;}
		else { extension = "000"+extension;}

		//if (Integer.valueOf(extension) < 10){ extension = "000"+extension;}
		//else if (Integer.valueOf(extension) < 100){ extension = "00"+extension;}
		//else if (Integer.valueOf(extension) < 1000){ extension = "0"+extension;}
		//else { extension = "0000"+extension;}
		
		
		// C - COMBINE BOTH AND RETURN IT
		String jobID = currentTime+extension;	
		//System.out.println("TIMESTAMP "+currentTime); // debugging
		//System.out.println("JOB ID IS "+jobID); // debugging
		return jobID;
	}


	// *************************
	public int getComplexityID(){
		return complexityID;
	}

	public void setComplexityID(int complexity){
		switch(complexity){
		case 1: this.complexityID = 10; break;
		case 2: this.complexityID = 20; break;
		case 3: this.complexityID = 40; break;
		case 4: this.complexityID = 60; break;
		case 5: this.complexityID = 80; break;
		}
	}
	//******************/

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

	//*************************
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
	//******************

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

	// *************************
	// Confirm not required
	// *************************
	
	public Date getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}	

	// *************************
	// Debugging
	// *************************
	
	public static int getPerSecond(){
		return perSecond;
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
}


