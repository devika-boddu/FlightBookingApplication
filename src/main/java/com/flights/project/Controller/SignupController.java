package com.flights.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flights.project.Dao.CustomerDao;
import com.flights.project.Exception.CustomerException;
import com.flights.project.Pojo.Customer;
import com.flights.project.Validator.SignupValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class SignupController {
	
	@Autowired
	SignupValidator signupValidator;
	
	@Autowired
	CustomerDao customerDao;
	
	
	@GetMapping("/signup.htm")
	public String handleLogin(ModelMap model, Customer customer) {
		 model.addAttribute("customer", customer);
		return "CustomerSignup";
		
	}
	
	@PostMapping("/signUpLogin.htm")
	public String handleUserSignUp(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("customer") Customer customer, BindingResult result) throws Exception
	{
		signupValidator.validate(customer, result);
		
		if(result.hasErrors()) {
			return "CustomerSignup";
		}else {
			String firstName = request.getParameter("customerFirstName");
			String lastName = request.getParameter("customerLastName");
			String emailId = request.getParameter("customerEmail");
			String password = request.getParameter("customerPassword");
		
		
			customer.setCustomerFirstName(firstName);
			customer.setCustomerLastName(lastName);
			customer.setCustomerEmail(emailId);
			customer.setCustomerPassword(password);
			
			try {
				customerDao.save(customer);
				session.setAttribute("customer", customer);
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "CustomerLogin";
			
		}
	
	}

}
