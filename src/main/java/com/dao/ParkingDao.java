package com.dao;

import java.util.Date;

import com.bean.ParkingBean;


public interface ParkingDao {
	public void checkin(ParkingBean bean);
	public void checkout(ParkingBean bean);
	public Float costCalculationApi(Date checkInDateTime, Date checkOutDateTime,int lot); 
		
}
