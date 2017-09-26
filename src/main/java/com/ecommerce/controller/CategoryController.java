package com.ecommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.ecommerce.entity.Category;
import com.ecommerce.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/CategoryController")
public class CategoryController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Resource
	CategoryRepository categoryRepository;
	
	

	/* <----------##########..........Category API..........##########----------> */
	
		@RequestMapping(value = "/getAllCategory" )
		@ResponseBody
		public ResponseEntity<Iterable<Category>> getAllCategory()
		{
			
			System.out.println("##########......In Side Get All Category API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : getAllCategory ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				List<Category> categoryList = (List<Category>) categoryRepository.findAll();
				
				System.out.println(categoryList.get(0).getCategoryId()+categoryList.get(0).getCategoryName());
				
				if(categoryList.isEmpty())
				{
					return new ResponseEntity<Iterable<Category>>(HttpStatus.NOT_FOUND);
				}
				else
				{
					return new ResponseEntity<Iterable<Category>>(categoryList ,  HttpStatus.OK);
				}
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<Iterable<Category>>(HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : getAllCategory ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}	
		}
		
		@RequestMapping(value = "/addCategory" )
		@ResponseBody
		public ResponseEntity<String> addCategory(@RequestBody Category category)
		{
			
			System.out.println("##########.....In Side Add Category API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : addCategory ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println(category.getCategoryId()+ " : " + category.getCategoryName());
			     categoryRepository.save(category);
				return new ResponseEntity<String>("Category Details Added Successfully..." , HttpStatus.OK);
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Unable To Add Category Details...", HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : addCategory ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}
		}
		
		@RequestMapping(value = "/updateCategory" , method = RequestMethod.PUT)
		@ResponseBody
		public ResponseEntity<String> updateCategory(@RequestBody Category category)
		{
			
			System.out.println("##########.....In Side Add Category API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : updateCategory ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println(category.getCategoryId() + " : " + category.getCategoryName());
				
				Category Category2 = categoryRepository.findOne(category.getCategoryId());
				
				if(Category2 != null)
				{
					Category2 = categoryRepository.save(category);
					return new ResponseEntity<String>("Category Details Updated Successfully..." , HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("Category Details Not Found..." , HttpStatus.NOT_FOUND);
				}
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Unable To Update Categoty Details..." , HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : updateCategory ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}	
		}
		
			@RequestMapping(value = "/deleteCategory" , method = RequestMethod.POST)
			@ResponseBody
			public ResponseEntity<String> deleteCategory(@RequestBody Category Category)
			{
				
				System.out.println("##########.....In Side Delete Category API.....$$$$$$$$$$");
				
				logger.debug("********************************************************************");
				logger.debug("Entry : deleteCategory ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
				
				try
				{
					System.out.println(Category.getCategoryId() );
				
					 categoryRepository.delete(Category);
					return new ResponseEntity<String>("Category Details Deleted Successfully...", HttpStatus.OK);
				}
				catch(Exception error)
				{
					error.printStackTrace();
					System.out.println(error.getMessage());
					return new ResponseEntity<String>("Category Details Not Found...", HttpStatus.NOT_FOUND);
				}
				finally
				{
					logger.debug("********************************************************************");
					logger.debug("Exit : deleteCategory ::" + "::" + System.currentTimeMillis());
					logger.debug("********************************************************************");
				}
			}
			
			@RequestMapping(value = "/getAllCategories" , method = RequestMethod.GET)
			@ResponseBody
			public ResponseEntity<Iterable<String>> getAllCategories()
			{
				
				System.out.println("##########......In Side Get All Category (String) API.....$$$$$$$$$$");
				
				logger.debug("********************************************************************");
				logger.debug("Entry : getAllCategories ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
				
				try
				{
					List<Category> categoryList = (List<Category>) categoryRepository.findAll();
					
					List<String> categoryListFromDB = new ArrayList<String>();
					
					System.out.println(categoryList.get(0).getCategoryId()+categoryList.get(0).getCategoryName());
					
					if(categoryList.isEmpty())
					{
						return new ResponseEntity<Iterable<String>>(HttpStatus.NOT_FOUND);
					}
					else
					{
						for(Category category : categoryList)
						{
							String categoryName = category.getCategoryName();
							categoryListFromDB.add(categoryName);
						}
						
						return new ResponseEntity<Iterable<String>>(categoryListFromDB, HttpStatus.OK);
					}
				}
				catch(Exception error)
				{
					error.printStackTrace();
					System.out.println(error.getMessage());
					return new ResponseEntity<Iterable<String>>(HttpStatus.BAD_REQUEST);
				}
				finally
				{
					logger.debug("********************************************************************");
					logger.debug("Exit : getAllCategories ::" + "::" + System.currentTimeMillis());
					logger.debug("********************************************************************");
				}	
			}
}
