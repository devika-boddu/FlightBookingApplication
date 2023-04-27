package com.flights.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.flights.project.Pojo","com.flights.project.Controller","com.flights.project.Dao","com.flights.project.Exception","com.flights.project.Filter","com.flights.project.Util","com.flights.project.Validator"})
public class FlightsProjectApplication extends SpringBootServletInitializer implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FlightsProjectApplication.class, args);
	}
	
	@Override
	 public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("welcome");
	 }


}
