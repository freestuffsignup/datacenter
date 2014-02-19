package optimisation;


import java.util.*;

import communicators.OptimisationComm;

import dataCenter.Job;


public class Optimisation {

	static HashMap currentThermalMap;
	int optimal=0;
	ArrayList<Job> optimalSch = new ArrayList<Job>();
	
	public ArrayList<Job> computeOptimalSch(ArrayList<Job> jobsList1, ArrayList<Job> jobsList2, ArrayList<Job> jobsList3){
		
		OptimisationComm oc = new OptimisationComm();  // for return trip
		Cost cost = new Cost();
		double[] a1 = cost.printi(jobsList1, jobsList2, jobsList3, currentThermalMap);
		//System.out.println("OPTIMISATION DOT JAVA\nCost of Schedule 1 is :" + a1[0] + "\nCost of Schedule 2 is: " + a1[1]);
		if(a1[0] >= a1[1] && a1[0] >= a1[2] ){
			oc.setOptimalSchedule(jobsList1);
			optimal = 1;
			optimalSch = jobsList1;
		}
		else if(a1[1] >= a1[2]){
			oc.setOptimalSchedule(jobsList2);
			optimal = 2;
			optimalSch = jobsList2;
		}
		else{
			oc.setOptimalSchedule(jobsList3);
			optimal = 3;
			optimalSch = jobsList3;
		}
		System.out.println("optimal schedule is "+optimal);
		return optimalSch;
	}

	public void setCRAC(HashMap thermalMap){
		OptimisationComm oc = new OptimisationComm(); 
		currentThermalMap = thermalMap;
		oc.setCracTemp(true);
	}

}
