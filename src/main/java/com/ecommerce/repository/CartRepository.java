package com.ecommerce.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;

@Repository
public interface CartRepository extends CrudRepository<Cart	, Long> , Serializable{
	
	 public List<Cart> findByUser(User user);
	
}
