package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.bean.ParkingBean;
import com.dao.ParkingDao;



@Service("ParkingService")
@ComponentScan(basePackages="com.dao")
public class ParkingServiceImpl implements ParkingService{

	@Autowired
	 private ParkingDao dao;
	
	// Checking In the vehicle by calling the function of parking Dao
	public void checkin(ParkingBean bean) {
		
		if(bean.getParkingType()>=0 && bean.getParkingType()<=2)
			dao.checkin(bean);
		else {
			bean.setErrorFlag(true);
			bean.setMsg("Invalid Parking Lot");
		}
	}


	// Checking Out the vehicle by calling the function of parking Dao
	public void checkoutByTokenId(ParkingBean bean){
		dao.checkout(bean);
	}
	
	public void checkoutManually(ParkingBean bean){
		bean.setCharges(dao.costCalculationApi(bean.getInTime(), bean.getOutTime(), bean.getParkingType()));
	}

}
