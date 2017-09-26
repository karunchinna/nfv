package com.ecommerce.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Offer;
import com.ecommerce.entity.Product;

@Repository
public interface OfferRepository extends CrudRepository<Offer , Long>,Serializable{

	
	public List<Offer> findByProduct(Product product);
}
