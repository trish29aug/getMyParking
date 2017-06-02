package com.dao;

import com.bean.ParkingBean;


public interface ParkingDao {
	public void checkin(ParkingBean bean);
	public void checkout(ParkingBean bean);
}
