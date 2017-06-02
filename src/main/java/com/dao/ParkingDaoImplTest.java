/**
 * 
 */
package com.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.containsString;
import junit.framework.TestCase;

/**
 * @author Trishla
 *
 */
public class ParkingDaoImplTest extends TestCase {

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	/**
	 * Test method for {@link com.dao.ParkingDaoImpl#checkin(com.bean.ParkingBean)}.
	 */
	public void testCheckin() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.dao.ParkingDaoImpl#checkout(com.bean.ParkingBean)}.
	 */
	public void testCheckout() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.dao.ParkingDaoImpl#costCalculationApi(java.util.Date, java.util.Date, int)}.
	 * This test case tests the basic parking session Cost
	 */
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiBasic() {
		
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			Date in=new Date(117, 6, 1, 10, 30,0);
			Date out=new Date(117, 6, 1, 18, 30,0);
			float costShouldBe=160;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			
			assertTrue(cost==costShouldBe);
			
	}
	
	/*
	 * This test case tests the scenario 1 : Flat rate for first x hours, then incremental Rs y for every z hours.
	 * Example Rs 10 for first 2 hours then Rs 5 every hour. 
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenario1(){
		
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=1;
			Date in=new Date(117, 6, 1, 10, 30,0);
			Date out=new Date(117, 6, 1, 18, 30,0);
			float costShouldBe=40;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			
			
			assertTrue(cost==costShouldBe);
			
	}
	/*
	 * This test case tests the scenario 2 : Flat rate for x hours repetitively, then incremental Rs y for every z hours.
	 * Example Rs 20 for first 2 hours then Rs 10 for next 3 hours, then Rs 5 every hour.  
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenario2(){
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=2;
			Date in=new Date(117, 6, 1, 10, 30,0);
			Date out=new Date(117, 6, 1, 18, 30,0);
			float costShouldBe=45;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			
			
			assertTrue(cost==costShouldBe);
			
	}
	
	/*
	 * This test case tests the Special Night charges in addition to the fixed prices
	 * Check In time and Out time is on the same Date
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenarioNight1(){
		
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			Date in=new Date(117, 6, 1, 22, 30,0);
			Date out=new Date(117, 6, 1, 23, 30,0);
			float costShouldBe=40;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
		
			assertTrue(cost==costShouldBe);
			
			
	}
	
	/*
	 * This test case tests the Special Night charges in addition to the fixed prices Second scenario
	 * Check In time and Out time is on the different Date
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenarioNight2(){
		
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			Date in=new Date(117, 6, 1, 22, 30,0);
			Date out=new Date(117, 6, 2, 4, 30,0);
			float costShouldBe=140;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			
			assertTrue(cost==costShouldBe);
			
	}
	
	/*
	 * This test case tests the Special Night charges in addition to the fixed prices Third scenario
	 * Check In time and Out time is on the different Date,number of days is more than 1
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenarioNight3(){
		
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			Date in=new Date(117, 6, 1, 22, 30,0);
			Date out=new Date(117, 6, 3, 4, 30,0);
			float costShouldBe=640;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			System.out.println(cost);
			assertTrue(cost==costShouldBe);
			
	}
	
	
	
	/*
	 * This test case tests the if invalid lot number is entered
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiInvalidLot() {
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=4;
			Date in=new Date(117, 6, 1, 22, 30,0);
			Date out=new Date(117, 6, 2, 4, 30,0);
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			assertTrue(cost==0);
			
			
	}

}
