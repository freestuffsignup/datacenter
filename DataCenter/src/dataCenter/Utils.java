package dataCenter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;

public class Utils {
	public Utils(){}	
	public void createJsonFile(String fileName, int noJobs){
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName+".json", "UTF-8");
			writer.println("[");
			for (int i=0; i<noJobs; i++){
				writer.println("{\"complexity\":\""+0 + ((int)(Math.random() * 5) + 1)+"\"},");
			}
			writer.println("]");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashMap<Integer, Double> generateRandomthermalMap(){
		HashMap<Integer, Double> newThermalMap = new HashMap<Integer, Double>();
		double random=0;
		for (int i=0;i<400;i++){
			random=random();
			newThermalMap.put(i,random);
		}	
		return newThermalMap;
	}
	public double random(){
		double rangeMin=35.0;
		double rangeMax=45.0;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		return randomValue;
	}	
}
