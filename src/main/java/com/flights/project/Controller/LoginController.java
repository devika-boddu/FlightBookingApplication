package com.flights.project.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flights.project.Exception.CustomerException;
import com.flights.project.Pojo.Customer;
import com.flights.project.Pojo.CustomerFlightBookings;
import com.flights.project.Validator.LoginValidator;
import com.flights.project.Pojo.FlightPackages;

import com.flights.project.Dao.CustomerDao;
import com.flights.project.Dao.FlightPackagesDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	

	@Autowired
   private Environment environment;
	
	
	@Autowired
	LoginValidator loginValidator;
	
	@Autowired
	FlightPackagesDao flightDao;
	
	@GetMapping("/login.htm")
	public String handleLogin(ModelMap model, Customer customer) {
		 model.addAttribute("customer", customer);
		return "CustomerLogin";
		
	}
	
	@PostMapping("/home.htm")
	public String loginSuccessful(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("customer") Customer customer, BindingResult result) throws CustomerException, IOException {
		
		loginValidator.validate(customer, result);
		
		List<CustomerFlightBookings> customerBookings = null;
		
		List<FlightPackages> customerFlightCart = new ArrayList<FlightPackages>();
		List<FlightPackages> customerBookingOrders = new ArrayList<FlightPackages>();
		
		if(result.hasErrors()) {
			return "CustomerLogin";
		}else {
		System.out.println("Entered home.htm");
		CustomerDao customerDao = new CustomerDao();
		System.out.println(customer.getCustomerEmail());
		Customer currentCustomer = customerDao.getCustomer(customer.getCustomerEmail());
			System.out.println(currentCustomer);
			session.setAttribute("currentCustomer", currentCustomer);
			System.out.println(currentCustomer.getCustomerEmail());
			System.out.println(customer.getCustomerEmail());
			System.out.println(currentCustomer.getCustomerPassword());
			System.out.println(customer.getCustomerPassword());
			if(currentCustomer.getCustomerEmail().equalsIgnoreCase(customer.getCustomerEmail())) {
				if(currentCustomer.getCustomerPassword().equals(customer.getCustomerPassword())) {
					session.setAttribute("isLoggedIn", true);
					String userAgent = request.getHeader("User-Agent");
					System.out.println(userAgent);
					session.setAttribute("userAgent", userAgent);
					System.out.println("Valid Credentials");
					
				
				}else {
					System.out.println("Please check the Password");
					
				}
			}else {
				System.out.println("Username doesnot exist");
			}
	       
			
			List<FlightPackages> flights =flightDao.getProducts();
			session.setAttribute("flights", flights);
//			session.setAttribute("travelPackagesCart", travelPackagesCart);
//			session.setAttribute("travelPackagesOrders", travelPackagesOrders);
//			session.setAttribute("travelPackagesWishlist", travelPackagesWishlist);
			for (FlightPackages flight : flights) {
				
	            // Print all elements of ArrayList
				request.getSession().setAttribute("flight", flight);
	            System.out.println(flight);
	        }
			
			return "dashboard1";
		}
	}

}
