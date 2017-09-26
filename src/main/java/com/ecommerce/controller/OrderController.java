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

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Offer;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OfferRepository;
import com.ecommerce.repository.OrderRepository;

@RestController
@RequestMapping(value = "/OrderController")
public class OrderController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Resource
	OrderRepository orderRepository;
	
	@Resource
	CartRepository cartRepository;
	
	@Resource
	OfferRepository offerRepository;
	
	@RequestMapping(value = "/getAllOrders" , method =RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Order>> getAllOrder(@RequestBody User  user)
	{
		System.out.println("#####.....Inside Get All Order List API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : getAllOrder ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("User Id " + user.getUserId());
			
			List<Order> orderList = orderRepository.findByUser(user);
			
			if(orderList.isEmpty())
			{
				return new ResponseEntity<Iterable<Order>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<Iterable<Order>>(orderList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Order>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : getAllOrder ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	}
		
	@RequestMapping(value = "/placeOrder" , method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> placeOrder(@RequestBody Order  order)
	{
		System.out.println("#####.....Inside Place Order  API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : placeOrder ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		 try {
				System.out.println(order.getOrderId() + " : " + order.getCartId());
				
				Cart cart=cartRepository.findOne(order.getCartId());
				 
				List<Offer> offer=offerRepository.findByProduct(cart.getProduct());
	
				 if(offer.isEmpty())
				 {
					 order.setUser(cart.getUser());
					 orderRepository.save(order);
				 }
				 else
				 {
					 order.setOffer(offer.get(0));
					 order.setUser(cart.getUser());
					 orderRepository.save(order);
				 }
				
			     cartRepository.delete(cart.getCartId()); 
				 System.out.println(order.toString());
				 return new ResponseEntity<String>("Order Placed Successfully..." , HttpStatus.OK);
				 
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String>("Unable To Place Order..." , HttpStatus.BAD_REQUEST);
		}
		 finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : placeOrder ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	}

		@RequestMapping(value = "/updateOrderDetails" , method= RequestMethod.PUT)
		@ResponseBody
		public ResponseEntity<String> updateOrderDetails(@RequestBody Order  order)
		{
			System.out.println("#####.....Inside Update Order Details API.....$$$$$");
			logger.debug("************************************************************************");
			logger.debug("Entry : updateOrderDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************"); 
			try
			{	
				 System.out.println(order.getOrderId() + " : " + order.getCartId());
				 
			     Order order2 = orderRepository.findOne(order.getOrderId());
			     
			     if(order2 != null)
			     {
			    	 order2 = orderRepository.save(order);
			    	 return new ResponseEntity<String>("Order Details Updated Successfully..." , HttpStatus.OK);
			     }
			     else
			     {
			    	 return new ResponseEntity<String>("Order Details Not Found..." , HttpStatus.NOT_FOUND);
			     }
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Unable To Update Order Details..." , HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("************************************************************************");
				logger.debug("Exit : updateOrderDetails ::" + "::" + System.currentTimeMillis());
				logger.debug("************************************************************************");
			}
	    }

		@RequestMapping(value = "/cancelOrder" , method= RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> cancelOrder(@RequestBody Order  order)
		{
			System.out.println("#####.....Inside Cancel Order  API.....$$$$$");
			logger.debug("************************************************************************");
			logger.debug("Entry : cancelOrder ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************"); 
			try
			{	
				System.out.println(order.getOrderId());
				
			     orderRepository.delete(order.getOrderId());
				 return new ResponseEntity<String>("Order Cancelled Successfully..." , HttpStatus.OK);
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Unable To Cancel Order..." , HttpStatus.NOT_FOUND);
			}
			finally
			{
				logger.debug("************************************************************************");
				logger.debug("Exit : cancelOrder ::" + "::" + System.currentTimeMillis());
				logger.debug("************************************************************************");
			}	
	    }
}
