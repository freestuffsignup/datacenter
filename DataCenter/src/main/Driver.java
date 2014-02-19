package main;

import communicators.*;
import thermal.*;
import optimisation.*;
import scheduling.*;
import testing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import dataCenter.Cpu;
import dataCenter.DataController;
import dataCenter.Job;
import dataCenter.Server;
import dataCenter.Utils;

import scheduling.Schedule;

public class Driver {
	
	static int EPOCH = 1000;
    static int BATCH_SIZE = 50;
    static String fileName = "jobs";
    static DataController dataController = new DataController();
    static ArrayList<Job> queuedJobs= dataController.getQueuedJobs();
    static ArrayList<Job> completedJobs=new ArrayList<Job>();
    static ArrayList<Job> processingJobs=new ArrayList<Job>();
    static Utils util = new Utils();
    static ArrayList<Job> optimalSchedule = new ArrayList<Job>();
    static ExecutorService threadExecutor;
    static ArrayList<Job> jobSchedule;
    static SchedulingComm schedComm = new SchedulingComm();
    
    
	public static void main(String[] args) {
		
		//calls create JSON file which creates 10 random Jobs 
		util.createJsonFile("jobs", 10);
		dataController.receiveJobs(fileName);
		
		//a new schedule (arraylist of cpu's) is created through SERVER
		Server s = dataController.server;
		
		//a list is gathered from server object to send to scheduling
		List<Cpu> cpuList = s.grabFullCpuList();	
		
		
		System.out.println("Preprocessed Jobs");
		
		//set batch id of jobs before being sent to secheduling
		for (Job j : queuedJobs){
			j.setBatchID(1);
		}
		
		//pass the jobs and current schedule to schedulling through the schedule comm
		// called schedCom
		schedComm.processJobQueue(queuedJobs, cpuList);
		
		//retreive the optimal schedule from the data controller
		optimalSchedule=dataController.optimalSchedule;
		
		//clone the arraylist of optimal schedule jobs
		ArrayList<Job> cloned =  (ArrayList<Job>) optimalSchedule.clone();
		
		//output each optmial job stats
		System.out.println("OPTIMAL SCHEDULE");
		for(int y=0; y<optimalSchedule.size();y++){
			System.out.println(optimalSchedule.get(y).toString());
		}
	
		processingJobs = dataController.getProcessingJobs();
		completedJobs = dataController.getCompletedJobs();
		queuedJobs = dataController.getQueuedJobs();
		
		//use the thread executor to create a pool of threads the same size as the the optmial job list
		threadExecutor = Executors.newFixedThreadPool(optimalSchedule.size());
		
		System.out.println("THE NUMEBR OF JOBS IS "+optimalSchedule.size());
		
		int x = 0;
		
		
		//execute the run() method in each of the optimal jobs
		for (int y=0; y< cloned.size(); y++){
			
			System.out.println("THREAD "+x+" CREATED");
			threadExecutor.execute( cloned.get(y) );
			x++;
		}
		
		//dataController.setBatchJobs(queuedJobs);
		queuedJobs=null;
		for(int z=0; z<400; z++){
			dataController.thermalMap.put(z,0.0);
		}
		dataController.thermalMap=dataController.generateNewThermalMap();
		
		
		//output three of thermals updated thermal map
		System.out.println("...................................................."+ dataController.thermalMap.get(1));
		System.out.println("...................................................."+ dataController.thermalMap.get(80));
		System.out.println("...................................................."+ dataController.thermalMap.get(350));
		
		
	}

}
