package optimisation;

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

import dataCenter.Job;

public class Cost {


	//int[] compPercent = {10, 20,40,60,80};
	//int[] tempArray = {40, 41,42,43,44,45,46,47,48,49,50};

	int[] tempArray = {45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50,
			45,41,43,43,44,48,46,47,48,49,50,45,41,43,43,44,48,46,47,48,49,50};

	//int[] tempArray2 = {45, 41,43,43,44,48,46,47,48,49,50};
	double sum1 = 0, sum2 = 0, sum3 = 0;

	double rSum1 = 0;
	double rSum2 = 0;
	double rSum3 = 0;

	double pSum1 = 0;
	double pSum2 = 0;
	double pSum3 = 0;

	double r = 0;
	double r2 = 0;
	double r3 = 0;

	double c = 0;
	double c2 = 0;
	double c3 = 0;

	int cPower = 350;
	double cCon = cPower/600.0;
	//Data data = new Data();
	//Job job = new Job();
	/* ADD YOUR CODE HERE */

	public double[] printi(ArrayList<Job> jobsList, ArrayList<Job> jobsList2, ArrayList<Job> jobsList3, HashMap tempMap){

		//System.out.println(jobsList.size());
		for(int i = 0; i< jobsList.size(); i++){
			Job job =  (Job)jobsList.get(i);
			Job job2 =  (Job)jobsList2.get(i);
			Job job3 =  (Job)jobsList3.get(i);

//			System.out.println(i);
//			System.out.println("Complexity is "+job.getComplexityId());
//			System.out.println(.001*(Integer)job.getComplexityId() 
//					+ cCon*(Integer)job.getComplexityId()*.01*Math.exp((double)(tempArray[job.getCpuId()]-40)/3)/100);
//			System.out.println(+.001*(Integer)job2.getComplexityId() 
//					+ cCon*(Integer)job2.getComplexityId()*.01*Math.exp((double)(tempArray[job2.getCpuId()]-40)/3)/100);

			r = .01 * (Integer)job.getComplexityID();
			r2 = .01 * (Integer)job2.getComplexityID();
			r3 = .01 * (Integer)job3.getComplexityID();

			rSum1 +=  r;

			rSum2 += r2;
			rSum3 += r3; 

			c += .0001*(Integer)job.getComplexityID() 
					+  cCon*(Integer)job.getComplexityID()*.01*Math.exp((double)(tempArray[job.getCpuID()]-40)/5)/100;
			c2 += .0001*(Integer)job2.getComplexityID() 
					+  cCon*(Integer)job2.getComplexityID()*.01*Math.exp((double)(tempArray[job2.getCpuID()]-40)/5)/100;
			c3 += .0001*(Integer)job3.getComplexityID() 
					+  cCon*(Integer)job3.getComplexityID()*.01*Math.exp((double)(tempArray[job3.getCpuID()]-40)/5)/100;

			sum1 += c;
			sum2 += c2;
			sum3 += c3;

			pSum1 +=  r - c;
			pSum2 += r2 - c2 ;
			pSum3 += r3 - c3; 

		} // END OF FOR LOOP

		//System.out.println("Schedule 1 is :"+jobsList.size()+" long");
		//System.out.println("Schedule 2 is "+jobsList2.size()+" long");
		//System.out.println("Schedule 3 is "+jobsList3.size()+" long");
		//System.out.println("Cost for Schedule 1 is: "+sum1 + 
		//		"\nCost for Schedule 2 is: " + sum2+
		//		"\nCost for Schedule 3 is: " + sum3);

		//System.out.println();
		//System.out.println("profit" + pSum1 + "\n" + pSum2 + "\n" + pSum3);
		//System.out.println();
		//System.out.println("revenue" + rSum1 + "\n" + rSum2 + "\n" + rSum3);

		System.out.println();
		System.out.println("\t SCHEDULE 1 \t SCHEDULE 2 \t SCHEDULE 3");		
		System.out.printf("REVENUE  %,2f \t %,2f \t %,2f \n", rSum1, rSum2, rSum3);
		System.out.printf("COST     %,2f \t %,2f \t %,2f \n", sum1, sum2, sum3);
		System.out.printf("PROFIT   %,2f \t %,2f \t %,2f \n\n", pSum1, pSum2, pSum3);
		

		double[] costArray = {pSum1, pSum2, pSum3};
		return costArray;
	} // END OF METHOD

} // END OF CLASS
