package com.ecommerce.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.SubCategory;

@Repository
public interface ProductRepository extends CrudRepository<Product , Long>,Serializable , JpaRepository<Product, Long> , PagingAndSortingRepository<Product, Long>{

/*	public List<Product> findProductBySubCategoryId(long subCategoryId);*/
	
	public List<Product> findProductBySubCategory(SubCategory subCategory);
	
	public List<Product> findByColor(String color);
	
	public List<Product> findBySize(String size);
	
	public List<Product> findByProductNameIgnoreCase(String productName);
	
	public List<Product> findByProductNameStartingWithIgnoreCase(String productName);
	
	public List<Product> findByPrice(Float price);
	
	public List<Product> findByPriceLessThanEqual(float price);
	
	public List<Product> findByPriceBetween(float minPrice , float maxPrice);
	
	
}

/*
public List<Product> findByColorAndPriceAndProductName(String color,Float price,String ProductName);
public List<Product> findByColorAndPrice(String color,Float price);
public List<Product> findByColorAndProductName(String color,String ProductName);
public List<Product> findByPriceAndProductName(Float price,String ProductName);
public List<Product> findByPrice(Float price);
public List<Product> findByColor(String color);
public List<Product> findByProductName(String ProductName);
*/