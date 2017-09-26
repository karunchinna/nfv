package com.ecommerce.config;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
/**
 * @author 
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.ecommerce" })
public class ECommerceApplication extends SpringBootServletInitializer implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ECommerceApplication.class);
	
		
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder)
	{
		return springApplicationBuilder.sources(ECommerceApplication.class);
	}
	public static void main(String[] args) throws InterruptedException 
	{
		SpringApplication.run(ECommerceApplication.class, args);
		logger.info("Server started at " + Calendar.getInstance().getTime());
		
	}
	
}
