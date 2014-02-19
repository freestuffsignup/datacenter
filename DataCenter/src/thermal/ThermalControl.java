package thermal;
import communicators.*;
import thermal.*;
import optimisation.*;
import scheduling.*;
import testing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dataCenter.Cpu;
import dataCenter.Server;
import dataCenter.Utils;

//import com.google.common.collect.Multiset.Entry;

public class ThermalControl {
	/*****************************
	 	FIELDS
	******************************/
	Utils util = new Utils();
	private  HashMap<Integer, Double>  thermalMap = new  HashMap<Integer, Double> ();
	private ArrayList<Cpu> schedule=new ArrayList<Cpu>();
	int[] cracInfo;
	int powerConsumption, requiredCracTemp;
	Validation validate = new Validation();
	/*****************************
	 	GETTERS AND SETTERS
	******************************/
	public  HashMap<Integer, Double>  getThermalMap() {
		return thermalMap;
	}
	public void setThermalMap( HashMap<Integer, Double>  thermalMap) {
		this.thermalMap = thermalMap;
	}
	public ArrayList<Cpu> getSchedule() {
		return schedule;
	}
	public void setSchedule(ArrayList<Cpu> schedule) {
		this.schedule = schedule;
	}
	public int[] getCracInfo() {
		return cracInfo;
	}
	public void setCracInfo(int[] cracInfo) {
		this.cracInfo = cracInfo;
	}
	public int getPowerConsumption() {
		return powerConsumption;
	}
	public void setPowerConsumption(int powerConsumption) {
		this.powerConsumption = powerConsumption;
	}
	public int getRequiredCracTemp() {
		return requiredCracTemp;
	}
	public void setRequiredCracTemp(int requiredCracTemp) {
		if ( requiredCracTemp > 30 && requiredCracTemp < 40){
			this.requiredCracTemp = requiredCracTemp;
		} 
		else{
			System.out.println("setRequiredCracTemp failed");
		}
	}
	/*****************************
	 	CUSTOMIZED METHODS
	******************************/
	public HashMap<Integer, Double> generateThermalMap (HashMap<Integer, Double> currentThermalMap, Server s, int[] cracInfo){
		if (validate.isValid(currentThermalMap) && validate.isValid(s) && validate.isValid(cracInfo)){
			//create a new thermalMap
			HashMap<Integer, Double> newThermalMap = util.generateRandomthermalMap();
			//sort it
			newThermalMap = (HashMap<Integer, Double>) sortThermalMap(newThermalMap);
			if(validate.isValid(newThermalMap)){
				return newThermalMap;
			}
			else{return null;
			}
		}
		else{
			System.out.println("Im fort Knox bitch -- give me whereon to stand, and I will move the Earth");
			return null;
		}
	}
	public int calculateCurrentPowerConsumption(int[] ci){
		int newPowerConsumption = 0;
		return newPowerConsumption;
	}
	public static <K extends Comparable,V extends Comparable> Map<K,V> sortThermalMap(Map<K,V> map){
        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {
			@Override
			public int compare(java.util.Map.Entry<K, V> o1,java.util.Map.Entry<K, V> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
        });
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(Map.Entry<K,V> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
	    return sortedMap;
	}
}
