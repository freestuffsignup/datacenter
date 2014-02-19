package testing;


import static org.junit.Assert.assertEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import communicators.*;
import thermal.*;
import optimisation.*;
import scheduling.*;
import dataCenter.*;


public class CpuTest extends TestCase
{
	Server s;
	
	@Before
	public void setUp() throws Exception 
	{
		super.setUp();
		s = new Server();
	}
	
	public void tearDown() throws Exception 
	{
		super.tearDown();
		Server s = new Server();
	}
	
	@Test
	public void testGetCpuId() 
	{
		//Testing the getter for the ID
		int getCpuId = s.getCpuList().get(20).getId();

		assertEquals(20, getCpuId);
	}
	
	@Test
	public void testGetCurrentCpuTemp() 
	{
		//Test is the set & get temp methods working
		s.getCpuList().get(10).setPercentageUsed(45);
		int getCurTemp = (int) s.getCpuList().get(10).getPercentageUsed();

		assertEquals(45, getCurTemp);
	}
	
	@Test
	public void testSetCurrentPerUsed()
	{
		//Test is the set & get %used methods working
		s.getCpuList().get(10).setPercentageUsed(30);
		int getPerUsed = (int) s.getCpuList().get(10).getPercentageUsed();

		assertEquals(30, getPerUsed);
	}	
}
