package thermal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import dataCenter.Cpu;
import dataCenter.Server;


public class Validation {
	java.util.Iterator<Entry<Integer, Double>> it;
	
	/*******************************
	 	TOP-LEVEL TEST: THERMAL MAP
	********************************/
	public boolean isValid(HashMap<Integer, Double> thermalMap){
		boolean valid=false;
		if (thermalMap != null){
			if (!thermalMap.isEmpty()){
				if (thermalMap.size() == 400){
					if (thermalMapHasCorrectValues(thermalMap)){
						if (thermalMapIsRanked(thermalMap)){
							valid = true;
						}else{System.out.println("[ERROR] incorrect thermal map ranking");}
					}else{System.out.println("[ERROR] incorrect thermal map values");}
				}else{System.out.println("[ERROR] incorrect thermal map length");}
			}else{System.out.println("[ERROR] empty thermal map");}
		}else{System.out.println("[ERROR] null thermal map");}
		return valid;
	} 
	/*******************************
	 	TOP-LEVEL TEST: SERVER
	********************************/
	public boolean isValid(Server s){
		boolean valid = false;
		//validation
		if (s != null){
			if (s.getCpuList().size() == 400){
				if (cpuListHasCorrectValues(s.getCpuList())){
					valid = true;
				}else{System.out.println("[ERROR] incorrect server values");}
			}else{System.out.println("[ERROR] incorrect server size");}
		}else{System.out.println("[ERROR] null server");}
		return valid;
	} 
	/*******************************
	MID-LEVEL TEST: THERMAL MAP RANKED
	********************************/
	boolean thermalMapIsRanked(HashMap<Integer, Double> thermalMap){//NB TIME CONSUMING...
		//System.out.println("\n [JOE] thermalMapIsRanked called..." + thermalMap);
		boolean valid = false;
		Double previous = null;
		//loop through each value and check it's bigger than it's previous value... 
		it = thermalMap.entrySet().iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry)it.next();
            if (entry.getValue()!=null){
	            if (previous != null) {
	                if ((Double)entry.getValue() >= previous ){
	                	valid = true;
	                }
	                else{
	                	//System.out.println("[JOE] error- one of the values was greater than the one after it.");
	                	return false;
	                }
	            }
            }
            previous = (Double) entry.getValue();
        }		
		return valid;
	}
	/*******************************
	MID-LEVEL TEST: THERMAL MAP CORRECT VALUES
	********************************/
	boolean thermalMapHasCorrectValues(HashMap<Integer, Double> thermalMap){//NB TIME CONSUMING...
		boolean valid = false;
        it = thermalMap.entrySet().iterator();
        while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry)it.next();
            if ((Double)entry.getValue() >= Config.MIN_CPU_TEMP && (Double)entry.getValue() <= Config.MAX_CPU_TEMP ){
            	valid = true;
            }
            else{
            	//System.out.println("[JOE] error- one of the values was not in the expected range.");
            	return false;
            }
        }		
		return valid;
	}
	/*******************************
	MID-LEVEL TEST: CPULIST HAS CORRECT VALUES
	********************************/
	boolean cpuListHasCorrectValues(ArrayList<Cpu> cpus){
		boolean valid = false;
		boolean temp = false;
		for (Cpu cpu : cpus){
			if(cpu.getId() >= Config.TOTAL_CPUs || cpu.getId() < 0){
				System.out.println("[ERROR] cpu Id not within range ...");
				return false;
			}
			else if(cpu.getPercentageUsed() > Config.MAX_PERCENTAGE_USED || cpu.getPercentageUsed() < Config.MIN_PERCENTAGE_USED){
				System.out.println("[ERROR] cpu percentage used not within range..."); 
				return false;
			}
			else if(cpu.getTemp() > Config.MAX_CPU_TEMP || cpu.getTemp() < Config.MIN_CPU_TEMP){
				System.out.println("[ERROR] cpu temp not within range..."+valid); 
				return false;
			}
			else {
				valid = true;
			}
		}
		return valid;
	}
	/*******************************
	 	MID-LEVEL TEST: CRACINFO
	********************************/
	public boolean isValid(int[] cracInfo){
		boolean valid = false;
		//validation
		if (cracInfo != null){
			if (cracInfo.length == 4){
				if(cracInfo[0] <= Config.MAX_CPU_TEMP && cracInfo[0] >= Config.MIN_CPU_TEMP){
					if(cracInfo[1] <= Config.MAX_CPU_TEMP && cracInfo[1] >= Config.MIN_CPU_TEMP){
						if(cracInfo[3] <= Config.MAX_CRAC_POWER_CONSUMPTION && cracInfo[3] >= Config.MIN_CRAC_POWER_CONSUMPTION){
							valid = true;
						}else{System.out.println("[ERROR] crac power consumption not within range...");}
					}else{System.out.println("[ERROR] cracOut temp not within range...");}
				}else{System.out.println("[ERROR] cracIn not within range...");}
			}else{System.out.println("[ERROR] cracInfo[] Length not == 4 ...");}
		}else{System.out.println("[ERROR] null cracInfo");}
		return valid;
	} 
}
