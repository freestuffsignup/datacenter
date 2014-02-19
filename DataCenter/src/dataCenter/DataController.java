package dataCenter;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import communicators.ThermalComm;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class DataController {
	
	//Array Lists and Maps
	public HashMap<Integer, Double>  thermalMap = new  HashMap<Integer, Double> ();
	
	ArrayList<Cpu> currentSchedule = new ArrayList<Cpu>();
	static ArrayList<Job> completedJobs = new ArrayList<Job>();
	static ArrayList<Job> processingJobs = new ArrayList<Job>();
	static ArrayList<Job> queuedJobs = new ArrayList<Job>();
	static ArrayList<Job> batchJobs = new ArrayList<Job>();
	 public static ArrayList<Job> optimalSchedule = new ArrayList<Job>();
	ThermalComm thermalComm = new ThermalComm();
	public Server server = new Server();
	int[] cracTemp = {1,1,1,1};
	
	
	
	//DB connection
	DBConnector dbConnector;
	//Timing
	Date lastCheck;
	Date lastScheduleChange;
	Date lastThemalChange;
	//Set variables
	int stacks = 4;
	int shelves = 10;
	int cpuCount = 10;
	int totalCpuCount = stacks * shelves * cpuCount;
	int maxBatchSize = 50;
	int epochTimev = 1000;
	int batchId;

	//Data Controller constructor
	public DataController(){
		
	}

	
	//Store completed job in DB
	public void storeCompletedJob(Job job){
		
	}

	//Get Completed Jobs
	public ArrayList<Job> getCompletedJobs() {
		return completedJobs;
	}

	//Set Completed Jobs
	public void setCompletedJobs(ArrayList<Job> completedJobs) {
		this.completedJobs = completedJobs;
	}
	
	//Get Queued Jobs
	public ArrayList<Job> getQueuedJobs() {
		return queuedJobs;
	}

	//Set Queued Jobs
	public void setQueuedJobs(ArrayList<Job> queuedJobs) {
		this.queuedJobs = queuedJobs;
		
	}
	
	public void setBatchJobs(ArrayList<Job> batchJobs) {

		this.batchJobs = batchJobs;

	}
		
	
	public static void jobCompleted(Job job){
		if(processingJobs.contains(job)){	
			System.out.println("jobcompleted method called "+new Date());
			System.out.println("");
			System.out.println(job.toString());
			completedJobs.add(processingJobs.remove(processingJobs.indexOf(job)));
		}	
	}
	
	public ArrayList<Job> getProcessingJobs() {
		return processingJobs;
	}

	//add cuirrently proccessing job Jobs
	public static void addProcessingJobs(Job job) {
		
		if(optimalSchedule.contains(job)){
			System.out.println("addprocessing job method called"+new Date());
			System.out.println("");
			//System.out.println(job.toString());
			processingJobs.add(optimalSchedule.remove(optimalSchedule.indexOf(job)));	
		}else{
			System.out.println("This list doesnt contain this job");
			
		}

	}
//	
	public void receiveJobs(String path){
		JSONParser parser = new JSONParser();
		try {
			JSONArray a = (JSONArray) parser.parse(new FileReader(path+".json"));
			for (Object o : a){
			    JSONObject job = (JSONObject) o;
			    int complexity = Integer.parseInt((String)job.get("complexity"));
				Job j = new Job(complexity);
				queuedJobs.add(j);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}
	

	public HashMap<Integer, Double> generateNewThermalMap(){
		return thermalComm.getCurrentThermalMap(thermalMap, server, cracTemp);
		
	}


	public static void setOptimalSchedule(ArrayList<Job> oj) {
		optimalSchedule = oj;
		
	}
	
	
	

}
