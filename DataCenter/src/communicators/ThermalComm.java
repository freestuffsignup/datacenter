package communicators;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import thermal.ThermalControl;

import dataCenter.Server;



public class ThermalComm {
	
	ThermalControl tc;
	
	public ThermalComm(){
		tc = new ThermalControl();
	}
	/*****************************
	 	FIELDS
	******************************/
	//private Map<String, ArrayList> currentThermalMap = new HashMap<String, ArrayList>();
	//private ArrayList<Cpu> currentSchedule=new ArrayList<Cpu>();
	int currentPowerCracTemp, requiredCracTemp;
	/*****************************
	 	GETTERS AND SETTERS
	******************************/
//	public Map<String, ArrayList> getCurrentThermalMap() {
//		return currentThermalMap;
//	}
//	public void setCurrentThermalMap(Map<String, ArrayList> currentThermalMap, Server s, int[] cracInfo) {
//		
//		this.currentThermalMap = currentThermalMap;
//	}
	
	
	
	public HashMap<Integer, Double> getCurrentThermalMap(HashMap<Integer, Double> currentThermalMap, Server s, int[] cracInfo) {
		
		return tc.generateThermalMap(currentThermalMap,s,cracInfo);
		
	}

	

//	public ArrayList<Cpu>getCurrentSchedule() {
//		return currentSchedule;
//	}
//	public void setCurrentSchedule(ArrayList<Cpu> currentSchedule){
//		this.currentSchedule = currentSchedule;
//	}
	public int getCurrentPowerCracTemp() {
		return currentPowerCracTemp;
	}
	public void setCurrentPowerCracTemp(int currentPowerCracTemp) {
		this.currentPowerCracTemp = currentPowerCracTemp;
	}
	public int getRequiredCracTemp() {
		return requiredCracTemp;
	}
	public void setRequiredCracTemp(int requiredCracTemp) {
		this.requiredCracTemp = requiredCracTemp;
	}
	/*****************************
	 	CUSTOMIZED METHODS
	******************************/	
}
