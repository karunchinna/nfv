package com.ecommerce.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User	, Long>,Serializable{

	public User getByUserName(String userName);
	
	//public List<String> findByUserName(String userName);
}
