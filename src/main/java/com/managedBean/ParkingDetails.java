package com.managedBean;

import javax.persistence.Entity;

import java.util.Date;

import javax.persistence.*;


import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ParkingDetails")
public class ParkingDetails {

	@Id 
	@GeneratedValue
	   @Column(name = "id")
	   private Integer id;
	
	@Column(name = "Veh_No")
	   private String vehNo;
	
	@Column(name = "In_Time")
	@Temporal(TemporalType.TIMESTAMP)
	   private Date inTime;
	
	@Column(name = "Out_Tme")
	@Temporal(TemporalType.TIMESTAMP)
	   private Date outTime;

	@Column(name = "Total_Charge")
	   private Float charges;
	
	@Column(name = "Parking_Lot")
	   private Integer parkingType;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehNo() {
		return vehNo;
	}

	public void setVehNo(String vehNo) {
		this.vehNo = vehNo;
	}

	public Float getCharges() {
		return charges;
	}

	public void setCharges(Float charges) {
		this.charges = charges;
	}

	public Integer getParkingType() {
		return parkingType;
	}

	public void setParkingType(Integer parkingType) {
		this.parkingType = parkingType;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	
}
