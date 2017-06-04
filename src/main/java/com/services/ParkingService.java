package com.services;

import com.bean.ParkingBean;

public interface ParkingService {

	public void checkin(ParkingBean bean);
	public void checkoutByTokenId(ParkingBean bean);
	public void checkoutManually(ParkingBean bean);
}
