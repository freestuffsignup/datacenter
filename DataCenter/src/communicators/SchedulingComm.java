package communicators;

import java.util.ArrayList;
import java.util.List;

import scheduling.Schedule;


import dataCenter.Cpu;
import dataCenter.Job;

public class SchedulingComm {
	
	Job[] batches;
	ArrayList<Cpu> currentSchedule;
	
	public SchedulingComm(){}

	public void setJobsList(ArrayList<Job> list1, ArrayList<Job> list2,
			ArrayList<Job> list3) {
		OptimisationComm opComm = new OptimisationComm();
		System.out.println("FINSIHED WITH SCHED");
		opComm.setJobSchedules((ArrayList<Job>)list1, (ArrayList<Job>)list2, (ArrayList<Job>)list3); // cast BACK to arraylists for optimisation to handle
		
	}
	
	public void processJobQueue(ArrayList<Job> queuedJobs,List<Cpu> cpuList){
		Schedule mysch = new Schedule();
		mysch.processJobQueue(queuedJobs, cpuList);
	}
}
