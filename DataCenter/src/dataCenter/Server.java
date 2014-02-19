package dataCenter;

import communicators.*;
import thermal.*;
import optimisation.*;
import scheduling.*;
import testing.*;


import java.util.ArrayList;

import thermal.Config;

public class Server {
	Config config = new Config();
	
	
	/***************************
			CONSTRUCTOR
	***************************/
	public Server(){
		Cpu tempCpu;
		for (int i=0;i<config.TOTAL_CPUs;i++){
			tempCpu = new Cpu(i); 
			CpuList.add(tempCpu);
		}
	}
	/***************************
			FIELDS
	***************************/
	private ArrayList<Cpu> CpuList = new ArrayList<Cpu>();
	/***************************
		GETTERS AND SETTERS
	***************************/
	public ArrayList<Cpu> getCpuList() {
		return CpuList;
	}
	public Cpu getCpuById(int j) {
		for (int i=0;i<config.TOTAL_CPUs;i++){
			if(this.getCpuList().get(i).getId()==j){
				return this.getCpuList().get(i);
			}
		}
		return null;
	}
	public void setCpuList(ArrayList<Cpu> CpuList) {
		this.CpuList = CpuList;
	}
	/***************************
		TOSTRING
	***************************/
	public String toString(){
		String tempString="";
		tempString +="\n\n***SERVER OBJECT***";
		tempString +="\nThis Server object contains: "+this.getCpuList().size()+" servers";		
		return tempString;
	}
	

	public ArrayList grabFullCpuList(){ /// NEW
		return this.CpuList;
	}
	
}
