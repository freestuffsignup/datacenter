package testing;

import communicators.*;
import thermal.*;
import optimisation.*;
import scheduling.*;
import testing.*;
import java.util.HashMap;

import org.junit.Test;

import dataCenter.Cpu;
import dataCenter.Server;
import dataCenter.Utils;

import thermal.Config;
import thermal.ThermalControl;
import thermal.Validation;

import junit.framework.TestCase;

public class ThermalControlTest extends TestCase {

	Utils util;
	ThermalControl thermalControl;
	HashMap<Integer, Double> thermalMap;
	Server server;
	Validation validate;

	protected void setUp() throws Exception {
		super.setUp();
		util = new Utils();
		thermalControl = new ThermalControl();
		thermalMap = util.generateRandomthermalMap();
		thermalMap = (HashMap<Integer, Double>) thermalControl.sortThermalMap(thermalMap);//sort the thermalMap.
		thermalControl.setThermalMap(thermalMap);
		int[] temps = {63,12,80,100};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		server = new Server();
		validate = new Validation();
	}
	protected void tearDown() throws Exception {
		super.tearDown();
		util = new Utils();
		thermalControl = new ThermalControl();
		thermalMap = util.generateRandomthermalMap();
		thermalMap = (HashMap<Integer, Double>) thermalControl.sortThermalMap(thermalMap);//sort the thermalMap.
		thermalControl.setThermalMap(thermalMap);
		int[] temps = {63,12,80,100};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		server = new Server();
		validate = new Validation();
	}
	/******************************
		REF: Thermal Map Test (1)
	*******************************/
	@Test//valid thermalMap (Control Test)
	public void testValidThermalMap(){
		//Check that the "generateThermalMap(thermalMap, server, cracInfo)" Method returns a valid and new thermalMap... 
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//null thermalMap (Ref#: 1a)
	public void testNullthermalMap(){
		thermalMap = null;
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//empty thermalMap (Ref#: 1b)
	public void testEmptyThermalMap(){
		thermalMap = null;
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//size=401 thermalMap (Ref#: 1c (i))
	public void testSizeOneThermalMap(){
		thermalMap.put(400, 50.0);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//size=399 thermalMap (Ref#: 1c (ii))
	public void testSizeTwoThermalMap(){
		thermalMap.remove(399);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//size=400 thermalMap (Ref#: 1c (iii)) //Same as control test but left in for completeness...
	public void testSizeThreeThermalMap(){
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//a single thermalMap element has "-1" as a value(Ref#: 1d (i))
	public void testThermalMapHasCorrectValuesOne(){
		thermalMap.put(399,  Config.MIN_CPU_TEMP-1);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//a single thermalMap element has "100.01" as a value(Ref#: 1d (ii))
	public void testThermalMapHasCorrectValuesTwo(){
		thermalMap.put(399, Config.MAX_CPU_TEMP+.01);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//a single thermalMap element has "50" as a value(Ref#: 1d (iii))//Same as control test but left in for completeness...
	public void testThermalMapHasCorrectValuesThree(){
		thermalMap.put(399, 99.9999999);
		thermalMap = (HashMap<Integer, Double>) thermalControl.sortThermalMap(thermalMap);//Ranking it again.
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//thermalMap generated with randomly distributed values (Ref#: 1e (i))
	public void testRankThermalMapOne(){
		thermalMap = util.generateRandomthermalMap();
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//ranked thermalMap (Ref#: 1e (ii)) //Same as control test but left in for completeness...
	public void testRankThermalMapTwo(){
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	/******************************
		REF: Server Test (2)
	*******************************/
	@Test//valid server (Ref#: 2) (Control Test) 
	public void testValidServer(){
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//null server (Ref#: 2a)
	public void testNullServer(){
		server= null;
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//empty server (Ref#: 2b)
	public void testEmptyServer(){
		server= null;
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//server size = 401 (Ref#: 2c (i))
	public void testServerSizeOne(){
		server.getCpuList().add(new Cpu(999));
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//server size = 400 (Ref#: 2c (ii))(Control Test)
	public void testServerSizeTwo(){
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//server size = 399 (Ref#: 2c (iii))
	public void testServerSizeThree(){
		server.getCpuList().remove(399);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//test Cpu ids (Ref#: 2d (i))
	public void testServerCpuIdsOne(){
		server.getCpuById(1).setId(-1);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//test Cpu ids (Ref#: 2d (ii))
	public void testServerCpuIdsTwo(){
		server.getCpuById(1).setId(400);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//test Cpu ids (Ref#: 2d (iii))(Control Test)
	public void testServerCpuIdsThree(){
		server.getCpuById(1).setId(399);
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//test Cpu temps (Ref#: 2e (i))
	public void testCpuValuesOne(){
		server.getCpuById(1).setTemp( Config.MAX_CPU_TEMP+.01);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//test Cpu temps (Ref#: 2e (ii))
	public void testCpuValuesTwo(){
		server.getCpuById(1).setTemp( Config.MIN_CPU_TEMP-.01);
		assertFalse(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	@Test//test Cpu temps (Ref#: 2e (iii))
	public void testCpuValuesThree(){
		server.getCpuById(1).setTemp(50.0);
		assertTrue(validate.isValid(thermalControl.generateThermalMap(thermalMap, server, thermalControl.getCracInfo())));
	}
	/******************************
		REF: CracInfo Tests (3)
	*******************************/
	@Test//test cracInfo values (Control Test)
	public void testCracHasCorrectValues(){
		assertTrue(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//null cracInfo values (Ref#: 3a)
	public void testNullCrac(){
		int[] temps = null;
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}	
	@Test//empty cracInfo values (Ref#: 3b)
	public void testEmptyCrac(){
		int[] temps = {};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//cracIn temp values (Ref#: 3c (i))	
	public void testCracInTempOne(){
		int[] temps = {(int) (Config.MAX_CPU_TEMP+1),12,80,75};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//cracIn temp values (Ref#: 3c (ii))
	public void testCracInTempTwo(){
		int[] temps = {(int) (Config.MIN_CPU_TEMP-1),12,80,75};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//cracIn temp values (Ref#: 3c (iii))(Control)
	public void testCracInTempThree(){
		int[] temps = {(int) (Config.MAX_CPU_TEMP-1),12,80,75};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertTrue(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//cracOut temp values (Ref#: 3d (i))	
	public void testCracOutTempOne(){
		int[] temps = {12,(int) (Config.MAX_CPU_TEMP+1),80,75};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//cracOut temp values (Ref#: 3d (ii))
	public void testCracOutTempTwo(){
		int[] temps = {12,(int) (Config.MIN_CPU_TEMP-1),80,75};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//cracOut temp values (Ref#: 3d (iii))(Control)
	public void testCracOutTempThree(){
		int[] temps = {12,(int) (Config.MAX_CPU_TEMP-1),80,75};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertTrue(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//crac PowerConsumption values (Ref#: 3e (i))
	public void testCracPowerConsumptionOne(){
		int[] temps = {12,80,75,(int)(Config.MAX_CRAC_POWER_CONSUMPTION)+1,};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//crac PowerConsumption values (Ref#: 3e (ii))
	public void testCracPowerConsumptionTwo(){
		int[] temps = {12,80,75,(int)(Config.MIN_CRAC_POWER_CONSUMPTION)-1,};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertFalse(validate.isValid(thermalControl.getCracInfo()));
	}
	@Test//crac PowerConsumption values (Ref#: 3e (ii))(Control)
	public void testCracPowerConsumptionThree(){
		int[] temps = {12,80,75,(int)(Config.MAX_CRAC_POWER_CONSUMPTION),};
		thermalControl.setCracInfo(temps);//set valid cracInfo
		assertTrue(validate.isValid(thermalControl.getCracInfo()));
	}
}