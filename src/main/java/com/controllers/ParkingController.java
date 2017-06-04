package com.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bean.ParkingBean;
import com.services.ParkingService;



@Controller
@EnableWebMvc
@ComponentScan(basePackages="com.services")
public class ParkingController {
	
	@Autowired
	public ParkingService svc;
	@Autowired
	public ParkingBean bean;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkin",method = RequestMethod.POST,headers = {"Accept=application/json"}, consumes="application/json", produces = "application/json")
	public @ResponseBody JSONObject checkin(@RequestBody JSONObject request){
		

		// setting the values to Parking details bean
		bean.setVehNo(request.get("VehNo").toString());
		bean.setParkingType(Integer.parseInt(request.get("LotNo").toString()));
		Date date = new Date();
		bean.setInTime(new Timestamp(date.getTime()));
		bean.setErrorFlag(false);
		System.out.println(bean.getInTime());
		//calling function of parking service to checkin
		svc.checkin(bean);
				
		//MAP ENTRY for response json object
		Map<String, String> map= new HashMap<String, String>();
		
		//Check if the checkin process had any error
		if(!bean.getErrorFlag()){
		map.put("TokenId",Integer.toString(bean.getId()));
		map.put("VehNo",bean.getVehNo());
		map.put("InTime",bean.getInTime().toString());
		map.put("LotNo",Integer.toString(bean.getParkingType()));
		map.put("Error",Boolean.toString(bean.getErrorFlag()));
		map.put("Msg",bean.getMsg());
		}
		else{
			map.put("Error",Boolean.toString(bean.getErrorFlag()));
			map.put("Msg",bean.getMsg());
			if(bean.getId()!=null)
				map.put("TokenId", bean.getId().toString());
		}
		
		//response json object
		JSONObject json = new JSONObject();
	    json.putAll( map );
	    return json;
		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkoutById",method = RequestMethod.POST,headers = {"Accept=application/json"}, consumes="application/json", produces = "application/json")
	public @ResponseBody JSONObject checkoutByTokenId(@RequestBody JSONObject request){

		// setting the values to Parking details bean
		String id=request.get("tokenId").toString();
		bean.setId(Integer.parseInt(id));
		Date date = new Date();
		bean.setOutTime(new Timestamp(date.getTime()));
		
		bean.setErrorFlag(false);
		
		//calling function of parking service to checkout
		svc.checkoutByTokenId(bean);
		//MAP ENTRY for response json object	
		Map<String, String> map= new HashMap<String, String>();
		
		//Check if the checkout process had any error
		if (!bean.getErrorFlag()){
		map.put("TokenId",Integer.toString(bean.getId()));
		map.put("VehNo",bean.getVehNo());
		map.put("InTime",bean.getInTime().toString());
		map.put("OutTime",bean.getOutTime().toString());
		map.put("Total Charge",Float.toString(bean.getCharges()));
		map.put("Error",Boolean.toString(bean.getErrorFlag()));
		map.put("Msg",bean.getMsg());
		}
		else{
			map.put("Error",Boolean.toString(bean.getErrorFlag()));
			map.put("Msg",bean.getMsg());
		}
		
		//response json object
		JSONObject json = new JSONObject();
	    json.putAll( map );
	    return json;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/costCalculationAPI",method = RequestMethod.POST,headers = {"Accept=application/json"}, consumes="application/json", produces = "application/json")
	public @ResponseBody JSONObject costCalculationAPI(@RequestBody JSONObject request){

		
		bean.setParkingType(Integer.parseInt(request.get("LotNo").toString()));
		bean.setErrorFlag(false);
		try {
			bean.setInTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(request.get("inTime").toString()).getTime()));
			bean.setOutTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(request.get("outTime").toString()).getTime()));
		} catch (ParseException e) {
			bean.setErrorFlag(true);
			bean.setMsg("Invalid Date format");
		}
		
		//calling function of parking service to checkout
		svc.checkoutManually(bean);
		
		//MAP ENTRY for response json object	
		Map<String, String> map= new HashMap<String, String>();
		
		//Check if the checkout process had any error
		if (!bean.getErrorFlag()){
			bean.setMsg("Success");
		
		map.put("InTime",bean.getInTime().toString());
		map.put("OutTime",bean.getOutTime().toString());
		map.put("Total Charge",Float.toString(bean.getCharges()));
		map.put("Error",Boolean.toString(bean.getErrorFlag()));
		map.put("Msg",bean.getMsg());
		}
		else{
			map.put("Error",Boolean.toString(bean.getErrorFlag()));
			map.put("Msg",bean.getMsg());
		}
		
		//response json object
		JSONObject json = new JSONObject();
	    json.putAll( map );
	    return json;
	}

}
