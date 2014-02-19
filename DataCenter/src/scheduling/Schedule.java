package scheduling;

//IMPORT OTHER PACKAGES

//import com.datacenter.thermal.*;
//import com.datacenter.optimisation.*;

//import com.datacenter.integ.testing*;
//import com.datacenter.thermal.testing*;
//import com.datacenter.optimisation.*;
//import com.datacenter.scheduler.testing.*;

//import com.datacenter.comm.SchedulingComm;
//import com.datacenter.comm.ThermalComm;

import java.util.*;

import communicators.SchedulingComm;

import dataCenter.Cpu;
import dataCenter.Job;

public class Schedule {
	private List<Job> jobQueue;
	private List<Cpu> currentSchedule;
	/*private*/ ScheduleGenerator sg;
	SchedulingComm schedComm;
	
	public Schedule() {
		schedComm = new SchedulingComm();
	}
	
	public void processJobQueue(List<Job> jobQueue, List<Cpu> currentSchedule){
		System.out.println("Processing job queue...");
		long startTime = System.nanoTime();
		
		this.jobQueue = jobQueue;
		this.currentSchedule = currentSchedule;
		generateSchedules();
		sendSchedulesToOptimization();
		
		long elapsedTimeMillis = (System.nanoTime() - startTime) / 1000000;
		System.out.println("");
		System.out.println("Scheduler processing time is: " + elapsedTimeMillis + " Milliseconds");
		System.out.println("Processing job queue complete...");
	}
	
	private void sendSchedulesToOptimization(){
		System.out.println("Passing schedules to optimization...");
		List<Job> list1 = sg.getJobScheduleOne();
		List<Job> list2 = sg.getJobScheduleTwo();
		List<Job> list3 = sg.getJobScheduleThree();
		//pass to optimization
		/*System.out.println("");
		System.out.println("LIST 1 - First In First Out");
		System.out.println("------------------------");
		for(int i = 0; i < list1.size(); i++){
			System.out.println("Cpu Id = " + list1.get(i).getCpuId() + " - Job Id = " + list1.get(i).getJobId() + " - Complexity Id = " + list1.get(i).getComplexityId());
		}
		System.out.println("");
		System.out.println("LIST 2 - Highest Complexity First");
		System.out.println("------------------------");
		
		for(int x = 0; x < list2.size(); x++){
			System.out.println("Cpu Id = " + list2.get(x).getCpuId() + " - Job Id = " + list2.get(x).getJobId() + " - Complexity Id = " + list2.get(x).getComplexityId());	
		}
		System.out.println("");
		System.out.println("LIST 3 - Lowest Complexity First");
		System.out.println("------------------------");
		
		for(int y = 0; y < list3.size(); y++){
			System.out.println("Cpu Id = " + list3.get(y).getCpuId() + " - Job Id = " + list3.get(y).getJobId() + " - Complexity Id = " + list3.get(y).getComplexityId());
		}*/
		schedComm.setJobsList((ArrayList<Job>)list1, (ArrayList<Job>)list2, (ArrayList<Job>)list3);
		
	}
	
	private void generateSchedules(){
		System.out.println("Creating Schedule Generator...");
		sg = new ScheduleGenerator(jobQueue, currentSchedule);
		System.out.println("about to generate schedules...");
		sg.generateScheduleOne();
		sg.generateScheduleTwo();
		sg.generateScheduleThree();
	}
	
	public String toString(){ // NEW
		String details = "";
		details +="Schedule ONE is :\n"+this.sg.jobScheduleOne.toString();
		details +="\nSchedule ONE size is "+this.sg.jobScheduleOne.size();
		details +="\nSchedule TWO is :\n"+this.sg.jobScheduleTwo.toString();
		details +="\nSchedule TWO size is "+this.sg.jobScheduleTwo.size();
		details +="\nSchedule TWO is :\n"+this.sg.jobScheduleTwo.toString();
		details +="\nSchedule TWO size is "+this.sg.jobScheduleTwo.size();
		details +="\nSchedule THREE is :\n"+this.sg.jobScheduleThree.toString();
		details +="\nSchedule THREE size is "+this.sg.jobScheduleThree.size();
		return details;
	}
}