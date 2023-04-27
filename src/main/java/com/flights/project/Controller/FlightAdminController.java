package com.flights.project.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flights.project.Dao.FlightPackagesDao;
import com.flights.project.Exception.CustomerException;
import com.flights.project.Pojo.FlightPackages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class FlightAdminController {
	
	@Autowired
    private Environment environment;
	
	@Autowired
	private FlightPackagesDao flightPackDao;
	
	List<FlightPackages> allPackages = new ArrayList<FlightPackages>();
	

	@GetMapping("/flightAdmin.htm")
	public String handleLogin() {
		return "flightAdmin";
	}
	
	@PostMapping("/adminDashboard.htm")
	public String adminLoginSuccessful(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		String adminUserName = request.getParameter("flightAdminName");
		String adminPassword = request.getParameter("flightAdminPassword");
		String adminUname = environment.getProperty("flightAdmin.username");
		String adminPass = environment.getProperty("flightAdmin.password");
		
		if(adminUserName.equals(adminPassword)) {
			if(adminUname.equals(adminPass)) {
				return "adminDashboard";
			}
		}
		return "adminDashboard";
	}
	
	@PostMapping("/dashboard.htm")
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
			FlightPackages flightPackages) throws Exception {
		String clicked = request.getParameter("buttonClicked");
		if(clicked.contains("Add")) {
			flightPackages.setFlightName(request.getParameter("flightName"));
			flightPackages.setSource(request.getParameter("flightSource"));
			flightPackages.setDestination(request.getParameter("flightDestination"));
			flightPackages.setFlightDescription(request.getParameter("flightDescription"));
			flightPackages.setFlightPrice(Integer.parseInt(request.getParameter("flightPrice")));
			flightPackages.setFlightImage(request.getParameter("flightImageURL"));
			flightPackDao.save(flightPackages);
		}else if (clicked.contains("Get")) {
			String filightId = request.getParameter("flightId");
			Integer fId = Integer.parseInt(filightId);
			FlightPackages getProduct =flightPackDao.getSelectedProduct(fId);
			session.setAttribute("fId", fId);
			session.setAttribute("getProduct", getProduct);
			
		}else if (clicked.contains("Delete")) {
			String flightId = request.getParameter("flightId");
			Integer fId = Integer.parseInt(flightId);
			FlightPackages getProduct =flightPackDao.getSelectedProduct(fId);
			flightPackDao.delete(getProduct);
		}else if (clicked.contains("Update")) {
			String flightId = request.getParameter("fId");
			Integer fId = Integer.parseInt(flightId);
			//Integer flightId = Integer.parseInt(request.getParameter("flightId"));
			String flightName = request.getParameter("flightName");
			String flightSource = request.getParameter("flightSource");
			String flightDestination = request.getParameter("flightDestination");
			String flightDescription = request.getParameter("flightDescription");
			Integer flightPrice = Integer.parseInt(request.getParameter("flightPrice"));
			String flightImageURl = request.getParameter("flightImageURL");
			flightPackDao.update(fId,flightName, flightSource,flightDestination, flightDescription, flightPrice, flightImageURl);
		}else if (clicked.contains("Display")) {
			allPackages = flightPackDao.getProducts();
			session.setAttribute("allPackages", allPackages);
		}
		
		
		return new ModelAndView("adminDashboard");
	}

}
