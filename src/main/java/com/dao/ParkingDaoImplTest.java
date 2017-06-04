/**
 * 
 */
package com.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bean.ParkingBean;

import junit.framework.TestCase;

/**
 * @author Trishla
 *
 */
public class ParkingDaoImplTest extends TestCase {

	
	/**
	 * Test method for {@link com.dao.ParkingDaoImpl#costCalculationApi(java.util.Date, java.util.Date, int)}.
	 * This test case tests the basic parking session Cost
	 */
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiBasic() {
		
		try{
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			//Date in=new Date(117, 6, 1, 10, 30,0);
			Date in  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 10:30:00");
			
			Date out=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 18:30:00");
			float costShouldBe=160;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			
			assertTrue(cost==costShouldBe);
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	/*
	 * This test case tests the scenario 1 : Flat rate for first x hours, then incremental Rs y for every z hours.
	 * Example Rs 10 for first 2 hours then Rs 5 every hour. 
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenario1(){
		
		
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=1;
			try {
				Date in = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 10:30:00");
				Date out=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 18:30:00");
				float costShouldBe=40;
				
				Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
				
				
				assertTrue(cost==costShouldBe);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	}
	/*
	 * This test case tests the scenario 2 : Flat rate for x hours repetitively, then incremental Rs y for every z hours.
	 * Example Rs 20 for first 2 hours then Rs 10 for next 3 hours, then Rs 5 every hour.  
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenario2(){
		try{
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=2;
			Date in=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 10:30:00");;
			Date out=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 18:30:00");;
			float costShouldBe=45;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			
			
			assertTrue(cost==costShouldBe);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * This test case tests the Special Night charges in addition to the fixed prices
	 * Check In time and Out time is on the same Date
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenarioNight1(){
		
		try{
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			Date in=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 22:30:00");
			Date out=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 23:30:00");;
			float costShouldBe=40;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
		
			assertTrue(cost==costShouldBe);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	/*
	 * This test case tests the Special Night charges in addition to the fixed prices Second scenario
	 * Check In time and Out time is on the different Date
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenarioNight2(){
		
		try{
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			Date in=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 22:30:00");
			Date out=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-02 04:30:00");
			float costShouldBe=140;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			
			assertTrue(cost==costShouldBe);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * This test case tests the Special Night charges in addition to the fixed prices Third scenario
	 * Check In time and Out time is on the different Date,number of days is more than 1
	*/
	@SuppressWarnings("deprecation")
	public void testCostCalculationApiScenarioNight3(){
		
		try{
			ParkingDaoImpl dao=new ParkingDaoImpl();
			int lot=0;
			Date in=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-01 22:30:00");
			Date out=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-06-03 04:30:00");
			float costShouldBe=640;
			
			Float cost=dao.costCalculationApi(new Timestamp(in.getTime()),new Timestamp(out.getTime()), lot);
			assertTrue(cost==costShouldBe);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


}
