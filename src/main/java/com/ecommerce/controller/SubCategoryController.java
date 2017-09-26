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
import com.ecommerce.entity.SubCategory;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.SubCategoryRepository;

@RestController
@RequestMapping(value = "/SubCategoryController")
public class SubCategoryController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Resource
	CategoryRepository categoryRepository;
	
	@Resource
	SubCategoryRepository subCategoryRepository;
	

	/* <----------##########..........Sub-Category API..........##########----------> */
	
	@RequestMapping(value = "/getAllSubCategory" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Iterable<SubCategory>> getAllSubCategory(@RequestBody Category category)
	{
		System.out.println("##########......In Side Get All Sub Category API.....$$$$$$$$$$");
		
		logger.debug("********************************************************************");
		logger.debug("Entry : getAllSubCategory ::" + "::" + System.currentTimeMillis());
		logger.debug("********************************************************************");
		
		try
		{
			System.out.println("Category  " + category);
		
			Category category2 = categoryRepository.findOne(category.getCategoryId());
		
			/*List<SubCategory> subCategoryList =  subCategoryRepository.findByCategoryId(category2.getCategoryId());*/
		
			List<SubCategory> subCategoryList =  subCategoryRepository.findByCategory(category2);
			
			if(subCategoryList.isEmpty())
			{
				return new ResponseEntity<Iterable<SubCategory>>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<Iterable<SubCategory>>(subCategoryList , HttpStatus.OK);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<SubCategory>>(HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("********************************************************************");
			logger.debug("Exit : getAllSubCategory ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
		}
	}
	
		@RequestMapping(value = "/addSubCategory" , method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> addSubCategory(@RequestBody SubCategory subCategory)
		{
			
			System.out.println("##########.....In Side Add Category API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : addSubCategory ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				 System.out.println(subCategory.getSubcategoryId() + " : " + subCategory.getSubCategoryName());
				
				 subCategoryRepository.save(subCategory);
				 return new ResponseEntity<String>("Sub-Category Details Added Successfully...", HttpStatus.OK);
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<String>("Unable To Add Sub-Category Details...",HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : addSubCategory ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}
		}
		
		@RequestMapping(value = "/updateSubCategory" , method = RequestMethod.PUT)
		@ResponseBody
		public ResponseEntity<String> updateSubCategory(@RequestBody SubCategory subCategory)
		{
			
			System.out.println("##########.....In Side Update Sub-Category API.....$$$$$$$$$$");
			
			logger.debug("********************************************************************");
			logger.debug("Entry : updateSubCategory ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println(subCategory.getSubcategoryId() + " : " + subCategory.getSubCategoryName());
				
				SubCategory subCategory2 = subCategoryRepository.findOne(subCategory.getSubcategoryId());
				
				if(subCategory2 != null)
				{
					subCategory2 = subCategoryRepository.save(subCategory);
					return new ResponseEntity<String>("Sub-Category Details Updated Successfully...", HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("Sub-Category Details Not Found..." , HttpStatus.NOT_FOUND);
				}
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<>("Unable To Update Sub-Category Details..." , HttpStatus.BAD_REQUEST);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : updateSubCategory ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}
		}
		
		@RequestMapping(value = "/deleteSubCategory" , method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> deleteSuCategory(@RequestBody SubCategory subCategory)
		{
		
			System.out.println("##########......In Side Delete Sub-Category API.....$$$$$$$$$$");
		
			logger.debug("********************************************************************");
			logger.debug("Entry : deleteSubCategory ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println("Product Id " + subCategory.getSubcategoryId());
				
				subCategoryRepository.delete(subCategory.getSubcategoryId());
				return new ResponseEntity<>("Sub-Category Details Deleted Successfully...", HttpStatus.OK);
			}
			catch(Exception error)
			{
				error.printStackTrace();
				System.out.println(error.getMessage());
				return new ResponseEntity<>("Sub-Category Details Not Found...", HttpStatus.NOT_FOUND);
			}
			finally
			{
				logger.debug("********************************************************************");
				logger.debug("Exit : deleteSubCategory ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}	
		}
		
		
		@RequestMapping(value = "/getAllSubCategories" , method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Iterable<String>> getAllSubCategories(@RequestBody Category category)
		{
			
			System.out.println("##########......In Side Get All Category (String) API.....$$$$$$$$$$");
			logger.debug("********************************************************************");
			logger.debug("Entry : getAllSubCategories ::" + "::" + System.currentTimeMillis());
			logger.debug("********************************************************************");
			
			try
			{
				System.out.println(category.getCategoryId());
				
				Category categoryFromDB = categoryRepository.findOne(category.getCategoryId());
				
				if(categoryFromDB != null)
				{
					List<String> subCategoryString = new ArrayList<String>();
					
					List<SubCategory> subCategoryListFromDB = subCategoryRepository.findByCategory(categoryFromDB);	
					
					if(subCategoryListFromDB.isEmpty())
					{
						return new ResponseEntity<Iterable<String>>(HttpStatus.NOT_FOUND);
					}
					else
					{
						for(SubCategory subcategory : subCategoryListFromDB)
						{
							String subCategoryName = subcategory.getSubCategoryName();
							subCategoryString.add(subCategoryName);
						}
						
						return new ResponseEntity<Iterable<String>>(subCategoryString , HttpStatus.OK);
					}	
				}
				else
				{
					return new ResponseEntity<Iterable<String>>(HttpStatus.NOT_FOUND);
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
				logger.debug("Exit : getAllSubCategories ::" + "::" + System.currentTimeMillis());
				logger.debug("********************************************************************");
			}	
		}
	
}
