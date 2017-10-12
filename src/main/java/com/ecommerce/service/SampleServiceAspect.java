package com.ecommerce.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleServiceAspect {

	@Before("execution(* com.ecommerce.controller.UserController.login(com.ecommerce.entity.User))")
	public void Advice()
	{
		
	}
}
