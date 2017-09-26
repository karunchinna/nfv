package com.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Offer;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.OfferRepository;

@RestController
@RequestMapping(value = "/OfferController")
public class OfferController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
	
	@Resource
	OfferRepository offerRepository;
	
	
	@RequestMapping(value = "/getAllOfferDetails" , method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity<Iterable<Offer>> getAllOfferDetails()
	{
		System.out.println("#####.....Inside Get All Offer Details API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : getAllOfferDetails ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			List<Offer> offerList = (List<Offer>) offerRepository.findAll();
			
			if( offerList.isEmpty())
			{
				return new ResponseEntity<Iterable<Offer>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<Iterable<Offer>>( offerList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Offer>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : getAllOfferDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}	
	}
	
	@RequestMapping(value = "/getOfferDetails" , method = RequestMethod.POST )
	@ResponseBody
	public ResponseEntity<Iterable<Offer>> getOfferDetailsByProductId(@RequestBody Product product)
	{
		System.out.println("#####.....Inside Get Offer Details API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : getOfferDetailsByProductId ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Product :" + product.getProductId());
			
			List<Offer> offerList = offerRepository.findByProduct(product);
			
			if(offerList.isEmpty())
			{
				return new ResponseEntity<Iterable<Offer>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<Iterable<Offer>>( offerList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Offer>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : getOfferDetailsByProductId ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	}
	
	@RequestMapping(value = "/addOfferDetails" , method = RequestMethod.POST )
	@ResponseBody
	public ResponseEntity<String> addOfferDetails(@RequestBody Offer offer)
	{
		System.out.println("#####.....Inside Add Offer Details API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : addOfferDetails  ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
		  System.out.println("Offer :" + offer.getOfferId());
			
		  offerRepository.save(offer);
		  return new ResponseEntity<String>("Offer Added Successfully...", HttpStatus.OK);
		}
		catch(Exception error)
		{
			 error.printStackTrace();
			 System.out.println(error.getMessage());
			 return new ResponseEntity<String>("Unable To Add Offer...", HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : addOfferDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	}
	
	
	@RequestMapping(value = "/updateOfferDetails" , method = RequestMethod.PUT )
	@ResponseBody
	public ResponseEntity<String> updateOfferDetails(@RequestBody Offer offer)
	{
		System.out.println("#####.....Inside Update Offer Details API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : updateOfferDetails  ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Pffer :" + offer.toString());
			
		  Offer offer1 =  offerRepository.findOne(offer.getOfferId());
		  
		  if(offer1 != null)
		  {
			  offer1 = offerRepository.save(offer);
			  return new ResponseEntity<String>("Offer Details Updated Successfully...", HttpStatus.OK); 
		  }
		  else
		  {
			  return new ResponseEntity<String>("Offer Details Not found...", HttpStatus.NOT_FOUND);
		  }
		}
		catch(Exception error)
		{
			 error.printStackTrace();
			 System.out.println(error.getMessage());
			 return new ResponseEntity<String>("Unable To Update Offer Details..." , HttpStatus.OK);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : updateOfferDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	}
	
	@RequestMapping(value = "/deleteOfferDetails" , method = RequestMethod.POST )
	public ResponseEntity<String> deleteOfferDetails(@RequestBody Offer offer)
	{
		System.out.println("#####.....Inside Delete Offer Details API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : deleteOfferDetails  ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Offer :" + offer.toString());
			
		     offerRepository.delete(offer.getOfferId());
			 return new ResponseEntity<String>("Offer Deleted Successfully...", HttpStatus.OK); 
		}
		catch(Exception error)
		{
			 error.printStackTrace();
			 System.out.println(error.getMessage());
			 return new ResponseEntity<String>("Unable To Delete Offer Details..." , HttpStatus.NOT_FOUND);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : deleteOfferDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	}

}
