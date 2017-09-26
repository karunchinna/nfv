package com.ecommerce.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;

@Repository
public interface OrderRepository extends CrudRepository<Order , Long>,Serializable{

	
	public List<Order> findByUser(User user);
	
}
