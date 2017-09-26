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
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;

@RestController
@RequestMapping(value = "/CartController")
public class CartController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Resource
	CartRepository cartRepository;
	
	@RequestMapping(value = "/getProductListFromCart" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Cart>> getProductListFromCart(@RequestBody User user)
	{
		System.out.println("#####.....Inside Get Product List From Cart API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : getProductListFromCart ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		
		try
		{
			System.out.println("User "  + user.getUserId());
			
		    List<Cart> cartProductList = cartRepository.findByUser(user);
		
			if(cartProductList.isEmpty())
			{
				return new ResponseEntity<Iterable<Cart>> (HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<Iterable<Cart>> (cartProductList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Cart>>( HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : getProductListFromCart ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");	
		}
		
	}
	
	@RequestMapping(value = "/getProductDetailsFromCart" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Cart> getProductDetailsFromCart(@RequestBody Cart cart)
	{
		System.out.println("#####.....Inside Get Product List From Cart API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : getProductDetailsFromCart ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		
		try
		{
			System.out.println("Cart  : " + cart.getCartId());
			
			Cart cart1 = cartRepository.findOne(cart.getCartId());
			
			if(cart1 != null)
			{
				return new ResponseEntity<Cart> ( cart1 , HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Cart> (HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Cart> (HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : getProductDetailsFromCart ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	}
	
	
	@RequestMapping(value = "/addProductToCart" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addProductToCart(@RequestBody Cart cart)
	{
		System.out.println("#####.....Inside Add Product to Cart API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : addProductToCart ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		
		try {
		    	System.out.println(cart.getProduct().getProductId());
			
		    	cartRepository.save(cart);
		    	return new ResponseEntity<String> ("Product Added To Cart Successfully..." , HttpStatus.OK);
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String> ("Unable To Add Product To Cart...",HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : addProductToCart ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");	
		}
   }

	@RequestMapping(value = "/updateProductDetailsIntoCart" , method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateProductDetailsFromCart(@RequestBody Cart cart)
	{
		System.out.println("#####.....Inside Update Product Details Into Cart API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : updateProductDetailsIntoCart ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		
		try
		{
			System.out.println(cart.getCartId() + " : " + cart.getProduct().getProductId());
			
			Cart cart1 = cartRepository.findOne(cart.getCartId());
			
			if(cart1 != null)
			{
				cart1 = cartRepository.save(cart);
				return new ResponseEntity<String> ("Cart Details Updated Successfully..." , HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Cart Details Not Found...", HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String>("Unable To Update Cart Details...", HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : updateProductDetailsIntoCart ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");	
		}
	}
	
	@RequestMapping(value = "/removeProductFromCart" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> removeProductFromCart(@RequestBody Cart cart)
	{
		System.out.println("#####.....Inside Remove Product From Cart API.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : removeProductFromCart ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");	
		
		try
		{
			System.out.println(cart.getCartId());
			
			cartRepository.delete(cart.getCartId());
		    return new ResponseEntity<String> ("Product Details Removed Successfully From Cart..." , HttpStatus.OK);
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String> ("Unable To Remove Product Details From Cart..." , HttpStatus.NOT_FOUND);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : removeProductFromCart ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");	
		}
	}
	
}
