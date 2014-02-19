package testing;

//IMPORT OTHER PACKAGES
//import com.datacenter.integ.*;
//import com.datacenter.thermal.*;
//import com.datacenter.optimisation.*;
//import com.datacenter.scheduler.*;
//import com.datacenter.integ.testing*;
//import com.datacenter.thermal.testing*;
//import com.datacenter.optimisation.*;
//import com.datacenter.scheduler.testing.*;
//import com.datacenter.comm.OptimisationComm.*;
//import com.datacenter.comm.SchedulingComm.*;
//import com.datacenter.comm.ThermalComm.*;

// OTHER IMPORTS
import static org.junit.Assert.*;
import java.lang.annotation.Annotation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dataCenter.Job;

// IMPORTS ACCESS TO OBJECTS

import communicators.*;
import thermal.*;
import optimisation.*;
import scheduling.*;
import testing.*;




public class JobTest {

	int startCounter = Job.getPerSecond();
	
	@Before 
	public void startingConditions(){
		
		// applies for EACH condition
		// common characteristics / settings for ALL tests (e.g. DB connections, imports)
		
		//int startCounter = Job.getPerSecond();
		//System.out.println("starting perSecond counter is "+startCounter);
		
	}
	
	@Test
	public void complexityBoundaryValues() {
		Job negative = new Job(-1);
		Job excessive = new Job(6);
		Job limit = new Job(0);
		
		// ****************************************
		// check if any increments to static counter arising from any of the above
		// should only increase if a new job object was created
		// ****************************************
		
		//System.out.println("Current static counter is "+Job.getPerSecond());
		assertEquals(startCounter, Job.getPerSecond()); 
	}
	

	@Test
	public void jobIdConfiguration(){
		Job newJob = new Job(4);
		long jobIDno = newJob.getJobID();
		
		//System.out.println("Job ID is"+jobIDno); // debugging
		
		// ****************************************
		// count number of digits in jobid
		// ****************************************
		int length = String.valueOf(""+jobIDno).length();
		assertEquals(length,18);
		
		//YYYY MM DD HH MM SS xxxx (xxx = 4 digit number = total digits = 18)
	}
	
	@Test
	public void initialiseJobNullFields(){
		Job newJob = new Job(4);
		
		assertNotNull(newJob.getJobID());		
		assertNotNull(newJob.getArrivalTime());	
		assertNotNull(newJob.getJobStatus());
		assertNotNull(newJob.getJobDuration());
		
		System.out.println("Job No "+newJob.getJobID()+" arrived at "+newJob.getArrivalTime()+
				" and has a status of "+newJob.getJobStatus()+
				" and has a length of "+newJob.getJobDuration()
				);
	}
	
	@Test
	public void jobStatusValue(){
		Job statusJob = new Job(3);
		
		System.out.println("Status of Job "+statusJob.getJobID()+" is "+statusJob.getJobStatus());
		assertEquals(statusJob.getJobStatus(),"Queued");
	}
	
	@Test
	public void queueJobNullFields(){
		Job newJob = new Job(4);
		
		assertNotNull(newJob.getBatchID());		
		//assertThat(0, not (equalTo(Integer.valueOf( newJob.getBatchID() ))) );
		
		System.out.println("Job No "+newJob.getJobID()+
				" was allocated to batch number  "+newJob.getBatchID()
				);
	}
}
