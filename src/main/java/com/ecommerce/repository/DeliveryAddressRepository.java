package com.ecommerce.repository;
import com.ecommerce.entity.DeliveryAddress;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long>, Serializable{
	
	public List<DeliveryAddress> findByPincode(int pincode);
	
	public List<DeliveryAddress> findByAddressType(String addressType);
	
	public List<DeliveryAddress> findByAddressTypeAndPincode(String addressType , int pincode);
	
}
