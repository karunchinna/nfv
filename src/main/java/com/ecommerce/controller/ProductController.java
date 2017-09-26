package com.ecommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.SubCategory;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.SubCategoryRepository;

@RestController
@RequestMapping(value= "/ProductController")
@EnableJpaRepositories("com.ecommerce.repository")
public class ProductController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	SubCategoryRepository subCategoryRepository;

	@Resource
	ProductRepository productRepository;
	
	
	/* <----------##########..........Product API..........##########----------> */
	
	
	@RequestMapping(value = "/getAllProductList" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<Product>> getAllProductList(@RequestBody SubCategory subCategory)
	{
		System.out.println("##########......In Side Get All Product API.....$$$$$$$$$$");
		logger.debug("********************************************************************");
		logger.debug("Entry : getAllProductList ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		try
		{
			SubCategory subCategory1 = subCategoryRepository.findOne(subCategory.getSubcategoryId());
	
			/*List<Product> productList =  productRepository.findProductBySubCategoryId(subCategory1.getSubcategoryId());*/
			
			List<Product> productList =  productRepository.findProductBySubCategory(subCategory1);
			
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
			logger.debug("********************************************************************");
			logger.debug("Exit : getAllProductList ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
	}
	
	
	
	@RequestMapping(value = "/getProductDetails/{productId}" , method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Product> getProductDetails(@PathVariable long productId)
	{
	
		System.out.println("##########......In Side Get Product Details API.....$$$$$$$$$$");
		
		logger.debug("********************************************************************");
		logger.debug("Entry : getProductDetails ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		try
		{
			System.out.println("productId " + productId);
			
	        Product product =  productRepository.findOne(productId);
		
			if(product != null)
			{
				return new ResponseEntity<Product>(product , HttpStatus.OK);	
			}
			else
			{
				return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : getProductDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
		
	}
	
   
		@RequestMapping(value = "/addProduct" , method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> addProduct(@RequestBody Product product)
		{
		
			System.out.println("##########......In Side Add Product API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : addProduct ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println("product " + product.toString());
				
				 productRepository.save(product);
				return new ResponseEntity<String>("Product Details Added Successfully..." , HttpStatus.OK);	
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Unable To Add Product Details...", HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : addProduct ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}
		}
		
		@RequestMapping(value = "/updateProductDetails" , method = RequestMethod.PUT)
		@ResponseBody
		public ResponseEntity<String> updateProductDetails(@RequestBody Product product)
		{
		
			System.out.println("##########......In Side Update Product Details API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : updateProductDetails ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println("product " + product.toString());
				
			   Product product1 =  productRepository.findOne(product.getProductId());
			
				if(product1 != null)
				{ 
					product1 = productRepository.save(product);
					return new ResponseEntity<String>("Product Details Updated Successfully...", HttpStatus.OK);	
				}
				else
				{
					return new ResponseEntity<String>("Product Details Not Found...",HttpStatus.NOT_FOUND);
				}
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Unable To Update Product Details..." , HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : updateProductDetails ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}
		}
		
	
		@RequestMapping(value = "/removeProduct" , method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> deleteProduct(@RequestBody Product product)
		{
		
			System.out.println("##########......In Side Delete Product Details API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : deleteProduct ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println("Product Id " + product.getProductId());
				
			  productRepository.delete(product.getProductId());
			  return new ResponseEntity<String>("Product Details Deleted Successfully..." , HttpStatus.OK);
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Product Details Not Found..." , HttpStatus.NOT_FOUND);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : deleteProduct ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}
		}
		
		
		/* <----------##########........Paging Code..........###########----------> */
		
		@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
		Page<Product> productPageable(Pageable pageable) {
			return productRepository.findAll(pageable);

		}

		
		/*private PageRequest gotoPage(int page)
	    {
	        PageRequest request = new PageRequest(page,1,Sort.Direction.ASC,"price");
	        return request;
	    }
		
		@RequestMapping(value = "/listPageables", method = RequestMethod.GET)
	    private void findAll(ProductRepository repository)
	    {
	        Iterable<Product> productList = repository.findAll(gotoPage(0));
	        for(Product product : productList)
	        logger.debug("Product " + product);
	    }*/

}
