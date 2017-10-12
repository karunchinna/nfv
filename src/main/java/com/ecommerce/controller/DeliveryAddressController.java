package com.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.DeliveryAddress;
import com.ecommerce.repository.DeliveryAddressRepository;
import com.ecommerce.repository.UserRepository;

@RestController
@RequestMapping(value = "/DeliverAddressController")
public class DeliveryAddressController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(DeliveryAddressController.class);
	
	@Resource
	DeliveryAddressRepository deliveryAddressRepository;

	@Resource
	UserRepository userRepository;

	// @RequestMapping(value="/getaddress",method=RequestMethod.GET)
	// public DeliveryAddress getAddress()
	// {
	// System.out.println("$$$$$$$$$$$$...Inside gerAddress API...###########");
	//
	// User user = userRepository.findOne(1001);
	// if(user!=null)
	// {
	// System.out.println(user);
	// return user.getUserName();
	// }
	// else
	// {
	// System.out.println("No User");
	// return "Not founbd";
	// }
	//
	// }

	@RequestMapping(value = "/getaddress/{addressId}", method = RequestMethod.GET)
	public ResponseEntity<DeliveryAddress> getAddressByUserId(@PathVariable long addressId) {

		logger.debug("********************************************************************");
		logger.debug("Entry : getAddressByUserId ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		try {
			
			DeliveryAddress deliveryAddress = deliveryAddressRepository.findOne(addressId);
			return new ResponseEntity<DeliveryAddress>(deliveryAddress, HttpStatus.OK);
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			System.out.println("karunakar did sum changes");
			return new ResponseEntity<DeliveryAddress>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : getAddressByUserId ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}	
	}

	
	@RequestMapping(value = "/getByPincode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<DeliveryAddress>> getByPincode(@RequestBody DeliveryAddress deliveryAddress, HttpSession session) {
		System.out.println("$$$$$$$$$$$$...Inside getByPincode API...###########");
		logger.debug("********************************************************************");
		logger.debug("Entry : getByPincode ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		try
		{
			 System.out.println(deliveryAddress.getPincode());

			 List<DeliveryAddress> deliveryAddress2 = deliveryAddressRepository.findByPincode(deliveryAddress.getPincode());
	session.setAttribute("ksrun", deliveryAddress2);
			 if (deliveryAddress2 != null) 
			 {
				 return new ResponseEntity<Iterable<DeliveryAddress>>(deliveryAddress2, HttpStatus.OK);
	
			 } else {
				return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : getByPincode ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
	}
	
	
	@RequestMapping(value = "/getByAddressType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<DeliveryAddress>> getByAddressType(@RequestBody DeliveryAddress deliveryAddress) {
		System.out.println("$$$$$$$$$$$$...Inside getByAddressType API...###########");
		logger.debug("********************************************************************");
		logger.debug("Entry : getByAddressType ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		try
		{
			System.out.println(deliveryAddress.getAddressType());
	
			List<DeliveryAddress> deliveryAddress2 = deliveryAddressRepository.findByAddressType(deliveryAddress.getAddressType());
	
			if (deliveryAddress2 != null) 
			{
				 return new ResponseEntity<Iterable<DeliveryAddress>>(deliveryAddress2, HttpStatus.OK);
	
			} else {
				return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{ 
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.BAD_REQUEST);	
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : getByAddressType ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
	}

	@RequestMapping(value = "/getAllAddress", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<DeliveryAddress>> getAllAddress() {
		System.out.println("$$$$$$$$$$$$...Inside getAllAddress API...###########");
		logger.debug("********************************************************************");
		logger.debug("Entry : getAllAddress ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		try
		{
			List<DeliveryAddress> list = (List<DeliveryAddress>) deliveryAddressRepository.findAll();
	
			//System.out.println(list.get(0).toString());
	
			if (list.isEmpty()) {
				return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Iterable<DeliveryAddress>>(list, HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : getAllAddress ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
	}
	
	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addAddress(@RequestBody DeliveryAddress deliveryAddress) {
		System.out.println("$$$$$$$$$$$$...Inside Add Addddress API...###########");
		logger.debug("********************************************************************");
		logger.debug("Entry : addAddress ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		String msString=null;
		
		try
		{
			System.out.println("Address :" + deliveryAddress);
			deliveryAddressRepository.save(deliveryAddress);
			
			msString="Address Details Saved Successfully...";
			return new ResponseEntity<String>(msString,HttpStatus.OK);
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			msString="Unable To Save Address Details...";
			return new ResponseEntity<String>(msString,HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : addAddress ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");	
		}
	}
	

	@RequestMapping(value = "/updateAddressDetails", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateAddressDetails(@RequestBody DeliveryAddress deliveryAddress) {
		
		System.out.println("$$$$$$$$$$$$...Inside Update Addddress API...###########");
		logger.debug("********************************************************************");
		logger.debug("Entry : updateAddressDetails ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		String msString=null;
		
		try
		{
			DeliveryAddress dAddress = deliveryAddressRepository.findOne(deliveryAddress.getAddressId());
			
			if(dAddress != null )
			{
				 dAddress=deliveryAddressRepository.save(deliveryAddress);
				 msString="Address Details Updated Successfully...";
				 return new ResponseEntity<String>(msString,HttpStatus.OK);
			}
		     else {	
				msString="Address Details Not Found...";
				return new ResponseEntity<String>(msString,HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			msString="Unable To Update Address Details...";
			return new ResponseEntity<String>(msString,HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : updateAddressDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
	}
	
	
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteAddress(@RequestBody DeliveryAddress deliveryAddress) {
		System.out.println("$$$$$$$$$$$$...Inside Delete Addddress API...###########");
		logger.debug("********************************************************************");
		logger.debug("Entry : deleteAddress ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		String msString=null;
		try
		{
		   System.out.println(deliveryAddress.getAddressId());
		   
		    deliveryAddressRepository.delete(deliveryAddress.getAddressId());
			msString="Address Details Deleted Successfully...";
		    return new ResponseEntity<String>(msString,HttpStatus.OK);
		}
		catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			msString="Address Details Not Found...";
			return new ResponseEntity<String>(msString,HttpStatus.NOT_FOUND);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : deleteAddress ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
	}
	
	@RequestMapping(value = "/getByAddressTypeAndPincode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<DeliveryAddress>> getByAddressTypeAndPincode(@RequestBody DeliveryAddress deliveryAddress) {
		System.out.println("$$$$$$$$$$$$...Inside getByAddressTypeAndPincode API...###########");
		logger.debug("********************************************************************");
		logger.debug("Entry : getByAddressTypeAndPincode ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		try
		{
			System.out.println(deliveryAddress.getAddressType());
	
			List<DeliveryAddress> deliveryAddress2= deliveryAddressRepository.findByAddressTypeAndPincode(deliveryAddress.getAddressType() ,deliveryAddress.getPincode());
	
			if (deliveryAddress2 != null) 
			{
				 return new ResponseEntity<Iterable<DeliveryAddress>>(deliveryAddress2, HttpStatus.OK);
	
			} else {
				return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<DeliveryAddress>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : getByAddressTypeAndPincode ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}	
	}
}