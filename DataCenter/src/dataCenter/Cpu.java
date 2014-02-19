package dataCenter;

import java.util.ArrayList;

public class Cpu {
	/***************************
		CONSTRUCTOR
	***************************/
	public Cpu(int id){
		this.setId(id);
	}
	/***************************
			FIELDS
	***************************/
	private ArrayList<Job>jobs = new ArrayList<Job>();
	private double percentageUsed, totalPriority;
	private int id;
	private double temp;
	 
	/***************************
		GETTERS & SETTERS
	***************************/
	public double getTemp() {
		return temp;
	}
	public void setTemp(double tempCoefficient) {
		this.temp = tempCoefficient;  
	}
	public ArrayList<Job> getJobs() {
		return jobs;
	}
	public void setJobs(ArrayList<Job> currentJobs) {
		this.jobs = currentJobs;
	}
	public double getPercentageUsed() {
		//some relationship between complexity and power in here
		return percentageUsed;
	}
	public void setPercentageUsed(double tempDouble) {
		//some relationship between complexity and power in here
		this.percentageUsed = tempDouble;
	}
	public double getTotalPriority() {
		return totalPriority;
	}
	public void setTotalPriority(double totalPriority) {
		this.totalPriority = totalPriority;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/***************************
		TOSTRING
	***************************/
	public String toString(){
		String tempString="";
		tempString +="\n\n***CPU OBJECT***";
		tempString +="\nCurrent Jobs: ";		
		for (Object o : this.getJobs()){
			tempString +="\n"+o;
		}
		return tempString;
	}
	/***************************
		CUSTOMISED METHODS
	***************************/
}
