package com.ecommerce.controller;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;

@RestController
@RequestMapping(value = "/UserController")
public class UserController implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	UserRepository userRepository ;
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody User user,HttpSecurity security)
	{
		System.out.println("#####.....Inside Login API.....#####");
		logger.debug("************************************************************************");
		logger.debug("Entry : login ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
		 System.out.println(user.getUserName()+ " :" + user.getPassword());
	
		 User user2 = userRepository.getByUserName(user.getUserName());
		
		 if(user2 != null)
		 {
			if(user2.getPassword().equals(user.getPassword()))
			{
				return new ResponseEntity<String>("User Logged-In Successfully...", HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Wrong Password...",HttpStatus.BAD_REQUEST);
			}
		 }
		 else
		 {
			 return new ResponseEntity<String>("User Details Not Found...", HttpStatus.NOT_FOUND);
		 }
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String>("Unable To Get User Details...", HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : login ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	 }
	
	
	@RequestMapping(value = "/registeration" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addUser(@RequestBody User user)
	{
		System.out.println("#####.....Inside Registeration API.....#####");
		logger.debug("************************************************************************");
		logger.debug("Entry : addUser ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println(user.getName() + " :" + user.getPassword());
			
			userRepository.save(user);
		    return new ResponseEntity<String>("Registered Successfully..." , HttpStatus.OK);
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String>("Unable To Register..." , HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : addUser ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	 }
	
	
	@RequestMapping(value = "/updateUser" , method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateUser(@RequestBody User user)
	{
		System.out.println("#####.....Inside Update User API.....#####");
		logger.debug("************************************************************************");
		logger.debug("Entry : updateUser ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");		
		try
		{
			System.out.println(user.getName() + " :" + user.getPassword());
			
			User user2 = userRepository.findOne(user.getUserId());
			
			if(user2 != null)
			{
				user2 = userRepository.save(user);
				return new ResponseEntity<String>( "User Details Updated Successfully..." , HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("User Details Not Found..." , HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String>("Unable To Update User Details..." , HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : updateUser ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	 }
	
	@RequestMapping(value = "/deleteUser" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteUser(@RequestBody User user)
	{
		System.out.println("#####.....Inside Delete User API.....#####");
		logger.debug("************************************************************************");
		logger.debug("Entry : deleteUser ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println(user.getName() + " :" + user.getPassword());
			
			userRepository.delete(user.getUserId());
			return new ResponseEntity<String>("User Deleted Succesfully..." , HttpStatus.OK);
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String>("User Details Not Found..." , HttpStatus.NOT_FOUND);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : deleteUser ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
	 }
	
	
	@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> logout(@RequestBody User user)
	{
		System.out.println("#####.....Inside Logout API.....#####");
		logger.debug("************************************************************************");
		logger.debug("Entry : logout ::" + "::" + System.currentTimeMillis());
		logger.debug("************************************************************************");
		try
		{
			System.out.println(user.getName() + " :" + user.getPassword());
			
			User user2 = userRepository.getByUserName(user.getUserName());
			
			if(user2 != null)
			{
			
				if(user2.getPassword().equals(user.getPassword()))
				{
					return new ResponseEntity<String>("Logout Successfully...", HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("Invalid Password...",HttpStatus.BAD_REQUEST);
				}
			}
			else
			{
				return new ResponseEntity<String>("User Not Found...",HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception error)
		{
			error.printStackTrace();
			System.out.println(error.getMessage());
			return new ResponseEntity<String>("User Details Not Found...",HttpStatus.BAD_REQUEST);
		}
		finally
		{
			logger.debug("************************************************************************");
			logger.debug("Exit : logout ::" + "::" + System.currentTimeMillis());
			logger.debug("************************************************************************");
		}
		
	 }
	
	
	/*@RequestMapping(value = "/getAllUserName" , method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity<Iterable<String>> getAllUserName()
	{
		System.out.println("#####.....Inside getAllUserName API.....#####");
		
		try
		{
			List<User> userlist = (List<User>) userRepository.findAll();
			
			System.out.println(userlist.toString());
			
		 if(userlist != null)
		 { 
			 List<String> userNameList = new ArrayList<String>();
			 for(User u : userlist)
			 {
				 
				 System.out.println(u.getUserName());
				 String Name = u.getUserName();
				 userNameList.add(Name);
			 }
			 
			 return new ResponseEntity<Iterable<String>>( userNameList , HttpStatus.OK);		 
		}
		 else
		 {
			 return new ResponseEntity<Iterable<String>>(HttpStatus.BAD_REQUEST);
		 }
		}
		catch(Exception error)
		{
			System.out.println(error.getMessage());
			return new ResponseEntity<Iterable<String>>(HttpStatus.BAD_REQUEST);
		}
	 }
	*/
	
	
}
	
	
	

