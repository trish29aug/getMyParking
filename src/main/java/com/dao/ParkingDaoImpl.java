package com.dao;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.ParkingBean;
import com.managedBean.ParkingDetails;


@Repository("ParkingDao")
public class ParkingDaoImpl implements ParkingDao {

	@Autowired
	private ParkingDetails dto;
	
	@SuppressWarnings("rawtypes")
	public void checkin(ParkingBean bean) {
		
		//Setting values to the Persisting Object
		dto.setVehNo(bean.getVehNo());
		dto.setInTime(bean.getInTime());
		dto.setParkingType(bean.getParkingType());
		
		
		//saving data in table ParkingDetails
		
		Configuration cfg=new Configuration();  
	    cfg.configure("hibernate.cfg.xml");
	        
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
	    		cfg.getProperties()). buildServiceRegistry();
		SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);  
	      
	     
	    Session session=factory.openSession();  
	      
	    Transaction t=session.beginTransaction();  
	    
	    //checking if the vehicle is already Checked In but not Checked Out yet
	    try{
	    String SQL_QUERY ="from ParkingDetails where vehNo=? and outTime=NULL";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,bean.getVehNo());
		
		List list = query.list();
		if(list!=null && list.size()>0)
		{
			bean.setId(((ParkingDetails) list.get(0)).getId());
			bean.setErrorFlag(true);
			bean.setMsg("Already Checked In");
			
		}
		if(!bean.getErrorFlag()){
	    
		    session.save(dto);//persisting the object  
		    t.commit();//transaction is committed    
		    bean.setMsg("Checked In");
		    bean.setId(dto.getId());
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		finally {
			session.close(); 
		}
	     
	}
	
	public void checkout(ParkingBean bean){
		
			Configuration cfg=new Configuration();  
		    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
		      
		    //creating session factory object  
		    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
		    		cfg.getProperties()). buildServiceRegistry();
			SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);  
		      
		    //creating session object  
		    Session session=factory.openSession();  
		     
		    //creating transaction object  
		    Transaction t=session.beginTransaction();
		    
		    try{ 
			String SQL_QUERY ="from ParkingDetails where id=?";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,bean.getId());
	
			//setting values of bean to null
			bean.setId(null);
			bean.setInTime(null);
			bean.setVehNo(null);
			bean.setCharges(null);
			bean.setParkingType(null);
			
			List list = query.list();
			if(list!=null && list.size()>0)
			{
				//checking if given token Id is already Checked Out
				if(((ParkingDetails) list.get(0)).getOutTime()!=null){
					bean.setErrorFlag(true);
					bean.setMsg("Already Checked Out");
					}
				//checking if Checking Out time is less than the Checked In time
				else if(bean.getOutTime().compareTo(((ParkingDetails) list.get(0)).getInTime())<0){
					bean.setErrorFlag(true);
					bean.setMsg("Check In Time is more the Checking Out time");
					}
				else{
					dto=(ParkingDetails) list.get(0);
					bean.setId(dto.getId());
					bean.setInTime(dto.getInTime());
					bean.setVehNo(dto.getVehNo());
					bean.setParkingType(dto.getParkingType());

					
			        //calling costCalculationApi Function to calculate the the total charge for the parking session
			        bean.setCharges(costCalculationApi(bean.getInTime(),bean.getOutTime(),bean.getParkingType()));
			        
			        
			        //updating Check out Time and total parking charge in the database
					Query q=session.createQuery("update ParkingDetails set outTime=:ot where id=:i");  
					q.setParameter("ot",bean.getOutTime());    
					q.setParameter("i",bean.getId());  
					int status=q.executeUpdate();  
					q=session.createQuery("update ParkingDetails set charges=:c where id=:i");
					q.setParameter("c",bean.getCharges());  
					q.setParameter("i",bean.getId()); 
					status=q.executeUpdate();  
					t.commit(); 
					
					//successfully checked out
					bean.setMsg("Checked Out");
				}
		}
			//If no entry is found in the database to the given token then that token id is not Checked in
			else {
				bean.setErrorFlag(true);
				bean.setMsg("Invalid Token Id");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		    finally {
		    	session.close();
			}
	}
	
	public Float costCalculationApi(Date checkInDateTime, Date checkOutDateTime,int lot){ 
		
		float cost=0;
		Boolean lotValidity=true;
		try{
			
		//difference in milliseconds
        long diff = checkOutDateTime.getTime() - checkInDateTime.getTime();
        if(diff>=0){
        	//difference in hours
	        long diffHours = diff / (60 * 60 * 1000) ;
	        
	        //calculating cost according to parking lot
	        switch (lot) {
			case 0:{
				int rate=20;
				if(diffHours<=1)
					cost=20;
				else
					cost=rate*diffHours;
				
				break;
			}
			case 1:{
				int rateForX=10,x=2,y=5,z=1;
				if(diffHours<=x){
					cost=rateForX;
				}
				else{
					cost=rateForX+(y*z*(diffHours-x));
				}
				break;
			}
			case 2:{
				int rateForX=20,x=2,rateForY=10,y=3,rateForZ=5,z=1;
				if(diffHours<=x){
					cost=rateForX;
				}
				else if((diffHours-x)<=y) {
					cost= rateForX + rateForY;
				}
				else{
					cost= rateForX + rateForY + (rateForZ*z*(diffHours-x-y));
				}
				break;
			}
			default:{
				lotValidity=false;
				break;
			}
			}
	        
	     if(lotValidity){
	        	//Calculating the Night charges if applicable
	        int nightCharges=20;
	        
	        int nightMin=22;
	    	int nightMax=5;
	    	
	    	
		        int inTime = Integer.parseInt(new SimpleDateFormat("HH").format(checkInDateTime).toString());
		        int outTime = Integer.parseInt(new SimpleDateFormat("HH").format(checkOutDateTime).toString());
		        
		        Date inDate=new SimpleDateFormat("yyyy-MM-dd").parse(checkInDateTime.toString());
		        Date outDate=new SimpleDateFormat("yyyy-M-dd").parse(checkOutDateTime.toString());
		        
		        //Check In time and Out time is on the same day
			        if(inDate.equals(outDate)){
			        	if(inTime>=nightMin || inTime<=nightMax ||outTime>=nightMin || outTime<=nightMax)
			        	cost=cost+nightCharges;
			        	
			        }
			      //Check In time and Out time is on the different day
			        else {
			        	Long daysDiff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			        		//if difference is less than a day but dates are different
			        			if(daysDiff==0)
			        				cost=cost+nightCharges;
			        			else{
			        				//if difference is more than a day 
			        				if(diffHours%24==0)
			        				cost=cost+(nightCharges*daysDiff);
			        				//if difference is more than a day but some hours more than a number of days
			        				else {
				        				cost=cost+(nightCharges*daysDiff);
				        				if(inTime>nightMin)
				        					cost=cost+nightCharges;
				        				if(outTime<nightMax)
				        					cost=cost+nightCharges;
				        			}
								}
			        		}
				}
        	}
        else{
        	cost=-1;
        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return cost;
	}
}
