package com.datacenter.integ;

import java.util.Date;


public class Execute {

	public static void main(String args[]) /*throws InterruptedException*/
	{
		for (int i = 0; i < 150; i++){
			Job test = new Job(4);
		}
		//System.out.println("Job ID is "+test.getJobID());
		
		// Boundary value analysis testing
		//Job negative = new Job(-1);
		//Job excessive = new Job(6);
		//Job limit = new Job(0);
		
		//System.out.println("Current static counter is "+Job.getPerSecond());
		/*
		//Job test;
		//Job preserved = new Job(4);
		
		
		for (int i = 0; i< 150; i++){
			test = new Job(4);
			if (i == 5) {preserved = test;
						long testId = test.getJobID();}
			System.out.println("Iteration Number"+i);
			System.out.println("Job ID is "+test.getJobID()+" at "+test.getArrivalTime());
		}
				
		System.out.println();
		//Long duration = preserved.getTimeElapsed();
		System.out.println("Arrival Time"+preserved.getArrivalTime());
		System.out.println("Job Id is "+preserved.getJobID());
		System.out.println("now is "+new Date());
		//System.out.println("Duration since arrival is "+duration+" seconds");
		*/
		
		
		//Complexity test = new Complexity();
		
		//Complexity.getComplexityID(3);
	     
	} // END OF MAIN METHOD
} // END OF CLASS
