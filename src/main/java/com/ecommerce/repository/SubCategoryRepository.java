package com.ecommerce.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory , Long>,Serializable{

	/*public List<SubCategory> findByCategoryId(long categoryId);*/
	
	public List<SubCategory> findByCategory(Category category);
	
}
