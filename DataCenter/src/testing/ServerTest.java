package testing;

import communicators.*;
import thermal.*;
import optimisation.*;
import scheduling.*;
import dataCenter.*;


import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;



public class ServerTest extends TestCase
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
	public void testServer() 
	{
		//Make sure CPU size is 400.	
		assertEquals(400, s.getCpuList().size());
	}
	

	
	@Test
	public void testServer3() 
	{
		//Cpu 401, test returns true if value greater than 400.
		s.getCpuList().remove(1);
		int newList = s.getCpuList().size();
		assertFalse(400 > newList);
	}
	
	@Test
	public void testServer4() 
	{
		//Test to see if the server returning empty
		s.getCpuList().clear();
		int emptyList = s.getCpuList().size();
		assertEquals(0, emptyList);	
	}
	
	@Test
	public void testServer5() 
	{
		//Test to see if the server returning empty
		s = null;
		assertFalse(s == null);	
	}

}