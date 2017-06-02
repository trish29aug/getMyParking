package com.bean;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class ParkingBean {

	private Integer id;
	private String vehNo;
	private Date inTime;
	private Date outTime;
	private Integer parkingType;
	private Float charges;
	private String msg;
	private Boolean errorFlag;
	
	
	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getVehNo() {
		return vehNo;
	}
	public void setVehNo(String vehNo) {
		this.vehNo = vehNo;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(Boolean errorFlag) {
		this.errorFlag = errorFlag;
	}
}
