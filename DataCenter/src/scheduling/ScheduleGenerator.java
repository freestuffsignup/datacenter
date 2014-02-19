package scheduling;

//IMPORT OTHER PACKAGES

//import com.datacenter.thermal.*;
//import com.datacenter.optimisation.*;

//import com.datacenter.integ.testing*;
//import com.datacenter.thermal.testing*;
//import com.datacenter.optimisation.*;
//import com.datacenter.scheduler.testing.*;
//import com.datacenter.comm.OptimisationComm.*;
//import com.datacenter.comm.SchedulingComm.*;
//import com.datacenter.comm.ThermalComm.*;

// IMPORT OTHERS
import java.util.*;

import dataCenter.Cpu;
import dataCenter.Job;

public class ScheduleGenerator {

	/* private*/ List<Job> jobScheduleOne;
	/*private*/ List<Job> jobScheduleTwo;
	/*private*/ List<Job> jobScheduleThree;
	private List<Job> jobQueue;
	private List<Cpu> currentSchedule;
	private List<Integer> cpuIndexArray;
	
	
	//setup job queue and current schedule
	//setup index array
	public ScheduleGenerator(List<Job> jobQueue, List<Cpu> currentSchedule) {
		cpuIndexArray = new ArrayList<Integer>();
		this.jobQueue = jobQueue;
		this.currentSchedule = currentSchedule;
		jobScheduleOne = new ArrayList<Job>();
		jobScheduleTwo = new ArrayList<Job>();
		jobScheduleThree = new ArrayList<Job>();
	}
	
	//set cpu index array to the same size as the current schedule
	private void setupCpuIndexArray(){
		cpuIndexArray = new ArrayList<Integer>();
		for(int i = 0; i < currentSchedule.size(); i++){
			cpuIndexArray.add(i);
		}
	}
	
	//First in First Out algorithm
	/*
	 *Allocates each job to the first available cpu in the current schedule 
	 */
	public void generateScheduleOne() {
		
		try
		{
			if(Valid()){
				for(int i = 0; i < jobQueue.size(); i++){
					Job job = jobQueue.get(i);
					int cpuId = currentSchedule.get(cpuIndexArray.get(0)).getId();
					//System.out.println("cpuId = " + cpuId);
					job.setCpuID(currentSchedule.get(cpuIndexArray.get(0)).getId());
					cpuIndexArray.remove(0);
					jobScheduleOne.add(job);
				}
			}
			else
			{
				jobScheduleOne = null;
			}
				
		}catch(NullPointerException e){
			System.out.println("Null value found when generating schedule 1");
			jobScheduleOne = null;
		}
		
		System.out.println("Schedule one generated");
		
	}
	
	//Highest Complexity First algorithm
	/*
	 *Sorts jobs based on highest complexity first
	 *Each job is then allocated to the first cpu in a FIFO manner 
	 */
	public void generateScheduleTwo() {
		try
		{
			if(Valid()){
				List<Job> sortedJobQueue = new ArrayList<Job>();
				/*for(int x = 0; x < jobQueue.size(); x++){
					try {
						Job temp = (Job) jobQueue.get(x).clone();
						sortedJobQueue.add(temp);
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
				
				sortedJobQueue = copyJobs(jobQueue);
				
				Collections.sort(sortedJobQueue, new ComplexityComparator());
				Collections.reverse(sortedJobQueue);
				for(int i = 0; i < sortedJobQueue.size(); i++){
					Job job = sortedJobQueue.get(i);
					job.setCpuID(currentSchedule.get(cpuIndexArray.get(0)).getId());
					cpuIndexArray.remove(0);
					jobScheduleTwo.add(job);
				}
			}
			else
			{
				jobScheduleTwo = null;
			}
			
		}catch(NullPointerException e){
			System.out.println("Null value found when generating schedule 2");
			jobScheduleTwo = null;
		}
		System.out.println("Schedule two generated");
	}
	
	//method to do a deep copy of the jobQueue
	public List<Job> copyJobs(List<Job> jobQueue){
		List<Job> copyJobQueue = new ArrayList<Job>();
		
		for(int i = 0; i < jobQueue.size(); i++){
			Job orig = jobQueue.get(i);
			Job temp = new Job();
			
			temp.setJobId(orig.getJobID());
			temp.setComplexityId(orig.getComplexityID()); 
			//System.out.println("ID copied is "+orig.getComplexityId());
			temp.setArrivalTime(orig.getArrivalTime());
			temp.setJobStatus(orig.getJobStatus());
			temp.setBatchID(orig.getBatchID());
			
			copyJobQueue.add(temp);
		}
		return copyJobQueue;
	}
	
	//Lowest Complexity First algorithm
	/*
	 *Sorts jobs based on lowest complexity first
	 *Each job is then allocated to the first cpu in a FIFO manner 
	 */
	public void generateScheduleThree() {
		try
		{
			if(Valid()){
				List<Job> sortedJobQueue = new ArrayList<Job>();								
				sortedJobQueue = copyJobs(jobQueue);
				Collections.sort(sortedJobQueue, new ComplexityComparator());
								
				for(int i = 0; i < jobQueue.size(); i++){
					Job job = sortedJobQueue.get(i);
					job.setCpuID(currentSchedule.get(cpuIndexArray.get(0)).getId());
					cpuIndexArray.remove(0);
					jobScheduleThree.add(job);
				}
			}
			else
			{
				jobScheduleThree = null;
			}
		}catch(NullPointerException e){
			System.out.println("Null value found when generating schedule 1");
			jobScheduleThree = null;
		}
		System.out.println("Schedule three generated");
	}

	/*Conditions
	 * JobQueue cannot be null
	 * CurrentSchedule cannot be null
	 * CurrentSchedule Size must be 400 in size
	 * JobQueue Size must be <= 50 in size
	 */
	private boolean Valid(){
		if(jobQueue != null && currentSchedule != null) {
			if(currentSchedule.size() == 400 && jobQueue.size() <= 50) {
					setupCpuIndexArray();
					return true;
			}
			
		}
		System.out.println("Scheduling validation failed");
		return false;
	}
	
	public class ComplexityComparator implements Comparator<Job>
	{
	    public int compare(Job o1, Job o2)
	    {
	    	Integer dis1 = o1.getComplexityID();
	    	Integer dis2 = o2.getComplexityID();
	        return dis1.compareTo(dis2);
	    }
	}
	
	public List<Job> getJobScheduleOne() {
		return jobScheduleOne;
	}

	public void setJobScheduleOne(List<Job> jobScheduleOne) {
		this.jobScheduleOne = jobScheduleOne;
	}

	public List<Job> getJobScheduleTwo() {
		return jobScheduleTwo;
	}

	public void setJobScheduleTwo(List<Job> jobScheduleTwo) {
		this.jobScheduleTwo = jobScheduleTwo;
	}

	public List<Job> getJobScheduleThree() {
		return jobScheduleThree;
	}

	public void setJobScheduleThree(List<Job> jobScheduleThree) {
		this.jobScheduleThree = jobScheduleThree;
	}

	public List<Job> getJobQueue() {
		return jobQueue;
	}

	public void setJobQueue(List<Job> jobQueue) {
		this.jobQueue = jobQueue;
	}

	public List<Cpu> getCurrentSchedule() {
		return currentSchedule;
	}

	public void setCurrentSchedule(List<Cpu> currentSchedule) {
		this.currentSchedule = currentSchedule;
	}
	
	
	public String toString(){                  // NEW
		String details ="";
		List<Job> cyclical = (List<Job>) this;
		for (int i = 0; i < cyclical.size(); i++){
			//details +="Job:"+ cyclical.get(i).getJobID();
		}
		
		return details;
	}
}