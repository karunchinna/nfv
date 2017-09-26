package com.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

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

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping( value = "/FilterController")
public class FilterController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(FilterController.class);
	
	@Resource
	ProductRepository productRepository;
	
	@RequestMapping(value = "/findByColor" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Product>> filterProductByColor(@RequestBody Product product)
	{
		System.out.println("#####.....Inside Filter Product By Color.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : filterProductByColor ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Product :" + product.toString());
			
			List<Product> productList = productRepository.findByColor(product.getColor());
			
			if(productList.isEmpty())
			{
				 return new ResponseEntity<Iterable<Product>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				 return new ResponseEntity<Iterable<Product>>(productList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : filterProductByColor ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
 	}
	
	@RequestMapping(value = "/findBySize" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Product>> filterProductBySize(@RequestBody Product product)
	{
		System.out.println("#####.....Inside Filter Product By Size.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : filterProductBySize ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Product :" + product.toString());
			
			List<Product> productList = productRepository.findBySize(product.getSize());
			
			System.out.println(productList.toString());
			
			if(productList.isEmpty())
			{
				 return new ResponseEntity<Iterable<Product>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				 return new ResponseEntity<Iterable<Product>>(productList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : filterProductBySize ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
 	}
	
	@RequestMapping(value = "/findByBrand" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Product>> filterProductByBrand(@RequestBody Product product)
	{
		System.out.println("#####.....Inside Filter Product By Brand.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : filterProductByBrand ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Product :" + product.toString());
			
			List<Product> productList = productRepository.findByProductNameStartingWithIgnoreCase(product.getProductName());
			
			if(productList.isEmpty())
			{
				 return new ResponseEntity<Iterable<Product>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				 return new ResponseEntity<Iterable<Product>>(productList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : filterProductByBrand ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
 	}

	@RequestMapping(value = "/findByPrice" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Product>> filterProductByPrice(@RequestBody Product product)
	{
		System.out.println("#####.....Inside Filter Product By Price.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : filterProductByPrice ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Product :" + product.toString());
			
			List<Product> productList = productRepository.findByPrice(product.getPrice());
			
			if(productList.isEmpty())
			{
				 return new ResponseEntity<Iterable<Product>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				 return new ResponseEntity<Iterable<Product>>(productList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : filterProductByPrice ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
 	}
	
	
	@RequestMapping(value = "/findByPriceRange" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Product>> filterProductByPriceRange(@RequestBody Product product)
	{
		System.out.println("#####.....Inside Filter Product By Price Range.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : filterProductByPriceRange ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("Product  :" + product.toString());
			
			List<Product> productList = productRepository.findByPriceLessThanEqual(product.getPrice());
			
			if(productList.isEmpty())
			{
				 return new ResponseEntity<Iterable<Product>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				 return new ResponseEntity<Iterable<Product>>(productList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : filterProductByPriceRange ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
 	}
	
	@RequestMapping(value = "/findByPriceBetween/{p1}/{p2}" , method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<Product>> filterProductByPriceBetween(@PathVariable float p1 , @PathVariable float p2)
	{
		System.out.println("#####.....Inside Filter Product By Price Between.....$$$$$");
		logger.debug("************************************************************************");
		logger.debug("Entry : filterProductByPriceBetween ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println("P1 = " + p1 + " : " + " P2 = " + p2);
			
			List<Product> productList = productRepository.findByPriceBetween(p1,p2);
				
			if(productList.isEmpty())
			{
				 return new ResponseEntity<Iterable<Product>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				 return new ResponseEntity<Iterable<Product>>(productList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : filterProductByPriceBetween ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
 	}
	 /* @RequestMapping(value = "/filterProduct", method = RequestMethod.POST)
	  public ResponseEntity<Iterable<Product>> filterProducts(@RequestBody Product product) {

	    System.out.println("$$$$$$$$$$$$...Get Filter Products API...###########");
	    List<Product> productList = null;
	    try {
	      System.out.println(product.getColor());
	      System.out.println(product.getPrice());
	      System.out.println(product.getProductName());

	      if ((product.getColor() != null && product.getPrice() != null) && product.getProductName() != null)
	        productList = filterRepository.findByColorAndPriceAndProductName(product.getColor(), product.getPrice(),
	            product.getProductName());
	      else if (product.getColor() != null && product.getPrice() != null)
	        productList = filterRepository.findByColorAndPrice(product.getColor(),product.getPrice());
	      else if (product.getColor() != null && product.getProductName() != null)
	        productList = filterRepository.findByColorAndProductName(product.getColor(),product.getProductName());
	      else if (product.getPrice() != null && product.getProductName()!=null)
	        productList = filterRepository.findByPriceAndProductName(product.getPrice(),product.getProductName());
	      else if (product.getProductName() != null)
	        productList = productRepositorys.findByProductName(product.getProductName());
	      else if (product.getPrice() != null)
	        productList = filterRepository.findByPrice(product.getPrice());
	      else if (product.getColor() != null)
	        productList = filterRepository.findByColor(product.getColor());

	      System.out.println(productList);

	      if (productList.isEmpty()) {
	        return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
	      } else {
	        return new ResponseEntity<Iterable<Product>>(productList, HttpStatus.OK);

	      }

	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	  }*/
	
}
