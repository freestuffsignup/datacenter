package communicators;

//IMPORT OTHER PACKAGES
//import com.datacenter.comm.OptimisationComm;

//import com.datacenter.thermal.*;

//import com.datacenter.integ.testing*;
//import com.datacenter.thermal.testing*;
//import com.datacenter.optimisation.*;
//import com.datacenter.scheduler.testing.*;

//import com.datacenter.comm.SchedulerComm.*;

import java.util.*;

import optimisation.Optimisation;

import dataCenter.DataController;
import dataCenter.Job;



public class OptimisationComm {

	int[] compPercent = {10, 20,40,60,80};


	// SCHEDULING ----> OPTIMISATION
	public void setJobSchedules(
			ArrayList<Job> jobsList1, 
			ArrayList<Job> jobsList2,
			ArrayList<Job> jobsList3 ){
		Optimisation opt = new Optimisation();
		setOptimalSchedule(opt.computeOptimalSch(jobsList1, jobsList2, jobsList3));
	}

	 public void setOptimalSchedule(ArrayList<Job> oj ){
		 
		 DataController.setOptimalSchedule(oj);
		 
	 }
	 
	 public void setCracTemp(boolean b){

	  }

}
