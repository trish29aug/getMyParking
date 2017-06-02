package com.services;

import com.bean.ParkingBean;

public interface ParkingService {

	public void checkin(ParkingBean bean);
	public void checkout(ParkingBean bean);
}
